# TQMa335x\[L\] on MBa335x carrier board

[[_TOC_]]

## Overview

### Supported Hardware

* TQMa335x: module revisions REV.020x 256/512 DDR3 memory variants
* TQMa335xL: module revisions REV.020x 256/512 DDR3 memory variants
* MBa335x: board revisions REV.020x

## Version information for software components

### Bootloader

* uboot-tq (Based on Mainline 2019.04)

### Kernel

* TI-linux-5.4.43 (Based on TI linux-5.4.y-07.00.00.005-rt)

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Known Issues

* TQMa335xL in MBa335x: sometimes after booting the ethernet phy shows
  up wrong addresses
* The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR


## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO-${MACHINE}: U-Boot MLO (SPL image)
* u-boot-${MACHINE}.img: U-Boot image to be booted by MLO

## HowTo

### MBa335x DIP Switches

| Switch  | Description       |
| ------- | :---------------: |
| S1      | Oscilator         |
| S2      | Boot device       |
| S3      | CAN termination   |
| S4      | RS485 Termination |

### MBa335x DIP Switch settings for Boot

#### SD Card

Boot sequence: MMC0 (SD) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |     |  x  |     |     |     |     |
| OFF     |  x  |  x  |  x  |     |  x  |  x  |  x  |  x  |

#### e-MMC

Boot sequence: MMC1 (e-MMC) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |     |     |     |     |     |     |
| OFF     |     |     |  x  |  x  |  x  |  x  |  x  |  x  |

#### SPI NOR

Boot sequence: SPI0 (NOR) → MMC0 (SD) → USB0 (N/A) → UART0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |  x  |     |     |     |     |     |
| OFF     |     |     |     |  x  |  x  |  x  |  x  |  x  |

## Boot device initialisation and update

Use wic system images to create bootable e-MMC / SD-Card

Use scripts provided in U-Boot environment to update bootloader.

## Support Wiki

See [TQ Embedded Wiki for TQMa335x](https://support.tq-group.com/en/arm/tqma335x)
