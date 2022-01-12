
SRC_URI_remove = " \
    git://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3/1.12.2;name=base \
    git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
    file://0001-omxvideodec-don-t-drop-frame-if-it-contains-header-d.patch \
"

SRC_URI_append = " \
    git://github.com/renesas-rcar/gst-omx.git;branch=RCAR-GEN3/1.12.2;name=base;protocol=https \
    git://anongit.freedesktop.org/git/gstreamer/common.git;destsuffix=git/common;name=common;protocol=https \
    file://gstomx.conf \
"
