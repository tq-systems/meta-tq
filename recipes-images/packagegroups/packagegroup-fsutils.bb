DESCRIPTION = "tools needed to support differnt types if file systems and storege"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    parted \
    e2fsprogs \
    mtd-utils \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'filesystems-layer', ' f2fs-tools', '', d)} \
    "
