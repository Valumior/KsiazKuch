#ifndef ADDPRZEPISWINDOW_H
#define ADDPRZEPISWINDOW_H

#include <QMainWindow>

namespace Ui {
class AddPrzepisWindow;
}

class AddPrzepisWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit AddPrzepisWindow(QWidget *parent = 0);
    ~AddPrzepisWindow();

private:
    Ui::AddPrzepisWindow *ui;
};

#endif // ADDPRZEPISWINDOW_H
