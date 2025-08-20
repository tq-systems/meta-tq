SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y"
SRCREV = "5329749f70899a39d657d8ba289da12a3a3a86d4"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.102"
