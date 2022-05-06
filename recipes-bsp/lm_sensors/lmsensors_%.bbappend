# remove rrdtool / sensord from packageconfig
# otherwise this causes a complex dependency chain (rddtool / pango / cairo / gles)
# We don't need sensord by default. It rrecommends on lighttpd which builds php and mariadb

PACKAGECONFIG ?= ""

# remove these, since thy depend on perl
RDEPENDS:${PN}:remove = "${PN}-sensorsdetect"
RDEPENDS:${PN}:remove = "${PN}-sensorsconfconvert"

SYSTEMD_AUTO_ENABLE = "enable"
