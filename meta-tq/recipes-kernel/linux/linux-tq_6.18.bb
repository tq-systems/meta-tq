SUMMARY = "Linux kernel based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq-6.18.inc

KBRANCH = "TQM-linux-v6.18.y"
SRCREV_machine = "1991e1d591ce2535a3d51bb16004390d3f2dbcba"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.44"
