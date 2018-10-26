
DESCRIPTION = "u-boot for TQ-Group NXP i.MX8x based modules"

require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

PROVIDES += "u-boot"
DEPENDS_append = " dtc-native"

SRCREV = "bc6a293a61f37f7882229d1e99d73f7416dcb168"
SRCBRANCH = "TQMa8xx-bringup-imx_v2017.03_4.9.88_imx8qxp_beta2"

SRC_URI = "${TQ_GIT_BASEURL}/u-boot-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
           file://0001-tqma8qx-next-fix-for-default-fdt_file.patch \
           file://0002-tqma8qx-enable-FAT_WRITE.patch \
           file://0003-tqma8qx-enable-some-commands.patch \
           file://0004-tqma8qx-env-add-update-scripts-for-kernel-and-u-boot.patch \
           file://0005-tqma8qx-configure-memtest.patch \
           file://0006-tqma8qx-enable-usb-in-defconfig.patch \
           file://0007-tqma8qx-enable-i2c-devices-in-defconfig.patch \
           file://0008-tqma8qx-enable-i2c1-in-dt.patch \
           file://0009-tqma8qx-enable-usb-in-dt.patch \
           file://0010-tqma8qx-add-devices-to-i2c1-bus.patch \
           file://0011-tqma8qx-mba8qx-add-devices-to-i2c1-bus.patch \
           file://0012-rtc-Add-DM-support-to-ds1307.patch \
           file://0013-tqma8qx-config-enable-rtc-driver-and-date-command-su.patch \
           file://0014-tqma8qx-dt-enable-rtc.patch \
           file://0015-i2c_eeprom-add-read-and-write-functions.patch \
           file://0016-i2c_eeprom-add-static-to-i2c_eeprom_std_ops-probe.patch \
           file://0017-cmd-mtest-give-more-info.patch \
           file://0018-wip-usb-fixes.patch \
           file://0019-tqma8qx-fix-ram-size.patch \
           "

S = "${WORKDIR}/git"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqma8qx-mba8qx|tqma8qxa0-mba8qx"

UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
