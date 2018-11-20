DEPENDS += "swap-file-endianess-native tcl-native"

do_deploy_append () {
	tclsh ${STAGING_BINDIR_NATIVE}/byteswap.tcl ${D}/boot/engine-pfe-bin/pfe_fw_sbl.itb ${DEPLOYDIR}/engine-pfe-bin/pfe_fw_sbl.itb 8
}
