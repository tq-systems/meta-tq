DESCRIPTION = "test and debugging tools"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

#the following builds cross gdb and gdbserver
#DEPENDS += "gdb-cross-${TARGET_ARCH}"

# note: we could also use packagegroup-core-tools-debug
# but this would install a full fledged gbd on target
# maybe perl comes also in as a dependency for mtrace
# and that's not what we want

ALSA_RDEPENDS = "\
    alsa-utils-alsabat \
    alsa-utils-speakertest \
"

RDEPENDS:${PN} = "\
    ${@bb.utils.contains('COMBINED_FEATURES', 'alsa', ' ${ALSA_RDEPENDS}', '', d)} \
    atop \
    dstat \
    evtest \
    fb-test \
    gdbserver \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', ' glmark2', '', d)} \
    htop \
    iotop \
    mc \
    memtester \
    nano \
    strace \
    stressapptest \
    stress-ng \
"

#
# although we should use openssh, prepare to use the packagegroup with dropbear
# sftp is needed for IDE like QtCreator
#
RRECOMMENDS:${PN} = "\
    openssh-sftp-server \
"

# note: kmscube is only available if we have opengl and if virtual/libgbm
# is built. Since this is at least not the case for TQMa6x with vendor graphic
# stack we need this ugly construct
RRECOMMENDS:${PN} += "\
    ${@oe.utils.ifelse( \
        bb.utils.contains('DISTRO_FEATURES', 'opengl', True, False, d) and \
	    d.getVar('PREFERRED_PROVIDER_virtual/libgbm') != "", \
        'kmscube', '', \
    )} \
"

