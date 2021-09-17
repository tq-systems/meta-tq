
DEPENDS += " \
    ${@bb.utils.contains('IMXBOOT_TARGETS', 'flash_linux_m4', 'virtual/imx-cortexm-demos', '', d)} \
"

M4_DEFAULT_IMAGE ?= "m4_image.bin"
M4_1_DEFAULT_IMAGE ?= "m4_1_image.bin"

##
# do assignment for TQMa8Xx[S] / TQMa8x SOM to enable bootstream with M4 demo
##
M4_DEFAULT_IMAGE_tqma8xx ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"
M4_DEFAULT_IMAGE_tqma8xxs ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

M4_DEFAULT_IMAGE_tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m40.bin"
M4_1_DEFAULT_IMAGE_tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m41.bin"

do_compile_prepend_mx8x () {
    for target in ${IMXBOOT_TARGETS}; do
        if [ "$target" = "flash_linux_m4" ]; then
            cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE} ${BOOT_STAGING}/m4_image.bin
        fi
    done
}

do_compile_prepend_mx8 () {
    for target in ${IMXBOOT_TARGETS}; do
        if [ "$target" = "flash_linux_m4" ]; then
            cp ${DEPLOY_DIR_IMAGE}/${M4_DEFAULT_IMAGE}   ${BOOT_STAGING}/m4_image.bin
            cp ${DEPLOY_DIR_IMAGE}/${M4_1_DEFAULT_IMAGE} ${BOOT_STAGING}/m4_1_image.bin
        fi
    done
}

##
# create link to real bootstream image in deploy dir, needed for wic image
##
do_deploy_append() {
    cd ${DEPLOYDIR}
    ln -sf ${BOOT_CONFIG_MACHINE}-${IMAGE_IMXBOOT_TARGET} ${BOOT_NAME}
    cd -
}
