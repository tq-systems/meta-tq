DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    usbutils \
    mmc-utils \
    i2c-tools \
    pciutils \
    "
