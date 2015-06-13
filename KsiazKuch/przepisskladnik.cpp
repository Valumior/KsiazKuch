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

QList<PrzepisSkladnik> PrzepisSkladnik::getObjects(QSqlQuery query, int przepisId)
{
    QList<PrzepisSkladnik> list;
    QString q = "SELECT skladniki_przepis.liczba, skladnik.id, skladnik.nazwa, skladnik.miara, ";
    q = q + "wartosci_odzywcze.kalorie, wartosci_odzywcze.tluszcze_calkowite, wartosci_odzywcze.tluszcze_nasycone, " +
            "wartosci_odzywcze.tluszcze_nienasycone, wartosci_odzywcze.cholesterol, wartosci_odzywcze.sod, " +
            "wartosci_odzywcze.weglowodany, wartosci_odzywcze.blonnik, wartosci_odzywcze.cukry, wartosci_odzywcze.bialka " +
            "FROM skladniki_przepis NATURAL JOIN skladnik NATURAL wartosci_odzywcze JOIN WHERE skladniki_przepis.przepis=:id";
    query.prepare(q);
    query.bindValue(":id", przepisId);
    query.exec();

    while(query.next())
    {
        WartosciOdzywcze wartOdz = WartosciOdzywcze(query.value(4).toInt(), query.value(5).toInt(), query.value(6).toInt(),
                                                    query.value(7).toInt(), query.value(8).toInt(), query.value(9).toInt(),
                                                    query.value(10).toInt(), query.value(11).toInt(), query.value(12).toInt(),
                                                    query.value(13).toInt());
        Skladnik skl = Skladnik(query.value(1).toInt(), query.value(2).toString(), Skladnik::Miara(query.value(3).toInt()),
                                wartOdz);
        PrzepisSkladnik przeSkla = PrzepisSkladnik(query.value(0).toInt(), skl);

        list.append(przeSkla);
    }

    return list;
}

int PrzepisSkladnik::getLiczba() const
{
    return liczba;
}

void PrzepisSkladnik::setLiczba(int value)
{
    liczba = value;
}
