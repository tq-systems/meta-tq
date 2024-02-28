#@TYPE: Machine
#@NAME: TQ-Systems GmbH TQMLS1088A Module on MBLS10xxa Baseboard
#@SOC: ls1088a
#@DESCRIPTION: Machine configuration for TQ-Systems GmbH TQMLS1088a
#@MAINTAINER: Gregor Herburger <oss@ew.tq-group.com>
#
# By default, this machine will build for the full featured option
# with QSPI-NOR boot
#
# Processor Memory          Boot Device  Configuration
# --------- ---------------	------------ --------------------
# LS1088A   2048MiB / ECC   QSPI-NOR     tqmls1088a_mbls10xxa_tfa_defconfig
# LS1088A   2048MiB / ECC   SD / eMMC    tqmls1088a_mbls10xxa_tfa_defconfig
#

require conf/machine/include/tqmls1088a.inc

# no default assignment here, since we overwrite defaults from qoriq-arm64.inc
SERIAL_CONSOLES = "115200;ttyS1"
SERIAL_CONSOLES_CHECK = "${SERIAL_CONSOLES}"
# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

KERNEL_DEVICETREE ?= " \
    freescale/fsl-ls1088a-tqmls1088a-mbls10xxa.dtb \
"

IMAGE_BOOT_FILES = "\
    mc_app/mc.itb;mc.itb \
    mc-utils/${MC_DPC};${MC_DPC} \
    mc-utils/${MC_DPL};${MC_DPL} \
"

# direct assignment to override empty default from meta-freescale
WKS_FILE = "tqmls1088a-sdimg.wks.in"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"

# Install kernel and devicetree also under /boot inside the rootfs,
# needed for UBIFS use case to prevent extra partition(s) for kernel and dtb.
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
"

UBOOT_CONFIG ?= "tfa"
UBOOT_CONFIG[tfa] = "tqmls1088a_mbls10xxa_tfa_defconfig"

RCWQSPI ??= "default/rcw_1400_3333_5559"
RCWAUTO ??= "default/rcw_1400_3333_5559"

ATF_RCW_VARIANTS ="\
    default/rcw_1400_1133_5506 \
    default/rcw_1400_1133_5559 \
    default/rcw_1400_1133_5577 \
    default/rcw_1400_1143_5506 \
    default/rcw_1400_1143_5559 \
    default/rcw_1400_1143_5577 \
    default/rcw_1400_1144_5506 \
    default/rcw_1400_1144_5559 \
    default/rcw_1400_1144_5577 \
    default/rcw_1400_3333_5506 \
    default/rcw_1400_3333_5559 \
    default/rcw_1400_3333_5577 \
"

#
# mc-utils example configuration for starter kit
# needs to be adjusted for custom boards / setups
#
MC_FOLDER ?= "tqmls1088a-mbls10xxa"
MC_DPC ?= "dpc.0x12_0x0D.dtb"
MC_DPL ?= "dpl-eth.0x12_0x0D.dtb"
