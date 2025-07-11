# TQMa335x\[L\]

This README contains some useful information for TQMa335x\[L\] on MBa335x

[[_TOC_]]

## Variants

* TQMa335x / TQMa335xL REV.020x 256 MiB DDR3
* TQMa335x / TQMa335xL REV.020x 512 MiB DDR3
* MBa335x REV.020x

## Version information for software components

### U-Boot

* uboot-tq (Based on Mainline 2019.04)

### Linux

* TI-linux-5.4.257-rt87 (Based on TI linux-5.4.y-07.00.00.005-rt)

## Supported machine configurations

See top level README.md for configurations usable as MACHINE.

## Known Issues

* TQMa335x[L] on MBa335x: sometimes after booting the ethernet phy shows
  up wrong addresses or can not be found. This needs a hardware fix on MBa335x.
  Please contact TQ-Systems support
* TQMa335x[L] on MBa335x: U-Boot: only the first port of the CPSW is working.
  This is a driver limitation in the used U-Boot version, under Linux both ports
  are working.
* TQMa335xL: warnings for non exisiting chips when booting Linux. Disable
  RTC / EEPROM / temperature sensor in DT.
* The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO-${MACHINE}: U-Boot MLO (SPL image for SD / eMMC)
* MLO-${MACHINE}.byteswap: U-Boot MLO (SPL image for SPI NOR flash)
* u-boot-${MACHINE}.img: U-Boot image to be booted by MLO

## DIP Switches

| Switch  | Description       |
| ------- | :---------------: |
| S1      | Oscilator         |
| S2      | Boot device       |
| S3      | CAN termination   |
| S4      | RS485 Termination |

### Boot sequence

#### SD Card

Boot sequence: MMC0 (SD) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |     |  x  |     |     |     |     |
| OFF     |  x  |  x  |  x  |     |  x  |  x  |  x  |  x  |

#### eMMC

Boot sequence: MMC1 (eMMC) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

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

## Boot device initialisation

### Prerequisites for block devices

Compressed WIC images and matching BMAP-files (block map files) are created by default.
To make use of this feature, install the `bmap-tools` package to use `bmaptool`.
The packed WIC can also be decompressed and used with `dd` or other disk image tools.

### Bootable SD-Card

Write the `*.wic` image to SD-Card to create a bootable SD-Card with complete system image.
The following command can be used (the example assumes an SD-Card reader on PC and
`bmap-tools` package is installed):

```bash
bmaptool copy <image>.wic[.compress] --bmap <image>.bmap /dev/sd<x>
```

To create a bootable SD-Card with boot stream only write `*.wic.bootonly` to SD (offset 0x0)

Example for Linux:

`sudo dd if=<image> of=/dev/sd<x> bs=4M conv=fsync`

### Bootable eMMC

To create a bootable eMMC with complete system image use the generated
[wic image](#artifacts):

write *.wic image to eMMC (offset 0)

To create a bootable eMMC with minimum boot image use the generated
[minimal wic image](#artifacts):

write *.wic.bootonly to eMMC (offset 0)

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

### Bootable SPI NOR

To create a bootable SPI NOR with boot loader only use the generated
[bootloader images](#artifacts). Example for U-Boot, booting from SD-Card:

```
sf probe
tftp MLO.byteswap
sf update ${loadaddr} 0 ${filesize}
tftp u-boot.img
sf update ${loadaddr} 0x20000 ${filesize}
```

## Update components via U-Boot

### U-Boot environment variables

For ease of development a set of variables and scripts are in default env.
Depending on your configuration some variable values needs to bet changend
to the right values. For files to use see the [artifacts](#artifacts) section.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
eMMC.

* `uboot`: name of U-Boot payload image for SD / eMMC (default = u-boot.img)
* `mlo`: name of U-Boot SPL image for SD / eMMC (default = MLO)
* `uboot_spi`: name of U-Boot payload image for SPI flash (default = u-boot.img)
* `mlo_spi`: name of U-Boot SPL image for SPI flash (default = MLO.byteswap)
* `mmcdev`: 1 for eMMC, 2 for SD-Card (automatically generated when booting
   from SD / eMMC with the index of the boot device, can be overwritten;
   must be set if needed when booting from SPI NOR)
* `fdtfile`: device tree blob,
* `bootfile`: kernel image,

### SD / eMMC

Download bootloader from TFTP and update (make sure `mmcdev` is correctly set):

```
run update_uboot_mmc
```

### SPI

Download bootloader from TFTP and update:

`run update_uboot_spi`

## HowTo

### Booting Linux OS

To boot a Linux OS from a running U-Boot following scripts are implemented in
environment:

* `mmcboot`: load kernel and dtb from SD/eMMC instance given with variable `mmcdev`
  * Boot device is SD / eMMC: `mmcdev` is set to device index of the boot device if
    `mmcautodetect` is `yes` (default)
  * Boot device is not SD / eMMC: `mmcdev` has to be set before using `mmcboot`
* `netboot`: load kernel and dtb using tftpboot and boots into rootfs on a NFS
  mount.

## Support Wiki

See [TQ Embedded Wiki for TQMa335x](https://support.tq-group.com/en/arm/tqma335x)
