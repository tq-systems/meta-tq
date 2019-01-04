DESCRIPTION = "Configuration script for MBLS1012al"
SRC_URI = "file://mbls1012al-config \
file://write_ksz9897.c"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/mbls1012al-config;beginline=4;endline=32;md5=e8013ff406c2ca9b976b1fd597a75c13"

# these 3 lines will have the script run on boot
inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "mbls1012al-config"

PROVIDES = "config-mbls1012al"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile () {
	${CC} ${WORKDIR}/write_ksz9897.c -o ${WORKDIR}/write_ksz9897
}

# install it in the correct location for update-rc.d
do_install() {
  install -d ${D}${INIT_D_DIR}
  install -m 0755 ${WORKDIR}/mbls1012al-config ${D}${INIT_D_DIR}/mbls1012al-config
  install -d ${D}/usr
  install -d ${D}/usr/sbin
  install -m 0755 ${WORKDIR}/write_ksz9897 ${D}/usr/sbin/write_ksz9897
}

# package it as it is not installed in a standard location
FILES_${PN} = "${INIT_D_DIR}/mbls1012al-config \
/usr/sbin/write_ksz9897"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "tqmls1012al-mbls1012al"
