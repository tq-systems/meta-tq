DESCRIPTION = "tools needed for NPU support"
LICENSE = "MIT"

# allow depend on machine specific packages
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# Default dependencies
RDEPENDS:${PN} ?= "\
"
