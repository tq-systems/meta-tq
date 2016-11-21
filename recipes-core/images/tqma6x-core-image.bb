# Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_FEATURES += "ssh-server-dropbear"

# Include modules in rootfs
IMAGE_INSTALL_append = " \
	kernel-modules \
	"
