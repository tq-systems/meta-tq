# TQMa6\[QP,DP,Q,D,DL,S\] up to Rev.040x on MBa6x REV.020x carrier board

[[_TOC_]]

## Variants

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### Linux

NOTE: Linux Kernel 6.1 is incompatible to yocto scarthgap.
For Linux 6.1 use yocto kirkstone.

|                              | linux-tq-6.6  |
| ---------------------------- | :-----------: |
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
| RS-485 (X16)                 |      x        |
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
* Mic In (X21)
* SIM card (X24)
* MIPI-CSI (X28)
* MIPI-DSI (X28)
* MLB (X28)

## Known issues / Limitations

- PCIe requires a power cycle to work reliably. Asserting a POR using S9 or S10 is not sufficient.
- eth1 (X12) (USB to Ethernet) causes an error on `usb reset` if no MAC address
  is set: `Error: smsc95xx_eth address not set.`
- Backlight on parallel displays are enabled upon Power-On which might lead to random output.
  Display will be disabled during bootup and can be used normally afterwards.
- The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR
- U-Boot: FEC Ethernet port is from time to time not working after U-Boot start.
  Another powercycle/reset or PHY software reset (`mdio write ethernet@2188000 0
  0x8000`) is required
- U-Boot: USB dual role port (X8) is deactivated
- U-Boot: Setting and clearing GPIOs (e.g. for user LEDs) is not working

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* u-boot-with-spl-${MACHINE}.imx-sd: boot stream for SD / e-MMC and SPI NOR

## Boot DIP Switches

_Note:_

* S1/2/4 are for BOOT_CFG.
* S5 is for Boot Mode.
* X means position of DIP, - means don't care

### SD Card

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |      |  x   |      |      |      |      |    |     |     |  x  |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x   |      |  x   |  x   |  x   |  x   |    |  x  |  x  |     |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

### e-MMC

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

## Boot device initialisation and update

See [here](./README.imx-arm64.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx-arm64.UUU.md) for details about using Serial Download mode and UUU.

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
| Parallel        | imx6\[dl,q,qp\]-mba6\[a,b\]-cdtech-dc44.dtb               | CDTECT DC44 (DMB)  |
| Parallel        | imx6\[dl,q,qp\]-mba6\[a,b\]-cdtech-fc21.dtb               | CDTECH FC21 (DMB)  |

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
