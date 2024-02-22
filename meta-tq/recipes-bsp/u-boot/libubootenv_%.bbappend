# install config file for U-Boot env tools
RSUGGESTS:${PN} += "u-boot-default-env"

# This should be removed when all machines supported by meta-tq can use
# libubootenv
do_install:append() {
	#
	# For compatibility with legacy applications based on U-Boot supplied
	# env utils. U-Boot supplied env utils are normally deployed as
	# fw_printenv/fw_setenv under /sbin. To be compatible add a link so that
	# libubootenv utils can be used the same way as the older U-Boot utils.
	#
	install -d ${D}${base_sbindir}
	ln -sf ${bindir}/fw_printenv ${D}${base_sbindir}/fw_printenv
	ln -sf ${bindir}/fw_setenv ${D}${base_sbindir}/fw_setenv
}
