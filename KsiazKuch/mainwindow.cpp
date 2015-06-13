#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "addprzepiswindow.h"
#include "addskladnikwindow.h"
#include "showprzepiswindow.h"
#include "showskladnikiwindow.h"
#include <QDebug>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSql>
#include <QtSql/QSqlQuery>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    db.setDatabaseName("../DB/cookbook.db");
    if(!db.open())
        qDebug("Not working");
    else
        qDebug("Working");

    ui->setupUi(this);

    connect(ui->addPrzepisButton, SIGNAL(clicked()), this, SLOT(showAddPrzepis()));
    connect(ui->addSkladnikButton, SIGNAL(clicked()), this, SLOT(showAddSkladnik()));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_actionWyjscie_triggered()
{
    this->close();
}

void MainWindow::showAddPrzepis()
{
    AddPrzepisWindow *addPrzepisWindow = new AddPrzepisWindow(this);
    addPrzepisWindow->show();
}

void MainWindow::showAddSkladnik()
{
    AddSkladnikWindow *addSkladnikWindow = new AddSkladnikWindow(this);
    addSkladnikWindow->show();
}

void MainWindow::showSeePrzepisy()
{
    ShowPrzepisWindow *showPrzepisWindow = new ShowPrzepisWindow(this);
    showPrzepisWindow->show();
}

void MainWindow::showSeeSkladniki()
{
    ShowSkladnikiWindow *showSeeSkladnik = new ShowSkladnikiWindow(this);
    showSeeSkladnik->show();
}
