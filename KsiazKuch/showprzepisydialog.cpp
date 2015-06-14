#include "showprzepisydialog.h"
#include "ui_showprzepisydialog.h"
#include "przepisskladnik.h"
#include <QDebug>

ShowPrzepisyDialog::ShowPrzepisyDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ShowPrzepisyDialog)
{
    ui->setupUi(this);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        przepiss = Przepis::getObjects(query);
        foreach(Przepis przepis, przepiss)
            this->ui->przepisyListWidget->addItem(przepis.getNazwa());

        database.close();
    }
}

ShowPrzepisyDialog::~ShowPrzepisyDialog()
{
    delete ui;
}

void ShowPrzepisyDialog::on_przepisyListWidget_itemSelectionChanged()
{
    Przepis przepis = this->przepiss.at(this->ui->przepisyListWidget->currentIndex().row());
    this->loadPrzepisData(przepis);
}

void ShowPrzepisyDialog::loadPrzepisData(const Przepis &przepis) {
    this->ui->przepisyPrzyrzadzenieTextEdit->textCursor().insertText(przepis.getInstrukcja());
    this->ui->przepisNazwaLabel->setText(przepis.getNazwa());
    this->ui->przepisCzasPrzygotowaniaLabel->setText(QString::number(przepis.getCzasPrzygotowania()) + " minut");

    switch(przepis.getTrudnosc()) {
        case 0:
            this->ui->przepisTrudnosclabel->setText("ŁATWE");
            break;
        case 1:
            this->ui->przepisTrudnosclabel->setText("ŚREDNIE");
            break;
        case 2:
            this->ui->przepisTrudnosclabel->setText("TRUDNE");
            break;
        case 3:
            this->ui->przepisTrudnosclabel->setText("MISTRZ KUCHNI");
            break;
        default:
            break;
    }

    double kalorieTotal = 0, tluszcze_calkowiteTotal = 0, tluszcze_nasyconeTotal = 0, tluszcze_nienasyconeTotal = 0,
            cholesterolTotal = 0, sodTotal = 0, weglowodanyTotal = 0, blonnikTotal = 0, cukryTotal = 0, bialkaTotal = 0;

    foreach(PrzepisSkladnik skladnik, przepis.getSkladniki()) {
        double ratio = 1;
        if(skladnik.getSkladnik().getMiara() == Skladnik::Mililitry || skladnik.getSkladnik().getMiara() == Skladnik::Gramy) {
            ratio = 100;
        }

        if(skladnik.getSkladnik().getMiara() == Skladnik::Mililitry)
            this->ui->przepisSkladnikiListWidget->addItem(skladnik.getSkladnik().getNazwa() + " "
                                                          + QString::number(skladnik.getLiczba()) + "ml");
        else if(skladnik.getSkladnik().getMiara() == Skladnik::Gramy)
            this->ui->przepisSkladnikiListWidget->addItem(skladnik.getSkladnik().getNazwa() + " "
                                                          + QString::number(skladnik.getLiczba()) + "g");
        else
            this->ui->przepisSkladnikiListWidget->addItem(skladnik.getSkladnik().getNazwa() + " "
                                                          + QString::number(skladnik.getLiczba()) + " szt.");

        ratio = skladnik.getLiczba() / ratio;

        WartosciOdzywcze wartosciOdzywcze = skladnik.getSkladnik().getWartosciOdzywcze();
        kalorieTotal += wartosciOdzywcze.getKalorie() * ratio;
        tluszcze_calkowiteTotal += wartosciOdzywcze.getTluszczeCalkowite() * ratio;
        tluszcze_nasyconeTotal += wartosciOdzywcze.getTluszczeNasycone() * ratio;
        tluszcze_nienasyconeTotal += wartosciOdzywcze.getTluszczeNienasycone() * ratio;
        cholesterolTotal += wartosciOdzywcze.getCholesterol() * ratio;
        sodTotal += wartosciOdzywcze.getSod() * ratio;
        weglowodanyTotal += wartosciOdzywcze.getWeglowodany() * ratio;
        blonnikTotal += wartosciOdzywcze.getBlonnik() * ratio;
        cukryTotal += wartosciOdzywcze.getCukry() * ratio;
        bialkaTotal += wartosciOdzywcze.getBialka() * ratio;
    }

    this->ui->przepisKalorieLabel->setText(QString::number(kalorieTotal));
    this->ui->przepisTluszczeCalkowiteLabel->setText(QString::number(tluszcze_calkowiteTotal));
    this->ui->przepisTluszczeNasyconeLabel->setText(QString::number(tluszcze_nasyconeTotal));
    this->ui->przepisTluszczeNienasyconeLabel->setText(QString::number(tluszcze_nienasyconeTotal));
    this->ui->przepisCholesterolLabel->setText(QString::number(cholesterolTotal));
    this->ui->przepisSodLabel->setText(QString::number(sodTotal));
    this->ui->przepisWeglowodanyLabel->setText(QString::number(weglowodanyTotal));
    this->ui->przepisBlonnikLabel->setText(QString::number(blonnikTotal));
    this->ui->przepisCukryLabel->setText(QString::number(cukryTotal));
    this->ui->przepisBialkaLabel->setText(QString::number(bialkaTotal));
}
