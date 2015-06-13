#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "addprzepisdialog.h"
#include "addskladnikdialog.h"
#include "showprzepisydialog.h"
#include "showskladnikidialog.h"
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
    AddPrzepisDialog *addPrzepisDialog = new AddPrzepisDialog(this);
    addPrzepisDialog->show();
}

void MainWindow::showAddSkladnik()
{
    AddSkladnikDialog *addSkladnikDialog = new AddSkladnikDialog(this);
    addSkladnikDialog->show();
}

void MainWindow::showSeePrzepisy()
{
    ShowPrzepisyDialog *showPrzepisyDialog = new ShowPrzepisyDialog(this);
    showPrzepisyDialog->show();
}

void MainWindow::showSeeSkladniki()
{
    ShowSkladnikiDialog *showSkladnikiDialog = new ShowSkladnikiDialog(this);
    showSkladnikiDialog->show();
}
