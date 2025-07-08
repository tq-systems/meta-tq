# Common definitions for TQ-Systems GmbH TQMLS1012AL modules

require conf/machine/include/soc-family.inc

# source the machine specific features for RCW, multimedia etc. from
# meta-freescale

MACHINEOVERRIDES =. "fsl-lsch2:ls1012a:tqmlsx:tqmls1012al:"

# TQ-Systems U-Boot and kernel has to be set before qoriq-arm64.inc
# to allow weak settings
#

require conf/machine/include/tqmxx.inc

PREFERRED_PROVIDER_u-boot:tqmls1012al ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/bootloader:tqmls1012al ?= "u-boot-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "linux-imx-tq"

require conf/machine/include/qoriq-arm64.inc

require conf/machine/include/arm/armv8a/tune-cortexa53.inc

# Define the default image type, overwrite default from qoriq-arm64.inc
KERNEL_IMAGETYPE = "Image.gz"

UBOOT_ENTRYPOINT:ls1012a = "0x80008000"

# Machine specific options
#

MACHINE_EXTRA_RRECOMMENDS += "\
    ppfe-firmware \
    udev-extraconf \
"

# provide info about machine's capabilities
# OP-TEE is currently untested, so we don't enable the feature for now
MACHINE_FEATURES:append = "\
    ext2 \
    pci \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

# Image Options

# Add packages we want to be deployed, even when they are not part of rootfs
EXTRA_IMAGEDEPENDS += "\
    ppfe-firmware \
    qoriq-atf \
    rcw \
"

# default RCW with 1 GHz core frequency
RCWQSPI ?= "default/rcw_1000_sd"

RCW_FOLDER:${MACHINE} = "tqmls1012al"
RCW_SUFFIX:${MACHINE} = ".bin"
PLATFORM:${MACHINE} = "tqmls1012al_512mb"
PLATFORM_ADDITIONAL_TARGET:${MACHINE} = "tqmls1012al_1gb"

WKS_FILE = "tqmls1012al-sdimg.wks"

# Common image configuration
require conf/machine/include/tq-image.inc
