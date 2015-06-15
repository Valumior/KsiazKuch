#ifndef EDITSKLADNIKDIALOG_H
#define EDITSKLADNIKDIALOG_H

#include <QDialog>
#include "skladnik.h"

namespace Ui {
class editskladnikdialog;
}

class editskladnikdialog : public QDialog
{
    Q_OBJECT

public:
    explicit editskladnikdialog(QWidget *parent = 0);
    ~editskladnikdialog();

private slots:
    void on_pushButton_clicked();

private:
    Ui::editskladnikdialog *ui;
    QList<Skladnik> skladniks;
};

#endif // EDITSKLADNIKDIALOG_H
