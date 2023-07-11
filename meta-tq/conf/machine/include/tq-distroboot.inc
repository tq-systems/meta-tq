# Common settings for distroboot support (extlinux.conf and boot.scr generation)

IMAGE_BOOT_FILES += "boot-blockdev.scr;boot.scr"
EXTRA_IMAGEDEPENDS += "tq-bootscripts"
MACHINE_EXTRA_RRECOMMENDS += "tq-extlinux"
