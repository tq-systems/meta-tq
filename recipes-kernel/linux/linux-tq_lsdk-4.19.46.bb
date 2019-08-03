SUMMARY = "Linux for TQ-Group Freescale LS1088A module based on LSDK 19.06"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

inherit kernel

DEPENDS += " lzop-native bc-native openssl-native"
#
# append linux-mainline if we provide mainline kernel versions
#
PROVIDES += "linux-mainline linux-tq"

SRC_URI = "\
  git://source.codeaurora.org/external/qoriq/qoriq-components/linux;nobranch=1 \
  file://0001-tqmls1046a-Add-initial-board-support.patch \
  file://0002-tqmls1043a-Add-initial-board-support.patch \
  file://0003-ls1088a-Fix-clock-mapping-for-platform-clock-periphe.patch \
  file://0004-clk-qoriq-Add-HWA-clocks-for-LS1088A.patch \
  file://0005-ls1088a-Use-HWA-clock-for-esdhc.patch \
  file://0006-sdhci-of-esdhc-Add-clk_fixup-for-LS1043A-and-LS1088A.patch \
  file://0007-tqmls1088a-Add-initial-board-support.patch \
  file://defconfig;md5=335abcc600ce7ddb91f491bf478927c6 \
"

SRCREV = "1a4cab2c597de7ac87c6ab4ce2a42e5e5adb3c0b"


COMPATIBLE_MACHINE = "tqmls1088a"

S = "${WORKDIR}/git"
