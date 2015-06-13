#ifndef SHOWSKLADNIKIWINDOW_H
#define SHOWSKLADNIKIWINDOW_H

#include <QMainWindow>

namespace Ui {
class ShowSkladnikiWindow;
}

class ShowSkladnikiWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit ShowSkladnikiWindow(QWidget *parent = 0);
    ~ShowSkladnikiWindow();

private:
    Ui::ShowSkladnikiWindow *ui;
};

#endif // SHOWSKLADNIKIWINDOW_H
