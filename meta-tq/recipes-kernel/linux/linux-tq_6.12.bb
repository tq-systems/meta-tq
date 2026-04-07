SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "7525b1b56ba977968b834891eab63bb7508a5ad1"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.80"
