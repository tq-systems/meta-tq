# Common definitions for TQ-Systems GmbH MBa6ULx baseboard for TQMa6ULLx SOM

KERNEL_DEVICETREE_linux-imx-tq = "\
    imx6ull-mba6ulx.dtb \
    imx6ull-mba6ulx-lvds.dtb \
    imx6ull-mba6ulx-dmb-ct21.dtb \
    imx6ull-mba6ulx-dmb-ct44.dtb \
    imx6ull-mba6ulx-glyn-etm0700g0edh6.dtb \
"

KERNEL_DEVICETREE_linux-tq = "\
    imx6ull-tqma6ull2-mba6ulx.dtb \
    imx6ull-tqma6ull2-mba6ulx-lvds.dtb \
    imx6ull-tqma6ull2-mba6ulx-cdtech-dc44.dtb \
    imx6ull-tqma6ull2-mba6ulx-cdtech-fc21.dtb \
"

KERNEL_DEVICETREE:append = " ${@kernel_provider_dtbs(d)} "
