# TQMa8Mx

This README contains some useful information for TQMa8Mx on MBa8Mx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from rel_imx_4.14.78_1.0.0_ga / imx_v2018.03_4.14.78_1.0.0_ga

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from rel_imx_4.14.98_2.2.0

## Supported Features

### U-Boot:

* RAM configs 1GB / 2 GB
* CPU variants i.MX8MQ / i.MX8MQL
* Fuses
* speed grade / temperature grade detection
* GPIO
* I2C
* ENET (GigE via Phy on MBa8Mx)
* Bootdevices: e-MMC / SD-Card
* HDMI (fixed resolution)

### Linux:

* RAM configs 1GB / 2 GB
* CPU variants i.MX8MQ / i.MX8MQL
* speed grade / temperature grade detection
* DVFS (CPU overdrive mode)
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* HDMI
* GPU
* LVDS (needs DSI to LVDS Adapter)
* ENET (GigE via Phy on MBa8Mx)
* PCIe (mini-PCIe on MBa8x)

## SD-Card Boot

### Dip Switches

S6 : 11010111

S5 : 11111111

S7 : 1111

S8 : 0010

S9 : 0101

S10: 0000

### Bootable SD-Card

Complete system inage:

write *.wic Image to SD (offset 0)

Write bootstream only:

write imx-boot-tqma8mq-mba8mx-sd.bin at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-tqma8mq-mba8mx-sd.bin of=/dev/sd<x> bs=1k seek=33 conv=fsync`

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

### Bootable e-MMC-Card

write *.wic Image to e-MMC (offset 0)

Write bootstream only:

Boot from SD-Card and write imx-boot-tqma8mq-mba8mx-sd.bin at
offset 33 kiB (0x8400) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-tqma8mq-mba8mx-sd.bin of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

Example for U-Boot:

`
# 33k -> 66 Blocks -> 0x42

tftp <bootstream>
setexpr bsz $filesize + 1ff
setexpr bsz $bsz / 200
printenv bsz
mmc dev 0
mmc write 42 $bsz
`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

