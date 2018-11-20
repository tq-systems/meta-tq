PPA_NAME = "${PN}-${MACHINE}"

DEPENDS += "swap-file-endianess-native tcl-native"

do_deploy_append () {
	tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${S}/ppa/soc-${PPA_PATH}/build/obj/ppa.itb ${DEPLOYDIR}/${PPA_NAME}.itb 8
}
