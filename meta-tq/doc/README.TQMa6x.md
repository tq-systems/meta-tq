# TQMa6\[QP,DP,Q,D,DL,S\] up to Rev.040x on MBa6x REV.020x Carrier Board

[[_TOC_]]

## Variants

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### Linux

NOTE: Device tree from Linux Kernel 6.1 is not out of the box compatible to
yocto scarthgap. For Linux 6.1 support use yocto kirkstone.

|                              | linux-tq-6.6  |
| :--------------------------: | :-----------: |
| Fuses                        |      x        |
| UART (console on UART3, X15) |      x        |
| GPIO                         |      x        |
| Button (S6, S7, S8)          |      x        |
| I2C                          |      x        |
| EEPROM                       |      x        |
| RTC                          |      x        |
| SPI NOR                      |      x        |
| Buzzer                       |      x        |
| GPU                          |      x        |
| VPU H.264                    |      x        |
| VPU VP8                      |               |
| USB Host (X6/X7)             |      x        |
| USB Dual Role (X8)           |      x        |
| eMMC/SD (on-board/X9)        |      x        |
| SATA (X10)                   |      x        |
| Ethernet 1GiB/s (X11)        |      x        |
| Ethernet 100MBit/s (X12)     |      x        |
| CAN (X13/X14)                |      x        |
| RS-485 (X16)                 |               |
| HDMI (X17)                   |      x        |
| LVDS (X18, X19)              |      x        |
| LVDS FullHD (X18, X19)       |      x        |
| HDMI + LVDS (X17, X18)       |      x        |
| Audio Line In (X20)          |      x        |
| Audio Line Out (x22)         |      x        |
| PCIe (X23)                   |      x        |
| Parallel LCD (X27)           |      x        |
| I2C Touch (X27)              |      x        |
| Multi-Display                |      x        |

### ToDo / Untested

* SIM card (X24)
* MIPI-CSI (X28)
* MIPI-DSI (X28)
* MLB (X28)
* RS485 (UART4 / X16)

## Known Issues / Limitations

### General

* PCIe requires a power cycle to work reliably. Asserting a POR using S9 or S10 is not sufficient.
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle`.
  The values for `UBI_LEB_SIZE` and `UBI_MAX_LEB_COUNT` are predefined for 64 MiB SPI-NOR.
  The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR
* Backlight on parallel displays are enabled upon Power-On which might lead to random output.
  Display will be disabled during bootup and can be used normally afterwards.

### U-Boot

* FEC Ethernet port may have ARP timeouts. Restart the network command usually fixes
  the issue. Root cause is ethernet PHY on MBa6x.
* USB Ethernet (LAN9500 / X12C) is only usable after `usb start` and with valid
  MAC in `eth1addr` environment variable. Default value is parsed from EEPROM on MBa6x.
  To select the device, set `ethact` to the devicename. The name can be determined using
  `dm tree` and look for the device name.
* MTD and UBI Support are not configured. Only U-Boot and environment on SPI NOR
  are supported by built U-Boot configuration..
* USB dual role port (X8) is tested in U-Boot in peripheral mode only.
* Setting and clearing of several GPIOs (e.g. for user LEDs) is not working.
  Workaround: enable `GPIO_LED` support and other releated settings. Will be fixed in next
  release.

## Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* u-boot-with-spl-${MACHINE}.imx-sd: boot stream for SD / eMMC and SPI NOR

## Boot DIP Switches

### MBa6x DIP Switches

_Note:_

* S1/2/4 are for BOOT_CFG.
* S5 is for Boot Mode.
* `x` means position of DIP, `-` means don't care

### SD Card

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |      |  x   |      |      |      |      |    |     |     |  x  |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x   |      |  x   |  x   |  x   |  x   |    |  x  |  x  |     |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

### eMMC

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |  x   |      |      |      |      |      |    |     |  x  |     |  x  |     |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |      |  x   |  x   |  x   |  x   |  x   |    |  x  |     |  x  |     |  x  |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

### SPI

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |     |  x   |  x   |      |      |      |      |    |     |     |     |     |     |     |     |     |    |     |     |     |  x  |  x  |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |      |      |  x   |  x   |  x   |  x   |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |  x  |  x  |  x  |     |     |  x  |  x  |  x  |    |     |  x  |

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.
Support can vary with kernel branch and version.

*Note:* With MBa6x only one control interface for backlight is available.

| Interface       | Device tree                                               | Type               |
|-----------------|-----------------------------------------------------------|--------------------|
| HDMI            | imx6\[dl,q,qp\]-mba6\[a,b\]-hdmi.dtb                      | standard monitor   |
| HDMI + LVDS     | imx6\[dl,q,qp\]-mba6\[a,b\]-multi.dtb                     | HDMI + LVDS        |
| LVDS            | imx6\[dl,q,qp\]-mba6\[a,b\]-lvds-tm070jvhg33.dtb          | Tianma TM070JVHG33 |
| LVDS, dual      | imx6\[dl,q,qp\]-mba6\[a,b\]-duallvds-tm070jvhg33.dtb      | Tianma TM070JVHG33 |
| LVDS, FullHD    | imx6\[dl,q,qp\]-mba6\[a,b\]-lvds-g133han01.dtb            | AUO G133HAN.01     |
| Parallel        | imx6\[dl,q,qp\]-mba6\[a,b\]-cdtech-dc44.dtb               | CDTECH DC44 (DMB)  |
| Parallel        | imx6\[dl,q,qp\]-mba6\[a,b\]-cdtech-fc21.dtb               | CDTECH FC21 (DMB)  |

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime Support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
