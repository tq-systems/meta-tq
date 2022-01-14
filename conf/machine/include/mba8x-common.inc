# shared definitions for MBa8x mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

BOARD_DEVICETREE_FILES = "\
    imx8qm-tqma8qm-mba8x.dtb \
    imx8qm-tqma8qm-mba8x-dp.dtb \
    imx8qm-tqma8qm-mba8x-lvds0-tm070jvhg33.dtb \
    imx8qm-tqma8qm-mba8x-lvds1-tm070jvhg33.dtb \
    imx8qm-tqma8qm-mba8x-rpmsg.dtb \
"

BOARD_FIRMWARE_FILES = "\
    dpfw.bin \
    hdmirxfw.bin \
"

require conf/machine/include/tqma8-common.inc

IMAGE_BOOT_FILES ?= "\
    ${KERNEL_IMAGETYPE} \
    ${BOARD_DEVICETREE_FILES} \
    ${BOARD_FIRMWARE_FILES} \
"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "flash_spl flash_spl_flexspi flash_linux_m4"

MACHINE_FEATURES_append = " wifi"
MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-ath10k"

