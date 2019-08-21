DESCRIPTION = "test and debugging tools"
LICENSE = "MIT"

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
    "
