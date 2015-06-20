package jksiazkuch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class PrzepisSkladnik {
    
    private int liczba;
    private Skladnik skladnik;
    
    PrzepisSkladnik(int liczba, Skladnik skladnik){
        this.liczba = liczba;
        this.skladnik = skladnik;
    }
    
    void insertToDb(Statement query, int przepisId) throws SQLException{
        String q = "INSERT INTO skladniki_przepis (przepis, skladnik, liczba)" + 
                "VALUES (" + przepisId + ", " + this.skladnik.getId() + ", " + 
                this.liczba + ")";
        query.executeUpdate(q);
    }

    public static ArrayList<PrzepisSkladnik> getObjects(Statement query, int przepisId) throws SQLException{
        ArrayList<PrzepisSkladnik> list = new ArrayList<>();
        String q = "SELECT skladniki_przepis.liczba, skladnik.id, skladnik.nazwa, skladnik.miara, " + 
                "wartosci_odzywcze.kalorie, wartosci_odzywcze.tluszcze_calkowite, wartosci_odzywcze.tluszcze_nasycone, " +
                "wartosci_odzywcze.tluszcze_nienasycone, wartosci_odzywcze.cholesterol, wartosci_odzywcze.sod, " +
                "wartosci_odzywcze.weglowodany, wartosci_odzywcze.blonnik, wartosci_odzywcze.cukry, wartosci_odzywcze.bialka " +
                "FROM skladniki_przepis JOIN skladnik ON skladniki_przepis.skladnik=skladnik.id NATURAL JOIN wartosci_odzywcze " +
                "WHERE skladniki_przepis.przepis=" + przepisId;
        ResultSet result = query.executeQuery(q);

        while(result.next())
        {
            WartosciOdzywcze wartOdz = new WartosciOdzywcze(result.getInt(5),
                    result.getInt(6), result.getInt(7), result.getInt(8), 
                    result.getInt(9), result.getInt(10), result.getInt(11), 
                    result.getInt(12), result.getInt(13), result.getInt(14));
            Skladnik skl = new Skladnik(result.getInt(2), result.getString(3), 
                    Skladnik.Miara.values()[result.getInt(4)], wartOdz);
            PrzepisSkladnik przeSkla = new PrzepisSkladnik(result.getInt(1), skl);

            list.add(przeSkla);
        }

        return list;
    }


    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public Skladnik getSkladnik() {
        return skladnik;
    }

    public void setSkladnik(Skladnik skladnik) {
        this.skladnik = skladnik;
    }
}
