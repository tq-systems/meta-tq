DESCRIPTION = "tools needed to support differnt types if file systems and storege"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup


# needed for Rootfs on QSPI
MTD_UTILS_PACKAGES = "\
    mtd-utils \
    mtd-utils-misc \
    mtd-utils-jffs2 \
    mtd-utils-ubifs \
"

IMAGE_INSTALL_append = "\
    ${@bb.utils.contains('MACHINE_FEATURES', 'ubi', ' ${MTD_UTILS_PACKAGES}', '', d)} \
"

RDEPENDS_${PN} = " \
    parted \
    e2fsprogs \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'filesystems-layer', ' f2fs-tools', '', d)} \
    "
