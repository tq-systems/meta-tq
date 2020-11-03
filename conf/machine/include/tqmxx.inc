# Common definitions for TQ-Group ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://git@git.tq-group.com/tq-embedded/tqmaxx"
TQ_GIT_PROTOCOL ?= "ssh"

PREFERRED_PROVIDER_ptpd_imx = "ptpd"
PREFERRED_PROVIDER_ptpd_qoriq = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'freescale-layer', 'ptpd-qoriq', 'ptpd', d)}"
PREFERRED_PROVIDER_ptpd ?= "ptpd"
