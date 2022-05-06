DESCRIPTION = "tools needed for CAN support / testing"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    can-utils \
    can-utils-cantest \
    iproute2 \
"
