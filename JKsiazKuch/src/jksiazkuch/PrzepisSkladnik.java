package jksiazkuch;


public class PrzepisSkladnik {
    private int liczba;
    private Skladnik skladnik;
    
    PrzepisSkladnik(int liczba, Skladnik skladnik){
        this.liczba = liczba;
        this.skladnik = skladnik;
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
