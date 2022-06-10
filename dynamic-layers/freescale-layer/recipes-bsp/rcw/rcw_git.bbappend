SRC_URI_tqmls-rcw-common = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-rcw-common = "TQMLS-Integration"
SRCREV_tqmls-rcw-common = "c6a51f7491d8f2667b767aaa3ab5dcd84b6c4ce5"
LIC_FILES_CHKSUM_tqmls-rcw-common = "file://LICENSE;md5=44a0d0fad189770cc022af4ac6262cbe"

OVERRIDES_prepend_tqmls1012al = "tqmls-rcw-common:"
M_tqmls1012al = "tqmls1012al"

OVERRIDES_prepend_tqmls1028a = "tqmls-rcw-common:"
M_tqmls1028a = "tqmls1028a"

OVERRIDES_prepend_tqmlx2160a = "tqmls-rcw-common:"
M_tqmlx2160a = "tqmlx2160a"

OVERRIDES_prepend_tqmls10xxa = "tqmls-rcw-common:"
