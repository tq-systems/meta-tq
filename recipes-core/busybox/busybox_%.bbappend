FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://enable-BEEP.cfg \
	file://enable-CHAT.cfg \
	file://enable-DEVMEM.cfg \
	file://enable-VERBOSE_USAGE.cfg \
"

# necessary to prevent above change being reverted during "do_compile"
# BUSYBOX_SPLIT_SUID = "0"

