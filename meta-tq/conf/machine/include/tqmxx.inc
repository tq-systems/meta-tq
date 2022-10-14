# Common definitions for TQ-Systems GmbH ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

# target not supported in older U-Boot
UBOOT_INITIAL_ENV ?= ""

DEFAULT_KERNEL:imx ??= "linux-imx-tq"
DEFAULT_KERNEL:imx:use-mainline-bsp ??= "linux-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "${DEFAULT_KERNEL}"

# Return a list of device tree files depending on the virtual/kernel
# provider. The (optional) list is named KERNEL_DEVICETREE_<provider>
def kernel_provider_dtbs(d):
    kernel_provider = d.getVar('PREFERRED_PROVIDER_virtual/kernel')

    if kernel_provider == 'linux-rt-tq':
        # They share a common kernel, so they can be copied
        mainline_list = d.getVar('KERNEL_DEVICETREE_linux-tq')
        if mainline_list is not None :
            d.setVar('KERNEL_DEVICETREE_linux-rt-tq', mainline_list)

    dtbs_for_provider = d.getVar('KERNEL_DEVICETREE_' + kernel_provider)
    if dtbs_for_provider is not None:
        return d.getVar('KERNEL_DEVICETREE_' + kernel_provider)

    return ''
