# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc2"

IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE} \
    ${BOARD_DEVICETREE_FILES} \
"

MACHINE_FEATURES:append = "\
    camera \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS:append = " linux-firmware-ath10k"

IMAGE_BOOT_FILES += "\
    hello_world.bin \
    rpmsg_lite_pingpong_rtos_linux_remote.bin \
"

WKS_FILE_DEPENDS:append = "\
    virtual/imx-cortexm-demos \
"
