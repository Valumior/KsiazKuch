#-------------------------------------------------
#
# Project created by QtCreator 2015-06-12T14:49:27
#
#-------------------------------------------------

QT       += core gui\
            sql

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

INCLUDEPATH += /usr/include/mysql\
               /usr/lib/i386-linux-gnu

TARGET = KsiazKuch
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    przepis.cpp \
    skladnik.cpp \
    wartosciodzywcze.cpp \
    addprzepisdialog.cpp \
    addskladnikdialog.cpp \
    showprzepisydialog.cpp \
    showskladnikidialog.cpp \
    przepisskladnik.cpp

HEADERS  += mainwindow.h \
    przepis.h \
    skladnik.h \
    wartosciodzywcze.h \
    addprzepisdialog.h \
    addskladnikdialog.h \
    showprzepisydialog.h \
    showskladnikidialog.h \
    przepisskladnik.h

FORMS    += mainwindow.ui \
    addprzepisdialog.ui \
    addskladnikdialog.ui \
    showprzepisydialog.ui \
    showskladnikidialog.ui
