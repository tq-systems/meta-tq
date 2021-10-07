DESCRIPTION = "tools for wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = "\
    wpa-supplicant \
    iw \
    wireless-regdb-static \
"

