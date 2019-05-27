require imx-gpu-viv-v6.inc

SRC_URI[md5sum] = "d5529f8b6a6db1714f996a0de0b0858a"
SRC_URI[sha256sum] = "832d75095d746083bab13cefb0ab27e2da3930eadc215b8f2994d8cd02e387ab"

# Same compatible list as linux-tq_imx-4.14.78
COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"
