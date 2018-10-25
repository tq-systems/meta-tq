# shared definitions for MBa8QX mainboard

# We have to disable SERIAL_CONSOLE due to auto-serial-console
SERIAL_CONSOLES = "115200;ttyLP1"

# we do not want to have getty running on tty1 as we run
# auto-serial-console there
USE_VT = "0"

# TODO: move to bsp layer

IMAGE_INSTALL_append = " memtester"
IMAGE_INSTALL_append = " usbutils"
IMAGE_INSTALL_append = " mmc-utils"
IMAGE_INSTALL_append = " i2c-tools"
IMAGE_INSTALL_append = " pciutils"
IMAGE_INSTALL_append = " ethtool"
IMAGE_INSTALL_append = " e2fsprogs"
# IMAGE_INSTALL_append = " iperf2"
IMAGE_INSTALL_append = " iperf3"
