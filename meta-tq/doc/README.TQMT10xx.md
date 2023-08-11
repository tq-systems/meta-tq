# TQMT1042/TQMT1022/TQMT1040/TQMT1024 on carrier board STK104x

[[_TOC_]]

## Supported Hardware
* TQMT1042 with 2GiB RAM on STK104x Rev.0200
* TQMT1022 with 2GiB RAM on STK104x Rev.0200
* TQMT1040 with 2GiB RAM on STK104x Rev.0200
* TQMT1024 with 2GiB RAM on STK104x Rev.0200

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
| SPI NOR                      |      x        |
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
`${TMPDIR}/deploy/images/${MACHINE}`
* `fsl_rcw-nor-TQMT*_SERDES*.bin`: RCW binary
* `tqmt10xx-stkt104x.dtb`: device tree blob for STKT104x board
* `uImage`: Linux kernel image
* `u-boot-nor-*.bin` U-Boot binary for nor flash
* `u-boot-sdcard-*.bin` U-Boot binary for nor flash
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

## Build-Time Configuration

* FMAN_UCODE: FMan microcode binary file used for WIC Image creation
* FSL_RCW: RCW Configuration used in U-Boot build to create binary

Note: Currently only one Serdes Configuration per Module is supported.
(See [Ethernet/Serdes Port Assigment](#ethernetserdes-port-assignment)).

To support different configurations a custom device tree has to be created.

## Boot DIP Switches

 * S2-2: On : SD Card
 * S2-2: Off: IFC SPI Nor

## Signal Switch Select

DIP S3 allows switching of signals:
| DIP-Switch | Serdes Lane   | On  | Off |
| ---------- | ------------- | --- | ---- |
| S3-1       | Serdes Lane C | SGMII (X30BB) | QSGMII (X30) |
| S3-2       | Serdes Lane E | miniPCIE (X19) |Auroroa (X8) |
| S3-3       | Serdes Lane G | miniPCIE (X20) | see S3.4 |
| S3-4       | Serdes Lane G | SGMII (X37BB) | SATA(X26) |
| S2-1       | DIU SEL       | UCC | Display |
| S2-2       | Boot Source   | SD-Card | Nor Flash |
| S2-3       | USB Ref CLK   | USBCLK to D3 (for T1024)| USBCLK to F8 |
| S2-4       | Serdes Ref CLK| 156.25 MHz | 100Mhz |
| S4-1       | DVDD          | 3.3V | 1.8V |
| S4-2       | EVDD	     | 3.3V | 1.8V |
| S4-3       | CVDD	     | 3.3V | 1.8V |
| S4-4       | SDHC EXT SEL  | SD-Card| eMMC |
| S9-1       | Refclk D21    | 156.25 MHz | 125MHz |
| S9-2       | D21 Config    | QSGMII | SGMII |

Note: T1022/T1042/T1040 have 8 SerDes lanes whereas T1024 has only 4 lanes.
The upper lanes (E, F, G, H) of T104x are mapped to T1024 lanes (A, B, C, D).

## Ethernet/Serdes Port Assignment

### TQMT1042/TQMT1022:

For Serdes Config 0x86

#### Ethernet

| Linux Name | U-Boot Name |  MAC / Protocoll | Ethernet Port	      |
| ---------- | ----------- | ---------------- | ----------------------|
| fm1-gb0    | FM1@DTSEC1  | Lane C SGMII     | X30-BB: (Top Right)   |
| fm1-gb1    | FM1@DTSEC2  | Lane D SGMII     | X37-AB: (Bottom Right)|
| fm1-gb2    | FM1@DTSEC3  | Lane B SGMII     | X30-AA: (Bottom Left) |
| fm1-gb3    | FM1@DTSEC4  | RGMII EC1        | X37-BA: (Top Left)    |
| fm1-gb4    | FM1@DTSEC5  | RGMII EC2        | X37-AA: (Bottom Left) |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane E | X19 |
| Lane F | X24 |
| Lane G | X20 |

#### Dip Switch settings

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- | --- | ----|
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |
| S3-1       | Serdes Lane C |     |  X  |
| S3-2       | Serdes Lane E |  X  |     |
| S3-3       | Serdes Lane G |  X  |     |
| S2-4       | Refclk Serdes |     |  X  |

### TQMT1024:
For Serdes Config 0x6B

#### Ethernet

| Linux Name | U-Boot Name |  MAC / Protocoll | Ethernet Port	      |
| ---------- | ----------- | ---------------- | ----------------------|
| fm1-mac2    | FM1@DTSEC2  | Lane G SGMII    | X37-BB: (Top Right)   |
| fm1-mac4    | FM1@DTSEC4  | RGMII EC1       | X37-BA: (Top Left)    |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane A | X19 |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- | --- | ----|
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |
| S3-3       | Serdes Lane G |     |  X  |
| S3-4       | Serdes Lane G |  X  |     |
| S2-4       | Refclk Serdes |     |  X  |

### TQMT1024:
For Serdes Config 0x95

#### Ethernet

| Linux Name | U-Boot Name |  MAC / Protocoll | Ethernet Port         |
| ---------- | ----------- | ---------------- | ----------------------|
| fm1-mac1    | FM1@TGEC1  | Lane A XFI       | J3                    |
| fm1-mac4    | FM1@DTSEC4 | RGMII EC1        | X37-BA: (Top Left)    |

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane A | X19 |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- | --- | ----|
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |     |  X  |
| S3-3       | Serdes Lane G |     |  X  |
| S3-4       | Serdes Lane G |  X  |     |
| S2-4       | Refclk Serdes |  X  |     |

### TQMT1040:
For Serdes Config 0x66

#### Ethernet
| Linux Name   | U-Boot Name |  MAC / Protocoll | Ethernet Port	      |
| ------------ | ----------- | ---------------- | ----------------------|
| fm1-gb0      | FM1@DTSEC1  |                  | (To Seville Switch)   |
| fm1-gb3      | FM1@DTSEC4  | RGMII EC1        | X37-BA: (Top Left)    |
| fm1-gb4      | FM1@DTSEC5  | RGMII EC2        | X37-AA: (Bottom Left) |
| swp0@fm1-gb0 | xxxxxxxxxx  | QSGMII           | X30-AA: (Bottom Left) |
| swp1@fm1-gb0 | xxxxxxxxxx  | QSGMII           | X30-BB: (Top Right)   |
| swp2@fm1-gb0 | xxxxxxxxxx  | QSGMII           | X30-BA: (Top Left)    |
| swp3@fm1-gb0 | xxxxxxxxxx  | QSGMII           | X30-AB: (Bottom Right)|

#### PCIe

| Lane   | Connector |
| ------ | --------- |
| Lane E | X19 |
| Lane F | X24 |
| Lane G | X20 |

#### Dip Switch settings

Important Dip Switch settings for this configuration

| DIP-Switch | Function      | On  | Off |
| ---------- | ------------- | --- | ----|
| S9-1       | Refclk D21    |     |  X  |
| S9-2       | QSGMII/SGMII  |  X  |     |
| S3-1       | Serdes Lane C |  X  |     |
| S3-2       | Serdes Lane E |     |  X  |
| S3-3       | Serdes Lane G |  X  |     |
| S2-4       | Refclk Serdes |     |  X  |

## LVDS Display

Note that the output of the LVDS interface is in Jeida-24 mode.

## Program system image

### SD card / e-MMC

To program a complete system image, write the [WIC image](#Artifacts) to
SD card / e-MMC at offset 0.

### SPI-NOR

#### Flash Layout
```
0x000000000000-0x000000020000 : "NOR reset configuration word"
0x000000020000-0x000000820000 : "NOR Linux kernel image"
0x000000820000-0x000000840000 : "NOR Linux device tree blob"
0x000000840000-0x000004840000 : "NOR Linux root file system image"
0x000004840000-0x000007e40000 : "NOR spare partition"
0x000007f00000-0x000007f20000 : "NOR frame manager microcode + quicc engine microcode"
0x000007f20000-0x000007f40000 : "NOR u-boot environment variables"
0x000007f40000-0x000008000000 : "NOR u-boot bootloader image"
```

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

For Kernel, FDT, U-Boot, RCW and Fman-microcode in U-Boot are update commands
available:
```
update_nor_rcw
update_nor_uboot
update_nor_fdt
update_nor_kernel
update_nor_fman
```

## Support Wiki

See [TQ Embedded Wiki for TQMT10xx](https://support.tq-group.com/en/power/tqmt10xx)
