# TQMa6\[QP,DP,Q,D,DL,SL\] up to Rev.040x on MBa6x REV.020x carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

### Supported Features

|                              | linux-imx-tq-5.10 | linux-tq-5.15 |
| ---------------------------- | :---------------: | :-----------: |
| Fuses                        |       x           |      x        |
| UART (console on UART3, X15) |       x           |      x        |
| GPIO                         |       x           |      x        |
| Button (S6, S7, S8)          |       x           |      x        |
| I2C                          |       x           |      x        |
| GPIO expander                |       x           |      x        |
| EEPROM                       |       x           |      x        |
| RTC                          |       x           |      x        |
| SPI NOR                      |       x           |      x        |
| Buzzer                       |       x           |      x        |
| GPU                          |       x           |      x        |
| VPU H.264                    |       x           |      x        |
| VPU VP8                      |       x           |               |
| USB Host (X6/X7)             |       x           |      x        |
| USB Dual Role (X8)           |       x           |      x        |
| eMMC/SD (on-board/X9)        |       x           |      x        |
| Ethernet GigE (X11/X12)      |       x           |      x        |
| CAN (X13/X14)                |       x           |      x        |
| RS-485 (X16)                 |       x           |      x        |
| HDMI (X17)                   |       x           |      x        |
| LVDS (X18, X19)              |       x           |      x        |
| HDMI + LVDS (X17, X18)       |                   |      x        |
| Audio Line In (X20)          |       x           |      x        |
| Audio Line Out (x22)         |       x           |      x        |
| PCIe (X23)                   |       x           |      x        |
| Parallel LCD (X27)           |                   |      x        |
| Touch (X27)                  |                   |      x        |
| Multi-Display                |                   |      x        |

### ToDo / Untested
* Mic In (X21)
* SIM card (X24)
* MIPI-CSI (X28)
* MIPI-DSI (X28)
* MLB (X28)

### Known issues

- PCIe requires a power cycle to work reliably. Asserting a POR using S9 or S10 is not sufficient.
- eth1 (X12) uses a random MAC address. The one stored in MBa6 EEPROM is currently not used.
- Backlight on parallel displays are enabled upon Power-On which might lead to random output.
  Display will be disabled during bootup and can be used normally afterwards.
- GPIO wake-up not supported on `linux-imx-tq-5.10`

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* u-boot-${MACHINE}.imx-sd: boot stream for SD / e-MMC
* u-boot-${MACHINE}.imx-spinor: boot stream for SD / e-MMC

## HowTo:

### MBa6x DIP Switch settings for Boot

_Note:_

* S1/2/4 are for BOOT_CFG.
* S5 is for Boot Mode.
* X means position of DIP, - means don't care

#### SD Card

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |      |  x   |      |      |      |      |    |     |     |  x  |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x   |      |  x   |  x   |  x   |  x   |    |  x  |  x  |     |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

#### e-MMC

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |  x   |      |      |      |      |      |    |     |  x  |     |  x  |     |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |      |  x   |  x   |  x   |  x   |  x   |    |  x  |     |  x  |     |  x  |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

#### SPI

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |     |  x   |  x   |      |      |      |      |    |     |     |     |     |     |     |     |     |    |     |     |     |  x  |  x  |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |      |      |  x   |  x   |  x   |  x   |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |  x  |  x  |  x  |     |     |  x  |  x  |  x  |    |     |  x  |

### Boot device initialisation

#### QSPI NOR

To initialize QSPI NOR with bootloader, write the [bootloader image](#artifacts)
to QSPI NOR at offset 0x00:

```
setenv uboot <U-Boot QSPI boot image>
tftp ${loadaddr} ${uboot}
sf probe
sf update ${loadaddr} 0 ${filesize}
```

#### SD / e-MMC

To initialize SD / e-MMC with bootloader, write the [bootloader image](#artifacts)
for SD / e-MMC to SD / e-MMC at offset 0x400 / block #2

```
setenv uboot <U-Boot SD/e-MMC boot image>
tftp ${loadaddr} ${uboot}
mmc dev [0,1]
mmc rescan
setenv blkc ${filesize} + 1ff
setenv blkc ${blkc} / 200
mmc write ${loadaddr} 2 ${blkc}
setenv blkc
```

### Program system image

#### SPI NOR

Not supported. Only kernel and DTB can be stored at the moment.

#### SD / e-MMC

To program complete system image to SD / e-MMC, write [WIC image](#artifacts)
to SD / e-MMC at offset 0x00 / block #0

### Update parts of system

To update parts of system using U-Boot / TFTP following shortcuts exist, to
update the part on the active boot device.

```
setenv zimage <name of linux zimage>
run update_kernel
```

```
setenv fdt_file <name of fdt image>
run update_fdt
```

```
setenv uboot <name of u-boot image>
run update_uboot
```

### Dual LVDS usage

_Note:_ Only valid for `linux-imx-tq-5.10`

The 2nd framebuffer / display is blanked by default. In order to use the display on `LVDS1` it need to be unblanked: `echo 0 > /sys/devices/platform/fb@3/graphics/fb2/blank`

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
