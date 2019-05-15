DESCRIPTION = "tools needed for network support / testing"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    iproute2 \
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'openembedded-layer', ' can-utils', '', d)} \
    "
