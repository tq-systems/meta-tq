# perf now depends on pyhton3 (starting with linux 4.17)
# disable scripting per PACKAGECONFIG
# if needed perf can be backported to older kernel
# or perf recipe could be changed to ressurect python2 support

PACKAGECONFIG_remove_tqma6x = "scripting"
PACKAGECONFIG_remove_tqma7x = "scripting"
PACKAGECONFIG_remove_tqma6ulx = "scripting"
PACKAGECONFIG_remove_tqma6ullx = "scripting"
PACKAGECONFIG_remove_tqma57xx = "scripting"
PACKAGECONFIG_remove_tqmls102xa = "scripting"
