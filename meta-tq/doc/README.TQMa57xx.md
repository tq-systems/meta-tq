# TQMa57xx on MBa57xx carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa57xx: Module revisions REV.010x
* MBa57xx: Board revisions REV.020x

### Versions

_Bootloader:_

* uboot-tq-2019.04

_Kernel:_

* linux-ti-tq-5.10 (default, based on ti-rt-linux-5.10.y)
* linux-ti-tq-5.4 (based on ti-rt-linux-5.4.y)

### Known issues

* The current default kernel version 5.10 does not include display support. Add
  the following settings to local.conf to select the older kernel 5.4, where
  display support was available:

      PREFERRED_VERSION_linux-ti-tq = "5.4%"
      TI_SGX_DDK_KM_KERNVER = "5.4"

* USB 3 storage devices do not work in U-Boot. Attempting to run `usb start` or
  `usb reset` when such a device is connected leads to a crash of U-Boot.
* In U-Boot, only a single Ethernet port can be used (X52)
* The SPI-NOR flash partition layout used by Linux is outdated. It does not
  match the partitions used by U-Boot for environment storage when booting from
  SPI-NOR.
* SPI-NOR boot support is incomplete. No scripts for programming U-Boot to the
  flash or booting an Operating System are provided.
* The Mini PCIe slot does not work.
* The CAN interfaces do not work at the default bitrate of 500kBit/s and higher.
  A lower bitrate like 250 or 100kBit/s must be configured.
* The audio interface of the MBa57xx is unsupported.

## Build Artifacts

Artifacts can be found at:
`deploy-ti/images/${MACHINE}`

* \*.dtb: Device Tree blobs
* zImage: Compressed Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO: first-stage bootloader (SPL)
* u-boot.img: full bootloader

## DIP Switches

S1 must be configured as follows:

| S1      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |     |     |     |     |     |     |  x  |
| OFF     |     |  x  |  x  |  x  |  x  |  x  |  x  |     |

The DIP switch S2 are used to select the device to boot from.

### SD card

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |  x  |  x  |     |     |     |     |
| OFF     |  x  |  x  |     |     |  x  |  x  |  x  |  x  |

### eMMC


| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |  x  |     |     |     |     |     |
| OFF     |  x  |  x  |     |  x  |  x  |  x  |  x  |  x  |

### SPI NOR


| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |  x  |  x  |     |  x  |  x  |     |
| OFF     |  x  |  x  |     |     |  x  |     |     |  x  |

## Boot medium creation

### SD card / eMMC

To create a bootable SD card or prepare the eMMC with a boot image, write the
[WIC image](#build-artifacts) (`*.wic`) to the SD card or eMMC at offset 0.

A minimal image (`*.wic.bootonly`) containing only the bootloader partition
without the OS can be used alternatively.

#### Example for Linux

```
sudo dd if=<image> of=/dev/sd<x> bs=4M conv=fsync
```

Replace `<image>` with the name of the image file to program and `sd<x>` with
the target device. If you are

#### Example for U-Boot

The example expects that the image file to install can be loaded from a TFTP
server. The `ipaddr` and `serverip` variables need to be configured manually
or using the `dhcp` command.

```
tftp <image>
setexpr bsz ${filesize} + 0x1ff
setexpr bsz ${bsz} / 0x200
printenv bsz
mmc dev 1 # dev 0 is the SD card, dev 1 the eMMC
mmc write ${loadaddr} 0 ${bsz}
```

### SPI-NOR

The bootloader can be installed to the SPI-NOR flash in U-Boot.

The following example expects that the bootloader files (`MLO` and `u-boot.img`)
can be loaded from a TFTP server. The `ipaddr` and `serverip` variables need to
be configured manually or using the `dhcp` command.

```
sf probe
tftp MLO
sf update ${loadaddr} QSPI.SPL ${filesize}
tftp u-boot.img
sf update ${loadaddr} QSPI.u-boot ${filesize}
```

Booting an Operating System from the SPI-NOR flash is currently unsupported.

## Support Wiki

See [TQ Embedded Wiki for TQMa57xx](https://support.tq-group.com/en/arm/tqma57xx)
