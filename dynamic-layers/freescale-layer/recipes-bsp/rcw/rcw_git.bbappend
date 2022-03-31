SRC_URI_tqmls-rcw-common = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-rcw-common = "TQMLS-Integration"
SRCREV_tqmls-rcw-common = "4a036b8a36322ddc687dc515a80227dc04bd0c06"
LIC_FILES_CHKSUM_tqmls-rcw-common = "file://LICENSE;md5=44a0d0fad189770cc022af4ac6262cbe"

OVERRIDES_prepend_tqmls1012al = "tqmls-rcw-common:"
M_tqmls1012al = "tqmls1012al"

do_install_append_tqmls1012al-1gb () {
    mv -T ${D}/boot/rcw/tqmls1012al ${D}/boot/rcw/tqmls1012al_1gb
}

OVERRIDES_prepend_tqmls1028a = "tqmls-rcw-common:"
M_tqmls1028a = "tqmls1028a"

OVERRIDES_prepend_tqmlx2160a = "tqmls-rcw-common:"
M_tqmlx2160a = "tqmlx2160a"

OVERRIDES_prepend_tqmls10xxa = "tqmls-rcw-common:"
