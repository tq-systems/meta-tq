# render only + entaviv
PACKAGECONFIG:append:tqma6x = " gallium kmsro etnaviv"

PACKAGECONFIG:append:tqma7x = " gallium"
PACKAGECONFIG:append:tqma6ulx = " gallium"
PACKAGECONFIG:append:tqma6ullx = " gallium"

GALLIUMDRIVERS:append = ",swrast"
