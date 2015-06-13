drop table if exists skladniki_przepis;
drop table if exists przepis;
drop table if exists skladnik;
drop table if exists wartosci_odzywcze;

create table wartosci_odzywcze (
	id integer not null primary key autoincrement,
	kalorie integer not null,
	tluszcze_calkowite integer not null,
	tluszcze_nasycone integer,
	tluszcze_nienasycone integer,
	cholesterol integer not null,
	sod integer not null,
	weglowodany integer not null,
	blonnik integer,
	cukry integer,
	bialka integer not null
);

create table przepis (
	id integer primary key autoincrement,
	nazwa text not null,
	czas_przygotowania integer not null,
	trudnosc integer,
	ulubione numeric,
	instrukcja text not null
);

create table skladnik (
	id integer not null primary key autoincrement,
	nazwa text not null,
	miara integer not null,
	wartosci_odzywcze integer not null,
	foreign key(wartosci_odzywcze) references wartosci_odzywcze(id)
);

create table skladniki_przepis (
	przepis integer not null,
	skladnik integer not null,
	liczba integer not null,
	primary key (przepis, skladnik),
	foreign key(przepis) references przepis(id),
	foreign key(skladnik) references skladnik(id)
);
