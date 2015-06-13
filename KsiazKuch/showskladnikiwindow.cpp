#include "showskladnikiwindow.h"
#include "ui_showskladnikiwindow.h"

ShowSkladnikiWindow::ShowSkladnikiWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ShowSkladnikiWindow)
{
    ui->setupUi(this);
}

ShowSkladnikiWindow::~ShowSkladnikiWindow()
{
    delete ui;
}
