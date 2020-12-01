DESCRIPTION = ""

IMAGE_FEATURES += "splash ssh-server-openssh"

EXTRA_IMAGE_FEATURES_append = " package-management"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "
    
IMAGE_INSTALL_append = " can-utils \
                         mtd-utils \
                         memtester \
                         iperf3 \
                         optee-client \
                         optee-test \
                         prueth-fw-am65x-sr2 \
                         stress-ng \
                         stressapptest \
                         pciutils \
                         fb-test \
                         busybox \
                         opkg \
                         networkmanager \
                         apt \
                         iproute2 \
                         util-linux \
                         e2fsprogs \
                         parted \
                         init-ifupdown \
                         bc \
                         alsa-utils \
                         libfftw \
                         coreutils \
                         ntpdate \
                         xz \
                         dosfstools \
                        "
inherit core-image

                       
