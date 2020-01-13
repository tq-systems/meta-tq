FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/${MACHINE}:${THISDIR}/${PN}:"

# all our boards have at least one native network port
SRC_URI = " \
    file://10-eth0.network \
    file://90-dhcp-default.network \
"

# TODO: [TQMAACHTX-76] cleanup and find an more general aproach
# TQMa8x / TQMa8Xx / TQMa8XxS has two native interfaces and two CAN interfaces
SRC_URI_append_tqma8x = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"
SRC_URI_append_tqma8xx = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"
SRC_URI_append_tqma8xxs = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMa8Mx has two native interfaces ...
SRC_URI_append_tqma8mq = " \
    file://10-eth1.network \
"

SYSTEMD_SERVICE_${PN}_tqma8x = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma8xx = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma8xxs = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma8mx = ""


do_install_append() {
    install -d ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/10-eth0.network" ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/90-dhcp-default.network" ${D}${systemd_unitdir}/network/

    case ${MACHINE} in
        tqma8x* )
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqma8qm* )
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqma8mq*)
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            break
            ;;
        *)
            break
            ;;
    esac
}

FILES_${PN} = "\
    ${systemd_system_unitdir} \
    ${systemd_unitdir}/network/ \
"
