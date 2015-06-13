#include "wartosciodzywcze.h"

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
