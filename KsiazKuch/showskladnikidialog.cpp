#include "showskladnikidialog.h"
#include "ui_showskladnikidialog.h"

ShowSkladnikiDialog::ShowSkladnikiDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ShowSkladnikiDialog)
{
    ui->setupUi(this);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        skladniks = Skladnik::getObjects(query);
        foreach(Skladnik skladnik, skladniks) {
            this->ui->listWidget->addItem(skladnik.getNazwa());
        }

        database.close();
    }
}

ShowSkladnikiDialog::~ShowSkladnikiDialog()
{
    delete ui;
}

void ShowSkladnikiDialog::on_listWidget_itemSelectionChanged()
{
    Skladnik skladnik = this->skladniks.at(this->ui->listWidget->currentIndex().row());

    this->ui->skladnikiKalorieLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getKalorie()));
    this->ui->skladnikiTluszczeCalkowiteLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeCalkowite()));
    this->ui->skladnikiTluszczeNasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNasycone()));
    this->ui->skladnikiTluszczeNienasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNienasycone()));
    this->ui->skladnikiCholesterolLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCholesterol()));
    this->ui->skladnikiSodLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getSod()));
    this->ui->skladnikiWeglowodanyLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getWeglowodany()));
    this->ui->skladnikiCukryLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCukry()));
    this->ui->skladnikiBlonnikLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBlonnik()));
    this->ui->skladnikiBialkaLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBialka()));
}
