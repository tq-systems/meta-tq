DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
    usbutils \
    mmc-utils \
    i2c-tools \
    libgpiod \
    libgpiod-tools \
    lmsensors-libsensors \
    lmsensors-sensors \
    minicom \
    ${@bb.utils.contains('MACHINE_FEATURES', 'pci', ' pciutils', '', d)} \
    screen \
    spitools \
    uhubctl \
    usb-modeswitch \
"
