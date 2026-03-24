# this depends on machine specific firmware
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# This must be ELF,
# rename and copy to /lib/firmware/ethosu_firmware
# name is currently compiled fix into the driver
ETHOS_U_FIRMWARE:tqma93xx = "ethosu_apps_rpmsg.elf"

# appending to SRC_URI and overwriting do_install keeps license handling intact
SRC_URI:append:tqma93xx = " file://${ETHOS_U_FIRMWARE}"

do_install:tqma93xx () {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${UNPACKDIR}/${ETHOS_U_FIRMWARE} ${D}${nonarch_base_libdir}/firmware/ethosu_firmware
}

