STATIC_PBL = "no"

OVERRIDES:prepend:tqmls1028a = "tqmls-atf-common:"
OVERRIDES:prepend:tqmls1012al = "tqmls-atf-common:"

SRC_URI:tqmls-atf-common = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH:tqmls-atf-common = "TQM-v2.4"
SRCREV:tqmls-atf-common = "ea967df114f4458389133e3b5b0f331c6c35dd13"

RCW_FOLDER:tqmls1012al = "tqmls1012al"
RCW_SUFFIX:tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET:tqmls1012al = "tqmls1012al_1gb"

RCW_FOLDER:tqmls1028a = "tqmls1028a"
RCW_SUFFIX:tqmls1028a = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1028a = "tqmls1028a_1gb"
PLATFORM_ADDITIONAL_TARGET:tqmls1028a = "tqmls1028a_4gb"
STATIC_PBL:tqmls1028a = "yes"

export STATIC_PBL

COMPATIBLE_MACHINE = "^("
COMPATIBLE_MACHINE .= "|tqmls1012al"
COMPATIBLE_MACHINE .= "|tqmls1028a"
COMPATIBLE_MACHINE .= ")$"
