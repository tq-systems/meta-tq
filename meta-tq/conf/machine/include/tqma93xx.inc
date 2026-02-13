# Common definitions for TQ-Systems GmbH TQMa93xxLA / TQMa93xxCA

MACHINEOVERRIDES =. "imx:nxp-ahab:mx9:mx93:tqma93xx:"

IMX_SOC_REV ?= "A1"

ATF_PLATFORM = "imx93"

require conf/machine/include/tqma91xx_93xx.inc

MACHINE_FEATURES += "\
    npu \
"

PREFERRED_PROVIDER_virtual/kernel:mx93-nxp-bsp ?= "linux-imx-tq"
# At the moment we support only these forks:
PREFERRED_PROVIDER_u-boot:mx93-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx93-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx93-generic-bsp = "bin"
