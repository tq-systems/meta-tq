# Common definitions for all TQ-Systems GmbH starter kits with SOM
# based on i.MX8 CPU

# rootfs with kernel modules, device tree and kernel image
MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-image \
    kernel-modules \
"

WKS_FILE = "tqmaxx-imx-v8-sdimg.wks.in"

# EXTRA_IMAGEDEPENDS are applied to do_image_complete only. do_image_wic may run
# before and needs those packages as well
WKS_FILE_DEPENDS += "${EXTRA_IMAGEDEPENDS}"
