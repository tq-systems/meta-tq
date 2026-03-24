SUMMARY = "Firmware files for use with TI cc33xx"
LICENSE = "TI-TSPA"
LIC_FILES_CHKSUM = "file://LICENCE;md5=df68504cbd0a4da1643ebcfd5783dbc9"

SRCREV = "0b4f850d6c0fd8e0fe0ae1d3e80ac6733aced29b"
SRC_URI = "git://git.ti.com/git/cc33xx-wlan/cc33xx-fw.git;protocol=https;branch=master"

PV = "1.7.0.323"

CLEANBROKEN = "1"

do_compile[no_exec] = "1"

EXTRA_OEMAKE = "DEST_DIR=${D} BASE_LIB_DIR=${nonarch_base_libdir}"

do_install() {
    oe_runmake install
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/ti-connectivity/*"
