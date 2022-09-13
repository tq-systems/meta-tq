DESCRIPTION = "tools for wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    wpa-supplicant \
    hostapd \
    iw \
    wireless-regdb-static \
"

