
# set for meta-freescale to enforce mainline kernel / graphic stack
IMX_DEFAULT_BSP = "mainline"
DISTRO_FEATURES += "tq-mainline"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-tq"

PREFERRED_PROVIDER_virtual/egl = "mesa"
PREFERRED_PROVIDER_virtual/libgl = "mesa"
PREFERRED_PROVIDER_virtual/libgles1 = "mesa"
PREFERRED_PROVIDER_virtual/libgles2 = "mesa"
PREFERRED_PROVIDER_virtual/libgbm = "mesa"

# TODO:
PREFERRED_PROVIDER_virtual/kernel_tqma57xx = "linux-ti-tq"
