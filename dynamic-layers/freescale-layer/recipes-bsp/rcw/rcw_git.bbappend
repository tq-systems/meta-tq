SRC_URI_tqmls-rcw-common = "${TQ_GIT_BASEURL}/rcw.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-rcw-common = "TQMLS-Integration"
SRCREV_tqmls-rcw-common = "de8a7805e0e18a8fd27ab00f01eacf47b87cfd91"
LIC_FILES_CHKSUM_tqmls-rcw-common = "file://LICENSE;md5=44a0d0fad189770cc022af4ac6262cbe"

SRC_URI_tqmls10xxa = "${SRC_URI_tqmls-rcw-common}"
SRCBRANCH_tqmls10xxa = "TQMLS10xxA-rcw"
SRCREV_tqmls10xxa = "9f3db9ede3a5edec257c44c804131c168f281b97"
PYTHON_tqmls10xxa = "${USRBINPATH}/python2"

OVERRIDES_prepend_tqmls1012al = "tqmls-rcw-common:"
M_tqmls1012al = "tqmls1012al"

OVERRIDES_prepend_tqmls1028a = "tqmls-rcw-common:"
M_tqmls1028a = "tqmls1028a"
