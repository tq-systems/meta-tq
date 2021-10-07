DESCRIPTION = "camera testing tools"
LICENSE = "MIT"

inherit packagegroup

GST_PKGS = "\
    gstreamer1.0 \
    gstreamer1.0-plugins-bad-autoconvert \
    gstreamer1.0-plugins-bad-fbdevsink \
    gstreamer1.0-plugins-bad-gdp \
    gstreamer1.0-plugins-bad-pnm \
    gstreamer1.0-plugins-bad-kms \
    gstreamer1.0-plugins-base-tcp \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-good-multifile \
    gstreamer1.0-plugins-good-rtp \
    gstreamer1.0-plugins-good-rtsp \
    gstreamer1.0-plugins-good-udp \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-good-videocrop \
    gstreamer1.0-plugins-good-videofilter \
\
    gstreamer1.0-plugin-bayer2rgb-neon \
"

RDEPENDS_${PN} = " \
    ${GST_PKGS} \
    bayer2rgb-neon-bin \
"
