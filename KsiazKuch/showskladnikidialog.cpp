#include "showskladnikidialog.h"
#include "ui_showskladnikidialog.h"

ShowSkladnikiDialog::ShowSkladnikiDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ShowSkladnikiDialog)
{
    ui->setupUi(this);
}

ShowSkladnikiDialog::~ShowSkladnikiDialog()
{
    delete ui;
}
