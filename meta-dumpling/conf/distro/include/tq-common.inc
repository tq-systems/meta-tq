VENDOR_SHORT_NAME = "tq"
VENDOR_LONG_NAME = "TQ-Systems"
TARGET_VENDOR = "-${VENDOR_SHORT_NAME}"
# should be set to a useful name as soon as we want to go away from
# poky/meta-poky/conf/distro/poky.conf
DISTRO_VERSION ??= "${VENDOR_SHORT_NAME}.0.1"

# The bundle version interacts with the downgrade barrier.
# "r0" as version, disables the downgrade barrier. If a
# downgrade barrier is set with r1, you have to increase
# the version for every update. In that way old security
# vulnerabilities cannot be exploited.
RAUC_BUNDLE_VERSION ?= "r0"

SKIP_RECIPE[rauc-conf] = "replaced with tq-rauc-conf"

# RAUC upgrades are possible between images that use the same
# `RAUC_BUNDLE_COMPATIBLE`. The reference distros in meta-dumpling always use
# "dumpling-${MACHINE}", so upgrades between dumpling and spaetzle are supported.
RAUC_BUNDLE_COMPATIBLE ?= "dumpling-${MACHINE}"

SDK_VENDOR = "-${VENDOR_SHORT_NAME}sdk"
SDK_VERSION := "${DISTRO_VERSION}"
SDK_NAME = "${VENDOR_SHORT_NAME}-${DISTRO}-${TCLIBC}-${SDK_ARCH}-${IMAGE_BASENAME}-${TUNE_PKGARCH}"
SDKPATH = "/opt/${VENDOR_SHORT_NAME}-${DISTRO}/${SDK_VERSION}"

# include the config checker
INHERIT += "sanity"

# run QA tests
INHERIT += "insane"
QA_LOG = "1"

# generate 'etc/buildinfo' in image and in 'DEPLOY_DIR_IMAGE' folder
INHERIT += "tq-buildinfo"

# disable ptest feature
DISTRO_FEATURES_OPTED_OUT += "ptest"

DISTRO_EXTRA_RDEPENDS += "os-release u-boot-env-tq"
DISTRO_EXTRA_RRECOMMENDS += " \
    zstd \
    ${@bb.utils.contains('DISTRO_FEATURES', 'rauc', 'tq-mount-data', '', d)} \
"

# listed checks from ERROR_QA to WARN_QA (from insane.bbclass)
ERROR_TO_WARN_QA = "version-going-backwards"

INIT_MANAGER = "systemd"

# home for root is '/root' and nothing else
ROOT_HOME ?= "/root"

# FIT image signature generation defaults
# To actually enable signature generation, add
# UBOOT_SIGN_ENABLE = "1"
# to your local.conf
UBOOT_SIGN_KEYDIR ?= "${DUMPLING_LAYERDIR}/files/keys/u-boot"
UBOOT_SIGN_KEYNAME ?= "dev"
UBOOT_MKIMAGE_DTCOPTS = "--in-format dts --out-format dtb"

FIT_KERNEL_SIGN_ENABLE = "${UBOOT_SIGN_ENABLE}"
FIT_KERNEL_SIGN_KEYNAME = "${UBOOT_SIGN_KEYNAME}"
FIT_KERNEL_SIGN_KEYDIR = "${UBOOT_SIGN_KEYDIR}"

#
# busybox has prio 50 per default, inetutils has 79
# ping from iputils should have same features as full featured
# busybox ping and has priority of 100
# to prevent installing a crippled ping, decrease prio for inetutils
# implementation, so that we have
# inetutils.ping < busybox.ping < iputils.ping
#
ALTERNATIVE_PRIORITY_inetutils-ping[ping] = "27"
ALTERNATIVE_PRIORITY_inetutils-ping6[ping6] = "27"

ALTERNATIVE_PRIORITY_busybox-sysctl[sysctl] = "27"

# Set a more generic tuning for code reuse across parts.
# The machine definitions should include the best available
# tune flavour. Therefore 'DEFAULTTUNE' may already have been
# assigned by the mentioned tuning include.
# Hence we cannot use a default assignment here but reassign.
DEFAULTTUNE:am62xx              = "armv8a-crc-crypto"
DEFAULTTUNE:am64xx              = "armv8a-crc-crypto"
DEFAULTTUNE:am65xx              = "armv8a-crc-crypto"
DEFAULTTUNE:j722s               = "armv8a-crc-crypto"
DEFAULTTUNE:ls1012a             = "armv8a-crc-crypto"
DEFAULTTUNE:ls1028a             = "armv8a-crc-crypto"
DEFAULTTUNE:ls1043a             = "armv8a-crc-crypto"
DEFAULTTUNE:ls1046a             = "armv8a-crc-crypto"
DEFAULTTUNE:ls1088a             = "armv8a-crc-crypto"
DEFAULTTUNE:lx2160a             = "armv8a-crc-crypto"
DEFAULTTUNE:mx8-generic-bsp     = "armv8a-crc-crypto"
DEFAULTTUNE:mx8m-generic-bsp    = "armv8a-crc-crypto"
DEFAULTTUNE:mx8qm-generic-bsp   = "armv8a-crc-crypto"
DEFAULTTUNE:mx8x-generic-bsp    = "armv8a-crc-crypto"
DEFAULTTUNE:mx8ulp-generic-bsp  = "armv8a-crc-crypto"
DEFAULTTUNE:mx9-generic-bsp     = "armv8a-crc-crypto"
DEFAULTTUNE:mx91-generic-bsp    = "armv8a-crc-crypto"
DEFAULTTUNE:mx93-generic-bsp    = "armv8a-crc-crypto"
DEFAULTTUNE:mx95-generic-bsp    = "armv8a-crc-crypto"
