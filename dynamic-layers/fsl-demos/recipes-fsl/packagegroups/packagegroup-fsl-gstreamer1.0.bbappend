
# Basic networking plugins required by most pipelines that receive and/or send data
RDEPENDS_${PN}-network-base_remove = " \
    gstreamer1.0-plugins-good-souphttpsrc \
"

RDEPENDS_${PN}-network-base_append = " \
    gstreamer1.0-plugins-good-soup \
"
