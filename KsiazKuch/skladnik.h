#ifndef SKLADNIK_H
#define SKLADNIK_H

#include <QString>
#include <QSqlQuery>
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
    Skladnik(int id ,QString nazwa, Miara miara, WartosciOdzywcze wartosciOdzywcze);
    Skladnik(int id, QSqlQuery query);

    void insertToDb(QSqlQuery query);

    QString getNazwa() const;
    void setNazwa(const QString &value);

    Miara getMiara() const;
    void setMiara(const Miara &value);

    WartosciOdzywcze getWartosciOdzywcze() const;
    void setWartosciOdzywcze(const WartosciOdzywcze &value);

    int getId() const;

private:
    int id;
    QString nazwa;
    Miara miara;
    WartosciOdzywcze wartosciOdzywcze;
};

#endif // SKLADNIK_H
