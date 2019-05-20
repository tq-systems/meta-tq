DESCRIPTION = "tools needed for basic audio support / testing"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    alsa-lib \
    alsa-utils \
    alsa-tools \
    alsa-state \
"
