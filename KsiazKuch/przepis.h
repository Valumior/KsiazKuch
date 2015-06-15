#ifndef PRZEPIS_H
#define PRZEPIS_H

#include <QString>
#include <QList>
#include <QSqlQuery>
#include "przepisskladnik.h"
#include "skladnik.h"

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
    Przepis(int id, QSqlQuery query);

    void insertToDb(QSqlQuery query);
    void updateDb(QSqlQuery query);
    static QList<Przepis> getObjects(QSqlQuery query, bool fav = false, QString filter = "");

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

    QList<PrzepisSkladnik> getSkladniki() const;
    void setSkladniki(const QList<PrzepisSkladnik> &value);

    int getId() const;

private:
    int id;
    QString nazwa;
    int czasPrzygotowania;
    Trudnosc trudnosc;
    bool ulubione;
    QString instrukcja;
    QList<PrzepisSkladnik> skladniki;
};

#endif // PRZEPIS_H
