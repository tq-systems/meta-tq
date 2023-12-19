# TQMa6\[QP,DP,Q,D,DL,S\] up to Rev.040x on MBa6x REV.020x carrier board

[[_TOC_]]

## Variants

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level README.md for configurations usable as MACHINE.

## Supported Features

### Linux

|                              | linux-imx-tq-5.10 | linux-imx-tq-5.15 | linux-tq-5.15 | linux-tq-6.1  |
| ---------------------------- | :---------------: | :---------------: | :-----------: | :-----------: |
| Fuses                        |       x           |        x          |      x        |      x        |
| UART (console on UART3, X15) |       x           |        x          |      x        |      x        |
| GPIO                         |       x           |        x          |      x        |      x        |
| Button (S6, S7, S8)          |       x           |        x          |      x        |      x        |
| I2C                          |       x           |        x          |      x        |      x        |
| EEPROM                       |       x           |        x          |      x        |      x        |
| RTC                          |       x           |        x          |      x        |      x        |
| SPI NOR                      |       x           |        x          |      x        |      x        |
| Buzzer                       |       x           |        x          |      x        |      x        |
| GPU                          |       x           |        x          |      x        |      x        |
| VPU H.264                    |       x           |        x          |      x        |      x        |
| VPU VP8                      |       x           |        x          |               |               |
| USB Host (X6/X7)             |       x           |        x          |      x        |      x        |
| USB Dual Role (X8)           |       x           |        x          |      x        |      x        |
| eMMC/SD (on-board/X9)        |       x           |        x          |      x        |      x        |
| SATA (X10)                   |       x           |        x          |      x        |      x        |
| Ethernet 1GiB/s (X11)        |       x           |        x          |      x        |      x        |
| Ethernet 100MBit/s (X12)     |       x           |        x          |      x        |      x        |
| CAN (X13/X14)                |       x           |        x          |      x        |      x        |
| RS-485 (X16)                 |       x           |                   |      x        |      x        |
| HDMI (X17)                   |       x           |        x          |      x        |      x        |
| LVDS (X18, X19)              |       x           |        x          |      x        |      x        |
| LVDS FullHD (X18, X19)       |                   |                   |               |      x        |
| HDMI + LVDS (X17, X18)       |                   |                   |      x        |      x        |
| Audio Line In (X20)          |       x           |        x          |      x        |      x        |
| Audio Line Out (x22)         |       x           |        x          |      x        |      x        |
| PCIe (X23)                   |       x           |        x          |      x        |      x        |
| Parallel LCD (X27)           |                   |                   |      x        |      x        |
| I2C Touch (X27)              |                   |                   |      x        |      x        |
| Multi-Display                |                   |                   |      x        |      x        |

### ToDo / Untested
* Mic In (X21)
* SIM card (X24)
* MIPI-CSI (X28)
* MIPI-DSI (X28)
* MLB (X28)
* RS-485 (`linux-imx-tq-5.15`)

## Known issues / Limitations

- PCIe requires a power cycle to work reliably. Asserting a POR using S9 or S10 is not sufficient.
- eth1 (X12) uses a random MAC address. The one stored in MBa6 EEPROM is currently not used.
  (`linux-imx-tq` only)
  Workaround: Get MAC address from MBa6 EEPROM `i2c dev 0; i2c md 57 20 6` and
  run `setenv eth1addr <MAC address>; saveenv` once in u-boot console
- eth1 (X12) (USB to Ethernet) causes an error on `usb reset` if no MAC address
  is set: `Error: smsc95xx_eth address not set.`
- Backlight on parallel displays are enabled upon Power-On which might lead to random output.
  Display will be disabled during bootup and can be used normally afterwards.
- `asound.state` is not compatible with `linux-5.4` (`linux-imx-tq` as well as `linux-tq`)
- The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR
- `linux-imx-tq-5.15`
  * Default CMA size too small for HDMI. e.g. run `setenv cma_size 320M` once in u-boot console
  * Using dual LVDS the 2nd framebuffer needs to be unblanked first:
    * `echo 0 > /sys/class/graphics/fb2/blank`
    * `fb-test -f 2 -p 0`
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

### SPI NOR

To initialize SPI NOR with bootloader, write the [bootloader image](#artifacts)
to SPI NOR at offset 0x400:

```
setenv uboot <U-Boot SPI boot image>
tftp ${loadaddr} ${uboot}
sf probe
sf erase 0 0x100000
sf write ${loadaddr} 0x400 ${filesize}
```

### SD / e-MMC

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

### Update bootloader

To update the bootloader of system using U-Boot / TFTP following shortcuts
exist.

**Note**: Kernel and device tree are stored in the root fs and can be updated
on the filesystem level.

#### SD / e-MMC

```
setenv uboot <name of u-boot image>
run update_uboot_mmc
```

#### SPI

```
setenv uboot <name of u-boot image>
run update_uboot_spi
```

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

### Dual LVDS usage

_Note:_ Only valid for `linux-imx-tq-5.10`

The 2nd framebuffer / display is blanked by default. In order to use the display on `LVDS1` it need to be unblanked: `echo 0 > /sys/devices/platform/fb@3/graphics/fb2/blank`

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
