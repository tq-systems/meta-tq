# TQMa93xxLA

This README contains some useful information for TQMa93xxLA on MBa9xxxCA

[[_TOC_]]

## Variants

* TQMa93xxLA REV.010x on MBa9xxxCA REV.010x

## Version information for software components

* U-Boot based on v2022.04 and NXP vendor BSP
* Linux based on v5.15.y and NXP vendor BSP
* TF-A based on v2.6 ands NXP vendor BSP

## Supported machine configurations:

* tqma93xxla-mba9xxxca

## Supported Features

### U-Boot

| Feature                                          |   REV.010x   |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1 GiB       |
| CPU variants                                     |     i.MX93   |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |              |
| UART (console on UART1)                          |       x      |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| **I2C**                                          |              |
| system EEPROM parsing                            |       x      |
| PMIC                                             |       x      |
| **e-MMC / SD**                                   |              |
| Read                                             |       x      |
| Write                                            |       x      |
| **Ethernet**                                     |              |
| GigE / FEC via Phy on MBa9xxxCA                  |       x      |
| GigE / EQOS via Phy on MBa9xxxCA                 |       x      |
| **Bootdevices**                                  |              |
| SD-Card on USDHC2                                |       x      |
| e-MMC on USDHC1                                  |       x      |
| QSPI-NOR on FlexSPI                              |       x      |
| Serial Downloader                                |              |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |       x      |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x      |
| **QSPI NOR**                                     |              |
| Read with 1-1-1 SDR                              |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |
| **Cortex M33**                                   |              |
| env settings for starting from TCM               |              |
| examples with UART3 as debug console             |              |

**TODO or not tested / supported**

* CPU variants with single core and without NPU

### Linux

| Feature                                          |   REV.010x   |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1 GiB       |
| CPU variants                                     |     i.MX93   |
| Fuses / OCRAM                                    |              |
| speed grade / temperature grade detection        |              |
| **UART**                                         |              |
| console on UART1 (via USB / UART converter)      |       x      |
| UART2/3/4                                        |       x      |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| **I2C**                                          |              |
| EEPROMs                                          |       x      |
| PMIC                                             |       x      |
| RTC                                              |       x      |
| Temperature Sensors                              |       x      |
| **ENET**                                         |              |
| GigE / FEC via Phy on MBa9xxxCA                  |       x      |
| GigE / EQOS via Phy on MBa9xxxCA                 |       x      |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |              |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x      |
| **QSPI NOR**                                     |              |
| Read with 1-1-1 SDR                              |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |
| **Display**                                      |              |
| LVDS                                             |              |
| **CAN-FD**                                       |              |
| CAN-FD                                           |              |
| **SPI**                                          |              |
| spidev at all CS                                 |              |
| ADC                                              |              |
| **Cortex M33**                                   |              |
| examples running from TCM                        |              |
| use UART3 as debug console (see issues)          |              |
| LPB boot                                         |              |
| **MIPI CSI (see Issues section)**                |              |
| Gray with Vision Components GmbH camera (Sensor OV9281) |       |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |  |

## TODO:

* CAN
* USB Host
* SPI
* MIPI-DSI
* MIPI-CSI
* LVDS
* RS485
* WiFi
* Cortex M33
* UBI Boot
* LPB Booot modes

## Known Issues

* REV.010x EVK not compatible with UUU

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
    * mx93-tqma93xxla-mba9xxxca.dtb
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1.

| Bootmode | Description           | S1-1 | S1-2 | S1-2 | S1-4 |
| :------: | :-------------------: | :--: | :--: | :--: | :--: |
| 0000     | Boot from fuses       | OFF  | OFF  | OFF  | OFF  |
| 0001     | Serial Downloader     | OFF  | OFF  | OFF  | ON   |
| 0010     | e-MMC (USDHC1)        | OFF  | OFF  | ON   | OFF  |
| 0011     | SD Card (USDHC2)      | OFF  | OFF  | ON   | ON   |
| 0100     | QSPI (FlexSPI NOR     | OFF  | ON   | OFF  | OFF  |

**NOTE:** LPB boot modes not supported yet.

## Boot device initialisation and update

See [here](./README.BootMediaTQMa8.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

TODO

## Howto


### Display Support

TODO

### CAN

TODO

### Cortex M33

TODO

## Support Wiki

TODO
