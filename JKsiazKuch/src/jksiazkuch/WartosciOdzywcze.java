package jksiazkuch;

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
