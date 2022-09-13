SUMMARY = "Freescale i.MX firmware files"
DESCRIPTION = "Freescale/NXP i.MX firmware files for TQ-Systems GmbH SOM based on i.MX"
SECTION = "base"

inherit nxp-bin-unpack

# Year and version are from file COPYING in binary archive
LICENSE = "Proprietary & NXP-EULA-v24"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=417b82f17fc02b88125331ed312f6f1b \
"

ARCHIVE_NAME = "firmware-imx-${PV}"

SRC_URI = "${NXP_BIN_MIRROR}/${ARCHIVE_NAME}.bin;nxp-bin=true"
SRC_URI[md5sum] = "6062247e9f12f3ec27247079e59fb935"
SRC_URI[sha256sum] = "6b6747bf36ecc53e385234afdce01f69c5775adf0d6685c885281ca6e4e322ef"

S = "${WORKDIR}/${ARCHIVE_NAME}"

PR = "r0"

PACKAGES = "\
    ${PN}-nxp-license \
    ${PN}-vpu-imx6q \
    ${PN}-vpu-imx6d \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

# Only install files needed by subpackages in $PACKAGES
do_install () {
    install -d ${D}${nonarch_base_libdir}/firmware/vpu
    install -m 644 ${S}/firmware/vpu/vpu_fw_imx6q.bin ${D}${nonarch_base_libdir}/firmware/vpu
    install -m 644 ${S}/firmware/vpu/vpu_fw_imx6d.bin ${D}${nonarch_base_libdir}/firmware/vpu
    install -m 644 ${S}/COPYING "${D}${nonarch_base_libdir}/firmware/LICENCE.NXP-EULA-v24"
}

# Use same license scheme as in recipe linux-firmware
FILES:${PN}-nxp-license = "${nonarch_base_libdir}/firmware/LICENCE.NXP-EULA-v24"


LICENSE:${PN}-vpu-imx6q = "NXP-EULA-v24"
FILES:${PN}-vpu-imx6q = "${nonarch_base_libdir}/firmware/vpu/vpu_fw_imx6q.bin"
RDEPENDS:${PN}-vpu-imx6q += "${PN}-nxp-license"
RPROVIDES:${PN}-vpu-imx6q = "firmware-imx-vpu-imx6q"
RREPLACES:${PN}-vpu-imx6q = "firmware-imx-vpu-imx6q"
RCONFLICTS:${PN}-vpu-imx6q = "firmware-imx-vpu-imx6q"

LICENSE:${PN}-vpu-imx6d = "NXP-EULA-v24"
FILES:${PN}-vpu-imx6d = "${nonarch_base_libdir}/firmware/vpu/vpu_fw_imx6d.bin"
RDEPENDS:${PN}-vpu-imx6d += "${PN}-nxp-license"
RPROVIDES:${PN}-vpu-imx6d = "firmware-imx-vpu-imx6d"
RREPLACES:${PN}-vpu-imx6d = "firmware-imx-vpu-imx6d"
RCONFLICTS:${PN}-vpu-imx6d = "firmware-imx-vpu-imx6d"
