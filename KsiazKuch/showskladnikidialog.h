#ifndef SHOWSKLADNIKIDIALOG_H
#define SHOWSKLADNIKIDIALOG_H

#include <QDialog>
#include <QList>
#include <QLabel>
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

private slots:
    void on_listWidget_itemSelectionChanged();

    void on_skladnikWartoscihorizontalSlider_valueChanged(int value);

    void on_skladnikWartosciSpinBox_valueChanged(int arg1);

private:
    Ui::ShowSkladnikiDialog *ui;
    QList<Skladnik> skladniks;

    void adjustWartosciOdzywczeValues(int byValue);
    void rephraseKaloriesMiarasOn(QLabel *label);
    void rephraseGramysMiaraOn(QLabel *label);
    void changeTitleOnTopLabel();
};

#endif // SHOWSKLADNIKIDIALOG_H
