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

# R5 sysfw and U-Boot are built using the Bitbake MultiConfig feature. To avoid
# conflicts and race conditions, in particular when using the "rm_work" class,
# each of the multiconfigs must use a separate TMPDIR (resulting in directory
# names like "tmp-k3r5").
#
# We still need the alternative multiconfigs to use the same DEPLOY_DIR_IMAGE
# as the main configuration, so the value of that variable is expanded using :=
# before modifying TMPDIR.

MAIN_DEPLOY_DIR_IMAGE := "${DEPLOY_DIR_IMAGE}"
DEPLOY_DIR_IMAGE = "${MAIN_DEPLOY_DIR_IMAGE}"

TMPDIR .= "${@ '-${BB_CURRENT_MC}' if d.getVar('BB_CURRENT_MC') else ''}"
