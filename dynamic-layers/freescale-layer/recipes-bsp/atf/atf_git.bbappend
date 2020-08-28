SRC_URI_tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-atf-common = "TQMLS-Integration"
SRCREV_tqmls-atf-common = "2d6f7c3a75285cb86454affc69345903aa28c24b"

STATIC_PBL = "no"

OVERRIDES_prepend_tqmls1012al = "tqmls-atf-common:"
PLATFORM_tqmls1012al = "tqmls1012al"

OVERRIDES_prepend_tqmls1028a = "tqmls-atf-common:"
PLATFORM_tqmls1028a = "tqmls1028a"
STATIC_PBL_tqmls1028a = "yes"

OVERRIDES_prepend_tqmlx2160a = "tqmls-atf-common:"
PLATFORM_tqmlx2160a = "tqmlx2160a"

export STATIC_PBL
