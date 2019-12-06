# TQMa8Mx

This README contains some useful information for TQMa8Mx on MBa8Mx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from rel\_imx\_4.14.98\_2.2.0\_ga / imx\_v2018.03\_4.14.98\_2.2.0\_ga

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from rel\_imx\_4.14.98\_2.2.0

## Supported Features

### U-Boot:

_MBa8x HW Rev.020x only_

* RAM configs 1GB / 2 GB
* CPU variants i.MX8MQ / i.MX8MQL
* Fuses
* speed grade / temperature grade detection
* GPIO
  * LED
  * Button
  * BOOT_CFG
  * MUX CFG
* I2C
  * system EEPROM parsing
  * GPIO expander
* ENET (GigE via Phy on MBa8Mx)
* Bootdevices: e-MMC / SD-Card
* HDMI (fixed resolution)
* QSPI NOR (4 byte adressing, SPI mode)
* USB Host (USB1 via hub 2.0 and 3.0)
* USB Dual Role (USB0, 2.0 host only - hard coded in driver)
  * Cable Detect / ID
  * switchable VBUS

### Linux:

* RAM configs 1GB / 2 GB
* CPU variants i.MX8MQ / i.MX8MQL
* speed grade / temperature grade detection
* DVFS (CPU overdrive mode)
* suspend (deep / s2idle)
* I2C
  * Temperature Sensors
  * RTC (with wakealarm)
  * EEPROMS
  * GPIO expanders
* GPIO
  * LED
  * Button
  * HOG
* QSPI NOR
* ENET (GigE via Phy on MBa8Mx)
* Audio
  * via codec
  * HDMI audio (with pulse audio)
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 2.0 OTG only, Cable Detect, VBUS)
* HDMI
* GPU
* DSI
  * LVDS
* PCIe
  * mini-PCIe on MBa8Mx
  * PCIe (Slot)

## TODO:

* DSI to DP bridge
* DSI over LCDIF
* VPU not tested
* MIPI CSI
* MIKRO Bus
* SIM
* PCIe only tested with Gen 1

## Known Issues

* HDMI does not work after reboot
* reboot after SD3 / USDHC activated under linux does not work
* LVDS shows wrong colors on Tianma display kit (HW issue on display)
* USB OTG / DRD
  * USB OTG OC not handled yet
  * instable ID / Cable detect for open receptable
  * no USB 3.0

## SD-Card Boot

### Dip Switches

S6 : 11010111  
S5 : 11111111  
S7 : 1111  
S8 : 0010  
S9 : 0101  
S10: 0000  

### Bootable SD-Card

Complete system image:

write *.wic Image to SD (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

write bootstream at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

## e-MMC Boot

### Dip Switches

S6 : 11111011  
S5 : 11111111  
S7 : 1111  
S8 : 0010  
S9 : 0101  
S10: 0000  

### Bootable e-MMC

write *.wic image to e-MMC (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

Boot from SD-Card and write bootstream at offset 33 kiB (0x8400) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

Example for U-Boot:

```
# 33k -> 66 Blocks -> 0x42

tftp <bootstream>
setexpr bsz $filesize + 1ff
setexpr bsz $bsz / 200
printenv bsz
mmc dev 0
mmc write 42 $bsz
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

