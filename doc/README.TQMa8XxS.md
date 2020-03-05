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
* CPU variant i.MX8QXP
* QSPI
  * Read
  * Write
  * Boot
* SD
  * Read
  * Write
  * Boot
* e-MMC
  * Read
  * Write
  * Boot
* GPIO
* I2C
* ENET 1/2
* Fuses
* USB (see known issues)

**TODO or not tested with new BSP**

* Fuses
* RAM configs 512 MB
* CPU variants i.MX8DX
* speed grade / temperature grade detection
* create multiple Bootstreams for mmc and qspi

### Linux:

* RAM configs 1GB
* CPU variants i.MX8QXP
* e-MMC
* SD-CARD
* DVFS
  * speed grade
* CAN
* LVDS (Tianma 1240 x 800, GPIO Backlight)
* ENET 1/2
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* GPU
* USB (see known issues)
  * DRD (X4)
* QSPI NOR 

**TODO or not tested with new BSP**

* RAM configs 512 MB
* CPU variants i.MX8DX
* temperature grade detection
* VPU
* DSI - DP bridge
* M4
* Audio
* SPI

## Known Issues

* PCIe not working
* USB OTG (X4): Overcurrent detection does not work (hardware limitation)
* USB HUB: all ports signals overcurrent (hardware limitation)

## SD-Card Boot

### Dip Switches

     1234
S3 : 0110

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

     1234
S3 : 1000

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
mmc write ${loadaddr} 40 $bsz
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

     1234
S3 : 1100

### Build with Yocto

*Note:* QSPI boot stream currently not built by default. Following manual
changes needed:

set `UBOOT_CONFIG ??= "fspi"` in `conf/machine/tqma8xqps[xds]-mb-smarc-2.conf.conf`
set `IMXBOOT_TARGETS_tqma8xxs = "flash_flexspi"` in `dynamic-layers/fsl-bsp-release/recipes-bsp/imx-mkimage/imx-boot_0.2.bbappend`

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

## LVDS (tm070jvhg33)

*Hardware requirements:*

* Backlight supply 12V (V_BKLT_OUT) via bridge in connector X14 or 0Ohm 
  resistance R4
* DIP switch (S2) to select LVDS/eDP
* 3 signals for backlight control (LCD0\_BKLT\_EN, LCD0\_VDD\_EN, LCD0\_BKLT\_PWM)

*Software:*

* with hardware REV.010x `LCD0_BKLT_PWM` is routed to pins that are not pwm 
  capable yet - therefore `LCD0_BKLT_PWM` is bound to gpio-backlight driver
* `LCD0_VDD_EN` is controlled via regulator device `reg_panel_vdd`
* `LCD0_BKLT_EN` is controlled via `enable-gpios` of panel device
