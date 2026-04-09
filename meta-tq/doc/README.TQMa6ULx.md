# TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6ULLxL

This README contains some useful information for TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6ULLxL

[[_TOC_]]

## Variants

* TQMa6ULx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULx)
* TQMa6ULxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULxL)
* TQMa6ULLx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULLx)
* TQMa6ULLxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULLxL)

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |   REV.020x   |
| :----------------------------------------------: | :----------: |
| RAM configs                                      | 256, 512 MiB |
| CPU variants                                     | i.MX6UL[L]   |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |       x      |
| UART (console)                                   |       x      |
| **GPIO**                                         |              |
| generic                                          |       x      |
| **I2C**                                          |              |
| PMIC                                             |       x      |
| **eMMC / SD**                                    |              |
| Read                                             |       x      |
| Write                                            |       x      |
| **Ethernet**                                     |              |
| 2 x FEC via Phy on MBa6ULx                       |       x      |
| **Bootdevices**                                  |              |
| SD card                                          |       x      |
| eMMC                                             |       x      |
| QSPI-NOR on QuadSPI                              |       x      |
| Serial Downloader                                |       x      |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |       x      |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |              |
| **QSPI NOR**                                     |              |
| Read                                             |       x      |
| PP / Erase                                       |       x      |

### Linux

NOTE: Device tree from Linux Kernel 6.1 is not out of the box compatible to
yocto scarthgap. For Linux 6.1 support use yocto kirkstone.

| Feature                              | linux-tq-6.6 |
| :----------------------------------: | :----------: |
| Fuses                                |      x       |
| UART1 (console, X15)                 |      x       |
| UART3 (X5)                           |      x       |
| GPIO                                 |      x       |
| Button (S6, S7, S8)                  |      x       |
| I2C                                  |      x       |
| GPIO expander                        |      x       |
| EEPROM                               |      x       |
| RTC                                  |      x       |
| QSPI NOR                             |      x       |
| Buzzer                               |      x       |
| USB Host (X7/X8/X22)                 |      x       |
| USB Dual Role (X10)                  |      x       |
| eMMC/SD (on-board/X9)                |      x       |
| Ethernet 100M (X1400)                |      x       |
| Ethernet 100M (X1500) - not G1 CPU   |      x       |
| CAN (X13)                            |      x       |
| CAN (X14) not G1                     |      x       |
| RS-485 (X16)                         |              |
| LVDS (X17, X18)                      |      x       |
| Parallel LCD (X4)                    |      x       |
| Audio Line In (X20)                  |      x       |
| Audio Line Out (x21)                 |      x       |

_Note:_ Mini PCIe connector only supports USB.

### ToDo / Untested

* SIM card (X23)
* Resistive Touch (X4)
* Pixel Pipeline PXP
* RS485 (X16)

## Known Issues / Limitations

* edt-ft5406 touch controller on some Glyn displays might cause CRC errors
  after restart using `reboot` command. At startup as well as during runtime. The device
  is still functioning though.
* Linux / DTB: when booting kernel versions from this BSP the disabling of fused IP in
  device tree before loading the OS may fail with U-Boot from older BSP versions.
  Device tree path names were changed several times in CPU device tree fragment to conform the
  device tree specification.
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level. The distro definition and the image recipes
  `tq-image-small-[debug]` are intended for demonstration of howto generate a system
  running from SPI-NOR. This does not make any claims on feature / functional completeness.
* Environment of U-Boot v2023.04 was reworked to use variable names that conforms with
  distroboot contract. The default environment of older U-Boot versions are incompatible.
* MBa6ULx: USB devices at miniPCIe connector (X22) are not detected after POR. After warm reset
  devices enumerate.

## Build Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs for updating the rootfs from bootloader
   (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* u-boot-with-spl-${MACHINE}.imx-sd: bootloader for SD / eMMC
* u-boot-with-spl-${MACHINE}.imx-qspi: bootloader for QSPI
* u-boot-with-spl-${MACHINE}.imx-uuu: bootloader for UUU / USB serial download

## Boot DIP Switches

### MBa6ULx DIP Switches

_Note:_

* S12: BOOT_CFG1\[0 .. 7\]
* S11: BOOT_CFG2\[0 .. 7\]
* S13: BOOT_CFG4\[0 .. 7\]
* S5: BOOT\_MODE\[0 .. 1\]
* `x` means position of DIP, `-` means don't care

#### SD Card

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |  x  |     |  x  |  x  |  x  |  x  |  x  |   |  x  |  x  |     |  x  |  x  |  x  |     |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |     |  x  |     |     |     |     |     |   |     |     |  x  |     |     |     |  x  |     |    |     |     |     |     |     |     |     |     |    |  x  |     |

#### eMMC

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

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Display Support MBa6ULx

Each display can be used on its own by using the corresponding device tree.

*Note:* With MBa6ULx only one control interface for backlight is available.

| Interface       | Device tree                                  | Type               |
|-----------------|----------------------------------------------|--------------------|
| LVDS            | imx6ul-tqma6ul2[l]-mba6ulx-lvds.dtb          | Tianma TM070JVHG33 |
| Parallel        | imx6ul-tqma6ul2[l]-mba6ulx-cdtech-dc44.dtb   | CDTECH DC44 (DMB)  |
| Parallel        | imx6ul-tqma6ul2[l]-mba6ulx-cdtech-fc21.dtb   | CDTECH FC21 (DMB)  |
| LVDS            | imx6ull-tqma6ull2[l]-mba6ulx-lvds.dtb        | Tianma TM070JVHG33 |
| Parallel        | imx6ull-tqma6ull2[l]-mba6ulx-cdtech-dc44.dtb | CDTECH DC44 (DMB)  |
| Parallel        | imx6ull-tqma6ull2[l]-mba6ulx-cdtech-fc21.dtb | CDTECH FC21 (DMB)  |

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime Support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa6ULx and TQMa6ULLx](https://support.tq-group.com/en/arm/tqma6ulx)  
See [TQ Embedded Wiki for TQMa6ULxL and TQMa6ULLxL](https://support.tq-group.com/en/arm/tqma6ulxl)  
