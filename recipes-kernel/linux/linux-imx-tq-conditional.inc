# optional if meta-freescale present

# i.MX8 and i.MX6 variants have support for Vivante GPU when building with kernel
# based on linux-imx. We want to use fsl-vivante-kernel-driver-handler from
# meta-freescale. Therefore the following settings will ensure that we build the
# driver as an out of tree module from correct sources found in linux-imx from
# github.com/nxp-imx. This ensures the correct version needed for the Vivante graphic
# userland
# The imxgpu override comes from imx-base.inc in meta-freescale and should be
# included in the machine config file
MACHINE_HAS_VIVANTE_KERNEL_DRIVER_SUPPORT_imxgpu = "1"
MACHINE_USES_VIVANTE_KERNEL_DRIVER_MODULE_imxgpu = "1"

inherit fsl-vivante-kernel-driver-handler
