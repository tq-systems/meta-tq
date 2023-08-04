# TQMa8Xx / TQMa8Xx4

This README contains some useful information for TQMa8Xx and TQMa8Xx4 on MBa8Xx

[[_TOC_]]

## Variants

* TQMa8XDP REV.020x / 0x030x
* TQMa8XQP REV.020x / 0x030x
* TQMa8XDP4 REV.010x
* TQMa8XQP4 REV.010x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and Linux kernel.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

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
| **e-MMC / SD-Card**                              |                             |
| Read                                             |              x              |
| Write                                            |              x              |
| Boot                                             |              x              |
| **USB**                                          |                             |
| USB 2.0 Dual Role                                |              x              |
| USB 3.0 (Hub on MBa8Xx)                          |              x              |
| **ENET (GigE via Phy on MBa8x)**                 |                             |
| ENET 0                                           |              x              |
| ENET 1                                           |              x              |
| **Bootstreams**                                  |                             |
| FlexSPI                                          |              x              |
| SD / e-MMC                                       |              x              |
| UUU / mfgtool                                    |              x              |

**TODO or not tested / supported**

* temperature grade
  * SCU limitation
* CPU variants i.MX8DX/i.MX8DXP cannot be detected automatically from hardware
  (limitation of cpu driver / SCU firmware, currently fixed with U-Boot Kconfig)

### Linux

| Feature                                          |                             |
| :----------------------------------------------- | :-------------------------: |
| **RAM configs**                                  |                             |
| TQMa8X\[D,Q\]P                                   |       1,2 GiB DDR3L ECC     |
| TQMa8X\[D,Q\]P4                                  |         2 GiB LPDDR4        |
|                                                  |                             |
| CPU variants                                     |  i.MX8QXP C0 / i.MX8DXP C0  |
| Fuses / OCRAM                                    |              x              |
| speed grade / temperature grade detection        |              x              |
| **UART**                                         |                             |
| console on LPUART1 (X13)                         |              x              |
| LPUART3 via unused SAI pins                      |              x              |
| **GPIO**                                         |                             |
| LED                                              |              x              |
| Button                                           |              x              |
| wakeup from GPIO button                          |              x              |
| GPIO on pin heads                                |              x              |
| **I2C**                                          |                             |
| Temperature Sensors (without cpu-temp)           |              x              |
| RTC                                              |              x              |
| EEPROMS                                          |              x              |
| **ENET (GigE via Phy on MBa8x)**                 |                             |
| ENET 0                                           |              x              |
| ENET 1                                           |              x              |
| **USB**                                          |                             |
| USB 2.0 Dual Role                                |              x              |
| USB 3.0 (Hub on MBa8x)                           |              x              |
| **QSPI NOR**                                     |                             |
| Read with 1-1-4 SDR                              |              x              |
| PP / Erase with 1-1-1 SDR                        |              x              |
| **Graphic**                                      |                             |
| GPU                                              |              x              |
| VPU                                              |                             |
| **Display**                                      |                             |
| LVDS0/LVDS1                                      |              x              |
| **Audio**                                        |                             |
| Line IN / Line Out (X9, X10)                     |              x              |
| **PCIe**                                         |                             |
| mini-PCIe on MBa8xx (SX-PCEAC2-HMC-SP)           |              x              |
| M.2 PCIe with WiFi Card (SX-PCEAC2-M2-SP)        |              x              |
| **CAN-FD**                                       |                             |
| CAN-FD                                           |              x              |
| **SPI**                                          |                             |
| SPI user space device on all CS                  |              x              |
| **ADC**                                          |                             |
| ADC                                              |              x              |
| **PWM**                                          |                             |
| PWM in LVDS IP                                   |              x              |
| **CPU/PMIC thermal sensors**                     |                             |
| via thermal zone                                 |              x              |
| **Cortex M4**                                    |                             |
| examples running from TCM                        |              x              |
| use UART as debug console                        |              x              |

**TODO or not tested with new BSP**

* TQMa8XDP REV.020x
* temperature grade
  * SCU limitation
* Audio
  * Mic In untested
* DSI - DP bridge

## Known Issues

* Default setting for `fdt_file` in u-boot does not match new naming scheme.
  See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
* USB
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset
* Upon resume ethernet PHY will not establish a new link again
* SPI: Hardware-controlled chipselects are not driven as expected
  * Toggle after each Byte when using DMA
  * Inbetween each `spi_transfer`
  * Use of GPIO controlled chip-selects instead is recommended
  * By default chip-selects are configured as GPIO, if possible (see below)
  * Note for MBa8Xx: The following pin **cannot** be configured as GPIO:
    * `SPI3_CS1` on pad `SPI3_CS1`

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* imx8qxp\*.dtb: device tree blobs for TQMa8XQP\[4\]
* imx8dxp\*.dtb: device tree blobs for TQMa8XDP\[4\]
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4: boot stream for SD / e-MMC + M4 Demo
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU
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

### e-MMC

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

## Boot device initialisation and update

See [here](./README.BootMediaTQMa8.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

To build bootstream for UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

```
UBOOT_CONFIG:tqma8xx = "mfgtool"
IMXBOOT_TARGETS:tqma8xx = "flash_spl"
```

Rebuild boot stream:

```
bitbake imx-boot
```

Use new compiled bootstream containing U-Boot capable of handling SDP together
with UUU tool:

```
sudo uuu -b spl imx-boot-<machine>-mfgtool.bin
```

## Howto

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

*Note:* With MBa8Xx only one control interface for backlight is available (X22).

| Interface       | Device tree                                               | Type        ----   |
|-----------------|-----------------------------------------------------------|--------------------|
| LVDS0           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS1           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      |    X11    | SW1 |
| CAN1      |    X12    | SW2 |

#### Enable without CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with
MBa8Xx and meta-tq / systemd

Configure CAN1/2 per commandline without CAN-FD:
```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

To (permanently) configure CAN1/2 in systemd network file, set in files
* /lib/systemd/network/20-can0.network
* /lib/systemd/network/20-can1.network

`FDMode=no` to disable CAN-FD.

#### Enable CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with
MBa8Xx and meta-tq / systemd.

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### Cortex M4

Demos are compiled to use Cortex M4 UART with 115200 8N1 on Pins SCU\_GPIO\_00 and SCU\_GPIO\_01
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8Xx](https://support.tq-group.com/en/arm/tqma8xx)  
See [TQ Embedded Wiki for TQMa8Xx4](https://support.tq-group.com/en/arm/tqma8xx4)
