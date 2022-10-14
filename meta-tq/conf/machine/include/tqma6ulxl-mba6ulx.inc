# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for TQMa6ULxL SOM

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx6ul-mba6ulx-lga.dtb \
    imx6ul-mba6ulx-lga-lvds.dtb \
    imx6ul-mba6ulx-lga-dmb-ct21.dtb \
    imx6ul-mba6ulx-lga-dmb-ct44.dtb \
    imx6ul-mba6ulx-lga-glyn-etm0700g0edh6.dtb \
"

KERNEL_DEVICETREE_linux-tq = "\
    imx6ul-tqma6ul2l-mba6ulx.dtb \
    imx6ul-tqma6ul2l-mba6ulx-lvds.dtb \
    imx6ul-tqma6ul2l-mba6ulx-cdtech-dc44.dtb \
    imx6ul-tqma6ul2l-mba6ulx-cdtech-fc21.dtb \
"

KERNEL_DEVICETREE += "${@kernel_provider_dtbs(d)}"
