# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for
# TQMa6ULx/TQMa6ULLx/TQMa6ULxL/TQMa6ULLxL SOM

SERIAL_CONSOLES = "115200;ttymxc0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Files are needed in IMAGE_BOOT_FILES because we use an extra partition for
# backward compatibility.
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${@create_image_boot_dtb_files(d)} \
"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    touchscreen \
    wifi \
"

# install kernel and devicetree also under /boot inside the rootfs,
# needed for UBIFS use case and perpare removal of extra FAT boot partition
# on SD / e-MMC
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"

WKS_FILE = "tqmaxx-imx-v7-sdimg.wks.in"

# allow to easy override for different configuration
MACHINE_WKS_BOOTSTREAM ?= "${UBOOT_BINARY_BASENAME}-${MACHINE}.${UBOOT_SUFFIX}-sd"
