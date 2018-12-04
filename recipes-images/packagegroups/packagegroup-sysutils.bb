DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    rng-tools \
    linuxptp \
    cpufrequtils \
    "
