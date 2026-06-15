SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y"
SRCREV = "3ca1cf9f56e52f67371e508e2e170b7ed10072ec"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.142"
