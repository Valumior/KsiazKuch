#ifndef WARTOSCIODZYWCZE_H
#define WARTOSCIODZYWCZE_H

#include <QSqlQuery>

class WartosciOdzywcze
{
public:
    WartosciOdzywcze();
    WartosciOdzywcze(int kalorie, int tluszczeCalkowite, int tluszczeNasycone, int tluszczeNienasycone, int cholesterol,
                     int sod, int weglowodany, int blonnik, int cukry, int bialka);
    WartosciOdzywcze(int id, QSqlQuery query);

    void insertToDb(QSqlQuery query);
    void updateDb(QSqlQuery query, int id);

    int getKalorie() const;
    void setKalorie(int value);

    int getTluszczeCalkowite() const;
    void setTluszczeCalkowite(int value);

    int getTluszczeNasycone() const;
    void setTluszczeNasycone(int value);

    int getTluszczeNienasycone() const;
    void setTluszczeNienasycone(int value);

    int getCholesterol() const;
    void setCholesterol(int value);

    int getSod() const;
    void setSod(int value);

    int getWeglowodany() const;
    void setWeglowodany(int value);

    int getBlonnik() const;
    void setBlonnik(int value);

    int getCukry() const;
    void setCukry(int value);

    int getBialka() const;
    void setBialka(int value);

    int getId() const;

private:
    int id;
    int kalorie;
    int tluszczeCalkowite;
    int tluszczeNasycone;
    int tluszczeNienasycone;
    int cholesterol;
    int sod;
    int weglowodany;
    int blonnik;
    int cukry;
    int bialka;
};

#endif // WARTOSCIODZYWCZE_H
