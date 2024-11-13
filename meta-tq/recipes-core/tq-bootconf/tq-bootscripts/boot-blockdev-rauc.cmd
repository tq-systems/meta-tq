test -n "${BOOT_ORDER}" || setenv BOOT_ORDER "A B"
test -n "${BOOT_A_LEFT}" || setenv BOOT_A_LEFT 3
test -n "${BOOT_B_LEFT}" || setenv BOOT_B_LEFT 0

rootpart=

for BOOT_SLOT in "${BOOT_ORDER}"; do
	if test -n "${rootpart}"; then
		# skip remaining slots
	elif test "${BOOT_SLOT}" = "A"; then
		if test ${BOOT_A_LEFT} -gt 0; then
			echo "Found valid slot A, ${BOOT_A_LEFT} attempts remaining"
			setexpr BOOT_A_LEFT ${BOOT_A_LEFT} - 1
			bootslot=A
			rootpart=2
		fi
	elif test "${BOOT_SLOT}" = "B"; then
		if test ${BOOT_B_LEFT} -gt 0; then
			echo "Found valid slot B, ${BOOT_B_LEFT} attempts remaining"
			setexpr BOOT_B_LEFT ${BOOT_B_LEFT} - 1
			bootslot=B
			rootpart=3
		fi
	fi
done

if test -z "${rootpart}"; then
	echo "No valid slot found, resetting tries to 3"
	setenv BOOT_A_LEFT 3
	setenv BOOT_B_LEFT 3
	saveenv
	reset
fi

saveenv

# devtype/devnum are part of the distroboot contract
if test "${devtype}" = mmc; then
	# Explicitly set device path, so the correct rootfs is used even when
	# the same image has been written to eMMC and SD-card
	rootdev="/dev/mmcblk${devnum}p${rootpart}"
else
	# Generic fallback for other boot media like USB/SATA/... drives.
	# Requires a unique partition UUID to work as expected.
	part uuid ${devtype} ${devnum}:${rootpart} rootuuid
	rootdev="PARTUUID=${rootuuid}"
fi
setenv bootargs_root "root=${rootdev} rauc.slot=${bootslot}"

sysboot ${devtype} ${devnum}:${rootpart} any ${pxefile_addr_r} /boot/extlinux/extlinux.conf

reset
