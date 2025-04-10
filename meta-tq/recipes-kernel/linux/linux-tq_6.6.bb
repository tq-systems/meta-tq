SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y"
SRCREV = "599261e90613cc25c09063324c138a64bd59b63b"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.80"
