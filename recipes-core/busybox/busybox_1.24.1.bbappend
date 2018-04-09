FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
#SRC_URI += "file://enable-CONFIG_FEATURE_DD_THIRD_STATUS_LINE.cfg"
SRC_URI_append = "file:///opt/tqma57xx-bsp/yocto-bsp-tqma57xx/sources/meta-tq/recipes-core/busybox/enable-CONFIG_FEATURE_DD_THIRD_STATUS_LINE.cfg"

# necessary to prevent above change being reverted during "do_compile"
BUSYBOX_SPLIT_SUID = "0"
