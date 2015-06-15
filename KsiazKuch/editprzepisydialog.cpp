#include "editprzepisydialog.h"
#include "ui_editprzepisydialog.h"
#include "addprzepisdialog.h"

EditPrzepisyDialog::EditPrzepisyDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::EditPrzepisyDialog)
{
    ui->setupUi(this);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        przepiss = Przepis::getObjects(query);
        foreach(Przepis przepis, przepiss)
            this->ui->listWidget->addItem(przepis.getNazwa());

        database.close();
    }
}

EditPrzepisyDialog::~EditPrzepisyDialog()
{
    delete ui;
}

void EditPrzepisyDialog::on_pushButton_clicked()
{
    Przepis przepis = this->przepiss.at(this->ui->listWidget->currentIndex().row());

    AddPrzepisDialog *addPrzepisDialog = new AddPrzepisDialog(przepis, this);
    addPrzepisDialog->show();
    this->close();
}
