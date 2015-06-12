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
        mainwindow.cpp

HEADERS  += mainwindow.h

FORMS    += mainwindow.ui
