#ifndef SHOWSKLADNIKIDIALOG_H
#define SHOWSKLADNIKIDIALOG_H

#include <QDialog>

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
};

#endif // SHOWSKLADNIKIDIALOG_H
