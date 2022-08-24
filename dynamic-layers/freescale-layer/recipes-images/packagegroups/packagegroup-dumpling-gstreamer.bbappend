
# from meta-freescale
MACHINE_GSTREAMER_1_0_PLUGIN ?= ""

# plugins depending on the machine SOC
RDEPENDS_${PN}-base += "\
    ${MACHINE_GSTREAMER_1_0_PLUGIN} \
    ${@bb.utils.contains("MACHINE_GSTREAMER_1_0_PLUGIN", "imx-gst1.0-plugin", "imx-gst1.0-plugin-gplay", "", d)} \
    ${@bb.utils.contains("MACHINE_GSTREAMER_1_0_PLUGIN", "imx-gst1.0-plugin", "imx-gst1.0-plugin-grecorder", "", d)} \
"
