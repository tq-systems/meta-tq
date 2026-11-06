SUMMARY = "Linux kernel based on Linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "eafe76502c2b9430f2428f2a4e1c3d3a4a34f33c"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.116"
