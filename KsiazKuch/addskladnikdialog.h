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

private:
    Ui::AddSkladnikDialog *ui;
};

#endif // ADDSKLADNIKDIALOG_H
