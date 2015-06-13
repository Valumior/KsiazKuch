#ifndef ADDSKLADNIKWINDOW_H
#define ADDSKLADNIKWINDOW_H

#include <QMainWindow>

namespace Ui {
class AddSkladnikWindow;
}

class AddSkladnikWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit AddSkladnikWindow(QWidget *parent = 0);
    ~AddSkladnikWindow();

private:
    Ui::AddSkladnikWindow *ui;
};

#endif // ADDSKLADNIKWINDOW_H
