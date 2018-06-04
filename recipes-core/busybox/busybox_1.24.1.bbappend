FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://enable-CONFIG_FEATURE_DD_THIRD_STATUS_LINE.cfg"

# necessary to prevent above change being reverted during "do_compile"
BUSYBOX_SPLIT_SUID = "0"
