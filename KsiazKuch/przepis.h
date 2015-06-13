#ifndef PRZEPIS_H
#define PRZEPIS_H


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
    string nazwa;
    int czasPrzygotowania;
    Trudnosc trudnosc;
    bool ulubione;
    string instrukcja;
};

#endif // PRZEPIS_H
