# This file is used for all distro configs based on imx / fslc-imx kernel and
# vendor software stacks. To make it usable also with distros based on
# poky-tiny we need to add a specific PREFERRED_PROVIDER for virtual/kernel
# since poky-tiny has a hard assignment for linux-yocto-tiny

# variable from meta-freescale
# TODO: QORIQ_DEFAULT_BSP
IMX_DEFAULT_BSP = "nxp"
IMX_DEFAULT_KERNEL = "linux-imx-tq"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-imx-tq"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-imx-tq"

# machine without drm/kms in fslc BSP have no libgbm
PREFERRED_PROVIDER_virtual/libgbm = ""
PREFERRED_PROVIDER_virtual/libgbm:mx8-nxp-bsp = "imx-gpu-viv"
