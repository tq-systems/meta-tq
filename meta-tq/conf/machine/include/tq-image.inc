# SPDX-License-Identifier: MIT
# Copyright (C) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# We will have tar.gz for NFS, wic for SD/eMMC
#
# It is set unconditionally and should be included after SoC includes to override differing
# settings from vendor layers.
IMAGE_FSTYPES = "tar.gz wic wic.bmap"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# Always build bootloader and kernel
#
# Listing the kernel here is primarily needed for machines that include the
# kernel in IMAGE_BOOT_FILES. For other machines it would be built by default
# through KERNEL_DEPLOY_DEPEND anyways even if it is not referenced by the
# root filesystem.
EXTRA_IMAGEDEPENDS += " \
    virtual/bootloader \
    virtual/kernel \
"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. Make the same
# dependencies available to do_image_wic was well.
WKS_FILE_DEPENDS += "${WKS_FILE_DEPENDS_DEFAULT} ${EXTRA_IMAGEDEPENDS}"
