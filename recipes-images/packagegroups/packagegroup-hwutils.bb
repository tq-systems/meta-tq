DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    usbutils \
    mmc-utils \
    i2c-tools \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pci', ' pciutils', '', d)} \
    minicom \
    screen \
    "

PACKAGE_ARCH = "${MACHINE_ARCH}"
