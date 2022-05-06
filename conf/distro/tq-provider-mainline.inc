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

# Support building some machines without having neither mainline kernel support
# nor linux-yocto-tiny
# TODO: remove as soon we have a linux-tq recipe for the machine
PREFERRED_PROVIDER_virtual/kernel:tqma57xx ?= "linux-ti-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1012al ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1028a ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1088a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmlx2160a ?= "linux-lsdk-tq"
