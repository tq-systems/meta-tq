# Common definitions for TQ-Group ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

PREFERRED_PROVIDER_ptpd_imx = "ptpd"
PREFERRED_PROVIDER_ptpd_qoriq = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'freescale-layer', 'ptpd-qoriq', 'ptpd', d)}"
PREFERRED_PROVIDER_ptpd ?= "ptpd"

PREFERRED_VERSION_firmware-imx_imx ?= "8.0"
PREFERRED_VERSION_firmware-imx_tqma8x = "8.3"
PREFERRED_VERSION_firmware-imx_tqma8xx = "8.3"
PREFERRED_VERSION_firmware-imx_tqma8xxs = "8.3"
