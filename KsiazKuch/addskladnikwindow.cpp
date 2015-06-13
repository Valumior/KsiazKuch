#include "addskladnikwindow.h"
#include "ui_addskladnikwindow.h"

AddSkladnikWindow::AddSkladnikWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::AddSkladnikWindow)
{
    ui->setupUi(this);
}

AddSkladnikWindow::~AddSkladnikWindow()
{
    delete ui;
}
