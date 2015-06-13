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
    static QList<PrzepisSkladnik> getObjects(QSqlQuery query, int przepisId);

    int getLiczba() const;
    void setLiczba(int value);

    Skladnik getSkladnik() const;
    void setSkladnik(Skladnik &value);

private:
    int liczba;
    Skladnik skladnik;
};

#endif // PRZEPISSKLADNIK_H
