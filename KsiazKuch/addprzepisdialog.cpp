#include "addprzepisdialog.h"
#include "ui_addprzepisdialog.h"
#include <QMessageBox>
#include <QTimer>
#include "przepis.h"
#include "przepisskladnik.h"

AddPrzepisDialog::AddPrzepisDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::AddPrzepisDialog)
{
    ui->setupUi(this);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        skladniks = Skladnik::getObjects(query);
        foreach(Skladnik skladnik, skladniks) {
            this->ui->skladnikiComboBox->addItem(skladnik.getNazwa());
        }

        database.close();
    }
}

AddPrzepisDialog::~AddPrzepisDialog()
{
    delete ui;
}

void AddPrzepisDialog::on_stworzPrzepisPushButton_clicked()
{
    if(this->ui->nazwaLineEdit->text().length() == 0)
    {
        this->displayDialog("Upewnij się że podałeś nazwę przepisu!");
        return;
    }

    if(this->ui->przygotowanieTextEdit->toPlainText().length() == 0)
    {
        this->displayDialog("Upewnij się że podałeś sposób przyrządzenia!");
        return;
    }

    if(this->ui->skladnikiListWidget->count() == 0)
    {
        this->displayDialog("Upewnij się że dodałeś do co najmniej jeden składnik!");
        return;
    }

    Przepis przepis(0, this->ui->nazwaLineEdit->text(),
                       this->ui->czasPrzygotowaniaSpinBox->value(),
                       Przepis::Trudnosc(this->ui->trudnoscComboBox->currentIndex()),
                       this->ui->ulubioneCheckBox->isChecked(),
                       this->ui->przygotowanieTextEdit->toPlainText());

    QList<PrzepisSkladnik> skladniksList;
    foreach(Skladnik skladnik, skladniks) {
        QList<QListWidgetItem *> itemsFound = this->ui->skladnikiListWidget
                ->findItems(skladnik.getNazwa(), Qt::MatchStartsWith);
        if(itemsFound.count() > 0) {
            int skladnikCount = itemsFound.at(0)->text().split(":").at(1).trimmed().split(" ").at(0).toInt();
            PrzepisSkladnik newSkladnik(skladnikCount, skladnik);
            skladniksList.append(newSkladnik);
        }
    }
    przepis.setSkladniki(skladniksList);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        przepis.insertToDb(query);
        this->displayDialog("Przepis poprawnie dodany! Smacznego!");
        database.close();
        this->close();
    }
}

void AddPrzepisDialog::displayDialog(QString message)
{
    QMessageBox *msgBox = new QMessageBox(this);
    msgBox->setText(message);
    msgBox->setStandardButtons(QMessageBox::Ok);
    msgBox->exec();
}

void AddPrzepisDialog::on_skladnikiComboBox_currentIndexChanged(int index)
{
    Skladnik skladnik = this->skladniks.at(index);
    switch(skladnik.getMiara())
    {
        case 0:
            this->ui->jednostkaLabel->setText("ml");
            break;
        case 1:
            this->ui->jednostkaLabel->setText("g");
            break;
        case 2:
            this->ui->jednostkaLabel->setText("sztuk");
            break;
        default:
            break;
    }
}

void AddPrzepisDialog::on_dodajSkladnikPushButton_clicked()
{
    Skladnik skladnik = this->skladniks.at(this->ui->skladnikiComboBox->currentIndex());
    QList<QListWidgetItem *> itemsFound = this->ui->skladnikiListWidget
            ->findItems(skladnik.getNazwa(), Qt::MatchStartsWith);
    if(itemsFound.size() > 0)
    {
        QListWidgetItem *removedItem = this->ui->skladnikiListWidget->
                takeItem(this->ui->skladnikiListWidget->row(itemsFound.at(0)));
        delete removedItem;
    }
    else
    {
        this->ui->skladnikiListWidget->addItem(skladnik.getNazwa() + ": " +
                                               QString::number(this->ui->jednostkaSpinBox->value()) + " " +
                                               this->ui->jednostkaLabel->text());
    }
}
