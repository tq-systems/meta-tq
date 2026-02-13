# Common definitions for TQ-Systems GmbH TQMa91xxLA / TQMa91xxCA

MACHINEOVERRIDES =. "imx:nxp-ahab:mx9:mx91:tqma91xx:"

IMX_SOC_REV ?= "A0"

ATF_PLATFORM = "imx91"

require conf/machine/include/tqma91xx_93xx.inc

PREFERRED_PROVIDER_virtual/kernel:mx91-nxp-bsp ?= "linux-imx-tq"
# At the moment we support only these forks:
PREFERRED_PROVIDER_u-boot:mx91-generic-bsp ?= "u-boot-imx-tq"
PREFERRED_PROVIDER_virtual/bootloader:mx91-generic-bsp ?= "u-boot-imx-tq"
UBOOT_SUFFIX:mx91-generic-bsp = "bin"
