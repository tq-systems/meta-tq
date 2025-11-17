SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "c6ddf3840775c096ad5dab692235e04549af6a36"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.57"
