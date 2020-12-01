require tq-custom-image.bb

DESCRIPTION = ""

IMAGE_FEATURES += " x11 x11-base"


IMAGE_INSTALL += "\
    packagegroup-xfce-base \
    "
    
IMAGE_INSTALL_append = " i2c-tools \
                         iw \
                         wpa-supplicant \
                         dhcp-client \
                         xrandr \
                         phoronix-test-suite \
                         libdrm \
                         kmscube \
                         lshw \
                         usbutils \
                         evtest \
                         ntp-utils \
                         xev \
                         ltp \
                         sharutils \
                         ethtool \
                         ifupdown \
                         ntp \
                        "                
