SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "d6f98b8f38fa752abf7618f04b7f63b81ce956d1"

LINUX_VERSION = "${LINUX_RELEASE}.159"

require linux-tq-6.1.inc
