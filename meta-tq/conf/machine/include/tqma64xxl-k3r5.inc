# Common definitions for R5 bootloader on TQMa64xxL

MACHINEOVERRIDES =. "tqma64xxl-k3r5:"

# From meta-ti
require conf/machine/include/k3r5.inc

# The tiboot3.bin filename is built from these variables. The sysfw
# configuration can be adjusted in the U-Boot source.
SYSFW_SOC = "am64x"
SYSFW_CONFIG = "evm"
SYSFW_SUFFIX = "gp"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-tq"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-tq"
