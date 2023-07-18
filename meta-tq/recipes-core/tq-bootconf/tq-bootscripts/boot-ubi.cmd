setenv bootargs_root "rootfstype=ubifs ubi.mtd=${rootfs_part_f} root=ubi0:root"

ubi part ${rootfs_part_f}
ubifsmount ubi0:root
sysboot ubi ubi0:root any ${pxefile_addr_r} /boot/extlinux/extlinux.conf
unifsumount
