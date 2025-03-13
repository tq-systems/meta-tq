# This file is used for all distro configs based on mainline kernel and / or
# mainline software stacks. To make it usable also with distros based on
# poky-tiny we need to add a specific PREFERRED_PROVIDER for virtual/kernel
# since poky-tiny has a hard assignment for linux-yocto-tiny

# set for meta-freescale to enforce mainline kernel / graphic stack
IMX_DEFAULT_BSP = "mainline"
DISTRO_FEATURES += "tq-mainline"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

PREFERRED_PROVIDER_virtual/egl = "mesa"
PREFERRED_PROVIDER_virtual/libgl = "mesa"
PREFERRED_PROVIDER_virtual/libgles1 = "mesa"
PREFERRED_PROVIDER_virtual/libgles2 = "mesa"
PREFERRED_PROVIDER_virtual/libgbm = "mesa"
