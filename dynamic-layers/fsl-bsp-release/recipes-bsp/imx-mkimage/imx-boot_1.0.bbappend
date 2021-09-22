DEPENDS += " \
    virtual/bootloader \
"

##
# do assignment for TQMa8Xx[S] SOM to enable bootstream with M4 demo
##
IMX_M4_DEMOS_tqma8xx  ?= "tqma8-cortexm-demos:do_deploy"
M4_DEFAULT_IMAGE_tqma8xx ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

IMX_M4_DEMOS_tqma8xxs  ?= "tqma8-cortexm-demos:do_deploy"
M4_DEFAULT_IMAGE_tqma8xxs ?= "rpmsg_lite_pingpong_rtos_linux_remote.bin"

IMX_M4_DEMOS_tqma8x  ?= "tqma8-cortexm-demos:do_deploy"
M4_DEFAULT_IMAGE_tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m40.bin"
M4_1_DEFAULT_IMAGE_tqma8x ?= "rpmsg_lite_pingpong_rtos_linux_remote_m41.bin"
