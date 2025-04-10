SUMMARY = "Linux kernel based on linux stable 6.1.y for TQ-Systems GmbH i.MX and Layerscape SoM"

KBRANCH = "TQM-linux-v6.1.y"
SRCREV = "01fdc106bde30a3b36c08d49f00557379f0e065c"

LINUX_VERSION = "${LINUX_RELEASE}.129"

require linux-tq-6.1.inc
