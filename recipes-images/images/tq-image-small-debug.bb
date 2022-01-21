require tq-image-small.bb

SUMMARY =  "This is a small image for TQ SOM with some test and debug features."

DESCRIPTION = "Demo image based on tq-image-generic and minimal selection of packages \
and IMAGE_FEATURES usually needed for development, debugging and testing. \
This creates a small image, that must not be used for production - \
especially from the aspect of security."

# enable root login w/o password and passwordless ssh login
IMAGE_FEATURES_append = " debug-tweaks"
