# TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6ULLxL

[[_TOC_]]

## Overview

### Supported Hardware

* TQMa6ULx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULx)
* TQMa6ULxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULxL)
* TQMa6ULLx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULLx)
* TQMa6ULLxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULLxL)
* TQMa6ULxL REV.020x on MBa6ULxL REV.020x carrier board

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

### Supported Features

|                              | linux-tq-5.15 |
| ---------------------------- | :-----------: |
| Fuses                        |      x        |
| UART1 (console, X15)         |      x        |
| UART3 (X5)                   |      x        |
| GPIO                         |      x        |
| Button (S6, S7, S8)          |      x        |
| I2C                          |      x        |
| GPIO expander                |      x        |
| EEPROM                       |      x        |
| RTC                          |      x        |
| QSPI NOR                     |      x        |
| Buzzer                       |      x        |
| USB Host (X7/X8/X22)         |      x        |
| USB Dual Role (X10)          |      x        |
| eMMC/SD (on-board/X9)        |      x        |
| Ethernet GigE (X1400)        |      x        |
| Ethernet GigE (X1500) not G1 |      x        |
| CAN (X13)                    |      x        |
| CAN (X14) not G1             |      x        |
| RS-485 (X16)                 |      x        |
| LVDS (X17, X18)              |      x        |
| Parallel LCD (X4)            |      x        |
| Audio Line In (X20)          |      x        |
| Audio Line Out (x21)         |      x        |

_Note:_ Mini PCIe connector only supports USB.

### ToDo / Untested
* Mic In (X19)
* SIM card (X23)
* Resistive Touch (X4)
* Pixel Pipeline PXP

### Known issues

* U-Boot: default device tree names for all machines using the starter kit
  mainboard (aka MBa6ULx) do not match device tree names in current supported
  mainline kernel version.
* edt-ft5406 touch controller on some Glyn displays might cause CRC errors
  after restart using `reboot` command. At startup as well as during runtime. The device
  is still functioning though.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* u-boot-${MACHINE}.imx-sd: boot stream for SD / e-MMC
* u-boot-${MACHINE}.imx-qspi: boot stream for SD / e-MMC

## HowTo

### MBa6ULx DIP Switch settings for Boot

_Note:_

* S12: BOOT_CFG1\[0 .. 7\]
* S11: BOOT_CFG2\[0 .. 7\]
* S13: BOOT_CFG4\[0 .. 7\]
* S5: BOOT\_MODE\[0 .. 1\]
* X means position of DIP, - means don't care

#### SD Card

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |  x  |     |  x  |  x  |  x  |  x  |  x  |   |  x  |  x  |     |  x  |  x  |  x  |     |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |     |  x  |     |     |     |     |     |   |     |     |  x  |     |     |     |  x  |     |    |     |     |     |     |     |     |     |     |    |  x  |     |

#### e-MMC

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |     |  x  |  x  |     |  x  |  x  |  x  |   |  x  |  x  |  x  |  x  |  x  |     |     |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |  x  |     |     |  x  |     |     |     |   |     |     |     |     |     |  x  |  x  |     |    |     |     |     |     |     |     |     |     |    |  x  |     |


#### QSPI

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |   |  x  |  x  |  x  |  x  |     |  x  |  x  |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |     |     |     |     |     |     |     |   |     |     |     |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |


### MBa6ULxL DIP Switch settings for Boot

_Note:_

* S16 are for BOOT_CFG 0..07.
* S13 is for Boot Mode.

#### SD Card

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |  x  |     |  x  |  x  |     |     |  x  |   |     |  x  |
| OFF     |  -  |     |  x  |     |     |  x  |  x  |     |   |  x  |     |

#### e-MMC

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |     |  x  |     |     |     |  x  |  x  |   |     |  x  |
| OFF     |  -  |  x  |     |  x  |  x  |  x  |     |     |   |  x  |     |

#### QSPI

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |  -  |  -  |  -  |  x  |  x  |  x  |     |   |     |  x  |
| OFF     |  -  |  -  |  -  |  -  |     |     |     |  x  |   |  x  |     |

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

#### QSPI NOR

__Attention__: This section is subject to change.

To program the rootfs as UBI, write <rootfs.ubi> image to `/dev/mtd5` (under Linux)
Copy the image on a USB stick and mount it to `/mnt`

```
ubiformat /dev/mtd5 -f /mnt/root.ubi
```

To check check usability of the programmed rootfs one can use

```
ubiattach /dev/ubi_ctrl -m 5
mount -t ubifs ubi0:rootfs /mnt
```

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

## Support Wiki

See [TQ Embedded Wiki for TQMa6ULx and TQMa6ULLx](https://support.tq-group.com/en/arm/tqma6ulx)  
See [TQ Embedded Wiki for TQMa6ULxL and TQMa6ULLxL](https://support.tq-group.com/en/arm/tqma6ulxl)  
