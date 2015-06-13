#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "addprzepiswindow.h"
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
