# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Package group for Qt5 demos"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    qtbase \
    qtserialport \
    qtdeclarative \
    qtmultimedia \
    qwt-qt5 \
"

RDEPENDS:${PN}:append = "\
    qwt-qt5-examples \
"

QT5_OPENGL_DEMOS ="\
    cinematicexperience \
    qt5everywheredemo \
"

RDEPENDS:${PN}:append = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', "${QT5_OPENGL_DEMOS}", '', d)} \
"
