#ifndef PRZEPIS_H
#define PRZEPIS_H

#include <QString>

class Przepis
{
public:
    enum Trudnosc: unsigned char
    {
        Latwe = 0,
        Srednie = 1,
        Trudne = 2,
        MistrzKuchni = 3
    };

    Przepis();
    Przepis(int id ,QString nazwa, int czasPrzygotowania, Trudnosc trudnosc, bool ulubione, QString instrukcja);

    QString getNazwa() const;
    void setNazwa(const QString &value);

    int getCzasPrzygotowania() const;
    void setCzasPrzygotowania(int value);

    Trudnosc getTrudnosc() const;
    void setTrudnosc(const Trudnosc &value);

    bool getUlubione() const;
    void setUlubione(bool value);

    QString getInstrukcja() const;
    void setInstrukcja(const QString &value);

private:
    int id;
    QString nazwa;
    int czasPrzygotowania;
    Trudnosc trudnosc;
    bool ulubione;
    QString instrukcja;
};

#endif // PRZEPIS_H
