DESCRIPTION = "tools needed for basic audio support / testing"
LICENSE = "MIT"

# prevent warning related to dynamic package renaming
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    alsa-lib \
    alsa-utils \
    alsa-tools \
    alsa-state \
"
