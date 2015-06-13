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
