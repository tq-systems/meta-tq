# Boot Device Initialisation and Update

This README documents how to write boostream images to different boot media and
how default U-Boot env supports update for development purpose.

[[_TOC_]]

## Boot Device Initialisation

### Bootstream Location on SD and eMMC

For SD-card and eMMC following table applies:

| CPU family | SD-card / eMMC user partition |   Block   | eMMC boot partition  |   Block   |
|:----------:|:-----------------------------:|:---------:|:--------------------:|:---------:|
|  LS1012A   |     not supported by CPU      |  ------   | not supported by CPU |     -     |
|  LS102xA   |         4 KiB (0x1000)        |  8 / 0x8  |      not tested      |     -     |
|  LS1028A   |         4 KiB (0x1000)        |  8 / 0x8  |      not tested      |     -     |
|  LS104xA   |         4 KiB (0x1000)        |  8 / 0x8  |      not tested      |     -     |
|  LS1088A   |         4 KiB (0x1000)        |  8 / 0x8  |      not tested      |     -     |
|  LX2160A   |         4 KiB (0x1000)        |  8 / 0x8  |      not tested      |     -     |

Note: Blocks are in sizes of 512 Bytes

### Prerequisites for Block Devices

Compressed WIC images and matching BMAP-files (block map files) are created by default.
To make use of this feature, install the `bmap-tools` package to use `bmaptool`.

**Attention**: when using `bmaptool` with BMAP-files keep in mind that only parts of the
image with valid information is written. As a consequence parts like U-Boot environment
may be left over from a previous version. If this could be an issue, use
`bmaptool copy --nobmap` or uncompress the wic-image and use `dd`.

### Bootable eMMC / SD-Card

Write the `*.wic` image to eMMC / SD-Card to create a bootable card with complete system image.
The following commands can be used - the example assumes `bmap-tools` package is installed:

```bash
bmaptool copy <image>.wic[.compress] --bmap <image>.bmap /dev/sd<y>
```

or

```bash
bmaptool copy <image>.wic[.compress] /dev/mmcblk0
```

To create a bootable card with boot images only (for exact file names see
SOM specific documentation), write bootstream image with correct
[offset](#bootstream-location-on-sd-and-emmc) to SD-Card:

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=<kiB offset> conv=fsync`

or

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=<kiB offset> conv=fsync`

### Bootable QSPI NOR

See scripts in SoM specific U-Boot environment

## Update Components via U-Boot

See scripts in SoM specific U-Boot environment
