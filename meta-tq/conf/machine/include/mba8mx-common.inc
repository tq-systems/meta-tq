
require mba8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

CORTEXM_DEMOS = "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

IMAGE_BOOT_FILES = "\
    ${CORTEXM_DEMOS} \
    ${KERNEL_IMAGETYPE} \
"

MACHINE_FEATURES += "\
    bluetooth \
    camera \
    screen \
    touchscreen \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
"

EXTRA_IMAGEDEPENDS += "\
    virtual/imx-cortexm-demos \
"

require tq-distroboot.inc
