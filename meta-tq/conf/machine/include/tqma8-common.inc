# Common definitions for all TQ-Systems GmbH TQMa8x / TQMa8Xx / TQMa8Mx /
# TQMa8MxML / TQMa8MxNL / TQMa8MPxL modules

require conf/machine/include/tqmxx.inc

MACHINE_FEATURES += "\
    alsa \
    ext2 \
    ext3 \
    rtc \
    serial \
    ubi \
    usbhost \
    vfat \
"

# This should be added to 'MACHINE_FEATURES` after inclusion of 'imx-base.inc'
MACHINE_FEATURES += "vpu"
MACHINE_FEATURES:remove:mx8mn-generic-bsp = "vpu"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"

KERNEL_IMAGETYPE = "Image"

SPL_BINARY = "spl/u-boot-spl.bin"

PREFERRED_PROVIDER_u-boot:mx8-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx8-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx8-generic-bsp = "bin"
UBOOT_SUFFIX:pn-u-boot-imx-tq = "bin"
IMX_DEFAULT_BOOTLOADER ?= "u-boot-imx-tq"


PREFERRED_PROVIDER_virtual/imx-cortexm-demos ?= "tqma8-cortexm-demos"

python() {
    wks_type = ""

    if bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
        wks_type = "-verity"
    if bb.utils.contains("DISTRO_FEATURES", "rauc", True, False, d):
        if not bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
            wks_type = "-rauc-ab"
        else:
            bb.warn("DISTRO_FEATURES contains 'rauc' and 'secure', using WKS_FILE selected by distro feature 'secure'")

    d.setVar("WKS_FILE", "tqmaxx-imx-v8-sdimg%s.wks.in" % wks_type)
}

# Common image configuration
require conf/machine/include/tq-image.inc
