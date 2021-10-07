SUMMARY =  "This is a weston image for TQ SOM with some test / debug features \
and basic Qt5 support"

LICENSE = "MIT"

require recipes-images/images/tq-image-weston.bb

inherit populate_sdk_qt5

IMAGE_INSTALL += "\
    packagegroup-qt5-demos \
"
