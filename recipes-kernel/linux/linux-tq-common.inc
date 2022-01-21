require linux-tq.inc

#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-mainline"

# name the intree defconfig file
KERNEL_TREE_DEFCONFIG ??= ""
