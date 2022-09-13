require tq-image-generic.bb
require tq-image-debug.inc

SUMMARY =  "This is a generic image for TQ SOM with test and debug features."

DESCRIPTION = "Demo image based on tq-image-generic and selection of packages \
and IMAGE_FEATURES usually needed for development, debugging and testing. \
This creates a medium sized image, that must not be used for production - \
especially from the aspect of security."
