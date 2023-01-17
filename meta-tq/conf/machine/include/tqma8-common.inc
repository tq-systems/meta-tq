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

MACHINE_FEATURES += "vpu"
MACHINE_FEATURES:remove:mx8mn-generic-bsp = "vpu"

PREFERRED_PROVIDER_u-boot-fw-utils:mx8-generic-bsp ?= "u-boot-fw-utils-imx-tq"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"

KERNEL_IMAGETYPE = "Image"

SPL_BINARY = "spl/u-boot-spl.bin"

PREFERRED_PROVIDER_u-boot:mx8-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx8-generic-bsp ?= "u-boot-imx-tq"

PREFERRED_PROVIDER_virtual/imx-cortexm-demos ?= "tqma8-cortexm-demos"

# provide dt name handling to prevent a lot of copy and paste
# in kernel prefix where to find dtb

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE = "tqmaxx-imx-v8-sdimg.wks.in"
