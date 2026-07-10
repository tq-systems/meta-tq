SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y"
SRCREV = "44abebb581b2a8cf37f5e224b5049a6da9169172"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.144"
