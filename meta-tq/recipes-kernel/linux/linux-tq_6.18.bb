SUMMARY = "Linux kernel based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq-6.18.inc

KBRANCH = "TQM-linux-v6.18.y"
SRCREV_machine = "b496f6f0a4b753e1fcb8f9af54804f25f4a0c403"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.28"
