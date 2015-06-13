#include "przepis.h"
#include <QVariant>

Przepis::Przepis()
{

}

Przepis::Przepis(int id ,QString nazwa, int czasPrzygotowania, Przepis::Trudnosc trudnosc, bool ulubione, QString instrukcja)
{
    this->id = id;
    this->nazwa = nazwa;
    this->czasPrzygotowania = czasPrzygotowania;
    this->trudnosc = trudnosc;
    this->ulubione = ulubione;
    this->instrukcja = instrukcja;
}

void Przepis::insertToDb(QSqlQuery query)
{
    QString q = "INSERT INTO przepis (nazwa, czas_przygotowania, trudnosc, ulubione, instrukcja) ";
    q = q + "VALUES (:naz, :cza, :tru, :ul, :inst)";
    query.prepare(q);
    query.bindValue(":naz", this->nazwa);
    query.bindValue(":cza", this->czasPrzygotowania);
    query.bindValue(":tru", (int)this->trudnosc);
    query.bindValue(":ul", (int)this->ulubione);
    query.bindValue(":inst", this->instrukcja);
    query.exec();

    query.exec("SELECT last_insert_rowid()");

    int przepId;

    if(query.next())
    {
        przepId = query.value(0).toInt();
    }

    for(int i = 0; i < this->skladniki.count(); ++i)
        this->skladniki[i].insertToDb(query, przepId);
}

QString Przepis::getNazwa() const
{
    return nazwa;
}

void Przepis::setNazwa(const QString &value)
{
    nazwa = value;
}

int Przepis::getCzasPrzygotowania() const
{
    return czasPrzygotowania;
}

void Przepis::setCzasPrzygotowania(int value)
{
    czasPrzygotowania = value;
}

Przepis::Trudnosc Przepis::getTrudnosc() const
{
    return trudnosc;
}

void Przepis::setTrudnosc(const Trudnosc &value)
{
    trudnosc = value;
}

bool Przepis::getUlubione() const
{
    return ulubione;
}

void Przepis::setUlubione(bool value)
{
    ulubione = value;
}

QString Przepis::getInstrukcja() const
{
    return instrukcja;
}

void Przepis::setInstrukcja(const QString &value)
{
    instrukcja = value;
}
QList<PrzepisSkladnik> Przepis::getSkladniki() const
{
    return skladniki;
}

void Przepis::setSkladniki(const QList<PrzepisSkladnik> &value)
{
    skladniki = value;
}

