DESCRIPTION = "tools needed to test basic hw support"
LICENSE = "MIT"

# allow depend on machine specific packages
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
    cpufrequtils \
    ${@oe.utils.ifelse(d.getVar('PREFERRED_PROVIDER_virtual/bootloader').startswith('u-boot'), 'libubootenv-bin', '')} \
    linuxptp \
    procps-sysctl \
    rng-tools \
"
