DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    cpufrequtils \
    linuxptp \
    rng-tools \
    u-boot-fw-utils \
"
