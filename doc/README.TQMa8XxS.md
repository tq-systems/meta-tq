# TQMa8XxS

This README contains some useful information for TQMa8XxS on MB-SMARC-2

## Versions

### SCFW:

* version: tq-TQMa8.NXP-v1.3.1.B4124.0029

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
* GPIO
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
* Bootstreams
  * FlexSPI + SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* CPU variants i.MX8DX
* speed grade / temperature grade detection (current SCU limitation)

### Linux:

* RAM configs 2GB
* CPU variants i.MX8QXP
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* GPU
* ENET (GigE via Phy on TQMa8XxS)
  * ENET 1
  * ENET 2
* QSPI NOR
* UART
  * console
* LVDS
* GPU
* PCIe (mini-PCIe)

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
* imx-boot-tqma8xqps-mb-smarc-2-sd.bin: boot stream for SD / e-MMC + FlexSPI
* imx-boot-mfgtool-tqma8xqps-mb-smarc-2-mfgtool.bin-flash_spl: boot stream for UUU

## Boot Dip Switches

_Note:_

* S3 is for Boot Mode.
* X means position of DIP, - means don't care

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

_Serial Downloader_

```
            1 2 3 4
DIP (S3)    0 0 0 1
```

## SD-Card Boot

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/sd<x> bs=1k seek=32 conv=fsync`

## e-MMC Boot

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

write bootstream at offset 32 kiB (0x8000) to e-MMC

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

## QSPI Boot

### Write bootstream to QSPI

To install QSPI bootstream from U-Boot running on SD, copy the QSPI bootstream from
deploy folder onto SD-Card partition 1 (firmware partition) or load via tftp.
File name see above.

```
setenv uboot xxx.bin-flash_spl_flexspi
# load from firmware partition
load mmc 1:1 ${loadaddr} ${uboot}
# load via tftp
tftp ${uboot}
sf probe
sf update ${loadaddr} 0x00 ${filesize}
# optional verfify
sf read 0x80300000 0x00 ${filesize}
cmp.b 0x80300000 ${loadaddr} ${filesize}
```

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_U-Boot environment variables_

* `uboot`: name of bootstream image (aka flash.bin)
* `mmcdev`: 0 for e-MMC, 1 for SD-Card
* `fdt_file`: device tree blob,
* `image`: kernel image,

_SD / e-MMC_

Download bootstream from TFTP and update:

`run update_uboot_mmc`

Download device tree blob from TFTP and update:

`run update_fdt_mmc`

Download kernel image from TFTP and update:

`run update_kernel_mmc`

_FLEXSPI_

Download bootstream from TFTP and update:

`run update_uboot_spi`

