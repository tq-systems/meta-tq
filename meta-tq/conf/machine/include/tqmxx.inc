# Common definitions for TQ-Systems GmbH ARM modules

# these are used for linux-tq and u-boot-tq
TQ_GIT_BASEURL ?= "git://github.com/tq-systems"
TQ_GIT_PROTOCOL ?= "https"

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

# Tools needed to construct the wic file
WKS_FILE_DEPENDS += "\
    dosfstools-native \
    e2fsprogs-native \
    mtools-native \
    parted-native \
"
