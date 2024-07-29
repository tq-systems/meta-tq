require conf/machine/include/tqmxx.inc

MACHINEOVERRIDES =. "tqmt10xx:"

require conf/machine/include/e5500-64b.inc

PREFERRED_PROVIDER_u-boot ?= "u-boot-tqmt"
PREFERRED_PROVIDER_virtual/kernel ?= "linux-tqmt"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS0"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmt10xx-sdimg.wks.in"

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    fm-ucode \
"

MACHINE_FEATURES:append = "\
    pci \
    rtc \
    screen \
    serial \
    touchscreen \
    ubi \
    usbhost \
    vfat \
"

# Overwrite default values
# physical erase block size
UBI_PEB_SIZE = "131072"
# logical erase block size
UBI_LEB_SIZE = "130944"
# logical erase block count
UBI_MAX_LEB_COUNT = "896"

# Common image configuration
require conf/machine/include/tq-image.inc
