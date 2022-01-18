SUMMARY =  "This is a weston image for TQ SOM with some test / debug features."

DESCRIPTION = "Demo image based on core-image-weston and essential packages \
for the machine. This creates a large image and includes also debug tools, not \
directly suitable for production - also from the aspect of security."

LICENSE = "MIT"

# for now: use poky weston image as a base
require recipes-graphics/images/core-image-weston.bb

# add our default stuff
require tq-image.inc

IMAGE_FEATURES_remove = " package-management"

###
# this may be a little bit overkill
###
THISIMAGE_GSTREAMER_PLUGINS = "\
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
"

IMAGE_INSTALL_append = "\
    weston-examples \
    ${THISIMAGE_GSTREAMER_PLUGINS} \
"
