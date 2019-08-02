DESCRIPTION = "The Management Complex (MC) is a key component of DPAA2"
SECTION = "mc-utils"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=386a6287daa6504b7e7e5014ddfb3987 \
"

DEPENDS += "dtc-native"

inherit deploy

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/mc-utils;nobranch=1"
SRCREV = "18c77603733b3fcd0b716bf9800e052330941f4e"

SRC_URI += " file://config/ "

S = "${WORKDIR}/git"

MC_CFG ?= ""
MC_CFG_ls1088a = "ls1088a"
MC_CFG_ls2088a = "ls2088a"
MC_CFG_lx2160a = "lx2160a"

MC_BOARD ?= "RDB"


do_install () {

    if [ -d "${WORKDIR}/config" ]; then
        cp -r "${WORKDIR}/config" "${S}"
    fi

    oe_runmake -C config 

    install -d ${D}/boot/mc-utils
    cp -r ${S}/config/${MC_CFG}/${MC_BOARD}/*.dtb ${D}/boot/mc-utils
    if [ -d ${S}/config/${MC_CFG}/${MC_BOARD}/custom/ ]; then
        install -d ${D}/boot/mc-utils/custom
        cp -r ${S}/config/${MC_CFG}/${MC_BOARD}/custom/*.dtb ${D}/boot/mc-utils/custom
    fi
}

do_deploy () {
    install -d ${DEPLOYDIR}/mc-utils
    cp -r ${S}/config/${MC_CFG}/${MC_BOARD}/*.dtb ${DEPLOYDIR}/mc-utils
    if [ -d ${S}/config/${MC_CFG}/${MC_BOARD}/custom/ ]; then
        install -d ${DEPLOYDIR}/mc-utils/custom
        cp -r ${S}/config/${MC_CFG}/${MC_BOARD}/custom/*.dtb ${DEPLOYDIR}/mc-utils/custom
    fi
    if [ -f  ${DEPLOYDIR}/mc-utils/${MC_DPL} ]; then
        ln -sfT mc-utils/${MC_DPL} ${DEPLOYDIR}/mc-dpl.dtb
    fi
    if [ -f  ${DEPLOYDIR}/mc-utils/${MC_DPC} ]; then
        ln -sfT mc-utils/${MC_DPC} ${DEPLOYDIR}/mc-dpc.dtb
    fi
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"
COMPATIBLE_MACHINE = "(fsl-lsch3)"
