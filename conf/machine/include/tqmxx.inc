# Common definitions for TQ-Group ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

PREFERRED_PROVIDER_ptpd_imx = "ptpd"
PREFERRED_PROVIDER_ptpd_qoriq = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'freescale-layer', 'ptpd-qoriq', 'ptpd', d)}"
PREFERRED_PROVIDER_ptpd ?= "ptpd"

IMAGE_FSTYPES ?= "tar.gz"
