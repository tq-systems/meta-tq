#
# this is hard enabled in imx specific gstreamer1.0-plugins-bad
# for meta-dumpling: remove to optimize build time
#
PACKAGECONFIG:remove:mx6q = " opencv"
PACKAGECONFIG:remove:mx6qp = " opencv"
