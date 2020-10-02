LIC_FILES_CHKSUM = "file://PRU-Package-v6.1-Manifest.html;md5=1e37797ebe9254922f4278bb6047211c"

BRANCH = "master"
SRC_URI = "git://git.ti.com/pru-software-support-package/pru-software-support-package.git;protocol=git;branch=${BRANCH}"
SRCREV = "a9bff6f43001cf66dc1ed3ef7e9dfb688b67f7bb"

PV = "5.7.0"
PR = "r0"

COMPATIBLE_MACHINE = "tqma65xx"

PLATFORM_tqma65xx = "am65x-sr2"

do_install_append_tqma65xx() {
    for i in 0 1 
    do
        install -m 644 ${S}/examples/${PLATFORM}/PRU_Halt/gen/PRU${i}/PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/RTU_Halt/gen/RTU${i}/RTU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
        install -m 644 ${S}/examples/${PLATFORM}/TX_PRU_Halt/gen/TX_PRU${i}/TX_PRU_Halt_${i}.out \
                   ${D}${base_libdir}/firmware/pru
    done
    for i in 0 1 2
    do
        for j in 0 1
        do
            install -m 0644 ${S}/examples/am65x/PRU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/PRU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru
            install -m 0644 ${S}/examples/am65x/RTU_RPMsg_Echo_Interrupt${j}/gen/icssg${i}/RTU_RPMsg_Echo_Interrupt${i}_${j}.out \
                            ${D}${base_libdir}/firmware/pru

        done
    done
}
PRU_ICSS_ALTERNATIVES_tqma65xx   = "am65x-pru0_0-fw am65x-pru0_1-fw am65x-pru1_0-fw am65x-pru1_1-fw am65x-pru2_0-fw am65x-pru2_1-fw am65x-rtu0_0-fw am65x-rtu0_1-fw am65x-rtu1_0-fw am65x-rtu1_1-fw am65x-rtu2_0-fw am65x-rtu2_1-fw"

# Set up link names for the firmwares
ALTERNATIVE_LINK_NAME[am65x-pru0_0-fw] = "${base_libdir}/firmware/am65x-pru0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru0_1-fw] = "${base_libdir}/firmware/am65x-pru0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-pru1_0-fw] = "${base_libdir}/firmware/am65x-pru1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru1_1-fw] = "${base_libdir}/firmware/am65x-pru1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-pru2_0-fw] = "${base_libdir}/firmware/am65x-pru2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-pru2_1-fw] = "${base_libdir}/firmware/am65x-pru2_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu0_0-fw] = "${base_libdir}/firmware/am65x-rtu0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu0_1-fw] = "${base_libdir}/firmware/am65x-rtu0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu1_0-fw] = "${base_libdir}/firmware/am65x-rtu1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu1_1-fw] = "${base_libdir}/firmware/am65x-rtu1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu2_0-fw] = "${base_libdir}/firmware/am65x-rtu2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-rtu2_1-fw] = "${base_libdir}/firmware/am65x-rtu2_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru0_0-fw] = "${base_libdir}/firmware/am65x-txpru0_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru0_1-fw] = "${base_libdir}/firmware/am65x-txpru0_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru1_0-fw] = "${base_libdir}/firmware/am65x-txpru1_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru1_1-fw] = "${base_libdir}/firmware/am65x-txpru1_1-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru2_0-fw] = "${base_libdir}/firmware/am65x-txpru2_0-fw"
ALTERNATIVE_LINK_NAME[am65x-txpru2_1-fw] = "${base_libdir}/firmware/am65x-txpru2_1-fw"

# Only Halt firmware images are supported for the Tx_PRU cores
ALTERNATIVE_pru-icss-halt_append_tqma65xx = " am65x-txpru0_0-fw am65x-txpru0_1-fw am65x-txpru1_0-fw am65x-txpru1_1-fw am65x-txpru2_0-fw am65x-txpru2_1-fw"

ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu2_0-fw] = "${base_libdir}/firmware/pru/RTU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-rtu2_1-fw] = "${base_libdir}/firmware/pru/RTU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru0_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru0_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru1_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru1_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru2_0-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_0.out"
ALTERNATIVE_TARGET_pru-icss-halt[am65x-txpru2_1-fw] = "${base_libdir}/firmware/pru/TX_PRU_Halt_1.out"

# Create the pru-icss-rpmsg-echo firmware alternatives
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru0_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru0_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru1_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru1_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru2_0-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-pru2_1-fw] = "${base_libdir}/firmware/pru/PRU_RPMsg_Echo_Interrupt2_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu0_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu0_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt0_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu1_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu1_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt1_1.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu2_0-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt2_0.out"
ALTERNATIVE_TARGET_pru-icss-rpmsg-echo[am65x-rtu2_1-fw] = "${base_libdir}/firmware/pru/RTU_RPMsg_Echo_Interrupt2_1.out"
