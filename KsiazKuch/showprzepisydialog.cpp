#include "showprzepisydialog.h"
#include "ui_showprzepisydialog.h"

ShowPrzepisyDialog::ShowPrzepisyDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ShowPrzepisyDialog)
{
    ui->setupUi(this);
}

ShowPrzepisyDialog::~ShowPrzepisyDialog()
{
    delete ui;
}
