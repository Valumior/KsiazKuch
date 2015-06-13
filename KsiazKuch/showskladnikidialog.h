#ifndef SHOWSKLADNIKIDIALOG_H
#define SHOWSKLADNIKIDIALOG_H

#include <QDialog>
#include <QList>
#include "skladnik.h"

namespace Ui {
class ShowSkladnikiDialog;
}

class ShowSkladnikiDialog : public QDialog
{
    Q_OBJECT

public:
    explicit ShowSkladnikiDialog(QWidget *parent = 0);
    ~ShowSkladnikiDialog();

private:
    Ui::ShowSkladnikiDialog *ui;
    QList<Skladnik> skladniks;
};

#endif // SHOWSKLADNIKIDIALOG_H
