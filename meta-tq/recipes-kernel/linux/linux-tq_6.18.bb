SUMMARY = "Linux kernel based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq-6.18.inc

KBRANCH = "TQM-linux-v6.18.y"
SRCREV_machine = "b4f29d18f413b74f98e6529211de5feb080356e8"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.59"
