#ifndef SKLADNIK_H
#define SKLADNIK_H

#include <QString>
#include "wartosciodzywcze.h"

class Skladnik
{
public:
    enum Miara: unsigned char
    {
        Sztuki = 0,
        Mililitry = 1,
        Gramy = 2
    };

    Skladnik();
private:
    QString nazwa;
    Miara miara;
    WartosciOdzywcze wartosci_odzywcze;
};

#endif // SKLADNIK_H
