# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer

SUMMARY = "Automatic selection of fw_env.config for TQ platforms"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd update-rc.d

SRC_URI = "\
    file://u-boot-env-tq.service \
    file://u-boot-env-tq.sh \
"

S = "${UNPACKDIR}"
SYSTEMD_SERVICE:${PN} = "u-boot-env-tq.service"

INITSCRIPT_NAME = "u-boot-env-tq.sh"
INITSCRIPT_PARAMS = "start 10 S ."

RDEPENDS:${PN} += "util-linux-findmnt"

do_install() {
    install -Dm 755 "${UNPACKDIR}/u-boot-env-tq.sh" "${D}${libexecdir}/tq/u-boot-env-tq.sh"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
	    install -d ${D}${sysconfdir}/init.d/
        ln -s ${libexecdir}/tq/u-boot-env-tq.sh ${D}${sysconfdir}/init.d/
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -Dm 0644 "${UNPACKDIR}/u-boot-env-tq.service" "${D}${systemd_system_unitdir}/u-boot-env-tq.service"
        sed -i -e "s,@LIBEXECDIR@,${libexecdir},g" "${D}${systemd_system_unitdir}/u-boot-env-tq.service"
    fi

    if [ -n "${TQ_FW_ENV_CONFIGS}" ]; then
        install -d "${D}${sysconfdir}"
        ln -s /run/tq/fw_env.config "${D}${sysconfdir}/fw_env.config"
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
