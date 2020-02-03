# Common definitions for all TQ-Group TQMa8x / TQMa8xx / TQMa8Mx modules

PREFERRED_PROVIDER_u-boot-fw-utils_mx8 ?= "u-boot-fw-utils-tq-imx"


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
