SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "02eecc5d220d89f6078d26a12ba658a894332962"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.87"
