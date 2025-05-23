# remove rrdtool / sensord from packageconfig
# otherwise this causes a complex dependency chain (rddtool / pango / cairo / gles)
# We don't need sensord by default. It rrecommends on lighttpd which builds php and mariadb

PACKAGECONFIG ?= ""

# remove these, since thy depend on perl
RDEPENDS:${PN}:remove = "${PN}-sensorsdetect"
RDEPENDS:${PN}:remove = "${PN}-sensorsconfconvert"

SYSTEMD_AUTO_ENABLE = "enable"

# Workaround for issue in upstream recipe, revert once
# once fc88c96c4e40 (lmsensors: Fix build without sensord) is backported to
# meta-openembedded, branch scarthgap
EXTRA_OEMAKE += 'PROG_EXTRA="sensors ${PACKAGECONFIG_CONFARGS}"'
