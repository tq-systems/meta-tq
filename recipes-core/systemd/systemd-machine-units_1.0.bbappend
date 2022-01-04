FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "\
    file://10-eth0.network \
    file://10-eth1.network \
    file://20-can0.network \
    file://20-can1.network \
    file://90-dhcp-default.network \
"

# most boards have a second ethernet port and 2 can interfaces
HAS_ETH1 ??= "true"
HAS_CAN0 ??= "true"
HAS_CAN1 ??= "true"

# adjust available interfaces for some boards
HAS_ETH1_tqma8mq ?= "false"
HAS_CAN0_tqma8mq ?= "false"
HAS_CAN1_tqma8mq ?= "false"

HAS_ETH1_tqma8mxml ?= "false"
HAS_CAN0_tqma8mxml ?= "false"
HAS_CAN1_tqma8mxml ?= "false"

HAS_ETH1_tqma8mxnl ?= "false"
HAS_CAN0_tqma8mxnl ?= "false"
HAS_CAN1_tqma8mxnl ?= "false"

HAS_ETH1_tqmls1028a ?= "false"

do_install_append() {
    # all our boards have at least one native network port
    install -d ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/10-eth0.network" ${D}${systemd_unitdir}/network/
    install -m 0644 "${WORKDIR}/90-dhcp-default.network" ${D}${systemd_unitdir}/network/

    if [ "${HAS_ETH1}" = "true" ]; then
        install -m 0644 "${WORKDIR}/10-eth1.network" ${D}${systemd_unitdir}/network/
    fi

    if [ "${HAS_CAN0}" = "true" ]; then
        install -m 0644 "${WORKDIR}/20-can0.network" ${D}${systemd_unitdir}/network/
    fi

    if [ "${HAS_CAN1}" = "true" ]; then
        install -m 0644 "${WORKDIR}/20-can1.network" ${D}${systemd_unitdir}/network/
    fi
}

FILES_${PN} = "\
    ${systemd_unitdir}/network/ \
"
