# TQMa7x on MBa7x Carrier Board (aka STKa7x)

This README contains some useful information for TQMa7x on MBa7x

[[_TOC_]]

## Variants

* TQMa7D module revisions REV.020x 512 MiB RAM
* TQMa7D module revisions REV.020x 1024 MiB RAM
* TQMa7D module revisions REV.020x 2048 MiB RAM
* MBa7x:  board revisions REV.020x

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### Linux

NOTE: Device tree for Linux Kernel older than 6.6 is not out of the box compatible to newer kernel
versions due to node naming changes.

|                            | linux-tq-6.6 |
| :------------------------: | :----------: |
| Fuses                      |      x       |
| UART (console, X13 or X14) |      x       |
| GPIO                       |      x       |
| Button (S11, S12, S13)     |      x       |
| I2C                        |      x       |
| GPIO expander              |      x       |
| EEPROM                     |      x       |
| RTC                        |      x       |
| SPI NOR                    |      x       |
| Buzzer                     |      x       |
| LEDs                       |      x       |
| SPI                        |      x       |
| USB Host (X4)              |      x       |
| USB Dual Role (X5)         |      x       |
| USB on Mini PCIe (X17)     |      x       |
| eMMC/SD (on-board/X7)      |      x       |
| Ethernet GigE (X8/X9)      |      x       |
| CAN (X10/X11)              |      x       |
| RS-485 (X12)               |              |
| LVDS (X15, X16)            |      x       |
| PCIe (X17)                 |              |
| Audio Line In (X20)        |      x       |
| Audio Line Out (x21)       |      x       |
| Parallel LCD (X23)         |      x       |
| Touch (X23)                |      x       |
| ADC (X23/X24)              |      x       |

### ToDo / Untested

* Smart card (X6)
* SIM card (X18)
* Pixel Pipeline PXP
* RS485 (X12)

## Known Issues / Limitations

* When using LCD Displays the signal BOOT_EN# (GPIO4_IO03)
  must be driven HIGH to separate BOOT_CFG circuits from LCD signals.
  Otherwise, artefacts may appear in the display view.
* Using internal PCIe PHY clock is currently not supported by the Linux
  mainline and newer NXP vendor kernel. PCIe can not be used on MBa7x
  with these kernel versions.
* `asound.state` is not compatible to older kernel versions. Use newer kernel - default
  is based on 6.6.y stable
* USB Dual Role gadget: Causing a device disconnect is not possible. D+ is
  erroneously supplying VBUS as well preventing a device disconnect per software.
  Occurs when gadget is disabled again. USB host might fail to detect a new USB
  descriptor once gadget is restarted.
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level. The distro definition and the image recipes
  `tq-image-small-[debug]` are intended for demonstration of howto generate a system
  running from SPI-NOR. This does not make any claims on feature / functional completeness.
* U-Boot: USB dual role port (X5) is tested in U-Boot in peripheral mode only.
* U-Boot: A POR might fail even if main power supply is disconnected, when running with
  USB dual role port (X5) connected to PC / HUB with powered VBUS.
* Environment of U-Boot v2023.04 was reworked to use variable names that conforms with
  distroboot contract. The default environment of older U-Boot versions are incompatible.

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
* u-boot-${MACHINE}.imx-sd: boot stream for SD / eMMC
* u-boot-${MACHINE}.imx-qspi: boot stream for QSPI

## Boot DIP Switches

### MBa7x DIP Switches

_Note:_

* S2/3/4 are for BOOT_CFG 0..20.
* S1 is for Boot Mode.
* `x` means position of DIP, `-` means don't care

### SD Card

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |  x  |     |     |  x  |     |     |     |    |      |     |     |     |  x  |     |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x  |  x  |     |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

### eMMC

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |  x  |     |     |    |      |     |     |  x  |     |  x  |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |     |  x  |  x  |    |  x   |  x  |  x  |     |  x  |     |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

### QSPI

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |     |     |     |    |      |     |     |     |     |     |  x  |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |  x  |  x  |     |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Mainline Kernel

Mainline kernel before v5.15 depends on the presence of running firmware in
TrustZone that implements the PSCI to start a second core. Starting with
commit e34645f45805 ("ARM: imx: add smp support for imx7d") linux is able to
handle the second CPU core without PSCI.

This version of BSP is only tested with an U-Boot version that implements PSCI
and starting linux outside of Trustzone (`non secure`).

The number of running CPUs can be checked with `nproc` under linux.

### Display Support

Each Display can be used on its own by using the corresponding device tree.
Using as device tree overlay is prepared.

*Note:* With MBa7x only one control interface for backlight is available.

| Interface       | Device tree                              | Type               |
|-----------------|------------------------------------------|--------------------|
| LVDS            | imx7d-mba7-lvds-tm070jvhg33.dtb          | Tianma TM070JVHG33 |
| Parallel        | imx7d-mba7-rgb-cdtech-dc44.dtb           | CDTECH DC44 (DMB)  |
| Parallel        | imx7d-mba7-rgb-cdtech-fc21.dtb           | CDTECH FC21 (DMB)  |

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime Support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa7x](https://support.tq-group.com/en/arm/tqma7x)
