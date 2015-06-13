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

}

