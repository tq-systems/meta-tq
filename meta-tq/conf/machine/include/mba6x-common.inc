# Common definitions for TQ-Systems GmbH TQMa6x modules on MBa6x

SERIAL_CONSOLES = "115200;ttymxc1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Files are needed in IMAGE_BOOT_FILES because we use an extra partition for
# backward compatibility.
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

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
    linux-firmware-ath10k \
    linux-firmware-qca \
"

WKS_FILE = "tqmaxx-imx-v7-sdimg.wks.in"

# allow to easy override for different configuration
MACHINE_WKS_BOOTSTREAM ?= "u-boot-with-spl-${MACHINE}.imx-sd"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"
