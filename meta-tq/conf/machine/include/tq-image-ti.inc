# SPDX-License-Identifier: MIT
# Copyright (C) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# Add MLO SD/e-MMC image generation for SOM based on TI SOC
# This image is a stripped down version of the normal .wic, consisting
# only of partition table, boot VFAT containing bootloader image(s)
# and environment for bootloader.
IMAGE_CLASSES += "image_type_bootonly"

IMAGE_FSTYPES += "wic.bootonly"

# Disable meta-ti's dynamic generation of KERNEL_DEVICETREE
KERNEL_DEVICETREE_PREFIX = ""

# Disable ti_ prefix in FIT nodes (Yocto default; set to 1 by meta-ti)
KERNEL_DTBVENDORED = "0"
