DISTRO_VERSION ??= "tq-generic"
VENDOR = "tq"
TARGET_VENDOR = "-${VENDOR}"

# The bundle version interacts with the downgrade barrier.
# "r0" as version, disables the downgrade barrier. If a
# downgrade barrier is set with r1, you have to increase
# the version for every update. In that way old security
# vulnerabilities cannot be exploited.
RAUC_BUNDLE_VERSION ?= "r0"

SDK_VENDOR = "-${VENDOR}sdk"
SDK_VERSION := "${DISTRO_VERSION}"
SDK_NAME = "${VENDOR}-${DISTRO}-${TCLIBC}-${SDK_ARCH}-${IMAGE_BASENAME}-${TUNE_PKGARCH}"
SDKPATH = "/opt/${VENDOR}-${DISTRO}/${SDK_VERSION}"

# include the config checker
INHERIT += "sanity"

# run QA tests
INHERIT += "insane"
QA_LOG = "1"

# Log information on images and packages
INHERIT += "buildhistory"

# Uncomment to disable commits
# BUILDHISTORY_COMMIT = "0"
INHERIT += "buildstats"

# do images prelink
INHERIT += "image-prelink"

# optimize lib sizes with mklibs
INHERIT += "image-mklibs"

# include the version package
IMAGE_INSTALL_append = " os-release"

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
