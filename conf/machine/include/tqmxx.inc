# Common definitions for TQ-Systems GmbH ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

# target not supported in older U-Boot
UBOOT_INITIAL_ENV ?= ""

PREFERRED_PROVIDER_ptpd_imx = "ptpd"
PREFERRED_PROVIDER_ptpd_qoriq = "${@bb.utils.contains('BBFILE_COLLECTIONS', 'freescale-layer', 'ptpd-qoriq', 'ptpd', d)}"
PREFERRED_PROVIDER_ptpd ?= "ptpd"

PREFERRED_PROVIDER_virtual/kernel_imx ??= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel_imx_use-mainline-bsp ??= "linux-tq"

# Return a list of device tree files depending on the virtual/kernel
# provider. The (optional) list is named KERNEL_DEVICETREE_<provider>
def kernel_provider_dtbs(d):
    kernel_provider = d.getVar('PREFERRED_PROVIDER_virtual/kernel')
    return d.getVar('KERNEL_DEVICETREE_' + kernel_provider)
