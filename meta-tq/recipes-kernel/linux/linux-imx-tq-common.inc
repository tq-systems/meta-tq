require linux-tq.inc

#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-imx linux-imx-tq"

#
# optional stuff from meta-freescale
#
require ${@bb.utils.contains("BBFILE_COLLECTIONS", "freescale-layer", 'linux-imx-tq-conditional.inc', '', d)}

# name the intree defconfig file
KERNEL_TREE_DEFCONFIG ??= ""
