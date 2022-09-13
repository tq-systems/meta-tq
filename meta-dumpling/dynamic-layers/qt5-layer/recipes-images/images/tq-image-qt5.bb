require recipes-images/images/tq-image-weston.bb

inherit populate_sdk_qt5

SUMMARY =  "This is a weston image for TQ SOM with some test features and basic Qt5 support."

DESCRIPTION = "Demo image based on tq-image-weston and Qt5 including some demos. \
This creates a large image and includes also test tools, not \
directly suitable for production - also from the aspect of security."

IMAGE_INSTALL += "\
    packagegroup-qt5-demos \
"
