# TQMa8Xx / TQMa8Xx4

This README contains some useful information for TQMa8Xx and TQMa8Xx4 on MBa8Xx

[[_TOC_]]

## Variants

* TQMa8XDP REV.020x / 0x030x
* TQMa8XQP REV.020x / 0x030x
* TQMa8XDP4 REV.010x
* TQMa8XQP4 REV.010x

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and Linux kernel.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |                             |
| :----------------------------------------------- | :-------------------------: |
| **RAM configs**                                  |                             |
| TQMa8X\[D,Q\]P                                   |       1,2 GiB DDR3L ECC     |
| TQMa8X\[D,Q\]P4                                  |         2 GiB LPDDR4        |
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
| USB 2.0 (Device, for UUU/MFG)                    |              x              |
| USB 3.0 (Hub on MBa8Xx)                          |              x              |
| **ENET (GigE via Phy on MBa8x)**                 |                             |
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
| TQMa8X\[D,Q\]P                                   |       1,2 GiB DDR3L ECC     |       1,2 GiB DDR3L ECC     |
| TQMa8X\[D,Q\]P4                                  |         2 GiB LPDDR4        |         2 GiB LPDDR4        |
|                                                  |                             |                             |
| CPU variants                                     |  i.MX8QXP C0 / i.MX8DXP C0  |  i.MX8QXP C0 / i.MX8DXP C0  |
| Fuses / OCRAM                                    |              x              |              x              |
| speed grade                                      |              x              |              x              |
| **UART**                                         |                             |                             |
| console on LPUART1 (X13)                         |              x              |              x              |
| LPUART3 via unused SAI pins (X4_45/X4_47)        |              x              |                             |
| **GPIO**                                         |                             |                             |
| LED                                              |              x              |              x              |
| Button                                           |              x              |              x              |
| wakeup from GPIO button                          |              x              |              x              |
| GPIO on pin heads                                |              x              |                             |
| **I2C**                                          |                             |                             |
| Temperature Sensors (without cpu-temp)           |              x              |              x              |
| RTC                                              |              x              |              x              |
| EEPROMS                                          |              x              |              x              |
| **ENET (GigE via Phy on MBa8x)**                 |                             |                             |
| ENET 0                                           |              x              |              x              |
| ENET 1                                           |              x              |              x              |
| **USB**                                          |                             |                             |
| USB 2.0 Dual Role                                |              x              |              x              |
| USB 3.0 (Hub on MBa8x)                           |              x              |              x              |
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
| Line IN / Line Out (X9, X10)                     |              x              |              x              |
| **PCIe**                                         |                             |                             |
| mini-PCIe on MBa8xx (SX-PCEAC2-HMC-SP)           |              x              |              x              |
| **CAN-FD**                                       |                             |                             |
| CAN-FD                                           |              x              |              x              |
| **SPI**                                          |                             |                             |
| SPI user space device on all CS                  |              x              |              x              |
| **ADC**                                          |                             |                             |
| ADC                                              |              x              |              x              |
| **PWM**                                          |                             |                             |
| PWM in LVDS IP                                   |              x              |              x              |
| **CPU/PMIC thermal sensors**                     |                             |                             |
| via thermal zone                                 |              x              |              x              |
| **Cortex M4**                                    |                             |                             |
| examples running from TCM                        |              x              |              x              |
| use UART as debug console                        |              x              |              x              |

**TODO or not tested with new BSP**

* TQMa8XDP REV.020x
* temperature grade
  * Due to SCU-API limitation temperature grading cannot be queried from SCU.
    Therfore thermal trip points are hardcoded in devicetree and must match the
    assembled CPU type. BSP default are trip points for industrial grading.
* DSI - DP bridge

## Known Issues / Limitations

* Default setting for `fdt_file` in u-boot does not match older linux kernel
  naming scheme. Current naming scheme is `<cpu>-<som>-<baseboard>[-feature].dtb`,
  old scheme was `<cpu>-<baseboard>[-feature].dtb`.
  See [Build Artefacts](#artefacts) for complete list of supported Device Tree files
* USB
  * U-Boot: USB 2.0 dual role port is limited to device mode for UUU / MFG use case
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset
* SPI: Hardware-controlled chipselects are not driven as expected
  * Toggle after each Byte when using DMA
  * Inbetween each `spi_transfer`
  * Use of GPIO controlled chip-selects instead is recommended
  * By default chip-selects are configured as GPIO, if possible (see below)
  * Note for MBa8Xx: The following pin **cannot** be configured as GPIO:
    * `SPI3_CS1` on pad `SPI3_CS1`
** UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.

## Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* imx8qxp\*.dtb: device tree blobs for TQMa8XQP\[4\]
* imx8dxp\*.dtb: device tree blobs for TQMa8XDP\[4\]
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

* S1 is for Boot Mode.
* X means position of DIP, - means don't care

### SD Card

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   | x | x |
| OFF      | x | x |   |   |

### eMMC

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   | x |   |
| OFF      | x | x |   | x |

### FLEXSPI

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   | x | x |   |
| OFF      | x |   |   | x |

### Serial Downloader

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   |   | x |
| OFF      | x | x | x |   |

### Boot from Fuses

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   |   |   |
| OFF      | x | x | x | x |

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

To build bootstream for UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

```
UBOOT_CONFIG:tqma8xx = "uuu"
IMXBOOT_TARGETS:tqma8xx = "flash_spl"
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

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

*Note:* With MBa8Xx only one control interface for backlight is available (X22).

| Interface       | Device tree                                               | Type               |
|-----------------|-----------------------------------------------------------|--------------------|
| LVDS0           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS1           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS, dual      | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds-g133han01.dtb    | AUO G133HAN.01     |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      |    X11    | SW1 |
| CAN1      |    X12    | SW2 |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

### Cortex M4

Demos are compiled to use Cortex M4 UART with 115200 8N1 on Pins SCU\_GPIO\_00 and SCU\_GPIO\_01
For demos available in the BSP and the device tree to be used see [artefacts section](#artefacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8Xx](https://support.tq-group.com/en/arm/tqma8xx)  
See [TQ Embedded Wiki for TQMa8Xx4](https://support.tq-group.com/en/arm/tqma8xx4)
