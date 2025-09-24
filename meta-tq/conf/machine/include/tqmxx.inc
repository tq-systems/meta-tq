# Common definitions for TQ-Systems GmbH ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

DEFAULT_KERNEL:imx ??= "linux-imx-tq"
DEFAULT_KERNEL:imx:use-mainline-bsp ??= "linux-tq"
PREFERRED_PROVIDER_virtual/kernel ??= "${DEFAULT_KERNEL}"
# weak default provided in meta-freescale/conf/machine/include/imx-base.inc
IMX_DEFAULT_ATF_PROVIDER:imx ?= "imx-atf-tq"
PREFERRED_PROVIDER_imx-atf ?= "imx-atf-tq"

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
        return dtbs_for_provider

    return ''

# this instructs bitbake to take the dependency established with the
# above function into account when calculating hashes for KERNEL_DEVICETREE
KERNEL_DEVICETREE[vardeps] = "KERNEL_DEVICETREE_${PREFERRED_PROVIDER_virtual/kernel}"

# Return a list of device tree files that does not contain a vendor
# directory prefix. This list is intended to be used for dtb list
# assignment of `IMAGE_BOOT_FILES`
def create_image_boot_dtb_files(d):
    # Generate IMAGE_BOOT_FILES entries for device tree files listed in
    # KERNEL_DEVICETREE.
    # Use only the basename for dtb files:
    alldtbs = d.getVar('KERNEL_DEVICETREE')

    # DTBs may be built out of kernel with devicetree.bbclass
    if not alldtbs:
        return ''

    def transform(dtb):
        if not (dtb.endswith('dtb') or dtb.endswith('dtbo')):
            # eg: <whatever>/<dtb file name>.dtb[o] has:
            #     DEPLOYDIR file: <dtb file name>.dtb[o]
            #     destination: <dtb file name>.dtb[o]
            bb.error("KERNEL_DEVICETREE entry %s is not a .dtb or .dtbo file." % (dtb) )
        return os.path.basename(dtb)

    return ' '.join([transform(dtb) for dtb in alldtbs.split() if dtb])

# this instructs bitbake to take the dependency established with the
# above function into account when calculating hashes for IMAGE_BOOTFILES
IMAGE_BOOT_FILES[vardeps] = "KERNEL_DEVICETREE"

def imx_v8_wks_from_distro_features(d):
    wks_type = ""

    if bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
        wks_type = "-verity"
    if bb.utils.contains("DISTRO_FEATURES", "rauc", True, False, d):
        if not bb.utils.contains("DISTRO_FEATURES", "secure", True, False, d):
            wks_type = "-rauc-ab"
        else:
            bb.warn("DISTRO_FEATURES contains 'rauc' and 'secure', using WKS_FILE selected by distro feature 'secure'")

    return "tqmaxx-imx-v8-sdimg" + wks_type + ".wks.in"
