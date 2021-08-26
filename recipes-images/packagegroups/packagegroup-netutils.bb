DESCRIPTION = "tools needed for network support / testing"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    ethtool \
    iproute2 \
"
