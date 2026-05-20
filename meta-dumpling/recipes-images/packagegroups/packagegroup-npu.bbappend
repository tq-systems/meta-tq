MX8MP_PKGS_LIST = " \
    tensorflow-lite \
    tensorflow-lite-vx-delegate \
"

RDEPENDS:${PN}:mx8mp-nxp-bsp += " ${MX8MP_PKGS_LIST}"

MX93_PKGS_LIST = " \
    ethos-u-firmware \
    ethos-u-vela \
    ethos-u-driver-stack \
    tensorflow-lite \
    tensorflow-lite-ethosu-delegate \
"

RDEPENDS:${PN}:mx93-nxp-bsp += " ${MX93_PKGS_LIST}"

MX95_PKGS_LIST = " \
    neutron \
    tensorflow-lite \
    tensorflow-lite-neutron-delegate \
"

RDEPENDS:${PN}:mx95-nxp-bsp += "${MX95_PKGS_LIST}"
