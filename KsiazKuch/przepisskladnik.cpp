#include "przepisskladnik.h"
#include <QVariant>

PrzepisSkladnik::PrzepisSkladnik()
{

}

PrzepisSkladnik::PrzepisSkladnik(int liczba, Skladnik &skladnik)
{
    this->liczba = liczba;
    this->skladnik = skladnik;
}

void PrzepisSkladnik::insertToDb(QSqlQuery query, int przepisId)
{
    QString q = "INSERT INTO skladniki_przepis (przepis, skladnik, liczba)";
    q = q + "VALUES (:prze, :skla, :licz)";
    query.prepare(q);
    query.bindValue(":prze", przepisId);
    query.bindValue(":skla", this->skladnik.getId());
    query.bindValue(":licz", this->liczba);
    query.exec();
}

int PrzepisSkladnik::getLiczba() const
{
    return liczba;
}

void PrzepisSkladnik::setLiczba(int value)
{
    liczba = value;
}
