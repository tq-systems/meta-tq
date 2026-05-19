SUMMARY = "Linux kernel based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq-6.18.inc

KBRANCH = "TQM-linux-v6.18.y"
SRCREV_machine = "06857733b0fb47ce8fa96a138c85f9e6d4165246"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.32"
