SUMMARY = "Reset Configuration Word"
DESCRIPTION = "Reset Configuration Word - hardware boot-time parameters for the QorIQ targets"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=45a017ee5f4cfe64b1cddf2eb06cffc7"

DEPENDS += "swap-file-endianess-native tcl-native"

inherit deploy

SRCREV = "9f3db9ede3a5edec257c44c804131c168f281b97"
SRCBRANCH = "TQMLS10xxA-rcw"

SRCREV_tqmls1028a = "a288315076746535139d947dd6a0f2d858b39000"
SRCBRANCH_tqmls1028a = "TQMLS1028A-rcw-LSDK-18.12"

SRC_URI = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

export PYTHON = "${USRBINPATH}/python2"

RCW_BOARD = "${MACHINE}"
RCW_BOARD_tqmls1028a = "tqc"

EXTRA_OEMAKE += "BOARDS=${RCW_BOARD}"

do_install () {
    oe_runmake DESTDIR=${D}/boot/rcw/ install
}

do_deploy () {
    install -d ${DEPLOYDIR}/rcw
    cp -r ${D}/boot/rcw/* ${DEPLOYDIR}/rcw/
    if [ -f  ${DEPLOYDIR}/rcw/${RCW_BOARD}/${RCWSD} ]; then
        ln -sfT rcw/${RCW_BOARD}/${RCWSD} ${DEPLOYDIR}/rcw-sdboot.bin
    fi
    if [ -f  ${DEPLOYDIR}/rcw/${RCW_BOARD}/${RCWEMMC} ]; then
        ln -sfT rcw/${RCW_BOARD}/${RCWEMMC} ${DEPLOYDIR}/rcw-emmcboot.bin
    fi
    if [ -f  ${DEPLOYDIR}/rcw/${RCW_BOARD}/${RCWQSPI} ]; then
        ln -sfT rcw/${RCW_BOARD}/${RCWQSPI} ${DEPLOYDIR}/rcw-qspiboot.bin
    fi
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_MACHINE = "(fsl-lsch2|fsl-lsch3)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
