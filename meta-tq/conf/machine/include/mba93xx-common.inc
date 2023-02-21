# Common definitions for TQ-Systems GmbH MBa93xxCA / MBa93xxLA

require conf/machine/include/tqma93xx-common.inc

# Machine specific options

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE = "tqmaxx-imx-v8-sdimg.wks.in"

WKS_FILE_DEPENDS += "\
    dosfstools-native \
    e2fsprogs-native \
    mtools-native \
    parted-native \
"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    touchscreen \
    usbhost \
    wifi \
"

IMXBOOT_TARGETS ?= "\
    flash_singleboot \
    flash_singleboot_flexspi \
"

#
# bootstream name, construction see imx-boot-tq recipe, do_deploy()
#
MACHINE_WKS_BOOTSTREAM ?= "imx-boot-${MACHINE}-sd.bin-flash_singleboot"

IMAGE_BOOT_FILES = "\
    ${@create_image_boot_dtb_files(d)} \
    ${KERNEL_IMAGETYPE} \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-pcie8997 \
    linux-firmware-sd8997 \
    linux-firmware-usb8997 \
"