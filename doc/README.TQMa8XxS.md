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

* QSPI

**TODO, not tested with new BSP**

* RAM configs 1GB / 512 MB
* CPU variants i.MX8QXP / i.MX8DX
* Fuses
* speed grade / temperature grade detection
* GPIO
* I2C
* ENET (GigE via Phy on MBa8Xx)
* Bootdevices: e-MMC / SD-Card

### Linux:

* CAN
* QSPI
* LVDS

**TODO, not tested with new BSP**

* RAM configs 1GB / 512 MB
* CPU variants i.MX8QXP / i.MX8DX
* speed grade / temperature grade detection
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* GPU
* ENET (GigE via Phy on MBa8Xx)

## Known Issues

* only boot tested
* PCIe not working


## SD-Card Boot

### Dip Switches

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

**TODO**

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

S1 : 0110

### Build with Yocto

set UBOOT_CONFIG ??= "fspi" in xxx.conf file
set IMXBOOT_TARGETS_tqma8xx = "flash_flexspi" in imx-boot_0.2.bbappend

### Write bootstream to QSPI

- copy xxx.bin-flash_flexspi from deploy folder on sd card

Vom U-Boot aus: 
- fatload mmc 1:1 ${loadaddr} xxx.bin-flash_flexspi
- sf erase 0x0 ${filesize}
- sf write ${loadaddr} 0x00 ${filesize}
- (optional) sf read 0x80300000 0x00 ${filesize}
- (optional) cmp.b 0x80300000 ${loadaddr} ${filesize}

## LVDS (tm070jvhg33)

Hardware:
* Backlight Spannungsversorgung 12V (V_BKLT_OUT) über Brücke in Stecker X14 oder 0Ohm Wiederstand R4
* DIP Switch zur auswahl LVDS/eDP prüfen (S2)
* 3 Signale zur Backlight ansteuerung über smarc (LCD0_BKLT_EN, LCD0_VDD_EN, LCD0_BKLT_PWM)

Software:
* In Rev100 ist LCD0_BKLT_PWM aus Prozessor Pins gelegt für die es keinen pwm devicetree eintrag gibt
* LCD0_BKLT_PWM vorerst über gpio-pwm realisiert
* LCD0_VDD_EN wird über "reg_panel_vdd" geschaltet
* LCD0_BKLT_EN über "enable-gpios" des panel geschaltet


