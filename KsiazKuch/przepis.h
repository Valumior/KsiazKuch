#ifndef PRZEPIS_H
#define PRZEPIS_H

#include <QString>

class Przepis
{
public:
    enum Trundnosc: unsigned char
    {
        Latwe = 0,
        Srednie = 1,
        Trudne = 2
    };

    Przepis();
private:
    QString nazwa;
    int czasPrzygotowania;
    Trundnosc trudnosc;
    bool ulubione;
    QString instrukcja;
};

#endif // PRZEPIS_H
