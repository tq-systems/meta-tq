# currently TQMaXx / TQMaXxS and TQMa8Mx have no configure option for
# imx fork of Open TEE OS
# remove these SOCs from COMPATIBLE_MACHINE list by using their
# MACHINE_OVERRIDE string

COMPATIBLE_MACHINE_remove = "tqma8xx"
COMPATIBLE_MACHINE_remove = "tqma8xxs"
COMPATIBLE_MACHINE_remove = "tqma8mx"
