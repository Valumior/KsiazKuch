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
    addprzepiswindow.cpp \
    addskladnikwindow.cpp \
    showprzepiswindow.cpp \
    showskladnikiwindow.cpp

HEADERS  += mainwindow.h \
    addprzepiswindow.h \
    addskladnikwindow.h \
    showprzepiswindow.h \
    showskladnikiwindow.h

FORMS    += mainwindow.ui \
    addprzepiswindow.ui \
    addskladnikwindow.ui \
    showprzepiswindow.ui \
    showskladnikiwindow.ui
