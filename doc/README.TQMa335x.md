# TQMa335x\[L\] on MBa335x carrier board

[[_TOC_]]

## Overview

### Supported Hardware

* TQMa335x: module revisions REV.020x 256/512 DDR3 memory variants
* TQMa335xL: module revisions REV.020x 256/512 DDR3 memory variants
* MBa335x: board revisions REV.020x

## Version information for software components

### Bootloader

* uboot-tq (Based on Mainline 2019.04)

### Kernel

* TI-linux-5.4.43 (Based on TI linux-5.4.y-07.00.00.005-rt)

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Known Issues

* TQMa335x[L] in MBa335x: sometimes after booting the ethernet phy shows
  up wrong addresses or can not be found. This needs a hardware fix on MBa335x.
  Please contact TQ-Systems support
* TQMa335xL: warnings for non exisiting chips when booting Linux. Disable
  RTC / EEPROM / temperature sensor in DT.
* The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO-${MACHINE}: U-Boot MLO (SPL image)
* u-boot-${MACHINE}.img: U-Boot image to be booted by MLO

## HowTo

### MBa335x DIP Switches

| Switch  | Description       |
| ------- | :---------------: |
| S1      | Oscilator         |
| S2      | Boot device       |
| S3      | CAN termination   |
| S4      | RS485 Termination |

### MBa335x DIP Switch settings for Boot

#### SD Card

Boot sequence: MMC0 (SD) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |     |  x  |     |     |     |     |
| OFF     |  x  |  x  |  x  |     |  x  |  x  |  x  |  x  |

#### e-MMC

Boot sequence: MMC1 (e-MMC) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |     |     |     |     |     |     |
| OFF     |     |     |  x  |  x  |  x  |  x  |  x  |  x  |

#### SPI NOR

Boot sequence: SPI0 (NOR) → MMC0 (SD) → USB0 (N/A) → UART0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |  x  |     |     |     |     |     |
| OFF     |     |     |     |  x  |  x  |  x  |  x  |  x  |

## Boot device initialisation and update

### Boot device initialisation

#### Bootable SD-Card

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with minimum boot image (for exact file name see
SOM specific documentation):

write *.wic.bootonly to SD (offset 0)

Example for Linux:

`sudo dd if=<image> of=/dev/sd<x> bs=4M conv=fsync`

#### Bootable e-MMC

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable SD-Card with minimum boot image (for exact file name see
SOM specific documentation):

write *.wic.bootonly to e-MMC (offset 0)

Example for Linux:

`sudo dd if=<image> of=/dev/mmcblk1 bs=4M conv=fsync`

Example for U-Boot:

```
tftp <image>
setexpr bsz ${filesize} + 0x1ff
setexpr bsz ${bsz} / 0x200
printenv bsz
mmc dev 1
mmc write ${loadaddr} 0 ${bsz}
```

#### Bootable SPI NOR

To create a bootable SPI NOR with boot loader only

Example for U-Boot, booting from SD-Card:

```
sf probe
tftp MLO.bytesawp
sf update ${loadaddr} 0 ${filesize}
tftp u-boot.img
sf update ${loadaddr} 0x20000 ${filesize}
```

### Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
e-MMC.

#### U-Boot environment variables

* `uboot`: name of U-Boot payload image for SD / e-MMC (default = u-boot.img)
* `mlo`: name of U-Boot SPL image for SD / e-MMC (default = MLO)
* `uboot_spi`: name of U-Boot payload image for SPI flash (default = u-boot.img)
* `mlo_spi`: name of U-Boot SPL image for SPI flash (default = MLO.byteswap)
* `mmcdev`: 1 for e-MMC, 2 for SD-Card (automatically generated when booting
   from SD / e-MMC with the index of the boot device, can be overwritten;
   must be set if needed when booting from SPI NOR)
* `fdtfile`: device tree blob,
* `bootfile`: kernel image,

#### SD / e-MMC

Download bootloader from TFTP and update (make sure `mmcdev` is correctly set):

```
run update_uboot_mmc
```

#### SPI

Download bootloader from TFTP and update:

`run update_uboot_spi`

### Booting Linux OS

To boot a Linux OS from a running U-Boot following scripts are implemented in
environment:

* `mmcboot`: load kernel and dtb from SD/e-MMC instance given with variable `mmcdev`
  * Boot device is SD / e-MMC: `mmcdev` is set to device index of the boot device if
    `mmcautodetect` is `yes` (default)
  * Boot device is not SD / e-MMC: `mmcdev` has to be set before using `mmcboot`
* `netboot`: load kernel and dtb using tftpboot and boots into rootfs on a NFS
  mount.

## Support Wiki

See [TQ Embedded Wiki for TQMa335x](https://support.tq-group.com/en/arm/tqma335x)
