# TQMa8MPxS

This README contains some useful information for TQMa8MPxS on MB-SMARC-2

[[_TOC_]]

## Variants

* TQMa8MPQS REV.010x on MB-SMARC-2

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                   |  REV.010x   |
|:------------------------------------------|:-----------:|
| RAM configs                               | 1,2,4,8 GiB |
| CPU variants                              |  i.MX8MPQ   |
| Fuses / OCRAM                             |      x      |
| speed grade / temperature grade detection |      x      |
| UART (console on UART3, X20)              |      x      |
| **I2C**                                   |             |
| system EEPROM parsing                     |      x      |
| PMIC                                      |      x      |
| **eMMC / SD**                             |             |
| Read                                      |      x      |
| Write                                     |      x      |
| **Ethernet**                              |             |
| GBE0 (X11)                                |      x      |
| GBE1 (X10)                                |      x      |
| **Bootdevices**                           |             |
| SD-Card on USDHC2                         |      x      |
| eMMC on USDHC3                            |      x      |
| QSPI-NOR on FlexSPI                       |      x      |
| Serial Downloader (X4)                    |      x      |
| **USB**                                   |             |
| USB 3.0 Host / Hub (X7)                   |      x      |
| USB 2.0 Host / Hub (X8)                   |      x      |
| USB DRD (USB 2.0 Cable Detect, VBUS, X4)  |      x      |
| **QSPI NOR**                              |             |
| Read with 1-4-4 SDR                       |      x      |
| PP / Erase with 1-1-4 SDR                 |      x      |
| **Cortex M7**                             |             |
| env settings for starting from TCM        |             |
| examples with UART3 as debug console      |             |

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite

### Linux

| Feature                                                      |    6.12.y   |
|:-------------------------------------------------------------| :---------: |
| RAM configs                                                  | 1,2,4,8 GiB |
| CPU variants                                                 |  i.MX8MPQ   |
| Fuses / OCRAM                                                |      x      |
| speed grade / temperature grade detection                    |      x      |
| **UART**                                                     |             |
| SER0 on UART1 (X39)                                          |      x      |
| SER1 on UART3 (console, X20)                                 |      x      |
| SER2 on UART2 (X25)                                          |      x      |
| SER3 on UART4 (X40)                                          |      x      |
| **I2C**                                                      |      x      |
| EEPROMs                                                      |      x      |
| PMIC                                                         |      x      |
| RTC                                                          |      x      |
| Temperature Sensors                                          |      x      |
| **ENET**                                                     |      x      |
| GBE0 (X11)                                                   |      x      |
| GBE1 (X10)                                                   |      x      |
| **USB**                                                      |             |
| USB 3.0 Host / Hub (X7)                                      |      x      |
| USB 2.0 Host / Hub (X8)                                      |      x      |
| USB DRD USB 2.0 (X4, peripheral only)                        |      x      |
| USB DRD (USB 3.0 Cable Detect, VBUS, X3)                     |             |
| **QSPI NOR**                                                 |             |
| Read with 1-4-4 SDR                                          |      x      |
| PP / Erase with 1-4-4 SDR                                    |      x      |
| **Graphic / Multimedia**                                     |             |
| GPU                                                          |      x      |
| VPU                                                          |      x      |
| **Display**                                                  |             |
| LVDS (X46, X48)                                              |      x      |
| Dual-Channel LVDS                                            |             |
| HDMI (X6)                                                    |      x      |
| DisplayPort using MIPI-DSI Bridge (X5)                       |      x      |
| **Audio**                                                    |             |
| HDMI                                                         |             |
| Codec (Line IN / Line OUT)                                   |      x      |
| **PCIe**                                                     |             |
| wireless card at M.2 (X44)                                   |      x      |
| **CAN-FD**                                                   |             |
| CAN-FD (X29, X30)                                            |      x      |
| **Cortex M7**                                                |             |
| examples running from TCM                                    |             |
| use UART4 as debug console (see issues)                      |             |
| **MIPI CSI (see Issues section)**                            |             |
| Gray with Vision Components GmbH camera (Sensor OV9281)      |             |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |             |
| **NPU**                                                      |             |
| NPU                                                          |             |

## TODO / Untested

* Audio
  * Codec Microphone in
* I²C interface of PCIe Clock generator not tested
* HDMI Audio

## Known Issues / Limitations

* Ethernet
  * Possible communication error to PHY attached to FEC, reboot required to fix
  * ETH1 looses manual assigned IP after suspend/resume. Default systemd network
    configuration uses DHCP with fallback. Has to be adjusted if needed.
* USB Host
  * USB Superspeed U3 powersave mode is broken
  * U-Boot: some USB stick types are not working correctly in U-Boot.
    After `usb reset` they may fail to enumerate correctly or causing
    a system reset during enumeration with errors like:
    ```
    WARN halted endpoint, queueing URB anyway.
    Unexpected XHCI event TRB, skipping... (fbf120f0 00000000 13000000 03008401)
    BUG at drivers/usb/host/xhci-ring.c:496/abort_td()!
    BUG!
    �esetting ...
    ```
* USB DR ports don't detect USB devices in host mode
* USB DR ports don't setup a USB device in peripheral mode
* USB Bluetooth:
  * Some adapters cause the following error during bootup

    `Bluetooth: hci0: unexpected event for opcode 0xfc2f`

    According to https://lkml.org/lkml/2019/6/6/868 this can be ignored
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.
* Kernel based on linux-tq / linux-rt-tq
  * Suspend & resume not supported (yet)
* no support for vendor kernel and BSP
* SER2's RTS and CTS signals are controlled by GPIO only

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * imx8mp-tqma8mpqs-mb-smarc-2.dtb
  * imx8mp-tqma8mpqs-mb-smarc-2-lvds0-tm070jvhg33.dtb (LVDS display TIANMA TM070JVHG33 on LVDS0, X46, X48)
  * imx8mp-tqma8mpqs-mb-smarc-2-lvds1-tm070jvhg33.dtb (LVDS display TIANMA TM070JVHG33 on LVDS1, X46, X48)

* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_evk\_uboot: boot stream for UUU

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S3 on MB-SMARC-2.

### SD Card

| DIP S3  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   | x | x |   |
| OFF     | x |   |   | x |

### eMMC

| DIP S3  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      | x |   |   |   |
| OFF     |   | x | x | x |

### FLEXSPI

| DIP S3  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      | x | x |   |   |
| OFF     |   |   | x | x |

### Serial Downloader

| DIP S3  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   |   | x |
| OFF     | x | x | x |   |

## Boot device initialisation and update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### OS boot

See the [Distroboot README](README.Distroboot.md).

__Note:__ Default u-boot environment variable `bootcmd` has to be set to `run distro_bootcmd`

### OS updates

See [RAUC](RAUC.md).

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

### Display Support

HDMI and DP support are enabled by default. Additionally LVDS display can be enabled by
using the corresponding device tree. To allow reusage, the support for each display
is separated in a dtsi fragment.

| Interface       | Device tree                                       | Type               |
|-----------------|---------------------------------------------------|--------------------|
| HDMI + DP       | imx8mp-tqma8mpqs-mb-smarc-2.dtb                   | compatible monitor |
| LVDS0           | imx8mp-tqma8mpqs-mb-smarc-2-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS1           | imx8mp-tqma8mpqs-mb-smarc-2-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |

*Note*: `weston` by default uses the DRI device with highest number. This is usually Display Port.
To explicitely select a DRI device, please refer to `--drm-device` argument during startup.

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      | X29       |  S4 |
| CAN1      | X30       |  S4 |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

**Note**: There is an absolute hardware limit on the bitrate of 1MBit/s, independently from CAN-FD.
  For that reason CAN-FD is disabled by default. While technically possible using CAN-FD with 1MBit/s, the non-datarate
  has to be lowered accordingly.

  When CAN-FD is enabled, the non-datarate needs to be lower than the datarate. The Linux kernel will emit a warning if it is deemed the `brp` setting do not match.

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8MPxS](https://support.tq-group.com/en/arm/tqma8mpxs)
