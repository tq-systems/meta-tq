SRC_URI_remove = " \
    git://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3/1.12.2;name=base \
    git://anongit.freedesktop.org/gstreamer/common;destsuffix=git/common;name=common \
"
SRC_URI_append = " \
    git://github.com/renesas-rcar/gst-plugins-good.git;branch=RCAR-GEN3/1.12.2;name=base;protocol=https \
    git://anongit.freedesktop.org/git/gstreamer/common.git;destsuffix=git/common;name=common;protocol=https \
"
