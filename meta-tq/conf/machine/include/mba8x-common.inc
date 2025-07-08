# shared definitions for MBa8x mainboard
require conf/machine/include/tqma8x.inc
require conf/machine/include/tq-machine.inc

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE = ""

KERNEL_DEVICETREE_linux-imx-tq = "\
    freescale/imx8qm-tqma8qm-mba8x.dtb \
    freescale/imx8qm-tqma8qm-mba8x-lvds0-g133han01.dtb \
    freescale/imx8qm-tqma8qm-mba8x-lvds0-tm070jvhg33.dtb \
    freescale/imx8qm-tqma8qm-mba8x-lvds1-g133han01.dtb \
    freescale/imx8qm-tqma8qm-mba8x-lvds1-tm070jvhg33.dtb \
    freescale/imx8qm-tqma8qm-mba8x-rpmsg.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

BOARD_FIRMWARE_FILES = "\
    dpfw.bin \
    hdmirxfw.bin \
"

IMAGE_BOOT_FILES = "\
    ${@create_image_boot_dtb_files(d)} \
    ${BOARD_FIRMWARE_FILES} \
    ${KERNEL_IMAGETYPE} \
"

# TODO: handle optee as soon as supported
IMXBOOT_TARGETS ?= "\
    flash_linux_m4 \
    flash_spl \
    flash_spl_flexspi \
"

MACHINE_FEATURES += "\
    bluetooth \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"
