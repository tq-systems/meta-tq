
SRC_URI:tqmls1012al = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH:tqmls1012al = "TQM-v2.4"
SRCREV:tqmls1012al = "a857a81ce8b1f8a2a4fbd7f23f2dec4746a7fa33"
RCW_FOLDER:tqmls1012al = "tqmls1012al"
RCW_SUFFIX:tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM:tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET:tqmls1012al = "tqmls1012al_1gb"
