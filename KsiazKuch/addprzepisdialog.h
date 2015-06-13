#ifndef ADDPRZEPISDIALOG_H
#define ADDPRZEPISDIALOG_H

#include <QDialog>
#include "skladnik.h"

namespace Ui {
class AddPrzepisDialog;
}

class AddPrzepisDialog : public QDialog
{
    Q_OBJECT

public:
    explicit AddPrzepisDialog(QWidget *parent = 0);
    ~AddPrzepisDialog();

private slots:
    void on_stworzPrzepisPushButton_clicked();

    void on_skladnikiComboBox_currentIndexChanged(int index);

    void on_dodajSkladnikPushButton_clicked();

private:
    Ui::AddPrzepisDialog *ui;
    QList<Skladnik> skladniks;

    void displayDialog(QString message);
};

#endif // ADDPRZEPISDIALOG_H
