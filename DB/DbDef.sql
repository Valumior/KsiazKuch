drop table skladniki_przepis;
drop table przepis;
drop table skladnik;
drop table wartosci_odzywcze;
drop table enum_trudnosc;
drop table enum_miara;

create table enum_trudnosc (
	id number(11) not null auto increment,
	nazwa varchar(50),
	primary key (id)
);

create table enum_miara (
	id number(11) auto increment,
	nazwa varchar(30),
	primary key (id)
);

create table wartosci_odzywcze (
	id number(11) not null auto increment,
	kalorie number(5) not null,
	tluszcze_calkowite number(5) not null,
	tluszcze_nasycone number(5),
	tluszcze_nienasycone number(5),
	cholesterol number(5) not null,
	sod number(5) not null,
	weglowodany number(5) not null,
	blonnik number(5),
	cukry number(5),
	bialka number(5) not null,
	primary key(id)
);

create table przepis (
	id number(11) auto increment primary key,
	nazwa varchar(200) not null,
	czas przygotowania number(3) not null,
	trudnosc number(11),
	ulubione bit,
	instrukcja text not null,
	obrazek varchar(1000),
	primary key (id),
	foreign key(trudnosc) references (enum_trudnosc(id))
);

create table skladnik (
	id number(11) not null auto increment,
	nazwa varchar(200) not null,
	miara number(11) not null,
	wartosci_odzywcze number(11) not null,
	obrazek varchar(1000),
	primary key(id),
	foreign key(miara) references (enum_miara(id)),
	foreign key(wartosci_odzywcze) references (wartosci_odzywcze(id))
);

create table skladniki_przepis (
	przepis number(11) not null,
	skladnik number(11) not null,
	liczba number(6) not null,
	primary key (przepis, skladnik),
	foreign key(przepis) references (przepis(id)),
	foreign key(skladnik) references (skladnik(id))
);
