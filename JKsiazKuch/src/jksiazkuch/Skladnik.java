package jksiazkuch;

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
    
    Skladnik(int id, String nazwa, Miara miara, WartosciOdzywcze wartosciOdzywcze){
        this.id = id;
        this.nazwa = nazwa;
        this.miara = miara;
        this.wartosciOdzywcze = wartosciOdzywcze;
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
