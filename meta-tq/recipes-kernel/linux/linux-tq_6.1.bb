SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "3ffeb344d228ace513e1311fd46a38bdc300dafe"

LINUX_VERSION = "${LINUX_RELEASE}.164"

require linux-tq-6.1.inc
