SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "08e377c227bff390dad0e83805a6ba163fdd0a64"

LINUX_VERSION = "${LINUX_RELEASE}.150"

require linux-tq-6.1.inc
