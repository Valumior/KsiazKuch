#include "przepisskladnik.h"

PrzepisSkladnik::PrzepisSkladnik()
{

}

PrzepisSkladnik::PrzepisSkladnik(int count, Skladnik &skladnik)
{
    this->count = count;
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


