#include "skladnik.h"

Skladnik::Skladnik()
{

}

Skladnik::Skladnik(int id ,QString nazwa, Skladnik::Miara miara, WartosciOdzywcze wartosciOdzywcze)
{
    this->id = id;
    this->nazwa = nazwa;
    this->miara = miara;
    this->wartosciOdzywcze = wartosciOdzywcze;
}

QString Skladnik::getNazwa() const
{
    return nazwa;
}

void Skladnik::setNazwa(const QString &value)
{
    nazwa = value;
}

Skladnik::Miara Skladnik::getMiara() const
{
    return miara;
}

void Skladnik::setMiara(const Miara &value)
{
    miara = value;
}

WartosciOdzywcze Skladnik::getWartosciOdzywcze() const
{
    return wartosciOdzywcze;
}

void Skladnik::setWartosciOdzywcze(const WartosciOdzywcze &value)
{
    wartosciOdzywcze = value;
}
int Skladnik::getId() const
{
    return id;
}

