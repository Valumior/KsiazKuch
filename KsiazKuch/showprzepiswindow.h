#ifndef SHOWPRZEPISWINDOW_H
#define SHOWPRZEPISWINDOW_H

#include <QMainWindow>

namespace Ui {
class ShowPrzepisWindow;
}

class ShowPrzepisWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit ShowPrzepisWindow(QWidget *parent = 0);
    ~ShowPrzepisWindow();

private:
    Ui::ShowPrzepisWindow *ui;
};

#endif // SHOWPRZEPISWINDOW_H
