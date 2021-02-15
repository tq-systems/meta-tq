SUMMARY = "PRU Ethernet firmware for AM65x"

require recipes-ti/includes/ti-paths.inc
require recipes-bsp/emac-lld/emac-lld.inc

LICENSE = "TI-TFL"
LIC_FILES_CHKSUM = "file://icss_dualmac/src/makefile;beginline=6;endline=53;md5=3f9129d208f240940749757214bdc191"

PR = "r0"

TI_PDK_COMP = "ti.drv.emac.firmware"

B = "${S}/icss_dualmac"

COMPATIBLE_MACHINE = "am65xx"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "ti-cgt-pru-native"

EXTRA_OEMAKE += "CL_PRU_INSTALL_PATH="${TI_CGT_PRU_INSTALL_DIR}""

do_compile() {
    oe_runmake -C src
}

do_install() {
    install -d ${D}${base_libdir}/firmware/ti-pruss

    install -m 0644 bin/rxl2_txl2_rgmii0/rxl2_txl2.out \
        ${D}${base_libdir}/firmware/ti-pruss/am65x-pru0-prueth-fw.elf

    install -m 0644 bin/rtu_test0/rtu_v2.out \
        ${D}${base_libdir}/firmware/ti-pruss/am65x-rtu0-prueth-fw.elf

    install -m 0644 bin/rxl2_txl2_rgmii1/rxl2_txl2.out \
        ${D}${base_libdir}/firmware/ti-pruss/am65x-pru1-prueth-fw.elf

    install -m 0644 bin/rtu_test1/rtu_v2.out \
        ${D}${base_libdir}/firmware/ti-pruss/am65x-rtu1-prueth-fw.elf
}

FILES_${PN} = "${base_libdir}/firmware"

INSANE_SKIP_${PN} = "arch"
