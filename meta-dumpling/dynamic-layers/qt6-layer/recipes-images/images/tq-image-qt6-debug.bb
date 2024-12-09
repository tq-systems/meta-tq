require tq-image-qt6.bb
require recipes-images/images/tq-image-debug.inc

SUMMARY =  "This is a Qt6 image for TQ SOM with test and debug features."

DESCRIPTION = "Demo image based on tq-image-qt6 and selection of packages \
and IMAGE_FEATURES usually needed for development, debugging and testing. \
This creates a fairly large image, that must not be used for production - \
especially from the aspect of security."

IMAGE_INSTALL += "\
    packagegroup-qt6-demos \
"
