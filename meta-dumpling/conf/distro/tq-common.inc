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

SDK_VENDOR = "-${VENDOR_SHORT_NAME}sdk"
SDK_VERSION := "${DISTRO_VERSION}"
SDK_NAME = "${VENDOR_SHORT_NAME}-${DISTRO}-${TCLIBC}-${SDK_ARCH}-${IMAGE_BASENAME}-${TUNE_PKGARCH}"
SDKPATH = "/opt/${VENDOR_SHORT_NAME}-${DISTRO}/${SDK_VERSION}"

# include the config checker
INHERIT += "sanity"

# run QA tests
INHERIT += "insane"
QA_LOG = "1"

# include the version package
DISTRO_EXTRA_RDEPENDS += "os-release"

# include additional udev rules
DISTRO_EXTRA_RDEPENDS += "tq-udev-rules"

# listed checks from ERROR_QA to WARN_QA (from insane.bbclass)
ERROR_TO_WARN_QA = "version-going-backwards"

VIRTUAL-RUNTIME_dev_manager = "udev"
VIRTUAL-RUNTIME_init_manager = "systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_syslog = ""
VIRTUAL-RUNTIME_initscripts = ""

# home for root is '/root' and nothing else
ROOT_HOME ?= "/root"

IMAGE_FSTYPES ?= " tar.gz"

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
