# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for
# TQMa6ULx/TQMa6ULLx/TQMa6ULxL/TQMa6ULLxL SOM

SERIAL_CONSOLES = "115200;ttymxc0"

# disable virtual console support for this board and limit getty to SERIAL_CONSOLES only
USE_VT = "0"

# Files are needed in IMAGE_BOOT_FILES because we use an extra partition for
# backward compatibility.
IMAGE_BOOT_FILES = "\
    ${KERNEL_IMAGETYPE};linuximage \
    ${KERNEL_DEVICETREE} \
"

MACHINE_FEATURES += "\
    bluetooth \
    display \
    wifi \
"

# install kernel and devicetree also under /boot inside the rootfs,
# needed for UBIFS use case and perpare removal of extra FAT boot partition
# on SD / e-MMC
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
    linux-firmware-ath10k \
    linux-firmware-qca \
"

WKS_FILE = "tqmaxx-sdimg.wks"

WKS_FILE_DEPENDS += "\
    dosfstools-native \
    e2fsprogs-native \
    mtools-native \
    parted-native \
"
