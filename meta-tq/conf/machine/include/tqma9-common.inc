# Common definitions for all TQ-Systems GmbH TQMa9xxx SOM

require conf/machine/include/tqmxx.inc

MACHINE_EXTRA_RRECOMMENDS += "\
    kernel-devicetree \
    kernel-modules \
"

PREFERRED_PROVIDER_imx-boot ?= "imx-boot-tq"

KERNEL_IMAGETYPE = "Image"

SPL_BINARY = "spl/u-boot-spl.bin"

# provide dt name handling to prevent a lot of copy and paste
# in kernel prefix where to find dtb

KERNEL_DEVICETREE_PATH_PREFIX = "freescale/"

python () {
    dt_list = d.getVar("BOARD_DEVICETREE_FILES").split()
    dt_prefix = d.getVar("KERNEL_DEVICETREE_PATH_PREFIX")
    dt_prefix += "% s"
    dt_list =  [dt_prefix % i for i in dt_list]
    d.setVar("KERNEL_DEVICETREE", ' '.join(dt_list))
}

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

WKS_FILE = "tqmaxx-imx-v8-sdimg.wks.in"
