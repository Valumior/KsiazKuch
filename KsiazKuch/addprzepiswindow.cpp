#include "addprzepiswindow.h"
#include "ui_addprzepiswindow.h"

AddPrzepisWindow::AddPrzepisWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::AddPrzepisWindow)
{
    ui->setupUi(this);
}

AddPrzepisWindow::~AddPrzepisWindow()
{
    delete ui;
}
