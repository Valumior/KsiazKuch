#ifndef ADDPRZEPISDIALOG_H
#define ADDPRZEPISDIALOG_H

#include <QDialog>

namespace Ui {
class AddPrzepisDialog;
}

class AddPrzepisDialog : public QDialog
{
    Q_OBJECT

public:
    explicit AddPrzepisDialog(QWidget *parent = 0);
    ~AddPrzepisDialog();

private:
    Ui::AddPrzepisDialog *ui;
};

#endif // ADDPRZEPISDIALOG_H
