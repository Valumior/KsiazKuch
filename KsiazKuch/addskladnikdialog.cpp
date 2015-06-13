#include "addskladnikdialog.h"
#include "ui_addskladnikdialog.h"
#include <QMessageBox>
#include "wartosciodzywcze.h"
#include "skladnik.h"

AddSkladnikDialog::AddSkladnikDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::AddSkladnikDialog)
{
    ui->setupUi(this);
}

AddSkladnikDialog::~AddSkladnikDialog()
{
    delete ui;
}

void AddSkladnikDialog::on_pushButton_clicked()
{
    QString nazwaString = this->ui->nazwaLineEdit->text();
    if(nazwaString.length() == 0)
    {
        displayDialog("Upewnij się, czy podałeś nazwę składnika!");
        return;
    }

    //- y u no constructor
    //= dunno, gr8 b8 m8 i r8 8/8
    WartosciOdzywcze wartosciOdzywcze;
    wartosciOdzywcze.setBialka(this->ui->bialkaSpinBox->value());
    wartosciOdzywcze.setBlonnik(this->ui->blonnikSpinBox->value());
    wartosciOdzywcze.setCholesterol(this->ui->cholesterolSpinBox_5->value());
    wartosciOdzywcze.setCukry(this->ui->cukrySpinBox->value());
    wartosciOdzywcze.setKalorie(this->ui->kalorieSpinBox->value());
    wartosciOdzywcze.setSod(this->ui->sodSpinBox->value());
    wartosciOdzywcze.setTluszczeCalkowite(this->ui->tluszczeCalkowiteSpinBox->value());
    wartosciOdzywcze.setTluszczeNasycone(this->ui->tluszczeNasyconeSpinBox->value());
    wartosciOdzywcze.setTluszczeNienasycone(this->ui->tluszczeNienasyconeSpinBox->value());
    wartosciOdzywcze.setWeglowodany(this->ui->weglowodanySpinBox->value());

    Skladnik skladnik;
    skladnik.setNazwa(this->ui->nazwaLineEdit->text());
    skladnik.setMiara(Skladnik::Miara(this->ui->miaraComboBox->currentIndex()));
    skladnik.setWartosciOdzywcze(wartosciOdzywcze);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open()) {
        QSqlQuery query(database);
        skladnik.insertToDb(query);
        this->displayDialog("Składnik poprawnie dodany!");
        database.close();
        this->close();
    }
    else {
        this->displayDialog("Nie można bylo nawiązać połączenia z bazą danych!");
    }
}

void AddSkladnikDialog::displayDialog(QString errorDescripton) {
    QMessageBox *msgBox = new QMessageBox(this);
    msgBox->setText(errorDescripton);
    msgBox->setStandardButtons(QMessageBox::Ok);
    msgBox->exec();
}

void AddSkladnikDialog::on_miaraComboBox_currentIndexChanged(int index)
{
    switch(index) {
        case 0:
            this->ui->wartosciOdzywczeLabel->setText("Wartości odżywcze dla 100 ml");
            break;
        case 1:
            this->ui->wartosciOdzywczeLabel->setText("Wartości odżywcze dla 100 g");
            break;
        case 2:
            this->ui->wartosciOdzywczeLabel->setText("Wartości odżywcze dla 1 sztuki");
            break;
        default:
            break;
    }
}

void AddSkladnikDialog::on_tluszczeNasyconeSpinBox_valueChanged(int arg1)
{
    int tluszczeCalkowite = this->ui->tluszczeCalkowiteSpinBox->value();
    int tluszczeNienasycone = this->ui->tluszczeNienasyconeSpinBox->value();

    if(arg1 + tluszczeNienasycone > tluszczeCalkowite)
    {
        this->displayDialog("Suma tłuszczów nasyconych i nienasyconych nie może być większa niż tłuszcze całkowite!");
        this->ui->tluszczeNasyconeSpinBox->setValue(tluszczeCalkowite - tluszczeNienasycone);
    }
}

void AddSkladnikDialog::on_tluszczeNienasyconeSpinBox_valueChanged(int arg1)
{
    int tluszczeCalkowite = this->ui->tluszczeCalkowiteSpinBox->value();
    int tluszczeNasycone = this->ui->tluszczeNasyconeSpinBox->value();

    if(arg1 + tluszczeNasycone > tluszczeCalkowite)
    {
        this->displayDialog("Suma tłuszczów nasyconych i nienasyconych nie może być większa niż tłuszcze całkowite!");
        this->ui->tluszczeNienasyconeSpinBox->setValue(tluszczeCalkowite - tluszczeNasycone);
    }
}

void AddSkladnikDialog::on_blonnikSpinBox_valueChanged(int arg1)
{
    int cukry = this->ui->cukrySpinBox->value();
    int weglowodany = this->ui->weglowodanySpinBox->value();

    if(arg1 + cukry > weglowodany)
    {
        this->displayDialog("Suma cukrów i błonnika nie może być większa niż węglowodany!");
        this->ui->blonnikSpinBox->setValue(weglowodany - cukry);
    }
}

void AddSkladnikDialog::on_cukrySpinBox_valueChanged(int arg1)
{
    int blonnik = this->ui->blonnikSpinBox->value();
    int weglowodany = this->ui->weglowodanySpinBox->value();

    if(arg1 + blonnik > weglowodany)
    {
        this->displayDialog("Suma cukrów i błonnika nie może być większa niż węglowodany!");
        this->ui->cukrySpinBox->setValue(weglowodany - blonnik);
    }
}
