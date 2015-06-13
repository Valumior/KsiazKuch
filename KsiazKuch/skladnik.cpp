#include "skladnik.h"
#include <QVariant>

Skladnik::Skladnik()
{

}

Skladnik::Skladnik(QString nazwa, Skladnik::Miara miara, WartosciOdzywcze wartosciOdzywcze)
{
    this->nazwa = nazwa;
    this->miara = miara;
    this->wartosciOdzywcze = wartosciOdzywcze;
}

Skladnik::Skladnik(int id, QSqlQuery query)
{
    this->id = id;
    QString q = "SELECT nazwa, miara, wartosci_odzywcze ";
    q = q + "FROM skladnik WHERE id=:id";
    query.prepare(q);
    query.bindValue(":id", id);
    query.exec();

    int wartOdzId;

    if(query.next())
    {
        this->nazwa = query.value(0).toString();
        this->miara = (Skladnik::Miara)query.value(1).toInt();
        wartOdzId = query.value(2).toInt();
    }
    else
        qDebug("Bad ID");

    this->wartosciOdzywcze = WartosciOdzywcze(wartOdzId, query);
}

void Skladnik::insertToDb(QSqlQuery query)
{
    wartosciOdzywcze.insertToDb(query);
    query.prepare("SELECT seq FROM sqlite_sequences WHERE name=:wart");
    query.bindValue(":wart", "wartosci_odzywcze");
    query.exec();

    int wartOdzId;

    if(query.next())
    {
        wartOdzId = query.value(0).toInt();
    }
    else
        qDebug("Bad ID");

    QString q = "INSERT INTO skladnik (nazwa, miara, wartosci_odzywcze) ";
    q = q + "VALUES (:naz, :mia, :wart)";
    query.prepare(q);
    query.bindValue(":naz", this->nazwa);
    query.bindValue(":mia",(int)this->miara);
    query.bindValue(":wart", wartOdzId);
    query.exec();
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
