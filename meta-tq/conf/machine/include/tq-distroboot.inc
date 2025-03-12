# Common settings for distroboot support (extlinux.conf and boot.scr generation)

TQ_BOOTSCRIPT ?= "boot-blockdev${@bb.utils.contains('DISTRO_FEATURES', 'rauc', '-rauc', '', d)}.scr"

IMAGE_BOOT_FILES += "${TQ_BOOTSCRIPT};boot.scr"
EXTRA_IMAGEDEPENDS += "tq-bootscripts"
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "tq-extlinux"
