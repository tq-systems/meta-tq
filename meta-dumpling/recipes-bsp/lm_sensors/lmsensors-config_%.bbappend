# remove rrdtool
# otherwise this causes a complex dependency chain (rddtool / pango / cairo / gles)
# We don't need sensord by default. It rrecommends on lighttpd which builds php and mariadb

PACKAGECONFIG ?= ""
