require recipes-core/images/core-image-minimal.bb

SUMMARY =  "This is a small image for TQ SOM with some test / debug features."

DESCRIPTION = "Demo image based on core-image-minimal and essential packages \
for the machine. This creates a large image and includes also debug tools, not \
directly suitable for production - also from the aspect of security."

require tq-image-base.inc

# force dropbear
IMAGE_FEATURES_append = " ssh-server-dropbear"
IMAGE_FEATURES_remove = " ssh-server-openssh"
