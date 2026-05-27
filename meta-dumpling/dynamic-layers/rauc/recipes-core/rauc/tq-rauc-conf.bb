# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer
#
# Based on rauc-conf.bb from meta-rauc.

SUMMARY = "RAUC system configuration & verification keyring (TQ example config)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

require example-files.inc

RAUC_KEYRING_URI ??= "file://${RAUC_KEYRING_FILE}"

RDEPENDS:${PN} += "util-linux-findmnt"
RPROVIDES:${PN} += "virtual-rauc-conf"

INHIBIT_DEFAULT_DEPS = "1"
do_compile[noexec] = "1"

RAUC_SYSTEM_CONF_VARIANTS = "mmc0 mmc1"

SRC_URI = " \
    ${RAUC_KEYRING_URI} \
    file://rauc-conf.sh \
    file://system.conf-mmc0 \
    file://system.conf-mmc1 \
    file://tq-rauc-conf.conf \
    file://tq-rauc-conf.service \
"

inherit systemd update-rc.d

SYSTEMD_SERVICE:${PN} = "tq-rauc-conf.service"

INITSCRIPT_NAME = "tq-rauc-conf.sh"
INITSCRIPT_PARAMS = "start 10 S ."

do_install () {
    install -d "${D}${sysconfdir}/rauc"
    install -m 0644 "${UNPACKDIR}/${RAUC_KEYRING_FILE}" "${D}${sysconfdir}/rauc/"
    ln -s /run/tq/rauc/system.conf "${D}${sysconfdir}/rauc/system.conf"

    for variant in ${RAUC_SYSTEM_CONF_VARIANTS}; do
        install -m 0644 "${UNPACKDIR}/system.conf-${variant}" "${D}${sysconfdir}/rauc/"
        sed -i \
            -e "s!@RAUC_BUNDLE_COMPATIBLE@!${RAUC_BUNDLE_COMPATIBLE}!g" \
            -e "s!@RAUC_KEYRING_FILE@!$(basename "${RAUC_KEYRING_FILE}")!g" \
            "${D}${sysconfdir}/rauc/system.conf-${variant}"
    done

    install -Dm 0755 "${UNPACKDIR}/rauc-conf.sh" "${D}${libexecdir}/tq/rauc-conf.sh"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
	    install -d ${D}${sysconfdir}/init.d/
        ln -s ${libexecdir}/tq/rauc-conf.sh ${D}${sysconfdir}/init.d/tq-rauc-conf.sh
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -Dm 0644 "${UNPACKDIR}/tq-rauc-conf.service" "${D}${systemd_system_unitdir}/tq-rauc-conf.service"
        sed -i -e "s,@LIBEXECDIR@,${libexecdir},g" "${D}${systemd_system_unitdir}/tq-rauc-conf.service"

        install -Dm 0644 "${UNPACKDIR}/tq-rauc-conf.conf" "${D}${systemd_system_unitdir}/rauc.service.d/10-tq-rauc-conf.conf"
        install -Dm 0644 "${UNPACKDIR}/tq-rauc-conf.conf" "${D}${systemd_system_unitdir}/rauc-mark-good.service.d/10-tq-rauc-conf.conf"
    fi
}

FILES:${PN} += "${systemd_system_unitdir}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
