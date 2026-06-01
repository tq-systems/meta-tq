# SPDX-License-Identifier: MIT
# Copyright (C) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.

# We default to tar.gz for NFS, wic.zst for SD/eMMC
#
# It is set unconditionally and should be included after SoC includes to override differing
# settings from vendor layers.
TQ_IMAGE_COMPRESSION ?= ".zst"
IMAGE_FSTYPES = "tar.gz wic${TQ_IMAGE_COMPRESSION} wic.bmap"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# For RAUC - demo values only, must be adjusted to actual requirements
WIC_ROOTPART_SIZE ?= "1G"
WIC_DATAPART_SIZE ?= "100M"

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

# Override unwanted dependencies from SoC includes
MACHINE_ESSENTIAL_EXTRA_RDEPENDS = ""

# Do not unconditionally install kernel-image (which pulls in all image types)
RRECOMMENDS:${KERNEL_PACKAGE_NAME}-base = ""

# Create and deploy the vmlinux artifact which gets included into the FIT image
KERNEL_CLASSES += "kernel-fit-extra-artifacts"

# Configure the image.bbclass to depend on the FIT image instead of only
# the kernel to ensure the FIT image is built and deployed with the image
KERNEL_DEPLOY_DEPEND = "linux-tq-fitimage:do_deploy"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. Make the same
# dependencies available to do_image_wic was well.
WKS_FILE_DEPENDS += "${WKS_FILE_DEPENDS_DEFAULT} ${EXTRA_IMAGEDEPENDS}"

# Verified values to generate UBI image - all our SoM that features an
# assembled QSPI NOR use flash types with 64 kiB erase block size and per default
# flash chips with 64 MiB total size. These values can be overwritten per machine
# if needed

# physical erase block size
UBI_PEB_SIZE ?= "65536"
UBI_MINIMUM_IO_UNIT_SIZE ?= "1"
UBI_SUB_PAGE_SIZE ?= "1"
# logical erase block size
UBI_LEB_SIZE ?= "65408"

# If assembled, 64 MB SPI-NOR is default,
# reserve 8 MB for bootloader and env should be enough
# so use 1024 - 128 LEB as default for this case
UBI_MAX_LEB_COUNT ?= "896"

MKUBIFS_ARGS = "\
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --leb-size=${UBI_LEB_SIZE} \
    --max-leb-cnt=${UBI_MAX_LEB_COUNT} \
"

UBINIZE_ARGS = "\
    --peb-size=${UBI_PEB_SIZE} \
    --min-io-size=${UBI_MINIMUM_IO_UNIT_SIZE} \
    --sub-page-size=${UBI_SUB_PAGE_SIZE} \
"

# needs to be set to have it consistent for kernel command line
# Use the expected value of the ubifs filesystem's volume name in the kernel
# and u-boot.
UBI_VOLNAME = "root"

require conf/machine/include/tq-image-cyclonedx.inc
