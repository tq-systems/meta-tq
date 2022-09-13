require recipes-core/images/core-image-minimal.bb
require tq-image-base.inc

SUMMARY =  "This is a small image for TQ SOM with some test / debug features."

DESCRIPTION = "Demo image based on core-image-minimal and essential packages \
for the machine. This creates a large image and includes also debug tools, not \
directly suitable for production - also from the aspect of security."

# force dropbear
IMAGE_FEATURES:append = " ssh-server-dropbear"
IMAGE_FEATURES:remove = "ssh-server-openssh"

IMAGE_INSTALL:append = "\
    kernel-devicetree \
    kernel-image \
"
