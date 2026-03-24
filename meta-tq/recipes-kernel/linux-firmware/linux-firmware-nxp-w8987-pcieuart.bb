SUMMARY = "WiFi/Bluetooth firmware for Marvell/NXP W8987 (PCIe/UART version)"
DESCRIPTION = "WiFi/Bluetooth firmware for Marvell/NXP W8987, \
which is currently not covered by the linux-firmware package. \
W8997 PCIe/UART firmware is also used for W8987. \
\
This variant of the firmware uses PCIe for WiFi and UART for Bluetooth."

SECTION = "kernel"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=44a8052c384584ba09077e85a3d1654f"

SRC_URI = "git://github.com/NXP/imx-firmware.git;protocol=https;branch=lf-6.6.3_1.0.0"
SRCREV = "2afa15e77f0b58eade42b4f59c9215339efcca66"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/mrvl
    install -m 0644 nxp/FwImage_8997/pcieuart8997_combo_v4.bin ${D}${nonarch_base_libdir}/firmware/mrvl/pcieuart8997_combo_v4.bin
}

# Alternative provider for mrvl/pcieuart8997_combo_v4.bin
RCONFLICTS:${PN} += "linux-firmware-pcie8997"

RPROVIDES:${PN} += "linux-firmware-pcie8997"
FILES:${PN} += " \
    ${nonarch_base_libdir}/firmware/mrvl/ \
"
