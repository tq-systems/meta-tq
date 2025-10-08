SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.1.inc

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "dda9d3752ca992cc32c29bc93ffec42a4a3c87a9"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.140"
