# Common definitions for TQ-Systems GmbH TQMa335x modules on MBA335x board

KERNEL_DEVICETREE = "\
    am335x-mba335x.dtb \
    am335x-mba335x-dmb-ct44.dtb \
    am335x-mba335x-glyn-etm0700.dtb \
    am335x-mba335x-lvds-g104x1.dtb \
"

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because kernel / DTB in extra FAT boot partition on SD / e-MMC shall be
# removed in future
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"

IMAGE_BOOT_FILES:append = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

MACHINE_FEATURES:append = "\
    display \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', 'ti-sgx-ddk-km', '', d)}"

SERIAL_CONSOLES ?= "115200;ttyS4"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

WKS_FILE = "tqmaxx-ti-sdimg.wks"
