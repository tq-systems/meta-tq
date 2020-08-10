# TQMa8XxS

This README contains some useful information for TQMa8XxS on MB-SMARC-2

## Versions

### SCFW:

* version: tq-TQMa8.NXP-v1.3.1.B4124.0028

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from lf-5.4.y-1.0.0

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from lf-5.4.y-1.0.0

## Supported Features

### U-Boot:

* RAM configs: 2GB
* CPU variants: i.MX8QXP
* Fuses
* QSPI
* I2C
* e-MMC / SD
  * Read
  * Write
  * Boot
* USB Hub
* USB OTG
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2

**TODO or not tested / supported**

* CPU variants i.MX8DX
* speed grade / temperature grade detection
* create Bootstreams for qspi (see chapter QSPI Boot)
* GPIO

### Linux:

* RAM configs 2GB
* CPU variants i.MX8QXP
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* GPU
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2
* QSPI NOR
* LVDS
* PCIe (mini-PCIe only)

**TODO or not tested with new BSP**

* temperature grade
* Audio
* DSI - DP bridge
* LED
* GPIO
  * Suspend / Wakeup via GPIO button
* DVFS
  * speed grade
* SPI
  * spi user space device on all CS

## Known Issues

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/tqma8xqps-mb-smarc-2`

* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-tqma8xqps-mb-smarc-2-sd.bin: boot stream for SD / e-MMC

## Boot Dip Switches

_Note:_

* S3 is for Boot Mode.

_SD Card_

```
            1 2 3 4
DIP (S3)    0 1 1 0

```

_e-MMC_

```
            1 2 3 4
DIP (S3)    1 0 0 0
```

_QSPI_

```
            1 2 3 4
DIP (S3)    1 1 0 0
```

## SD-Card Boot

### Bootable SD-Card

Complete system image:

write *.wic Image to SD (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/sd<x> bs=1k seek=32 conv=fsync`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

## e-MMC Boot

### Bootable e-MMC

write *.wic image to e-MMC (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 blocks -> 0x40

tftp <bootstream>
setexpr bsz $filesize + 1ff
setexpr bsz $bsz / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

### Update components via U-Boot

To update components on boot media following u-boot environment scripts are
prepared. These can be used to update the items using a network connection.

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

## QSPI Boot

### Build with Yocto

*Note:* QSPI boot stream currently not built by default. Following manual
changes needed:

set `UBOOT_CONFIG ??= "fspi"` in `conf/machine/tqma8xqp[xd]-mbpa8xx.conf`

Find the resulting QSPI bootstream image in the deploy folder named as
`xxx.bin-flash_flexspi`.

### Write bootstream to QSPI

To install QSPI bootstream from U-Boot running on SD, copy the QSPI bootstream from
deploy folder onto SD-Card partition 1 (firmware partition) or load via tftp

```
setenv uboot xxx.bin-flash_flexspi
# load from firmware partition
load mmc 1:1 ${loadaddr} ${uboot}
# load via tftp
tftp ${uboot}
sf probe
sf erase 0x0 100000
sf write ${loadaddr} 0x00 ${filesize}
# optional verfify
sf read 0x80300000 0x00 ${filesize}
cmp.b 0x80300000 ${loadaddr} ${filesize}
```
