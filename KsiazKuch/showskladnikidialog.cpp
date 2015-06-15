#include "showskladnikidialog.h"
#include "ui_showskladnikidialog.h"

ShowSkladnikiDialog::ShowSkladnikiDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::ShowSkladnikiDialog)
{
    ui->setupUi(this);

    QSqlDatabase database = QSqlDatabase::addDatabase("QSQLITE","cookbook");
    database.setDatabaseName("../DB/cookbook.db");

    if(database.open())
    {
        QSqlQuery query(database);
        skladniks = Skladnik::getObjects(query);
        foreach(Skladnik skladnik, skladniks) {
            this->ui->listWidget->addItem(skladnik.getNazwa());
        }

        database.close();
    }
}

ShowSkladnikiDialog::~ShowSkladnikiDialog()
{
    delete ui;
}

void ShowSkladnikiDialog::on_listWidget_itemSelectionChanged()
{
    Skladnik skladnik = this->skladniks.at(this->ui->listWidget->currentIndex().row());

    if(skladnik.getMiara() == Skladnik::Mililitry || skladnik.getMiara() == Skladnik::Gramy) {
        this->ui->skladnikWartoscihorizontalSlider->setMinimum(1);
        this->ui->skladnikWartoscihorizontalSlider->setMaximum(10000);
        this->ui->skladnikWartoscihorizontalSlider->setValue(100);

        this->ui->skladnikWartosciSpinBox->setMinimum(1);
        this->ui->skladnikWartosciSpinBox->setMaximum(10000);
        this->ui->skladnikWartosciSpinBox->setValue(100);
    }
    else {
        this->ui->skladnikWartoscihorizontalSlider->setMinimum(1);
        this->ui->skladnikWartoscihorizontalSlider->setMaximum(100);
        this->ui->skladnikWartoscihorizontalSlider->setValue(1);

        this->ui->skladnikWartosciSpinBox->setMinimum(1);
        this->ui->skladnikWartosciSpinBox->setMaximum(100);
        this->ui->skladnikWartosciSpinBox->setValue(1);
    }

    this->ui->skladnikiKalorieLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getKalorie()));
    this->ui->skladnikiTluszczeCalkowiteLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeCalkowite()));
    this->ui->skladnikiTluszczeNasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNasycone()));
    this->ui->skladnikiTluszczeNienasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNienasycone()));
    this->ui->skladnikiCholesterolLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCholesterol()));
    this->ui->skladnikiSodLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getSod()));
    this->ui->skladnikiWeglowodanyLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getWeglowodany()));
    this->ui->skladnikiCukryLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCukry()));
    this->ui->skladnikiBlonnikLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBlonnik()));
    this->ui->skladnikiBialkaLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBialka()));

    this->rephraseKaloriesMiarasOn(this->ui->skladnikiKalorieLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeCalkowiteLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeNasyconeLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeNienasyconeLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiCholesterolLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiSodLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiWeglowodanyLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiCukryLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiBlonnikLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiBialkaLabel);

    this->changeTitleOnTopLabel();
}

void ShowSkladnikiDialog::on_skladnikWartoscihorizontalSlider_valueChanged(int value)
{
    this->ui->skladnikWartosciSpinBox->setValue(value);
    this->adjustWartosciOdzywczeValues(value);
}

void ShowSkladnikiDialog::on_skladnikWartosciSpinBox_valueChanged(int arg1)
{
    this->ui->skladnikWartoscihorizontalSlider->setValue(arg1);
    this->adjustWartosciOdzywczeValues(arg1);
}

void ShowSkladnikiDialog::adjustWartosciOdzywczeValues(int byValue) {
    Skladnik skladnik = this->skladniks.at(this->ui->listWidget->currentIndex().row());

    double ratio = 1;
    if(skladnik.getMiara() == Skladnik::Mililitry || skladnik.getMiara() == Skladnik::Gramy) {
        ratio = 100;
    }

    ratio = byValue / ratio;

    this->ui->skladnikiKalorieLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getKalorie() * ratio));
    this->ui->skladnikiTluszczeCalkowiteLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeCalkowite() * ratio));
    this->ui->skladnikiTluszczeNasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNasycone() * ratio));
    this->ui->skladnikiTluszczeNienasyconeLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getTluszczeNienasycone() * ratio));
    this->ui->skladnikiCholesterolLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCholesterol() * ratio));
    this->ui->skladnikiSodLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getSod() * ratio));
    this->ui->skladnikiWeglowodanyLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getWeglowodany() * ratio));
    this->ui->skladnikiCukryLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getCukry() * ratio));
    this->ui->skladnikiBlonnikLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBlonnik() * ratio));
    this->ui->skladnikiBialkaLabel->setText(QString::number(skladnik.getWartosciOdzywcze().getBialka() * ratio));

    this->rephraseKaloriesMiarasOn(this->ui->skladnikiKalorieLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeCalkowiteLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeNasyconeLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiTluszczeNienasyconeLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiCholesterolLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiSodLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiWeglowodanyLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiCukryLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiBlonnikLabel);
    this->rephraseGramysMiaraOn(this->ui->skladnikiBialkaLabel);

    this->changeTitleOnTopLabel();
}

void ShowSkladnikiDialog::rephraseKaloriesMiarasOn(QLabel *label) {
    double kalories = label->text().toDouble();
    double kilokalories = kalories / 1000;

    if(kilokalories < 1.0f) {
        label->setText(QString::number(kalories) + " cal");
        return;
    }
    label->setText(QString::number(kilokalories) + " kcal");
}

void ShowSkladnikiDialog::rephraseGramysMiaraOn(QLabel *label) {
    double miligramys = label->text().toDouble();
    double gramys = miligramys / 1000;

    if(gramys < 1.0f) {
        label->setText(QString::number(miligramys) + " mg");
        return;
    }

    double kilogramys = gramys / 1000;
    if(kilogramys < 1.0f) {
        label->setText(QString::number(gramys) + " g");
        return;
    }
    label->setText(QString::number(kilogramys) + " kg");
}

void ShowSkladnikiDialog::changeTitleOnTopLabel() {
    Skladnik skladnik = this->skladniks.at(this->ui->listWidget->currentIndex().row());
    int miaraValue = this->ui->skladnikWartosciSpinBox->value();

    switch(skladnik.getMiara()) {
        case 0:
            this->ui->skladnikWartosciOdzywczeLabel->setText("Wartości odżywcze dla "
                                                             + QString::number(miaraValue) + " ml");
            break;
        case 1:
            this->ui->skladnikWartosciOdzywczeLabel->setText("Wartości odżywcze dla "
                                                             + QString::number(miaraValue) + " g");
            break;
        case 2:
            this->ui->skladnikWartosciOdzywczeLabel->setText("Wartości odżywcze dla "
                                                             + QString::number(miaraValue) + " sztuk");
            break;
        default:
            break;
    }
}
