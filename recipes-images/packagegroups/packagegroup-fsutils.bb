DESCRIPTION = "tools needed to support differnt types if file systems and storege"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    parted \
    e2fsprogs \
    mtd-utils \
    mtd-utils-jffs2 \
    mtd-utils-ubifs \
    mtd-utils-misc \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'filesystems-layer', ' f2fs-tools', '', d)} \
    "
