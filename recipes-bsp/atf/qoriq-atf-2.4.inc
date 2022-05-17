DESCRIPTION = "ARM Trusted Firmware"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.rst;md5=1dd070c98a281d18d9eefd938729b031"

SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/atf.git;nobranch=1"
SRCREV = "bb4957067d4b96a6ee197a333425948e409e990d"

S = "${WORKDIR}/git"

SRC_URI_tqmls1012al = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH}"
SRCBRANCH_tqmls1012al = "TQM-v2.4"
SRCREV_tqmls1012al = "a857a81ce8b1f8a2a4fbd7f23f2dec4746a7fa33"
RCW_FOLDER_tqmls1012al = "tqmls1012al"
RCW_SUFFIX_tqmls1012al = "${@bb.utils.contains('DISTRO_FEATURES', 'secure', '_sben.bin', '.bin', d)}"
PLATFORM_tqmls1012al = "tqmls1012al"
PLATFORM_ADDITIONAL_TARGET = "tqmls1012al_1gb"
