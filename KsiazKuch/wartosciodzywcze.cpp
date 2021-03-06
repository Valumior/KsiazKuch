#include "wartosciodzywcze.h"
#include <QVariant>

WartosciOdzywcze::WartosciOdzywcze()
{

}

WartosciOdzywcze::WartosciOdzywcze(int kalorie, int tluszczeCalkowite, int tluszczeNasycone, int tluszczeNienasycone,
                                   int cholesterol, int sod, int weglowodany, int blonnik, int cukry, int bialka)
{
    this->kalorie = kalorie;
    this->tluszczeCalkowite = tluszczeCalkowite;
    this->tluszczeNasycone = tluszczeNasycone;
    this->tluszczeNienasycone = tluszczeNienasycone;
    this->cholesterol = cholesterol;
    this->sod = sod;
    this->weglowodany = weglowodany;
    this->blonnik = blonnik;
    this->cukry = cukry;
    this->bialka = bialka;
}

WartosciOdzywcze::WartosciOdzywcze(int id, QSqlQuery query)
{
    this->id = id;
    QString q = "SELECT kalorie, tluszcze_calkowite, tluszcze_nasycone, tluszcze_nienasycone, cholesterol, ";
    q = q + "sod, weglowodany, blonnik, cukry, bialka FROM wartosci_odzywcze WHERE id=:id";
    query.prepare(q);
    query.bindValue(":id", id);
    query.exec();

    if(query.next())
    {
        this->kalorie = query.value(0).toInt();
        this->tluszczeCalkowite = query.value(1).toInt();
        this->tluszczeNasycone = query.value(2).toInt();
        this->tluszczeNienasycone = query.value(3).toInt();
        this->cholesterol = query.value(4).toInt();
        this->sod = query.value(5).toInt();
        this->weglowodany = query.value(6).toInt();
        this->blonnik = query.value(7).toInt();
        this->cukry = query.value(9).toInt();
        this->bialka = query.value(10).toInt();
    }
    else
        qDebug("Bad ID");
}

void WartosciOdzywcze::insertToDb(QSqlQuery query)
{
    QString q = "INSERT INTO wartosci_odzywcze ";
    q = q + "(kalorie, tluszcze_calkowite, tluszcze_nasycone, tluszcze_nienasycone, " +
            "cholesterol, sod, weglowodany, blonnik, cukry, bialka) " +
            "VALUES (:kal, :tcal, :tnas, :tnnas, :chol, :sod, :wegl, :blo, :cuk, :bia)";
    query.prepare(q);
    query.bindValue(":kal", this->kalorie);
    query.bindValue(":tcal", this->tluszczeCalkowite);
    query.bindValue(":tnas", this->tluszczeNasycone);
    query.bindValue(":tnnas", this->tluszczeNienasycone);
    query.bindValue(":chol", this->cholesterol);
    query.bindValue(":sod", this->sod);
    query.bindValue(":wegl", this->weglowodany);
    query.bindValue(":blo", this->blonnik);
    query.bindValue(":cuk", this->cukry);
    query.bindValue(":bia", this->bialka);
    query.exec();
}

void WartosciOdzywcze::updateDb(QSqlQuery query, int id)
{
    QString q = "UPDATE wartosci_odzywcze ";
    q = q + "SET kalorie=:kal, tluszcze_calkowite=:tcal, tluszcze_nasycone=:tnas, " +
            "tluszcze_nienasycone=:tnnas, cholesterol=:chol, sod=:sod, weglowodany=:wegl, " +
            "blonnik=:blo, cukry=:cuk, bialka=:bia " +
            "WHERE id=:id";
    query.prepare(q);
    query.bindValue(":kal", this->kalorie);
    query.bindValue(":tcal", this->tluszczeCalkowite);
    query.bindValue(":tnas", this->tluszczeNasycone);
    query.bindValue(":tnnas", this->tluszczeNienasycone);
    query.bindValue(":chol", this->cholesterol);
    query.bindValue(":sod", this->sod);
    query.bindValue(":wegl", this->weglowodany);
    query.bindValue(":blo", this->blonnik);
    query.bindValue(":cuk", this->cukry);
    query.bindValue(":bia", this->bialka);
    query.bindValue(":id", id);
    query.exec();
}

int WartosciOdzywcze::getKalorie() const
{
    return kalorie;
}

void WartosciOdzywcze::setKalorie(int value)
{
    kalorie = value;
}

int WartosciOdzywcze::getTluszczeCalkowite() const
{
    return tluszczeCalkowite;
}

void WartosciOdzywcze::setTluszczeCalkowite(int value)
{
    tluszczeCalkowite = value;
}

int WartosciOdzywcze::getTluszczeNasycone() const
{
    return tluszczeNasycone;
}

void WartosciOdzywcze::setTluszczeNasycone(int value)
{
    tluszczeNasycone = value;
}

int WartosciOdzywcze::getTluszczeNienasycone() const
{
    return tluszczeNienasycone;
}

void WartosciOdzywcze::setTluszczeNienasycone(int value)
{
    tluszczeNienasycone = value;
}

int WartosciOdzywcze::getCholesterol() const
{
    return cholesterol;
}

void WartosciOdzywcze::setCholesterol(int value)
{
    cholesterol = value;
}

int WartosciOdzywcze::getSod() const
{
    return sod;
}

void WartosciOdzywcze::setSod(int value)
{
    sod = value;
}

int WartosciOdzywcze::getWeglowodany() const
{
    return weglowodany;
}

void WartosciOdzywcze::setWeglowodany(int value)
{
    weglowodany = value;
}

int WartosciOdzywcze::getBlonnik() const
{
    return blonnik;
}

void WartosciOdzywcze::setBlonnik(int value)
{
    blonnik = value;
}

int WartosciOdzywcze::getCukry() const
{
    return cukry;
}

void WartosciOdzywcze::setCukry(int value)
{
    cukry = value;
}

int WartosciOdzywcze::getBialka() const
{
    return bialka;
}

void WartosciOdzywcze::setBialka(int value)
{
    bialka = value;
}
int WartosciOdzywcze::getId() const
{
    return id;
}

