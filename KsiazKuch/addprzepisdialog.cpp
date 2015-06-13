#include "addprzepisdialog.h"
#include "ui_addprzepisdialog.h"

AddPrzepisDialog::AddPrzepisDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::AddPrzepisDialog)
{
    ui->setupUi(this);
}

AddPrzepisDialog::~AddPrzepisDialog()
{
    delete ui;
}
