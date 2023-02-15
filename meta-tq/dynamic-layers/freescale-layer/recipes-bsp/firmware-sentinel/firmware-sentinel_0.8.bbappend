# TODO:
# meta-imx has here: `COMPATIBLE_MACHINE = "(mx8ulp-nxp-bsp|mx9-nxp-bsp)"`
# at the moment there is no mx9 support in meta-freescale
# so lets add imx93 for now to support our upcomming TQMa93xx SOM
# family and allow using with mainline kernel
COMPATIBLE_MACHINE = "(mx93-generic-bsp|mx8ulp-generic-bsp)"
