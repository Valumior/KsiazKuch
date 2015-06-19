package jksiazkuch;

import java.util.ArrayList;

public class Przepis {
    
    public enum Trudnosc{
        Latwe,
        Srednie,
        Trudne,
        MistrzKuchni
    };
    
    int id;
    String nazwa;
    int czasPrzygotowania;
    Trudnosc trudnosc;
    boolean ulubione;
    String instrukcja;
    ArrayList<PrzepisSkladnik> skladniki;
    
    public Przepis(int id ,String nazwa, int czasPrzygotowania, Trudnosc trudnosc, 
            boolean ulubione, String instrukcja){
        this.id = id;
        this.nazwa = nazwa;
        this.czasPrzygotowania = czasPrzygotowania;
        this.trudnosc = trudnosc;
        this.ulubione = ulubione;
        this.instrukcja = instrukcja;
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
}
