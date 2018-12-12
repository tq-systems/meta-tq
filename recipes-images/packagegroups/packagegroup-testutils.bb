DESCRIPTION = "test and debugging tools"
LICENSE = "MIT"

inherit packagegroup

#the following builds cross gdb and gdbserver
#DEPENDS += "gdb-cross-${TARGET_ARCH}"

RDEPENDS_${PN} = " \
    perf \
    strace \
    gdbserver \
    "
