DESCRIPTION = "security related packages packages"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

OPTEE_PACKAGES = " \
    optee-client \
    optee-test \
"

RDEPENDS:${PN} += " \
    ${@bb.utils.contains("MACHINE_FEATURES", "optee", "${OPTEE_PACKAGES}", "", d)} \
"
