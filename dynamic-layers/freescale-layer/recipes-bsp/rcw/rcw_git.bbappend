SRC_URI = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH = "TQMLS-Integration"
SRCREV = "4a036b8a36322ddc687dc515a80227dc04bd0c06"
LIC_FILES_CHKSUM = "file://LICENSE;md5=44a0d0fad189770cc022af4ac6262cbe"

M_tqmls1012al = "tqmls1012al"

do_install_append_tqmls1012al-1gb () {
    mv -T ${D}/boot/rcw/tqmls1012al ${D}/boot/rcw/tqmls1012al_1gb
}

M_tqmls1028a = "tqmls1028a"

M_tqmlx2160a = "tqmlx2160a"
