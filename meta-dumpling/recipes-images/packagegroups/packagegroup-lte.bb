DESCRIPTION = "tools needed for LTE support with Quectel EC21-E module"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    ppp \
    quectel-ppp \
"
