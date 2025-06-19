# TQMT1022/TQMT1024/TQMT1040/TQMT1042 on carrier board STK104x

[[_TOC_]]

## Supported Hardware

Board: STK104x Rev.020x

Module:
* TQMT1022 Rev.0200 (2 GiB RAM)
* TQMT1024 Rev.0200 (2 GiB RAM)
* TQMT1040 Rev.0200 (2 GiB RAM)
* TQMT1042 Rev.0200 (2 GiB RAM)

## Version information for software components

### U-Boot

* based on mainline u-boot v2015.07

### Linux

* based on linux stable v5.15.86

### Supported Features

|                              |   Linux       |
| ---------------------------- | :-----------: |
| UART (console)               |      x        |
| GPIO                         |      x        |
| Button (S6, S7, S8)          |      x        |
| I2C                          |      x        |
| GPIO expander                |      x        |
| EEPROM                       |      x        |
| RTC                          |      x        |
| NOR                          |      x        |
| LEDs                         |      x        |
| USB Hub (X23)                |      x        |
| USB Host (X28)               |      x        |
| SD-Card                      |      x        |
| Ethernet                     |      x        |
| LVDS                         |      x        |
| PCIe                         |      x        |


## Known Issues

* USB on Port X28 only works in Host-Mode.
* Musl libc does not support the e5500 core in 64bit. It is possible
  using musl libc with a powerpc-nf (32bit) toolchain.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)`
* `rcw_*_nor.bin`: RCW binary for NOR flash
* `tqmt10xx-stkt104x.dtb`: device tree blob for STKT104x board
* `uImage`: Linux kernel image
* `u-boot-nor-*.bin` U-Boot binary for NOR flash
* `u-boot-sdcard-*.bin` U-Boot binary for SD card / eMMC
* `u-boot-with-spl-pbl-rcw_*_sdcard.bin` U-Boot binary with spl, pbl, rcw and Serdes for SD card /eMMC
* `\*.wic[.<compress>]`: SD / e-MMC system image
* `\*.rootfs.tar.gz`: RootFS archive (NFS root etc.)

## Build-Time Configuration

The following variables can be used to override the default settings

* `FMAN_UCODE`: FMan microcode binary file from `fm-ucode` package used for WIC Image
  creation or stored in NOR flash (to be loaded from U-Boot)
* `UBOOT_RCW_VARIANTS`: RCW configurations used in U-Boot build to create SD/eMMC binaries
  consisting of RCW PBL and U-Boot proper

__NOTE:__ Changes of Serdes/RCW configuration needs usage of matching device tree.

## Boot DIP Switches

| S2-2 |           |
| ---- | --------- |
| on   | SD Card   |
| off  | NOR Flash |


## Signal Switch Select

DIP S3 allows switching of signals:

| DIP Switch | Serdes Lane    | On                       | Off          |
| ---------- | -------------- | ------------------------ | ------------ |
| S2-1       | DIU SEL        | UCC                      | Display      |
| S2-2       | Boot Source    | SD-Card                  | NOR Flash    |
| S2-3       | USB Ref CLK    | USBCLK to D3 (for T1024) | USBCLK to F8 |
| S2-4       | Serdes Ref CLK | 156.25 MHz               | 100Mhz       |
| S3-1       | Serdes Lane C  | SGMII (X30BB)            | QSGMII (X30) |
| S3-2       | Serdes Lane E  | miniPCIE (X19)           | Auroroa (X8) |
| S3-3       | Serdes Lane G  | miniPCIE (X20)           | see S3.4     |
| S3-4       | Serdes Lane G  | SGMII (X37BB)            | SATA(X26)    |
| S4-1       | DVDD           | 3.3V                     | 1.8V         |
| S4-2       | EVDD           | 3.3V                     | 1.8V         |
| S4-3       | CVDD           | 3.3V                     | 1.8V         |
| S4-4       | SDHC EXT SEL   | SD-Card                  | eMMC         |
| S9-1       | Refclk D21     | 156.25 MHz               | 125MHz       |
| S9-2       | D21 Config     | QSGMII                   | SGMII        |

Note: T1022/T1040/T1042 have 8 SerDes lanes whereas T1024 has only 4 lanes.
The upper lanes (E, F, G, H) of T104x are mapped to T1024 lanes (A, B, C, D).

## Ethernet/Serdes Port Assignment

### TQMT1022/TQMT1042 (Serdes Config 0x86)

#### Ethernet

| Linux Name | U-Boot Name | MAC / Protocol | Ethernet Port          |
| ---------- | ----------- | -------------- | ---------------------- |
| fm1-gb0    | FM1@DTSEC1  | Lane C SGMII   | X30-BB: (Top Right)    |
| fm1-gb1    | FM1@DTSEC2  | Lane D SGMII   | X37-AB: (Bottom Right) |
| fm1-gb2    | FM1@DTSEC3  | Lane B SGMII   | X30-AA: (Bottom Left)  |
| fm1-gb3    | FM1@DTSEC4  | RGMII EC1      | X37-BA: (Top Left)     |
| fm1-gb4    | FM1@DTSEC5  | RGMII EC2      | X37-AA: (Bottom Left)  |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane E | X19       |
| Lane F | X24       |
| Lane G | X20       |

#### Dip Switch settings

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |     |  X  |
| S3-1       | Serdes Lane C |  X  |     |
| S3-2       | Serdes Lane E |  X  |     |
| S3-3       | Serdes Lane G |  X  |     |
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |

### TQMT1022/TQMT1042 (Serdes Config 0x88)

#### Ethernet

| Linux Name | U-Boot Name | MAC / Protocol | Ethernet Port          |
| ---------- | ----------- | -------------- | ---------------------- |
| fm1-gb0    | FM1@DTSEC1  | Lane C SGMII   | X30-BB: (Top Right)    |
| fm1-gb1    | FM1@DTSEC2  | Lane D SGMII   | X37-AB: (Bottom Right) |
| fm1-gb2    | FM1@DTSEC3  | Lane B SGMII   | X30-AA: (Bottom Left)  |
| fm1-gb3    | FM1@DTSEC4  | RGMII EC1      | X37-BA: (Top Left)     |
| fm1-gb4    | FM1@DTSEC5  | RGMII EC2      | X37-AA: (Bottom Left)  |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane E | X19       |
| Lane F | X24       |

#### SATA

| Lane   | Connector |
| ------ | --------- |
| Lane G | X26       |

#### Dip Switch settings

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |     | X   |
| S3-1       | Serdes Lane C | X   |     |
| S3-2       | Serdes Lane E | X   |     |
| S3-3       | Serdes Lane G |     | X   |
| S3-4       | Serdes Lane G |     | X   |
| S9-1       | Refclk D21    |     | X   |
| S9-2       | QSGMII/SGMII  |     | X   |

### TQMT1022/TQMT1042 (Serdes Config 0x8E)

#### Ethernet

| Linux Name | U-Boot Name | MAC / Protocol | Ethernet Port          |
| ---------- | ----------- | -------------- | ---------------------- |
| fm1-gb0    | FM1@DTSEC1  | Lane E XFI     | J3                     |
| fm1-gb1    | FM1@DTSEC2  | Lane D SGMII   | X37-AB: (Bottom Right) |
| fm1-gb2    | FM1@DTSEC3  | Lane B SGMII   | X30-AA: (Bottom Left)  |
| fm1-gb3    | FM1@DTSEC4  | Lane G SGMII   | X37-BB: (Top Right)    |
| fm1-gb4    | FM1@DTSEC5  | RGMII EC2      | X37-AA: (Bottom Left)  |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane F | X24       |

#### Dip Switch settings

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |     | X   |
| S3-1       | Serdes Lane C | X   |     |
| S3-2       | Serdes Lane E |     | X   |
| S3-3       | Serdes Lane G |     | X   |
| S3-4       | Serdes Lane G | X   |     |
| S9-1       | Refclk D21    |     | X   |
| S9-2       | QSGMII/SGMII  |     | X   |

### TQMT1024 (Serdes Config 0x6B)

#### Ethernet

| Linux Name | U-Boot Name | MAC / Protocol | Ethernet Port       |
| ---------- | ----------- | -------------- | ------------------- |
| fm1-mac2   | FM1@DTSEC2  | Lane G SGMII   | X37-BB: (Top Right) |
| fm1-mac4   | FM1@DTSEC4  | RGMII EC1      | X37-BA: (Top Left)  |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane A | X19 |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |     |  X  |
| S3-3       | Serdes Lane G |     |  X  |
| S3-4       | Serdes Lane G |  X  |     |
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |

### TQMT1024 (Serdes Config 0x95)

#### Ethernet

| Linux Name | U-Boot Name | MAC / Protocol | Ethernet Port      |
| ---------- | ----------- | -------------- | ------------------ |
| fm1-mac1   | FM1@TGEC1   | Lane A XFI     | J3                 |
| fm1-mac4   | FM1@DTSEC4  | RGMII EC1      | X37-BA: (Top Left) |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane A | X19 |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |  X  |     |
| S3-3       | Serdes Lane G |     |  X  |
| S3-4       | Serdes Lane G |  X  |     |
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |

### TQMT1040 (Serdes Config 0x66)

#### Ethernet

| Linux Name   | U-Boot Name | MAC / Protocol | Ethernet Port          |
| ------------ | ----------- | -------------- | ---------------------- |
| fm1-gb0      | xxxxxxxxxx  | QSGMII         | (To Seville Switch)    |
| fm1-gb3      | FM1@DTSEC4  | RGMII EC1      | X37-BA: (Top Left)     |
| fm1-gb4      | FM1@DTSEC5  | RGMII EC2      | X37-AA: (Bottom Left)  |
| swp0@fm1-gb0 | xxxxxxxxxx  | QSGMII         | X30-AA: (Bottom Left)  |
| swp1@fm1-gb0 | xxxxxxxxxx  | QSGMII         | X30-BB: (Top Right)    |
| swp2@fm1-gb0 | xxxxxxxxxx  | QSGMII         | X30-BA: (Top Left)     |
| swp3@fm1-gb0 | xxxxxxxxxx  | QSGMII         | X30-AB: (Bottom Right) |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane E | X19       |
| Lane F | X24       |
| Lane G | X20       |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- |:---:|:---:|
| S2-4       | Refclk Serdes |     |  X  |
| S3-1       | Serdes Lane C |     |  X  |
| S3-2       | Serdes Lane E |  X  |     |
| S3-3       | Serdes Lane G |  X  |     |
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |  X  |     |

## LVDS Display

Note that the output of the LVDS interface is in Jeida-24 mode.

## Program system image

### Prerequisites for block devices

Compressed WIC images and matching BMAP-files (block map files) are created by default.
To make use of this feature, install the `bmap-tools` package to use `bmaptool`.
The packed WIC can also be decompressed and used with `dd` or other disk image tools.

#### SD card / e-MMC

To program a complete system image, write the [WIC image](#artifacts) to
SD card / e-MMC at offset 0x0.

The following command can be used (the example assumes `bmaptool`):
```bash
bmaptool copy <image>.wic[.compress] --bmap <image>.bmap /dev/sd<y>
```

U-Boot update scripts for the following components are available  in default environment:
* complete system image
* device tree
* Fman-microcode
* Kernel (without kernel modules)
* U-Boot

| script name       | for                                  |
| ----------------- | ------------------------------------ |
| update_mmc        | SD system image                      |
| update_mmc_fdt    | device tree                          |
| update_mmc_fman   | FMan microcode                       |
| update_mmc_kernel | Kernel (without kernel modules)      |
| update_mmc_uboot  | U-Boot with spl, pbl, rcw and Serdes |

### SPI-NOR

#### Flash Layout

| start      | end        | area                                                 |
| ---------- | ---------- | ---------------------------------------------------- |
| 0x00000000 | 0x00020000 | NOR reset configuration word                         |
| 0x00020000 | 0x00820000 | NOR Linux kernel image                               |
| 0x00820000 | 0x00840000 | NOR Linux device tree blob                           |
| 0x00840000 | 0x04840000 | NOR Linux root file system image                     |
| 0x04840000 | 0x07e40000 | NOR spare partition                                  |
| 0x07f00000 | 0x07f20000 | NOR frame manager microcode + quicc engine microcode |
| 0x07f20000 | 0x07f40000 | NOR u-boot environment variables                     |
| 0x07f40000 | 0x08000000 | NOR u-boot bootloader image                          |

#### Program Files to SPI-NOR Flash

To program the root filesystem, format `/dev/mtd3` as a UBI volume and write
the UBI image to it. If the image is stored at `/mnt/rootfs.ubi` (for example
on a USB drive), use the following command:
```
ubiformat /dev/mtd3 -f /mnt/rootfs.ubi
```

To check check usability of the programmed root filesystem, the following
commands can be used:
```
ubiattach -p /dev/mtd3
mount -t ubifs ubi0:root /mnt
```

U-Boot update scripts for the following components are available in default environment:
* device tree
* Fman-microcode
* Kernel (without kernel modules)
* RCW
* U-Boot

| script name       | for                             |
| ----------------- | ------------------------------- |
| update_nor_fdt    | device tree                     |
| update_nor_fman   | FMan microcode                  |
| update_nor_kernel | Kernel (without kernel modules) |
| update_nor_rcw    | RCW and Serdes                  |
| update_nor_uboot  | U-Boot                          |

## Support Wiki

See [TQ Embedded Wiki for TQMT10xx](https://support.tq-group.com/en/power/tqmt10xx)
