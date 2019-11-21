SUMMARY = "Reset Configuration Word"
DESCRIPTION = "Reset Configuration Word - hardware boot-time parameters for the QorIQ targets"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=45a017ee5f4cfe64b1cddf2eb06cffc7"

DEPENDS += "swap-file-endianess-native tcl-native"

inherit deploy

SRCREV = "9f3db9ede3a5edec257c44c804131c168f281b97"
SRCBRANCH = "TQMLS10xxA-rcw"

SRC_URI = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"

S = "${WORKDIR}/git"

export PYTHON = "${USRBINPATH}/python2"

do_install () {
    oe_runmake BOARDS=${MACHINE} DESTDIR=${D}/boot/rcw/ install
}

do_deploy () {
    install -d ${DEPLOYDIR}/rcw
    cp -r ${D}/boot/rcw/* ${DEPLOYDIR}/rcw/
    if [ -f  ${DEPLOYDIR}/rcw/${MACHINE}/${RCWSD} ]; then
        ln -sfT rcw/${MACHINE}/${RCWSD} ${DEPLOYDIR}/rcw-sdboot.bin
    fi
    if [ -f  ${DEPLOYDIR}/rcw/${MACHINE}/${RCWEMMC} ]; then
        ln -sfT rcw/${MACHINE}/${RCWEMMC} ${DEPLOYDIR}/rcw-emmcboot.bin
    fi
    if [ -f  ${DEPLOYDIR}/rcw/${MACHINE}/${RCWQSPI} ]; then
        ln -sfT rcw/${MACHINE}/${RCWQSPI} ${DEPLOYDIR}/rcw-qspiboot.bin
    fi
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

COMPATIBLE_MACHINE = "(fsl-lsch2|fsl-lsch3)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
