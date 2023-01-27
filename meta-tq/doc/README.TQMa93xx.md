# TQMa93xxLA

This README contains some useful information for TQMa93xxLA on MBa93xxCA

[[_TOC_]]

## Variants

* TQMa93xxLA REV.010x on MBa93xxCA REV.010x

## Version information for software components

* U-Boot based on v2022.04 and NXP vendor BSP
* Linux based on v5.15.y and NXP vendor BSP
* TF-A based on v2.6 ands NXP vendor BSP

## Supported machine configurations:

* tqma93xxla-mba93xxca

## Supported Features

### U-Boot

| Feature                                          |   REV.010x   |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1 GiB       |
| CPU variants                                     |     i.MX93   |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |       x      |
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
| GigE / FEC via Phy on MBa93xxCA                  |       x      |
| GigE / EQOS via Phy on MBa93xxCA                 |       x      |
| **Bootdevices**                                  |              |
| SD-Card on USDHC2                                |       x      |
| e-MMC on USDHC1                                  |       x      |
| QSPI-NOR on FlexSPI                              |       x      |
| Serial Downloader                                |              |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |       x      |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x      |
| **QSPI NOR**                                     |              |
| Read with 1-1-4 SDR                              |       x      |
| PP / Erase with 1-1-4 SDR                        |       x      |
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
| IMU / Gyroscope                                  |       x      |
| Port expander                                    |       x      |
| **ENET**                                         |              |
| GigE / FEC via Phy on MBa93xxCA                  |       x      |
| GigE / EQOS via Phy on MBa93xxCA                 |       x      |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |              |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x      |
| **QSPI NOR**                                     |              |
| Read with 1-1-4 SDR                              |       x      |
| PP / Erase with 1-1-4 SDR                        |       x      |
| **Display**                                      |              |
| LVDS                                             |       x      |
| **CAN-FD**                                       |              |
| CAN-FD                                           |       x      |
| **SPI**                                          |              |
| spidev at all CS                                 |              |
| **internal ADC**                                 |              |
| ADC                                              |       x      |
| **Cortex M33**                                   |              |
| examples running from TCM                        |              |
| use UART3 as debug console (see issues)          |              |
| LPB boot                                         |              |
| **MIPI CSI (see Issues section)**                |              |
| Gray with Vision Components GmbH camera (Sensor OV9281) |       |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |  |

## TODO:

* SPI
* MIPI-DSI
* MIPI-CSI
* RS485 (technical limitation, will be fixed with MBa93xxCA REV.020x)
* WiFi (driver and firmware loading OK, needs additional testing)
* Cortex M33
* UBI Boot
* LPB Booot modes

## Known Issues

* REV.010x EVK not compatible with UUU

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx93-tqma93xxla-mba9xxxca.dtb
  * imx93-tqma93xxla-mba9xxxca-lvds-tm070jvhg33.dtb
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

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                                    | Type        ----   |
|-----------------|------------------------------------------------|--------------------|
| LVDS            | imx93-tqma93xxla-mba9-lvds-tm070jvhg33.dtb     | Tianma TM070JVHG33 |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP  |
| --------- | --------- | ---- |
| CAN0      | X8        | S5.2 |
| CAN1      | X9        | S5.1 |

#### Enable without CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with
MBa93xxCA and meta-tq / systemd.

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
MBa93xxCA and meta-tq / systemd.

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### RS485

TODO

### Cortex M33

TODO

## Support Wiki

TODO
