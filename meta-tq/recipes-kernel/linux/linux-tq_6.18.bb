SUMMARY = "Linux kernel based on linux stable 6.18.y for TQ-Systems SoMs"

require linux-tq-6.18.inc

KBRANCH = "TQM-linux-v6.18.y"
SRCREV_machine = "0fff7284e2c7ceec3d282a1771398e0983ffa78f"

# LINUX_VERSION must match version from Makefile
LINUX_VERSION = "${LINUX_RELEASE}.34"
