#
# i.MX6 with wayland and fsl kernel will fail otherwise
#

PACKAGECONFIG_remove_imxgpu   = "gallium"
PACKAGECONFIG_append_imxgpu   = " osmesa"
DRIDRIVERS_append_imxgpu = "swrast"

do_install_append_imxgpu3d () {
    rm -f ${D}${includedir}/GL/glcorearb.h
}
