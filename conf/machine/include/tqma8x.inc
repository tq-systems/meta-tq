# Common definitions for TQ-Group TQMa8x b0 modules

MACHINEOVERRIDES =. "tqma8x:"

require conf/machine/include/tqma8x-common.inc

# force to use older version
PREFERRED_VERSION_u-boot-imx-tq_${MACHINE} = "2019.04"
PREFERRED_VERSION_imx-sc-firmware_${MACHINE} = "1.3.1"
PREFERRED_VERSION_imx-atf_${MACHINE} = "2.0"
PREFERRED_VERSION_imx-seco_${MACHINE} = "2.5.6"
