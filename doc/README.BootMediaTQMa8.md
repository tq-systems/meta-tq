# Boot device initialisation and update

This README documents how to write boostream images to different boot media and
how default U-Boot env supports update for development purpose.

[[_TOC_]]

## Boot device initialisation

### Bootable SD-Card

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (for exact file name see
SOM specific documentation):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=32 conv=fsync`

### Bootable e-MMC

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (for exact file name see
SOM specific documentation)

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 blocks -> 0x40

tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

### Bootable QSPI NOR

To create a bootable QSPI NOR with boot stream only (for exact file name see
SOM specific documentation)

Example for U-Boot, booting from SD-Card:

```
tftp <bootstream>
sf probe
sf update ${loadaddr} 0 ${filesize}
```

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
e-MMC.

### U-Boot environment variables

* `uboot`: name of bootstream image (default = bootstream.bin)
* `mmcdev`: 0 for e-MMC, 1 for SD-Card (automatically generated,
  can be overwritten)
* `mmcpart`: partition number for kernel and devicetree (default = 1)
* `mmcpath`: path to kernel and device tree (default = /)
* `fdt_file`: device tree blob,
* `image`: kernel image,
* `ubirootfspart`: name of ubi partition for rootfs  (default = ubi)
* `ubirootfsvol`: name of ubi volume for rootfs (default = rootfs)
* `ubimtdidx`: number of ubi partition for rootfs (default = 3)

_Attention_ UBI related variables have to be in sync with `mtdparts` and
`mtdids` variables.

### SD / e-MMC

Download bootstream from TFTP and update:

`run update_uboot_mmc`

Download device tree blob from TFTP and update:

`run update_fdt_mmc`

Download kernel image from TFTP and update:

`run update_kernel_mmc`

### FLEXSPI

Download bootstream from TFTP and update:

`run update_uboot_spi`

To use UBIFS on SPI NOR, one time initialisation is needed:

`run prepare_ubi_part`

This carries out the following tasks:

- ubi partition creation
- ubi volume creation

Download UBIFS image from TFTP and update:

`run update_rootfs_spi`
