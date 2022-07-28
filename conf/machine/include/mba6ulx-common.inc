# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for
# TQMa6ULx/TQMa6ULLx/TQMa6ULxL/TQMa6ULLxL SOM

SERIAL_CONSOLES = "115200;ttymxc0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# install also under /boot inside the rootfs, needed for UBIFS use case
# and because extra FAT boot partition on SD / e-MMC shall be removed
IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"

# Files are needed in IMAGE_BOOT_FILES because we use an extra partition for
# backward compatibility.
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

WKS_FILE = "tqmaxx-sdimg.wks"

MACHINE_FEATURES += "\
    bluetooth \
    display \
    wifi \
"

MACHINE_EXTRA_RRECOMMENDS += "\
    linux-firmware-ath10k \
    linux-firmware-qca \
"
