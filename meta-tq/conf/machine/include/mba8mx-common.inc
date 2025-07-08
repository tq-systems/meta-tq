
require conf/machine/include/tq-machine.inc

# Set Serial console @ UART3
SERIAL_CONSOLES = "115200;ttymxc2"
# recipe imx-atf-tq: boot / debug UART. i.MX8M families have the same IP base addresses
# for UART3
ATF_IMX_BOOT_UART_BASE ?= "0x30880000"

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
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'linux-firmware-ath10k', '', d)}"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('COMBINED_FEATURES', 'bluetooth', 'linux-firmware-qca', '', d)}"

EXTRA_IMAGEDEPENDS += "\
    virtual/imx-cortexm-demos \
"

require tq-distroboot.inc
