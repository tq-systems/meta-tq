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

RDEPENDS_${PN} = " \
    perf \
    strace \
    gdbserver \
    mc \
    ${@bb.utils.contains('MACHINE_FEATURES', 'alsa', ' alsa-utils-speakertest', '', d)} \
    "
