SRC_URI_remove = "${KERNEL_URL};protocol=https;nocheckout=1;branch=${BRANCH}"

KERNEL_URL = "${TQ_GIT_BASEURL}/linux-tqmaxx.git"
BRANCH = "TQMaRZG2x-linux-v5.10.x-cip"
SRCREV = "2dd3207b997c0e972a28f15043a6b931473f0592"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    ${KERNEL_URL};protocol=${TQ_GIT_PROTOCOL};nocheckout=1;branch=${BRANCH} \
    file://tq-generic-devices.cfg \
"

COMPATIBLE_MACHINE_tqmarzg2x = "tqmarzg2x"
