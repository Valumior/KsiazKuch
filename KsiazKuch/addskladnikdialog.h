#ifndef ADDSKLADNIKDIALOG_H
#define ADDSKLADNIKDIALOG_H

#include <QDialog>

namespace Ui {
class AddSkladnikDialog;
}

class AddSkladnikDialog : public QDialog
{
    Q_OBJECT

public:
    explicit AddSkladnikDialog(QWidget *parent = 0);
    ~AddSkladnikDialog();

private slots:
    void on_pushButton_clicked();

    void on_miaraComboBox_currentIndexChanged(int index);

    void on_tluszczeNasyconeSpinBox_valueChanged(int arg1);

    void on_tluszczeNienasyconeSpinBox_valueChanged(int arg1);

    void on_blonnikSpinBox_valueChanged(int arg1);

    void on_cukrySpinBox_valueChanged(int arg1);

private:
    Ui::AddSkladnikDialog *ui;

    void displayDialog(QString errorDescription);
};

#endif // ADDSKLADNIKDIALOG_H
