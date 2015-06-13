#ifndef SHOWPRZEPISYDIALOG_H
#define SHOWPRZEPISYDIALOG_H

#include <QDialog>
#include <QListWidgetItem>
#include "przepis.h"

namespace Ui {
class ShowPrzepisyDialog;
}

class ShowPrzepisyDialog : public QDialog
{
    Q_OBJECT

public:
    explicit ShowPrzepisyDialog(QWidget *parent = 0);
    ~ShowPrzepisyDialog();

private slots:
    void on_przepisyListWidget_itemSelectionChanged();

private:
    Ui::ShowPrzepisyDialog *ui;
    QList<Przepis> przepiss;

    void loadPrzepisData(const Przepis &przepis);
};

#endif // SHOWPRZEPISYDIALOG_H
