PREFERRED_PROVIDER_virtual/kernel:tqma335x = "linux-ti-tq"
PREFERRED_PROVIDER_virtual/kernel:tqma57xx = "linux-ti-tq"

# Workaround for incorrect SIGGEN_EXCLUDERECIPES_ABISAFE in meta-ti
SIGGEN_EXCLUDERECIPES_ABISAFE:remove = "mesa-pvr"

# Add MLO SD/e-MMC image generation for SOM based on TI SOC
# This image is a stripped down version of the normal .wic, consisting
# only of partition table, boot VFAT containing bootloader image(s)
# and environment for bootloader.

IMAGE_CLASSES += "image_type_bootonly"
IMAGE_FSTYPES += "wic.bootonly"
