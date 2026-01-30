require conf/machine/include/tq-machine.inc

# direct assign to SERIAL_CONSOLES to prevent wrong defaults and not using
# deprecated SERIAL_CONSOLE
SERIAL_CONSOLES = "115200;ttyLP6"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# assign UART to use by TF-A during boot and for emergency
ATF_IMX_BOOT_UART_BASE ?= "0x44380000"

UBOOT_CONFIG ??= "\
    sd \
"

KERNEL_DEVICETREE = ""

KERNEL_DEVICETREE_linux-imx-tq = "\
    freescale/imx95-tqma9596la-mba95xxca.dtb \
    freescale/imx95-tqma9596la-mba95xxca-lvds-tm070jvhg33.dtb \
    freescale/imx95-tqma9596la-mba95xxca-lvds-tm070jvhg33.dtbo \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    touchscreen \
    wifi \
"

MACHINE_FIRMWARE += "\
    firmware-nxp-wifi-nxp8801-sdio \
    firmware-nxp-wifi-nxp8987-sdio \
    firmware-nxp-wifi-nxp8997-pcie \
    firmware-nxp-wifi-nxp8997-sdio \
    firmware-nxp-wifi-nxp9098-pcie \
    firmware-nxp-wifi-nxp9098-sdio \
    firmware-nxp-wifi-nxpiw416-sdio \
    firmware-nxp-wifi-nxpiw610-sdio \
    firmware-nxp-wifi-nxpiw612-sdio \
"

## MACHINE_EXTRA_RRECOMMENDS += "kernel-module-nxp-wlan"

#
# fitImage Settings:
# Kernel image offset in RAM:               16 MiB
# reserved space for uncompressed kernel:   48 MiB
# fdt/dtb offset in RAM:                    64 MiB
# dtbo offset in RAM:                       65 MiB
# init(rd) offset in RAM:                   80 MiB
# (loadaddr                                512 MiB)
# (optee offset                       256 + 96 MiB)
# (optee size                               32 MiB)
UBOOT_ENTRYPOINT = "0x91000000"
UBOOT_LOADADDRESS = "0x91000000"
UBOOT_DTB_LOADADDRESS = "0x94000000"
UBOOT_DTBO_LOADADDRESS = "0x9410000"
UBOOT_RD_LOADADDRESS = "0x95000000"
UBOOT_RD_ENTRYPOINT = "0x95000000"

UBOOT_EXTLINUX_FDTDIR ?= ""

UBOOT_EXTLINUX_LABELS ?= " \
    default \
"

EXTRA_EXTLINUX_LABELS ?= ""

EXTRA_EXTLINUX_LABELS:mx95-nxp-bsp = " \
    lvds-tm070jdhg30 \
"

UBOOT_EXTLINUX_LABELS += "${EXTRA_EXTLINUX_LABELS}"

UBOOT_EXTLINUX_DEFAULT_LABEL ?= "default"
UBOOT_EXTLINUX_KERNEL_IMAGE_default = "../fitImage"
UBOOT_EXTLINUX_MENU_DESCRIPTION_default = "${DISTRO_NAME} on ${MACHINE} (default)"

UBOOT_EXTLINUX_KERNEL_IMAGE_lvds-tm070jdhg30 = \
    "../fitImage#${FIT_CONF_PREFIX}imx95-tqma9596cla-mba95xxca.dtb#${FIT_CONF_PREFIX}imx95-tqma9596cla-mba95xxca-lvds-tm070jvhg33.dtbo"
UBOOT_EXTLINUX_MENU_DESCRIPTION_lvds-tm070jdhg30 = \
    "${DISTRO_NAME} on ${MACHINE} (LVDS display: TM070JDHG30)"

require conf/machine/include/tq-distroboot.inc
