#ifndef PRZEPISSKLADNIK_H
#define PRZEPISSKLADNIK_H

#include "skladnik.h"
#include <QSqlQuery>

class PrzepisSkladnik
{
public:
    PrzepisSkladnik();
    PrzepisSkladnik(int count, Skladnik &skladnik);

    void insertToDb(QSqlQuery query, int przepisId);
private:
    int count;
    Skladnik skladnik;
};

#endif // PRZEPISSKLADNIK_H
