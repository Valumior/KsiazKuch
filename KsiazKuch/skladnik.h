#ifndef SKLADNIK_H
#define SKLADNIK_H

#include <QString>
#include "wartosciodzywcze.h"

class Skladnik
{
public:
    enum Miara: unsigned char
    {
        Sztuki = 0,
        Mililitry = 1,
        Gramy = 2
    };

    Skladnik();
    Skladnik(QString nazwa, Miara miara, WartosciOdzywcze wartosciOdzywcze);
    QString getNazwa() const;

    void setNazwa(const QString &value);

    Miara getMiara() const;
    void setMiara(const Miara &value);

    WartosciOdzywcze getWartosciOdzywcze() const;
    void setWartosciOdzywcze(const WartosciOdzywcze &value);

private:
    QString nazwa;
    Miara miara;
    WartosciOdzywcze wartosciOdzywcze;
};

#endif // SKLADNIK_H
