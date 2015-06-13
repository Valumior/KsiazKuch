#include "skladnik.h"
#include <QVariant>

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
    query.exec("SELECT last_insert_rowid()");

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

QList<Skladnik> Skladnik::getObjects(QSqlQuery query, QString filter)
{
    QList<Skladnik> list = QList<Skladnik>();
    QString q = "SELECT skladnik.id skladnik.nazwa, skladnik.miara, ";
    q = q + "wartosci_odzywcze.kalorie, wartosci_odzywcze.tluszcze_calkowite, wartosci_odzywcze.tluszcze_nasycone, " +
            "wartosci_odzywcze.tluszcze_nienasycone, wartosci_odzywcze.cholesterol, wartosci_odzywcze.sod, " +
            "wartosci_odzywcze.weglowodany, wartosci_odzywcze.blonnik, wartosci_odzywcze.cukry, wartosci_odzywcze.bialka " +
            "FROM skladnik NATURAL JOIN wartosci_odzywcze";
    if(filter.trimmed().isEmpty())
        q = q + " WHERE skladnik.nazwa LIKE '%" + filter + "%'";

    query.exec(q);

    while(query.next())
    {
        WartosciOdzywcze wartOdz = WartosciOdzywcze(query.value(3).toInt(), query.value(4).toInt(), query.value(5).toInt(),
                                                    query.value(6).toInt(), query.value(7).toInt(), query.value(8).toInt(),
                                                    query.value(9).toInt(), query.value(10).toInt(), query.value(11).toInt(),
                                                    query.value(12).toInt());
        Skladnik skl = Skladnik(query.value(0).toInt(),query.value(1).toString(),Skladnik::Miara(query.value(2).toInt()),
                                wartOdz);
        list.append(skl);
    }

    return list;
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
