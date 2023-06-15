setenv rootpart 2

# devtype/devnum are part of the distroboot contract
if test "${devtype}" = mmc; then
	# Explicitly set device path, so the correct rootfs is used even when
	# the same image has been written to eMMC and SD-card
	setenv rootdev "/dev/mmcblk${devnum}p${rootpart}"
else
	# Generic fallback for other boot media like USB/SATA/... drives.
	# Requires a unique partition UUID to work as expected.
	part uuid ${devtype} ${devnum}:${rootpart} rootuuid
	setenv rootdev "PARTUUID=${rootuuid}"
fi
setenv bootargs_root "root=${rootdev}"

sysboot ${devtype} ${devnum}:${rootpart} any ${pxefile_addr_r} /boot/extlinux/extlinux.conf
