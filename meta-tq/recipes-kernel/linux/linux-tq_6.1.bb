SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "0557bc967fd610fd350c5817c47829d5a9e20e26"

LINUX_VERSION = "${LINUX_RELEASE}.132"

require linux-tq-6.1.inc
