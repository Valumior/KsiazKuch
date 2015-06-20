package jksiazkuch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WartosciOdzywcze {
    
    private int id;
    private int kalorie;
    private int tluszczeCalkowite;
    private int tluszczeNasycone;
    private int tluszczeNienasycone;
    private int cholesterol;
    private int sod;
    private int weglowodany;
    private int blonnik;
    private int cukry;
    private int bialka;
    
    WartosciOdzywcze(int kalorie, int tluszczeCalkowite, int tluszczeNasycone, 
            int tluszczeNienasycone, int cholesterol, int sod, int weglowodany, 
            int blonnik, int cukry, int bialka){
        this.kalorie = kalorie;
        this.tluszczeCalkowite = tluszczeCalkowite;
        this.tluszczeNasycone = tluszczeNasycone;
        this.tluszczeNienasycone = tluszczeNienasycone;
        this.cholesterol = cholesterol;
        this.sod = sod;
        this.weglowodany = weglowodany;
        this.blonnik = blonnik;
        this.cukry = cukry;
        this.bialka = bialka;
    }
    
    public WartosciOdzywcze(int id, Statement query) throws SQLException{
        this.id = id;
        String q = "SELECT kalorie, tluszcze_calkowite, tluszcze_nasycone," + 
                " tluszcze_nienasycone, cholesterol, sod, weglowodany, blonnik, " + 
                "cukry, bialka FROM wartosci_odzywcze WHERE id=" + id;
        ResultSet result = query.executeQuery(q);

        if(result.next()){
            this.kalorie = result.getInt(1);
            this.tluszczeCalkowite = result.getInt(2);
            this.tluszczeNasycone = result.getInt(3);
            this.tluszczeNienasycone = result.getInt(4);
            this.cholesterol = result.getInt(5);
            this.sod = result.getInt(6);
            this.weglowodany = result.getInt(7);
            this.blonnik = result.getInt(8);
            this.cukry = result.getInt(9);
            this.bialka = result.getInt(10);
        }
    }

    public void insertToDb(Statement query) throws SQLException{
        String q = "INSERT INTO wartosci_odzywcze " + 
                "(kalorie, tluszcze_calkowite, tluszcze_nasycone, tluszcze_nienasycone, " +
                "cholesterol, sod, weglowodany, blonnik, cukry, bialka) " +
                "VALUES (" + this.kalorie + ", " + this.tluszczeCalkowite + ", " + 
                this.tluszczeNasycone + ", " + this.tluszczeNienasycone + ", " + 
                this.cholesterol + ", " + this.sod + ", " + this.weglowodany + ", " + 
                this.blonnik + ", " + this.cukry + ", " + this.bialka + ")";
        query.executeUpdate(q);
    }

    public void updateDb(Statement query, int id) throws SQLException{
        String q = "UPDATE wartosci_odzywcze " + 
                "SET kalorie=" + this.kalorie + ", tluszcze_calkowite=" + 
                this.tluszczeCalkowite + ", tluszcze_nasycone=" + 
                this.tluszczeNasycone + ", tluszcze_nienasycone=" + 
                this.tluszczeNienasycone + ", cholesterol=" + this.cholesterol + 
                ", sod=" + this.sod + ", weglowodany=" + this.weglowodany + 
                ", blonnik=" + this.blonnik + ", cukry=" + this.cukry + 
                ", bialka=" + this.bialka + " " +
                "WHERE id=" + id;
        query.executeUpdate(q);
    }

    public int getId() {
        return id;
    }

    public int getKalorie() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie = kalorie;
    }

    public int getTluszczeCalkowite() {
        return tluszczeCalkowite;
    }

    public void setTluszczeCalkowite(int tluszczeCalkowite) {
        this.tluszczeCalkowite = tluszczeCalkowite;
    }

    public int getTluszczeNasycone() {
        return tluszczeNasycone;
    }

    public void setTluszczeNasycone(int tluszczeNasycone) {
        this.tluszczeNasycone = tluszczeNasycone;
    }

    public int getTluszczeNienasycone() {
        return tluszczeNienasycone;
    }

    public void setTluszczeNienasycone(int tluszczeNienasycone) {
        this.tluszczeNienasycone = tluszczeNienasycone;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public int getSod() {
        return sod;
    }

    public void setSod(int sod) {
        this.sod = sod;
    }

    public int getWeglowodany() {
        return weglowodany;
    }

    public void setWeglowodany(int weglowodany) {
        this.weglowodany = weglowodany;
    }

    public int getBlonnik() {
        return blonnik;
    }

    public void setBlonnik(int blonnik) {
        this.blonnik = blonnik;
    }

    public int getCukry() {
        return cukry;
    }

    public void setCukry(int cukry) {
        this.cukry = cukry;
    }

    public int getBialka() {
        return bialka;
    }

    public void setBialka(int bialka) {
        this.bialka = bialka;
    }
}
