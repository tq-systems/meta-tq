# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer

SUMMARY = "Automatic mount of a data partition for TQ platforms"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch systemd update-rc.d

SRC_URI = "\
    file://tq-mount-data.service \
    file://tq-mount-data.sh \
"
TQ_DATA_MOUNT_TARGET ?= "/srv/data"

SYSTEMD_SERVICE:${PN} = "tq-mount-data.service"

INITSCRIPT_NAME = "tq-mount-data.sh"
INITSCRIPT_PARAMS = "start 05 S ."

RDEPENDS:${PN} += "util-linux-findmnt"

do_install() {
    install -Dm 755 "${UNPACKDIR}/tq-mount-data.sh" "${D}${libexecdir}/tq/mount-data.sh"
    sed -i -e "s,@TQ_DATA_MOUNT_TARGET@,${TQ_DATA_MOUNT_TARGET},g" "${D}${libexecdir}/tq/mount-data.sh"

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
	    install -d ${D}${sysconfdir}/init.d/
        ln -s ${libexecdir}/tq/mount-data.sh ${D}${sysconfdir}/init.d/tq-mount-data.sh
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -Dm 0644 "${UNPACKDIR}/tq-mount-data.service" "${D}${systemd_system_unitdir}/tq-mount-data.service"
        sed -i -e "s,@LIBEXECDIR@,${libexecdir},g" "${D}${systemd_system_unitdir}/tq-mount-data.service"
    fi

    install -d "${D}${TQ_DATA_MOUNT_TARGET}"
}
FILES:${PN} += "${TQ_DATA_MOUNT_TARGET}"
