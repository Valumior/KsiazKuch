package jksiazkuch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Skladnik {
    
    public enum Miara{
        Mililitry,
        Gramy,
        Sztuki
    };
    
    private int id;
    private String nazwa;
    private Miara miara;
    private WartosciOdzywcze wartosciOdzywcze;
    
    public Skladnik(int id, String nazwa, Miara miara, WartosciOdzywcze wartosciOdzywcze){
        this.id = id;
        this.nazwa = nazwa;
        this.miara = miara;
        this.wartosciOdzywcze = wartosciOdzywcze;
    }
    
    public Skladnik(int id, Statement query) throws SQLException{
        this.id = id;
        String q = "SELECT nazwa, miara, wartosci_odzywcze " + 
                "FROM skladnik WHERE id=" + id;
        ResultSet result = query.executeQuery(q);

        if(result.next())
        {
            this.nazwa = result.getString(1);
            this.miara = Miara.values()[result.getInt(2)];
            this.wartosciOdzywcze = new WartosciOdzywcze(result.getInt(3), query);
        }
    }

    public void insertToDb(Statement query) throws SQLException{
        this.wartosciOdzywcze.insertToDb(query);
        ResultSet result = query.executeQuery("SELECT last_insert_rowid()");
        
        if(result.next())
        {
            String q = "INSERT INTO skladnik (nazwa, miara, wartosci_odzywcze) " + 
                    "VALUES (" + this.nazwa + ", " + this.miara.ordinal() + ", " + 
                    result.getInt(1) + ")";
            query.executeUpdate(q);
        }
    }

    public void updateDb(Statement query) throws SQLException{
        ResultSet result = query.executeQuery("SELECT wartosci_odzywcze FROM skladnik WHERE id=" + this.id);
        if(result.next())
            this.wartosciOdzywcze.updateDb(query, result.getInt(1));

        String q = "UPDATE skladnik  SET nazwa=" + this.nazwa + ", miara=" + 
                this.miara.ordinal() + " WHERE id=" + this.id;
        query.executeUpdate(q);
    }

    public static ArrayList<Skladnik> getObjects(Statement query, String filter) throws SQLException{
        ArrayList<Skladnik> list = new ArrayList<>();
        String q = "SELECT skladnik.id, skladnik.nazwa, skladnik.miara, " + 
                "wartosci_odzywcze.kalorie, wartosci_odzywcze.tluszcze_calkowite, wartosci_odzywcze.tluszcze_nasycone, " +
                "wartosci_odzywcze.tluszcze_nienasycone, wartosci_odzywcze.cholesterol, wartosci_odzywcze.sod, " +
                "wartosci_odzywcze.weglowodany, wartosci_odzywcze.blonnik, wartosci_odzywcze.cukry, wartosci_odzywcze.bialka " +
                "FROM skladnik NATURAL JOIN wartosci_odzywcze";
        if(!filter.trim().isEmpty())
            q = q + " WHERE skladnik.nazwa LIKE '%" + filter + "%'";

        ResultSet result = query.executeQuery(q);

        while(result.next())
        {
            WartosciOdzywcze wartOdz = new WartosciOdzywcze(result.getInt(4),
                    result.getInt(5), result.getInt(6), result.getInt(7), 
                    result.getInt(8), result.getInt(9), result.getInt(10), 
                    result.getInt(11), result.getInt(12), result.getInt(13));
            Skladnik skl = new Skladnik(result.getInt(1), result.getString(2), 
                    Miara.values()[result.getInt(3)], wartOdz);
            list.add(skl);
        }
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

    public Miara getMiara() {
        return miara;
    }

    public void setMiara(Miara miara) {
        this.miara = miara;
    }

    public WartosciOdzywcze getWartosciOdzywcze() {
        return wartosciOdzywcze;
    }

    public void setWartosciOdzywcze(WartosciOdzywcze wartosciOdzywcze) {
        this.wartosciOdzywcze = wartosciOdzywcze;
    }
}
