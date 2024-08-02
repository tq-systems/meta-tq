# Common definitions for TQ-Systems GmbH TQMa6x modules on MBa6x

SERIAL_CONSOLES = "115200;ttymxc1"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"

MACHINE_FEATURES += "\
    bluetooth \
    screen \
    touchscreen \
    wifi \
"

# rootfs with kernel modules, device tree and kernel image
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"

WKS_FILE = "tqmaxx-imx-v7-sdimg.wks.in"

# allow to easy override for different configuration
MACHINE_WKS_BOOTSTREAM ?= "u-boot-with-spl-${MACHINE}.imx-sd"
