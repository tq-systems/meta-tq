SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "eb669d55f26bdab7e3d39314d79a9aa63ea385d7"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.61"
