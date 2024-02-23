# look for files in the layer first
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "\
    file://enable-BEEP.cfg \
    file://enable-DEVMEM.cfg \
    file://enable-VERBOSE_USAGE.cfg \
    file://enable-DD_ALL_FEATURES.cfg \
    file://enable-TFTP_FULL_FEATURES.cfg \
    file://disable-TELNET.cfg \
    file://disable-FTP.cfg \
    file://tq-busybox-options.cfg \
"

# necessary to prevent above change being reverted during "do_compile"
# BUSYBOX_SPLIT_SUID = "0"

