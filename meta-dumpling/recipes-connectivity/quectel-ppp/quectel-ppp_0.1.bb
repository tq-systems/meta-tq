# These files are based on the details provided in
# Quectel_WCDMA&LTE_Linux_USB_Driver_User_Guide_V1.5.pdf and are shared
# as part of this layer with their permission.

SUMMARY = "PPP Scripts for Basic Quectel Chip Operation"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"
DESCRIPTION = "A set of Linux PPP scripts that provide connect, disconnect \
               and other basic Point-to-Point Protocol functionality for \
               Quectel wireless modems."

RDEPENDS_${PN} = "ppp"

SRC_URI = "\
    file://quectel-ppp.in \
    file://quectel-chat-connect.in \
    file://quectel-chat-disconnect \
"

S = "${WORKDIR}"

# set the Access Point Name (APN) of the SIM card
# set this variable in conf/local.conf
QUECTEL_PPP_APN ??= ""

# set the USB UART for AT commands of the wwan card
# default is /dev/ttyUSB2
# set this variable to the correct UART
QUECTEL_PPP_TTY ??= "/dev/ttyUSB2"

do_configure () {

    if [ -z "${QUECTEL_PPP_APN}" ]; then
	    bbwarn "APN of SIM card is not set for quectel-ppp! Set the APN in QUECTEL_PPP_APN."
    fi

    if [ -z "${QUECTEL_PPP_TTY}" ]; then
        bbwarn "USB UART of quectel wwan card is not set! Set the UART in QUECTEL_PPP_TTY."
    fi

    sed s#@QUECTEL_PPP_APN@#${QUECTEL_PPP_APN}#g ${WORKDIR}/quectel-chat-connect.in > ${S}/quectel-chat-connect
    sed s#@QUECTEL_PPP_TTY@#${QUECTEL_PPP_TTY}#g ${WORKDIR}/quectel-ppp.in > ${S}/quectel-ppp
}

inherit allarch

# Install script on target's root filesystem
do_install () {
    # Install init script and default settings
    # ${sysconfdir}
    install -d ${D}${sysconfdir}/
    install -d ${D}${sysconfdir}/ppp
    install -d ${D}${sysconfdir}/ppp/peers
    install -m 0755 ${S}/quectel-ppp ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-chat-connect ${D}${sysconfdir}/ppp/peers/
    install -m 0755 ${S}/quectel-chat-disconnect ${D}${sysconfdir}/ppp/peers/
}

# Mark the files which are part of this package
FILES_${PN} += "{sysconfdir}/ppp/peers/*"
