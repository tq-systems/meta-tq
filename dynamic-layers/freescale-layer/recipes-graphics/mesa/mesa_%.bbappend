#
# i.MX6 with wayland and fsl kernel will fail otherwise
#

PACKAGECONFIG_remove_imxgpu_mx6   = "gallium"
PACKAGECONFIG_append_imxgpu_mx6   = " osmesa"
DRIDRIVERS_append_imxgpu_mx6 = " swrast"

