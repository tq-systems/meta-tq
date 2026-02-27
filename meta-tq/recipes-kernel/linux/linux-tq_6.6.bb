SUMMARY = "Linux kernel based on linux stable 6.6.y for TQ-Systems GmbH i.MX and Layerscape SoM"

require linux-tq-6.6.inc

KBRANCH = "TQM-linux-v6.6.y"
SRCREV = "fe1992683f75f63cf304ebc3a415733b9fd40f6b"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.127"
