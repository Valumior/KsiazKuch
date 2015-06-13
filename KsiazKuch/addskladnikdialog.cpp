#include "addskladnikdialog.h"
#include "ui_addskladnikdialog.h"

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
