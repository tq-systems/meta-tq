# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for TQMa6ULx SOM

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx6ul-mba6ulx.dtb \
    imx6ul-mba6ulx-g1.dtb \
    imx6ul-mba6ulx-lvds.dtb \
    imx6ul-mba6ulx-dmb-ct21.dtb \
    imx6ul-mba6ulx-dmb-ct44.dtb \
    imx6ul-mba6ulx-glyn-etm0700g0edh6.dtb \
"
KERNEL_DEVICETREE_linux-tq = "\
    imx6ul-tqma6ul1-mba6ulx.dtb \
    imx6ul-tqma6ul2-mba6ulx.dtb \
    imx6ul-tqma6ul2-mba6ulx-cdtech-dc44.dtb \
    imx6ul-tqma6ul2-mba6ulx-cdtech-fc21.dtb \
    imx6ul-tqma6ul2-mba6ulx-lvds.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"
