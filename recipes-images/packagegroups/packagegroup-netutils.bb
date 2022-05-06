DESCRIPTION = "tools needed for network support / testing"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    ethtool \
    iperf2 \
    iperf3 \
    iproute2 \
"
