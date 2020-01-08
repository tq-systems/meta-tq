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
* Bootdevices: e-MMC / SD-Card
* I2C
* GPIO
* USB

**TODO**

* speed grade / temperature grade detection
* Fuses
* ENET (GigE via Phy on MBa8x)
* QSPI Boot

### Linux:

* RAM configs 4GB / 8GB
* CPU variants i.MX8MQ
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS

**TODO**

* speed grade / temperature grade detection
* GPU
* LVDS
* ENET (GigE via Phy on MBa8x)
* USB
* PCIE
* HDMI

## Known Issues

* sometimes hangs during first start with a fresh image
* random hangs starting boot loader when cold boot (only SCU comes up)
* USB support in U-Boot
  * USB3 stick on micro-B (X29 needs usb start + usb reset)
  * USB3 ID pin handling not fully implemented

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
``

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

**TODO**

### Dip Switches

```
      543210  
SW1 :   
```

