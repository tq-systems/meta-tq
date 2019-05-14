FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# all our boards have at least one native network port
SRC_URI = " \
    file://10-eth0.network \
    file://90-dhcp-default.network \
"

# TQMLS1012AL has two native interfaces ...
SRC_URI_append_tqmls1012al = " \
    file://10-eth1.network \
"

SYSTEMD_SERVICE_${PN}_tqmls1012al = ""


do_install_append() {
    install -d ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/10-eth0.network" ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/90-dhcp-default.network" ${D}${systemd_unitdir}/network/

    case ${MACHINE} in
        tqmls1012al*)
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
