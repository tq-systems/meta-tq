K3_IMAGE_GEN_SRCREV = "d02c19e2619f648f00fb6d0822482664410e8a68"

DEPENDS:remove:tqma65xx-k3r5 = "virtual/bootloader"
EXTRA_OEMAKE:remove:tqma65xx-k3r5 = "SBL="${STAGING_DIR_HOST}/boot/u-boot-spl.bin""
