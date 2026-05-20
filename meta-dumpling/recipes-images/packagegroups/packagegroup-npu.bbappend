# Add runtime dependencies for i.MX8M Plus tensorflow delegate
# RDEPENDS:${PN}:append:mx8mp-nxp-bsp  = " tensorflow-lite-vx-delegate"

MX93_PKGS_LIST = " \
    ethos-u-firmware \
    ethos-u-vela \
    ethos-u-driver-stack \
    tensorflow-lite \
    tensorflow-lite-ethosu-delegate \
"

RDEPENDS:${PN}:mx93-nxp-bsp += " ${MX93_PKGS_LIST}"
