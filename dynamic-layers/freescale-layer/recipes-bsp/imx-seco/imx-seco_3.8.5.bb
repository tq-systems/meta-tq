# Copyright 2019-2020 NXP
# Copyright 2020-2021 TQ-Systems GmbH

SUMMARY = "NXP i.MX SECO firmware"
DESCRIPTION = "NXP IMX SECO firmware"
SECTION = "base"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=e4098ac4459cb81b07d3f0c22b3e8370" 

inherit fsl-eula-unpack use-imx-security-controller-firmware deploy

# This version of the SECO is distributed under the FSL_EULA V33
# This workaraound schould be removed during update to Gathsgarth
FSL_EULA_FILE_MD5SUM_LA_OPT_NXP_SOFTWARE_LICENSE_V33 = "e4098ac4459cb81b07d3f0c22b3e8370"

FSL_EULA_FILE_MD5SUMS += " \
    ${FSL_EULA_FILE_MD5SUM_LA_OPT_NXP_SOFTWARE_LICENSE_V33} \
"

SRC_URI = "${FSL_MIRROR}/${BP}.bin;fsl-eula=true"

SRC_URI[md5sum] = "c1797407517b0499f6d5151c1e62e78f"
SRC_URI[sha256sum] = "b8731c626139c9bd1d530f243c2131c031a523f1428c6a40b017e5352b1da656"

do_compile[noexec] = "1"

do_install[noexec] = "1"

addtask deploy after do_install
do_deploy () {
    # Deploy i.MX8 SECO firmware files
    install -m 0644 ${S}/firmware/seco/${SECO_FIRMWARE_NAME} ${DEPLOYDIR}
}

COMPATIBLE_MACHINE = "(tqma8x|tqma8xx|tqma8xxs)"
