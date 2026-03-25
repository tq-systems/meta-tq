# Boot Device Initialisation and Update

This README documents how to write boostream images to different boot media and
how default U-Boot env supports update for development purpose.

[[_TOC_]]

## Boot Device Initialisation

### Bootstream Location on SD and eMMC

For SD-card and eMMC following table applies:

| CPU family | SD-card / eMMC user partition |   Block   | eMMC boot partition |   Block   |
|:----------:|:-----------------------------:|:---------:|:-------------------:|:---------:|
|    iMX6    |         1 KiB (0x400)         |  2 / 0x2  |    1 KiB (0x400)    |  2 / 0x2  |
|    iMX7    |         1 KiB (0x400)         |  2 / 0x2  |    1 KiB (0x400)    |  2 / 0x2  |
|   iMX8MQ   |        33 KiB (0x8400)        | 66 / 0x42 |   33 KiB (0x8400)   | 66 / 0x42 |
|   iMX8MM   |        33 KiB (0x8400)        | 66 / 0x42 |   33 KiB (0x8400)   | 66 / 0x42 |
|   iMX8MN   |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX8MP   |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX8X    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|    iMX8    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX91    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX93    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX94    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |
|   iMX95    |        32 KiB (0x8000)        | 64 / 0x40 |     0 KiB (0x0)     |  0 / 0x0  |

Note: iMX6 applies to all i.MX6, i.MX6UL and i.MX6ULL variants

Note: Blocks are in sizes of 512 Bytes

### Prerequisites for Block Devices

Compressed WIC images and matching BMAP-files (block map files) are created by default.
To make use of this feature, install the `bmap-tools` package to use `bmaptool`. Current
versions of `UUU` (https://github.com/nxp-imx/mfgtools) supports packed WIC and BMAP, too.
The packed WIC can also be decompressed and used with `dd` or other disk image tools.

**Attention**: when using `bmaptool` with BMAP-files keep in mind that only parts of the
image with valid information is written. As a consequence parts like U-Boot environment
may be left over from a previous version. If this could be an issue, use
`bmaptool copy --nobmap` or uncompress the wic-image and use `dd`.

### Bootable SD-Card

Write the `*.wic` image to SD-Card to create a bootable SD-Card with complete system image.
The following command can be used (the example assumes an SD-Card reader on PC and
`bmap-tools` package is installed):

```bash
bmaptool copy <image>.wic[.compress] --bmap <image>.bmap /dev/sd<y>
```

To create a bootable SD-Card with boot stream only (for exact file name see
SOM specific documentation), write bootstream image with correct
[offset](#bootstream-location-on-sd-and-emmc) to SD-Card:

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=<kiB offset> conv=fsync`

### Bootable eMMC

Write the `*.wic` image to eMMC user partition, offset 0x0 to create a bootable eMMC with
complete system image. The following command can be used (the example assumes running target
system with installed `bmaptool`):

```bash
bmaptool copy <image>.wic[.compress] /dev/mmcblk0
```

For provisioning etc. one can use `uuu` tool from NXPs `mfgtools`, too. See [here](./README.imx.UUU.md)
for details.

To create a bootable eMMC with boot stream only (for exact file name see
SOM specific documentation)

Boot with working boot source and write bootstream with correct [offset](#bootstream-location-on-sd-and-emmc)
to eMMC

Example for Linux:

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=<kiB offset> conv=fsync`

Example for U-Boot, see [SD-card / eMMC partition start block number](#bootstream-location-on-sd-and-emmc).

**Attention**: partition in this context means the eMMC hardware partitions. This is unrelated to MBR or
GPT partitions in eMMC user partition.

```
# assign bstart with start block number in hex
# use correct partition
# eMMC user partition: 0
# eMMC boot partition: 1 or 2
# assign partnum with partition number to use

setenv bstart <block number>
setenv partnum <part number>
tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0 ${partnum}
mmc write ${loadaddr} ${bstart} ${bsz}
mmc dev 0 0
```

### Bootable SPI-NOR on QSPI / FlexSPI / XSPI Controller

**Attention:** This documentation assumes that the boot image is prepended with a valid
firmware configuration block (FCB). This information is used by ROM loader
to initialise the SPI controller before loading the boot firmware

To create a bootable SPI-NOR with boot firmware only (for exact file name see
SOM specific documentation) the following steps are needed - the example
assumes using U-Boot, booted from SD-Card or via serial download mode:

```
tftp <bootstream>
sf probe
sf update ${loadaddr} 0 ${filesize}
```

## Update Components via U-Boot

For ease of development a set of variables and scripts are in default env.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
eMMC.

### U-Boot Environment Variables

* `uboot`: name of bootstream image (default = bootstream.bin)
* `mmcdev`: 0 for eMMC, 1 for SD-Card (automatically generated,
  can be overwritten)
* `mmcpart`: partition number for kernel and devicetree (default = 1)
* `mmcpath`: path to kernel and device tree (default = /)
* `fdtfile`: device tree blob (`fdt_file` on U-Boot based on version v2020.04)
* `image`: kernel image,
* `ubirootfspart`: name of ubi partition for rootfs  (default = ubi)
* `ubirootfsvol`: name of ubi volume for rootfs (default = rootfs)
* `ubimtdidx`: number or name of of mtd partition containing the ubi volume for
   rootfs (default = 3 or `ubi`)
* `ubirootfs`: name of ubifs image to be used for the update command
  (default = rootfs.ubifs)

_Attention_ UBI related variables have to be in sync with `mtdparts` and
`mtdids` variables.

### SD / eMMC

Download bootstream from TFTP and update:

`run update_uboot_mmc`

For eMMC this updates the U-Boot on the active Boot Partition or the USER Area.

### QSPI / FlexSPI / XSPI

Download bootstream from TFTP and update:

`run update_uboot_spi`

To use UBIFS on SPI NOR, __one time__ initialisation is needed:

`run prepare_ubi_part`

This carries out the following tasks:

- ubi partition creation
- ubi volume creation

Download UBIFS image from TFTP and update:

`run update_rootfs_spi`

__Note:__: set `ubirootfs` to the correct image name. This is usually the artifact with the extension
`.rootfs.ubifs`

## Booting Linux OS

To boot a Linux OS from a running U-Boot following scripts are implemented in
environment:

* `mmboot`: load kernel and dtb from SD/eMMC instance given with variable `mmcdev`
  * Boot device is SD / eMMC: `mcdev` is set to device index of the boot device if
    `mmcautodetect` is `yes` (default)
  * Boot device is not SD / eMMC: `mmcdev` has to be set before using `mmcboot`
* `ubiboot`: load kernel and dtb from UBIFS in default UBI volume and boots into
  ubifs rootfs in this volume
* `netboot`: load kernel and dtb using tftpboot and boots into rootfs on a NFS
  mount.
* `nfsboot`: load kernel and dtb from NFS and boots into rootfs on a NFS.
  Kernel and dtb are expected in the NFS rootfs (`${rootpath}/boot` on NFS server)

## Cortex M4/M7 Support

This section only applies to following CPU families:

| CPU family | Cortex M4 | Cortex M7  |
| ---------- | ----------| ---------- |
| iMX8MQ     |     x     |            |
| iMX8MMini  |     x     |            |
| iMX8MNano  |           |      x     |
| iMX8MPlus  |           |      x     |

Cortex M image: set env var `cm_image` to the name of your Cortex M image,
provide the file via TFTP and update on SD / eMMC via `run update_cm_mmc`
