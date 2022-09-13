DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

PACKAGES = "\
    ${PN}-base \
    ${PN}-extended \
"

USB_BASE_DEPENDS = "\
    uhubctl \
    usbutils \
"

####################################
# If we ever need to use USB devices that initially enumerate as mass
# storage device for the purpose of bringing Windows (TM) drivers and software
# and switching after an amount of time to their primary function we could
# install usb-modeswitch and usb-modeswitch-data. This will enable directly
# switching to the primary function of the device based on udev / systemd
# Since usb-modeswitch depends on TCL we leave this out here per default
####################################
USB_EXT_DEPENDS = "\
    usb-modeswitch \
    usb-modeswitch-data \
"

RDEPENDS:${PN}-base = "\
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
    ${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', ' ${USB_BASE_DEPENDS}', '', d)} \
"

# allows to use this package in image recipes without having USB host support
# for the machine.
ALLOW_EMPTY:${PN}-extended = "1"

RDEPENDS:${PN}-extended = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', ' ${USB_EXT_DEPENDS}', '', d)} \
"
