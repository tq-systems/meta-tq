# TQMa93xxCA and TQMa93xxLA

This README contains some useful information for TQMa93xxCA and TQMa93xxLA

[[_TOC_]]

## Variants

* TQMa93xxCA REV.010x on MBa93xxCA REV.010x
* TQMa93xxLA REV.010x on MBa93xxCA REV.010x
* TQMa93xxLA REV.010x on MBa93xxLA REV.010x

## Version information for software components

* U-Boot based on v2022.04 and NXP vendor BSP
* Linux based on v5.15.y and NXP vendor BSP
* TF-A based on v2.6 ands NXP vendor BSP

## Supported machine configurations

* tqma93xx-mba93xxca
* tqma93xxla-mba93xxla

## Supported Features

### U-Boot

| Feature                                          | MBa93xxCA  REV.010x   | MBa93xxLA  REV.010x   |
| :----------------------------------------------: | :-------------------: | :-------------------: |
| RAM configs                                      |  1 GiB                |   1 GiB               |
| CPU variants                                     |     i.MX93            |     i.MX93            |
| Fuses / OCRAM                                    |       x               |       x               |
| speed grade / temperature grade detection        |       x               |       x               |
| UART (console on UART1)                          |       x               |       x               |
| **GPIO**                                         |                       |                       |
| LED                                              |       x               |       x               |
| Button                                           |       x               |       x               |
| **I2C**                                          |                       |                       |
| system EEPROM parsing                            |       x               |       x               |
| PMIC                                             |       x               |       x               |
| **e-MMC / SD**                                   |                       |                       |
| Read                                             |       x               |       x               |
| Write                                            |       x               |       x               |
| **Ethernet**                                     |                       |                       |
| GigE / FEC via Phy on MBa93xxCA                  |       x               |       x               |
| GigE / EQOS via Phy on MBa93xxCA                 |       x               |       x               |
| **Bootdevices**                                  |                       |                       |
| SD-Card on USDHC2                                |       x               |       x               |
| e-MMC on USDHC1                                  |       x               |       x               |
| QSPI-NOR on FlexSPI                              |       x               |       x               |
| Serial Downloader                                |                       |       x               |
| **USB**                                          |                       |                       |
| USB 2.0 Host / Hub                               |       x               |       x               |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x               |       x               |
| **QSPI NOR**                                     |                       |                       |
| Read with 1-1-4 SDR                              |       x               |       x               |
| PP / Erase with 1-1-4 SDR                        |       x               |       x               |
| **Cortex M33**                                   |                       |                       |
| env settings for starting from TCM               |                       |                       |
| examples with UART3 as debug console             |                       |                       |

**TODO or not tested / supported**

* CPU variants with single core and without NPU

### Linux

| Feature                                          | MBa93xxCA  REV.010x   | MBa93xxLA  REV.010x   |
| :----------------------------------------------: | :-------------------: | :-------------------: |
| RAM configs                                      |  1 GiB                |   1 GiB               |
| CPU variants                                     |     i.MX93            |     i.MX93            |
| Fuses / OCRAM                                    |                       |                       |
| speed grade / temperature grade detection        |                       |                       |
| **UART**                                         |                       |                       |
| console on UART1 (via USB / UART converter)      |       x               |       x               |
| UART2/3/4                                        |       x               |       x               |
| **GPIO**                                         |                       |                       |
| LED                                              |       x               |       x               |
| Button                                           |       x               |       x               |
| **I2C**                                          |                       |                       |
| EEPROMs                                          |       x               |       x               |
| PMIC                                             |       x               |       x               |
| RTC                                              |       x               |       x               |
| Temperature Sensors                              |       x               |       x               |
| IMU / Gyroscope                                  |       x               |       x               |
| Port expander                                    |       x               |       x               |
| **ENET**                                         |                       |                       |
| GigE / FEC via Phy on MBa93xxCA                  |       x               |       x               |
| GigE / EQOS via Phy on MBa93xxCA                 |       x               |       x               |
| **USB**                                          |                       |                       |
| USB 2.0 Host / Hub                               |       x               |       x               |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x               |       x               |
| **QSPI NOR**                                     |                       |                       |
| Read with 1-1-4 SDR                              |       x               |       x               |
| PP / Erase with 1-1-4 SDR                        |       x               |       x               |
| **Display**                                      |                       |                       |
| LVDS                                             |       x               |       x               |
| **CAN-FD**                                       |                       |                       |
| CAN-FD                                           |       x               |       x               |
| **SPI**                                          |                       |                       |
| spidev at all CS                                 |                       |                       |
| **internal ADC**                                 |                       |                       |
| ADC                                              |       x               |       x               |
| **Cortex M33**                                   |                       |                       |
| examples running from TCM                        |                       |                       |
| use UART3 as debug console (see issues)          |                       |                       |
| LPB boot                                         |                       |                       |
| **MIPI CSI (see Issues section)**                |                       |                       |
| Gray with Vision Components GmbH camera (Sensor OV9281) |                |                       |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |           |                       |

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

* MBa93xxCA REV.010x EVK not compatible with UUU
* MBa93xxCA REV.010x EVK RS485 not functional

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx93-tqma93xx-mba9xxxca.dtb
  * imx93-tqma93xx-mba9xxxca-lvds-tm070jvhg33.dtb
  * imx93-tqma93xxla-mba9xxxla.dtb
  * imx93-tqma93xxla-mba9xxxla-lvds-tm070jvhg33.dtb
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1.

| Bootmode | Description           | S1-1 | S1-2 | S1-3 | S1-4 |
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

| Interface       | Device tree                                         | Type        ----   |
|-----------------|-----------------------------------------------------|--------------------|
| LVDS            | imx93-tqma93xx-mba93xxca-lvds-tm070jvhg33.dtb       | Tianma TM070JVHG33 |
| LVDS            | imx93-tqma93xxla-mba93xxla-lvds-tm070jvhg33.dtb     | Tianma TM070JVHG33 |

### CAN

#### Troubleshooting

In case of problems first check the bus termination

##### CAN bus termination MBa93xxCA

| Interface | Connector | DIP  |
| --------- | --------- | ---- |
| CAN0      | X8        | S5.2 |
| CAN1      | X9        | S5.1 |

##### CAN bus termination MBa93xxLA

| Interface | Connector | DIP  |
| --------- | --------- | ---- |
| CAN0      | X8        | S4.1/2 |
| CAN1      | X9        | S5.1/2 |

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
MBa93xxCA / MBa93xxLA and meta-tq / systemd.

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
