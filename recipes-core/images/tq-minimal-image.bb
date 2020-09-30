DESCRIPTION = ""

IMAGE_FEATURES += "splash ssh-server-openssh"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "
    
IMAGE_INSTALL_append = " can-utils \
                         mtd-utils \
                         memtester \
                         optee-client \
                         optee-test \
                        "
inherit core-image
