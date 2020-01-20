# TQMa8Xx

This README contains some useful information for TQMa8Xx on MBa8Xx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from rel\_imx\_4.14.98\_2.2.0\_ga / imx\_v2018.03\_4.14.98\_2.2.0\_ga

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from rel\_imx\_4.14.98\_2.2.0

## Supported Features

### U-Boot:

* RAM configs 1GB
* CPU variants i.MX8QXP
* Fuses
* GPIO
* QSPI
* I2C
* e-MMC / SD
  * Read
  * Write
  * Boot
* USB Hub
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2

**TODO or not tested with new BSP**

* RAM 512 MB
* CPU variants i.MX8DX
* speed grade / temperature grade detection
* create multiple Bootstreams for mmc and qspi

### Linux:

* RAM configs 1GB
* CPU variants i.MX8QXP
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* GPU
* LED
* GPIO
* LVDS
* DVFS
* PCIe (mini-PCIe on MBa8Xx)
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2

**TODO or not tested with new BSP**

* RAM configs 512 MB
* CPU variants i.MX8DX
* speed grade / temperature grade detection
* Beeper
* Audio
* DP bridge
* Suspend / Wakeup

## Known Issues

* USB Hub
  * HW Limitations (USB 2 / 3)
  * USB Hub erkennt unter U-Boot Stick erst bei zweiter Initialisierung
* QSPI not working correctly under Linux

## SD-Card Boot

### Dip Switches

MODE 3210
DIP  0123
S1 : 0011

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

### Dip Switches

MODE 3210
DIP  0123
S1 : 0010

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
mmc write 40 $bsz
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

### Dip Switches

MODE 3210
DIP  0123
S1 : 0110

### Build with Yocto

*Note:* QSPI boot stream currently not built by default. Following manual
changes needed:

set `UBOOT_CONFIG ??= "fspi"` in xxx.conf file
set `IMXBOOT_TARGETS_tqma8xx = "flash_flexspi"` in `imx-boot_0.2.bbappend`

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
