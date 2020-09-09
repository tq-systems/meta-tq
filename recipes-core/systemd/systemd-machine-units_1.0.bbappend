FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# all our boards have at least one native network port
SRC_URI = " \
    file://10-eth0.network \
    file://90-dhcp-default.network \
"

# TQMa6x has two native interfaces and two CAN interfaces
SRC_URI_append_tqma6x = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMa6ULx has two native interfaces and two CAN interfaces
SRC_URI_append_tqma6ulx = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMa6ULLx has two native interfaces and two CAN interfaces
SRC_URI_append_tqma6ullx = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMa7x has two native interfaces and two CAN interfaces
SRC_URI_append_tqma7x = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMLS102xa has two native interfaces and two CAN interfaces
SRC_URI_append_tqmls102xa = " \
    file://10-eth1.network \
    file://can0.service \
    file://can1.service \
"

# TQMLS1028a has two CAN interfaces
SRC_URI_append_tqmls1028a = " \
    file://can0.service \
    file://can1.service \
"

SYSTEMD_SERVICE_${PN}_tqma6x = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma6ulx = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma6ullx = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqma7x = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqmls102xa = "can0.service can1.service"
SYSTEMD_SERVICE_${PN}_tqmls1028a = "can0.service can1.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/10-eth0.network" ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/90-dhcp-default.network" ${D}${systemd_unitdir}/network/

    case ${MACHINE} in
        # place complete machine names first to make sure special settings
        # are prioritized
        tqma6dl* |\
        tqma6q* |\
        tqma6qp* |\
        tqma6s* )
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqma6ulx* )
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqma6ullx* )
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqma7x*)
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqmls102xa*)
            install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
            break
            ;;
        tqmls1028a*)
            install -d ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can0.service" ${D}${systemd_system_unitdir}/
            install -m 0644 "${WORKDIR}/can1.service" ${D}${systemd_system_unitdir}/
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
