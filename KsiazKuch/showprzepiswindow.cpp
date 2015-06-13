#include "showprzepiswindow.h"
#include "ui_showprzepiswindow.h"

ShowPrzepisWindow::ShowPrzepisWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ShowPrzepisWindow)
{
    ui->setupUi(this);
}

ShowPrzepisWindow::~ShowPrzepisWindow()
{
    delete ui;
}
