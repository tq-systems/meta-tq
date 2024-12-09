require recipes-images/images/tq-image-weston.bb

inherit populate_sdk_qt6

SUMMARY =  "This is a weston image for TQ SOM with some test features and basic Qt6 support."

DESCRIPTION = "Demo image based on tq-image-weston and Qt6. \
This creates a large image and includes also test tools, not \
directly suitable for production - also from the aspect of security."

IMAGE_INSTALL += "\
    packagegroup-qt6-libs \
"
