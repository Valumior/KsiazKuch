package jksiazkuch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Przepis {
    
    public enum Trudnosc{
        Latwe,
        Srednie,
        Trudne,
        MistrzKuchni
    };
    
    private int id;
    private String nazwa;
    private int czasPrzygotowania;
    private Trudnosc trudnosc;
    private boolean ulubione;
    private String instrukcja;
    private ArrayList<PrzepisSkladnik> skladniki;
    
    public Przepis(int id ,String nazwa, int czasPrzygotowania, Trudnosc trudnosc, 
            boolean ulubione, String instrukcja){
        this.id = id;
        this.nazwa = nazwa;
        this.czasPrzygotowania = czasPrzygotowania;
        this.trudnosc = trudnosc;
        this.ulubione = ulubione;
        this.instrukcja = instrukcja;
    }
    
    void insertToDb(Statement query) throws SQLException{
        String q = "INSERT INTO przepis (nazwa, czas_przygotowania, trudnosc, ulubione, instrukcja) " + 
                "VALUES (" + this.nazwa + ", " + this.czasPrzygotowania + ", " + 
                this.trudnosc + ", " + ((this.ulubione) ? 1 : 0) + ", " + this.instrukcja + ")";
        query.executeUpdate(q);

        ResultSet result = query.executeQuery("SELECT last_insert_rowid()");
        int przepId;

        if(result.next()){
            przepId = result.getInt(1);
            for(int i = 0; i < this.skladniki.size(); ++i)
                this.skladniki.get(i).insertToDb(query, przepId);
        }
    }

    void updateDb(Statement query) throws SQLException{
        query.executeUpdate("DELETE FORM skladniki_przepis WHERE przepis=" + this.id);

        String q = "UPDATE przepis SET nazwa=" + this.nazwa + ", czas_przygotowania=" + 
                this.czasPrzygotowania + ", trudnosc=" + this.trudnosc.ordinal() + 
                ", ulubione=" + ((this.ulubione) ? 1 : 0) + ", instrukcja=" + 
                this.instrukcja + " WHERE id=" + this.id;
        query.executeUpdate(q);

        for(int i = 0; i < this.skladniki.size(); ++i)
            this.skladniki.get(i).insertToDb(query, this.id);
    }
    
    public static ArrayList<Przepis> getObjects(Statement query) throws SQLException{
        return Przepis.getObjects(query, false, "");
    }
    
    public static ArrayList<Przepis> getObjects(Statement query, boolean fav) throws SQLException{
        return Przepis.getObjects(query, fav, "");
    }

    public static ArrayList<Przepis> getObjects(Statement query, String filter) throws SQLException{
        return Przepis.getObjects(query, false, filter);
    }
    
    public static ArrayList<Przepis> getObjects(Statement query, boolean fav, String filter) throws SQLException{
        ArrayList<Przepis> list = new ArrayList<>();
        String q = "SELECT id, nazwa, czas_przygotowania, trudnosc, ulubione, instrukcja FROM przepis";
        if(!filter.trim().isEmpty() || fav){
            q = q + " WHERE ";
            if(!filter.trim().isEmpty())
                q = q + "nazwa LIKE '%" + filter + "%' ";
            if(fav)
                q = q + "ulubione=1 ";
        }

        ResultSet result = query.executeQuery(q);

        while(result.next())
        {
            Przepis prze = new Przepis(result.getInt(1), result.getString(2), 
                    result.getInt(3), Trudnosc.values()[result.getInt(4)], 
                    result.getBoolean(5), result.getString(6));
            list.add(prze);
        }

    for(int i = 0; i < list.size(); ++i)
        list.get(i).setSkladniki(PrzepisSkladnik.getObjects(query, list.get(i).getId()));

    return list;
}

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getCzasPrzygotowania() {
        return czasPrzygotowania;
    }

    public void setCzasPrzygotowania(int czasPrzygotowania) {
        this.czasPrzygotowania = czasPrzygotowania;
    }

    public Trudnosc getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(Trudnosc trudnosc) {
        this.trudnosc = trudnosc;
    }

    public boolean isUlubione() {
        return ulubione;
    }

    public void setUlubione(boolean ulubione) {
        this.ulubione = ulubione;
    }

    public String getInstrukcja() {
        return instrukcja;
    }

    public void setInstrukcja(String instrukcja) {
        this.instrukcja = instrukcja;
    }

    public ArrayList<PrzepisSkladnik> getSkladniki() {
        return skladniki;
    }

    public void setSkladniki(ArrayList<PrzepisSkladnik> skladniki) {
        this.skladniki = skladniki;
    }

    @Override
    public String toString() {
        return this.getNazwa();
    }
}
