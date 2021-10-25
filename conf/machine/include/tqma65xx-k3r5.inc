# Common definitions for R5 bootloader on TQMa65xx

require conf/machine/include/k3r5.inc

# See https://git.ti.com/cgit/k3-image-gen/k3-image-gen/tree/README.md
# for information on SYSFW configurations
#
# The "evm" configuration should work for most usecases. For more advanced
# setups, in particular those using the R5 core, it may be necessary to create
# a modified configuration. Unfortunately, meta-ti currently lacks a convenient
# way to do so.

SYSFW_SOC = "am65x"
SYSFW_CONFIG = "evm"
SYSFW_SUFFIX = "gp"
SYSFW_SYMLINK = ""

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-tq"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-tq"

# R5 sysfw and U-Boot are build using the Bitbake MultiConfig feature. To avoid
# conflicts and race conditions, in particular when using the "rm_work" class,
# each of the multiconfigs must use a separate TMPDIR (resulting in directory
# names like "tmp-k3r5" and "tmp-k3r5-sr2").
#
# We still need the alternative multiconfigs to use the same DEPLOY_DIR_IMAGE
# as the main configuration, so the value of that variable is expanded using :=
# before modifying TMPDIR.

MAIN_DEPLOY_DIR_IMAGE := "${DEPLOY_DIR_IMAGE}"
DEPLOY_DIR_IMAGE = "${MAIN_DEPLOY_DIR_IMAGE}"

TMPDIR .= "${@ '-${BB_CURRENT_MC}' if d.getVar('BB_CURRENT_MC') else ''}"
