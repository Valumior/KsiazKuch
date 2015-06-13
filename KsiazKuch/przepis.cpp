#include "przepis.h"

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
