SUMMARY = "Linux kernel based on linux stable 6.12.y for TQ-Systems SoMs"

require linux-tq-6.12.inc

KBRANCH = "TQM-linux-v6.12.y"
SRCREV_machine = "d710f515a9ed82d6385750d4bd770efcea6f9999"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.64"
