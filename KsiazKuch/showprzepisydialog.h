#ifndef SHOWPRZEPISYDIALOG_H
#define SHOWPRZEPISYDIALOG_H

#include <QDialog>

namespace Ui {
class ShowPrzepisyDialog;
}

class ShowPrzepisyDialog : public QDialog
{
    Q_OBJECT

public:
    explicit ShowPrzepisyDialog(QWidget *parent = 0);
    ~ShowPrzepisyDialog();

private:
    Ui::ShowPrzepisyDialog *ui;
};

#endif // SHOWPRZEPISYDIALOG_H
