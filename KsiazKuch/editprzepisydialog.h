#ifndef EDITPRZEPISYDIALOG_H
#define EDITPRZEPISYDIALOG_H

#include <QDialog>
#include "przepis.h"

namespace Ui {
class EditPrzepisyDialog;
}

class EditPrzepisyDialog : public QDialog
{
    Q_OBJECT

public:
    explicit EditPrzepisyDialog(QWidget *parent = 0);
    ~EditPrzepisyDialog();

private slots:
    void on_pushButton_clicked();

private:
    Ui::EditPrzepisyDialog *ui;
    QList<Przepis> przepiss;
};

#endif // EDITPRZEPISYDIALOG_H
