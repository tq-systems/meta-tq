DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = "\
    cpufrequtils \
    ${@oe.utils.ifelse(d.getVar('PREFERRED_PROVIDER_virtual/bootloader').startswith('u-boot'), 'libubootenv', '')} \
    linuxptp \
    rng-tools \
"
