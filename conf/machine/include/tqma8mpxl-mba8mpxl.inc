# Common definitions for TQ-Group MBa8MPxL baseboard for TQMa8MPxL modules

# Set u-boot DTB
UBOOT_DTB_NAME = "imx8mp-mba8mpxl.dtb"

BOARD_DEVICETREE_FILES = "\
    imx8mp-mba8mpxl.dtb \
"

KERNEL_DEVICETREE = ""

require conf/machine/include/tqma8-common.inc

# Set Serial console
SERIAL_CONSOLES = "115200;ttymxc3"

IMAGE_BOOT_FILES ?= "\
	${KERNEL_IMAGETYPE} \
	${BOARD_DEVICETREE_FILES} \
	"

# TODO:
# IMXBOOT_TARGETS ?= "flash_spl_uboot flash_evk_flexspi"
IMXBOOT_TARGETS ?= "flash_spl_uboot"

# TODO:
#IMAGE_BOOT_FILES += " \
#    hello_world.bin \
#    rpmsg_lite_pingpong_rtos_linux_remote.bin \
#"
#
#WKS_FILE_DEPENDS_append = "\
#	cortexm-demos \
#	"
