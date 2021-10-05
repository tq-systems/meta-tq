DESCRIPTION = "tools needed to support different types of file systems and storage"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup


# needed for Rootfs on [Q]SPI
MTD_UTILS_PACKAGES = "\
    mtd-utils \
    mtd-utils-misc \
    mtd-utils-jffs2 \
    mtd-utils-ubifs \
"

RDEPENDS_${PN} = "\
    parted \
    e2fsprogs \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'filesystems-layer', ' f2fs-tools', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'ubi', ' ${MTD_UTILS_PACKAGES}', '', d)} \
"
