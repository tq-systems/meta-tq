# TQMa8XxS

This README contains some useful information for TQMa8XxS on MB-SMARC-2

[[_TOC_]]

## Variants

* TQMa8XQPS REV.030x
* TQMa8XDPS REV.030x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and Linux kernel.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |                             |
| :----------------------------------------------- | :-------------------------: |
| **RAM configs**                                  |                             |
| TQMa8X\[D,Q\]PS                                  |            2 GiB            |
|                                                  |                             |
| CPU variants                                     |  i.MX8QXP C0 / i.MX8DXP C0  |
| Fuses                                            |              x              |
| GPIO                                             |              x              |
| I2C                                              |              x              |
| **QSPI**                                         |                             |
| Read                                             |              x              |
| Write                                            |              x              |
| Boot                                             |              x              |
| **eMMC / SD-Card**                               |                             |
| Read                                             |              x              |
| Write                                            |              x              |
| Boot                                             |              x              |
| **USB**                                          |                             |
| USB 2.0 Dual Role                                |              x              |
| USB 3.0 (Hub on TQMa8XxS)                        |              x              |
| **ENET (GigE via Phy on TQMa8XxS)**              |                             |
| ENET 0                                           |              x              |
| ENET 1                                           |              x              |
| **Bootstreams**                                  |                             |
| FlexSPI                                          |              x              |
| SD / eMMC                                        |              x              |
| uuu                                              |              x              |

**TODO or not tested / supported**

* temperature grade
  * SCU limitation
* CPU variants i.MX8DX/i.MX8DXP cannot be detected automatically from hardware
  (limitation of cpu driver / SCU firmware, currently fixed with U-Boot Kconfig)

### Linux

| Feature                                          |          fslc-6.1           |          fslc-6.6           |
| :----------------------------------------------- | :-------------------------: | :-------------------------: |
| **RAM configs**                                  |                             |                             |
| TQMa8X\[D,Q\]PS                                  |            2 GiB            |            2 GiB            |
|                                                  |                             |                             |
| CPU variants                                     |  i.MX8QXP C0 / i.MX8DXP C0  |  i.MX8QXP C0 / i.MX8DXP C0  |
| Fuses / OCRAM                                    |              x              |              x              |
| speed grade                                      |              x              |              x              |
| **UART**                                         |                             |                             |
| console on LPUART1 (X39)                         |              x              |              x              |
| LPUART3 via unused SCU pins (X20)                |              x              |              x              |
| **GPIO**                                         |                             |                             |
| SMARC GPIO pins                                  |              x              |              x              |
| **I2C**                                          |                             |                             |
| Temperature Sensors (without cpu-temp)           |              x              |              x              |
| RTC                                              |              x              |              x              |
| EEPROMS                                          |              x              |              x              |
| **ENET (GigE via Phy on TQMa8XxS)**              |                             |                             |
| ENET 0                                           |              x              |              x              |
| ENET 1                                           |              x              |              x              |
| **USB**                                          |                             |                             |
| USB 2.0 Dual Role (X3/X4)                        |              x              |              x              |
| USB 3.0 (Hub on TQMa8XxS) (X7/X8)                |              x              |              x              |
| **QSPI NOR**                                     |                             |                             |
| Read with 1-1-4 SDR                              |              x              |              x              |
| PP / Erase with 1-1-1 SDR                        |              x              |              x              |
| **Graphic**                                      |                             |                             |
| GPU                                              |              x              |              x              |
| VPU                                              |              x              |              x              |
| **Display**                                      |                             |                             |
| LVDS0/LVDS1                                      |              x              |              x              |
| Dual-Channel LVDS                                |              x              |              x              |
| **Audio**                                        |                             |                             |
| Line IN / Line Out (X17, X18)                    |              x              |              x              |
| **PCIe**                                         |                             |                             |
| mini-PCIe on MB-SMARC-2 (SX-PCEAC2-HMC-SP)       |              x              |              x              |
| **CAN-FD**                                       |                             |                             |
| CAN-FD                                           |              x              |              x              |
| **SPI**                                          |                             |                             |
| SPI user space device on all CS                  |              x              |              x              |
| **PWM**                                          |                             |                             |
| PWM in LVDS IP                                   |              x              |              x              |
| **CPU/PMIC thermal sensors**                     |                             |                             |
| via thermal zone                                 |              x              |              x              |
| **Cortex M4**                                    |                             |                             |
| examples running from TCM                        |              x              |              x              |
| use UART as debug console                        |              x              |              x              |

**TODO or not tested with new BSP**

* temperature grade
  * Due to SCU-API limitation temperature grading cannot be queried from SCU.
    Therfore thermal trip points are hardcoded in devicetree and must match the
    assembled CPU type. BSP default are trip points for industrial grading.
* DSI - DP bridge
  * Custom, experimental support upon request
* GPIO
  * Suspend / Wakeup GPIO

## Known Issues

* Default setting for `fdt_file` in u-boot does not match older linux kernel
  naming scheme. Current naming scheme is `<cpu>-<som>-<baseboard>[-feature].dtb`,
  old scheme was `<cpu>-<baseboard>[-feature].dtb`.
  See [Build Artifacts](#artifacts) for complete list of supported Device Tree files
* CAN
  * CAN FD is only supported on MB-SMARC-2 up to 1MBit/s (system limitation)
* USB
  * Port USB3 (X3 on MB-SMARC-2) is host only. Do only use a matching adapter
    on MB-SMARC-2
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset
  * Linux: overcurrent with some USB Sticks on MB-SMARC-2
* FlexSPI
  * erase of ranges >= 16 MB fails under linux
* SPI: Hardware-controlled chipselects are not driven as expected
  * Toggle after each Byte when using DMA
  * Inbetween each `spi_transfer`
  * Use of GPIO controlled chip-selects instead is recommended
  * By default all chip-selects are configured as GPIO
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* imx8qxp\*.dtb: device tree blobs for TQMa8XQPS
* imx8dxp\*.dtb: device tree blobs for TQMa8XDPS
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4: boot stream for SD / eMMC + M4 Demo
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-uuu-${MACHINE}-uuu.bin-flash\_spl: boot stream for UUU
* hello\_world.bin (Cortex M4 demo, CM4 UART, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M4 demo, CM4 UART, TCM)

## Boot DIP Switches

_Note:_

* DIP S3 is for Boot Mode.

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

To build bootstream for UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

```
UBOOT_CONFIG:tqma8xxs = "uuu"
IMXBOOT_TARGETS:tqma8xxs = "flash_spl"
```

Rebuild boot stream:

```
bitbake imx-boot
```

Use new compiled bootstream containing U-Boot capable of handling SDP together
with UUU tool:

```
sudo uuu -b spl imx-boot-<machine>-uuu.bin
```

## Howto

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

Note: Only `rtc1` (RTC in CPU SNVS domain) can be used on this platform

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                                    | Type               |
|-----------------|------------------------------------------------|--------------------|
| LVDS0           | imx8\[d,q\]xp-mb-smarc-2-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS0, dual     | imx8\[d,q\]xp-mb-smarc-2-lvds-g133han01.dtb    | AUO G133HAN.01     |
| LVDS1           | imx8\[d,q\]xp-mb-smarc-2-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |

Please note manual for backlight power supply. For MB-SMARC-2 you can bridge
X14 pin 1 and 2 to provide 12V.

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP         |
| --------- | --------- | ----------- |
| CAN0      | X29       | `TERM CAN0` |
| CAN1      | X30       | `TERM CAN1` |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

**Note**: There is an absolute hardware limit on the bitrate of 1MBit/s, independently from CAN-FD.
  For that reason CAN-FD is disabled by default. While technically possible using CAN-FD with 1MBit/s, the non-datarate
  has to be lowered accordingly.

  When CAN-FD is enabled, the non-datarate needs to be lower than the datarate. The Linux kernel will emit a warning if it is deemed the `brp` setting do not match.

### Cortex M4

Demos are compiled to use Cortex M4 UART with 115200 8N1 on Pins SCU\_GPIO\_00 and SCU\_GPIO\_01
For demos available in the BSP and the device tree to be used see [artifacts section](#artifacts).

*Note:* UART3 uses the same pins as Cortex M4 UART and has to be disabled when using Cortex M4.

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8XxS](https://support.tq-group.com/en/arm/tqma8xxs)
