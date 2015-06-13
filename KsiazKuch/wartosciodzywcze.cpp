#include "wartosciodzywcze.h"
#include <QVariant>

WartosciOdzywcze::WartosciOdzywcze()
{

}

WartosciOdzywcze::WartosciOdzywcze(int kalorie, int tluszczeCalkowite, int tluszczeNasycone, int tluszczeNienasycone,
                                   int cholesterol, int sod, int weglowodany, int cukry, int bialka)
{
    this->kalorie = kalorie;
    this->tluszczeCalkowite = tluszczeCalkowite;
    this->tluszczeNasycone = tluszczeNasycone;
    this->tluszczeNienasycone = tluszczeNienasycone;
    this->cholesterol = cholesterol;
    this->sod = sod;
    this->weglowodany = weglowodany;
    this->cukry = cukry;
    this->bialka = bialka;
}

WartosciOdzywcze::WartosciOdzywcze(int id, QSqlQuery query)
{
    this->id = id;
    QString q = "SELECT kalorie, tluszcze_calkowite, tluszcze_nasycone, tluszcze_nienasycone, cholesterol, ";
    q = q + "sod, weglowodany, cukry, bialka FROM wartosci_odzywcze WHERE id=:id";
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
        this->cukry = query.value(7).toInt();
        this->bialka = query.value(8).toInt();
    }
    else
        qDebug("Bad ID");
}

void WartosciOdzywcze::insertToDb(QSqlQuery query)
{
    QString q = "INSERT INTO wartosci_odzywcze ";
    q = q + "(kalorie, tluszcze_calkowite, tluszcze_nasycone, tluszcze_nienasycone, " +
            "cholesterol, sod, weglowodany, cukry, bialka) " +
            "VALUES (:kal, :tcal, :tnas, :tnnas, :chol, :sod, :wegl, :cuk, :bia)";
    query.prepare(q);
    query.bindValue(":kal", this->kalorie);
    query.bindValue(":tcal", this->tluszczeCalkowite);
    query.bindValue(":tnas", this->tluszczeNasycone);
    query.bindValue(":tnnas", this->tluszczeNienasycone);
    query.bindValue(":chol", this->cholesterol);
    query.bindValue(":sod", this->sod);
    query.bindValue(":wegl", this->weglowodany);
    query.bindValue(":cuk", this->cukry);
    query.bindValue(":bia", this->bialka);
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

