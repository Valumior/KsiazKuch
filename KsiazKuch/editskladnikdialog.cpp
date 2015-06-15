#include "editskladnikdialog.h"
#include "ui_editskladnikdialog.h"
#include "skladnik.h"
#include "addskladnikdialog.h"

editskladnikdialog::editskladnikdialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::editskladnikdialog)
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

editskladnikdialog::~editskladnikdialog()
{
    delete ui;
}

void editskladnikdialog::on_pushButton_clicked()
{
    Skladnik skladnik = this->skladniks.at(this->ui->listWidget->currentIndex().row());

    AddSkladnikDialog *addSkladnikDialog = new AddSkladnikDialog(skladnik, this);
    addSkladnikDialog->show();
    this->close();
}
