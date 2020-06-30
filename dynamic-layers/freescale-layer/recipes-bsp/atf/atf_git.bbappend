SRC_URI_tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls-atf-common = "TQMLS-Integration"
SRCREV_tqmls-atf-common = "b5c4078a87f290a0cd7382d08fbcc04db3855710"

STATIC_PBL = "no"

OVERRIDES_prepend_tqmls1012al = "tqmls-atf-common:"
PLATFORM_tqmls1012al = "tqmls1012al"

OVERRIDES_prepend_tqmls1028a = "tqmls-atf-common:"
PLATFORM_tqmls1028a = "tqmls1028a"
STATIC_PBL_tqmls1028a = "yes"

export STATIC_PBL
