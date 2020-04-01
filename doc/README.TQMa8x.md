# TQMa8x

This README contains some useful information for TQMa8x on MBa8x

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from rel\_imx\_4.14.98\_2.2.0\_ga / imx\_v2018.03\_4.14.98\_2.2.0\_ga

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from rel\_imx\_4.14.98\_2.2.0

## Supported Features

### U-Boot:

* RAM configs 4GB / 8GB
* CPU variants i.MX8QM
* Bootdevices: e-MMC / SD-Card / QSPI
* I2C
* GPIO
* USB 2.0 / USB Hub (needs modification on MBa8x)
* USB 3.0 / Micro B (see issues)
* Fuses
* ENET (GigE via Phy on MBa8x)
* QSPI

**TODO**

* speed grade / temperature grade detection
* generation of multiple bootstreams under yocto
* Cortex M4

### Linux:

* RAM configs 4GB / 8GB
* CPU variants i.MX8MQ
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
* USB 2.0 / USB Hub (needs modification on MBa8x)
* USB 3.0 / Micro B (see issues)
* PCIE
* ENET (GigE via Phy on MBa8x)
* GPU
* LVDS0/LVDS1
* GPIO LED and button
  * wakeup from GPIO button
* Display Port (including audio)
* SATA
* ADC
* FTM (PWM)
* UARTS on pin heads
* GPIO on pin heads
* Audio
* DVFS
  * speed grade
* QSPI NOR
* VPU
* Mikrobus (Module RTC5)
* PWM

**TODO**

* temperature grade detection
* HDMI in
* Cortex M4


## Known Issues

* sometimes hangs in linux during first start with a fresh image
* random hangs starting boot loader when cold boot (only SCU comes up)
* USB support in U-Boot
  * USB3 stick on micro-B (X29 needs usb start + usb reset)
  * USB3 ID pin handling not fully implemented
* USB support in Linux
  * USB3 ID pin handling not implemented (missing DRD/OTG support in driver)
* counting of i2c devices bus starts at i2c-2 (because i2c-0 and i2c-1
  are reserved for i2c_rpmsgbus)
* can bitrate limited to 125000 (in can0/can1.service), no CAN FD
  due to hardware limitations on mainboard Rev01xx
* environment from QSPI fails sometimes
* VPU codecs not avaiable [TQMAACHTX-103]
* PWM only works after the second enable command (echo 1 > /pwmX/enable)

## SD-Card Boot

### Dip Switches

```
      543210  
SW1 : 001100  
```

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

```
      543210  
SW1 : 001000  
```

### Bootable e-MMC

Write *.wic image to e-MMC (offset 0)

Example for Linux:

Boot from SD-Card, copy wic file to USB stick and write *.wic image at offset 0
to e-MMC.

`sudo dd if=<*.wic> of=/dev/mmcblk0 bs=4M conv=fsync`

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

```
      543210  
SW1 : 011110  
```

### Build with Yocto

*Note:* QSPI boot stream currently not built by default. Following manual
changes needed:

set `UBOOT_CONFIG ??= "fspi"` in `conf/machine/tqma8qm-4[8]gb-mba8x.conf`
set `IMXBOOT_TARGETS_tqma8x = "flash_flexspi"` in `dynamic-layers/fsl-bsp-release/recipes-bsp/imx-mkimage/imx-boot_0.2.bbappend`

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

## Test sleepmode and wakeup

Use rtc1 (on module) to wakeup after 20 seconds:
```
echo enabled > /sys/class/rtc/rtc1/device/power/wakeup
echo 0 > /sys/class/rtc/rtc1/wakealarm
echo +20 > /sys/class/rtc/rtc1/wakealarm
echo mem > /sys/power/state
```
Send linux to sleep mode and press one of the gpio buttons SWITCH\_A or SWITCH\_B afterwards

```
echo mem > /sys/power/state
```

## LVDS

Each LVDS output could be activated independend by using the corresponding devicetree.
The actual configuration works with the Tianma Display (tm070jvhg33).

|       |                                                |
|-------|------------------------------------------------|
| LVDS0 | fsl-imx8qm-tqma8qm-mba8x-lvds0-tm070jvhg33.dtb |
| LVDS1 | fsl-imx8qm-tqma8qm-mba8x-lvds0-tm070jvhg33.dtb |
