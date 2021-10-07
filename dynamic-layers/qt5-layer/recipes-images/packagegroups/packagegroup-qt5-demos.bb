# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Package group for Qt5 demos"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    qtbase \
    qtserialport \
    qtdeclarative \
    qtmultimedia \
    qwt-qt5 \
"

RDEPENDS_${PN}_append = "\
    qwt-qt5-examples \
"

QT5_OPENGL_DEMOS ="\
    cinematicexperience \
    qt5everywheredemo \
"

RDEPENDS_${PN}_append = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', "${QT5_OPENGL_DEMOS}", '', d)} \
"
