# TQMa93xxCA and TQMa93xxLA

This README contains some useful information for TQMa93xxCA and TQMa93xxLA

[[_TOC_]]

## Variants

* TQMa93xxCA REV.010x on MBa93xxCA REV.020x (1 / 2 GiB RAM)
* TQMa93xxLA REV.010x on MBa93xxCA REV.020x (1 / 2 GiB RAM)
* TQMa93xxLA REV.010x on MBa93xxLA REV.020x (1 / 2 GiB RAM)

__Note__: Depending on the SoM revision different CPU mask variants may be assembled.
CPU mask revisions 1.0 and older are protoypes and have additional erratas.

## Version information for software components

* U-Boot based on v2023.04 and NXP vendor BSP
  (https://github.com/nxp-imx/uboot-imx.git, tag lf-6.1.55-2.2.0)
* Linux based on v6.1.y and NXP vendor BSP base community fork
  (https://github.com/Freescale/linux-fslc.git)
* TF-A based on v2.8 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git, tag lf-6.1.55-2.2.0)

## Supported machine configurations

See [top level README.md](./../README.md) for configurations usable as MACHINE.

* tqma93xx-mba93xxca
* tqma93xxla-mba93xxla

## Supported Features

### U-Boot

| Feature                                          | MBa93xxCA  REV.020x   | MBa93xxLA  REV.020x   |
| :----------------------------------------------: | :-------------------: | :-------------------: |
| RAM configs                                      |     1 / 2 GiB         |     1 / 2 GiB         |
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
| (configured as device to be usable with UUU )    |                       |                       |
| **QSPI NOR**                                     |                       |                       |
| Read with 1-1-4 SDR                              |       x               |       x               |
| PP / Erase with 1-1-4 SDR                        |       x               |       x               |
| **Cortex M33**                                   |                       |                       |
| env settings for starting from TCM               |                       |                       |
| examples with UART3 as debug console             |                       |                       |

**TODO or not tested / supported**

* CPU variants with single core and without NPU

### Linux

Support matrix for `MBa93xxCA` REV.020x and `MBa93xxLA`  REV.010x

|                           Feature                            | linux-imx-tq_5.15 | linux-imx-tq_6.1 |
|:------------------------------------------------------------:|:-----------------:|:----------------:|
|                         RAM configs                          |     1 / 2 GiB     |     1 / 2 GiB    |
|                         CPU variants                         |      i.MX93       |      i.MX93      |
|                        Fuses / OCRAM                         |                   |         x        |
|          speed grade / temperature grade detection           |                   |                  |
|                           **UART**                           |                   |                  |
|         console on UART1 (via USB / UART converter)          |         x         |         x        |
|                          UART2/3/4                           |         x         |         x        |
|                           **GPIO**                           |                   |                  |
|                             LED                              |         x         |         x        |
|                            Button                            |         x         |         x        |
|                           **I2C**                            |                   |                  |
|                           EEPROMs                            |         x         |         x        |
|                             PMIC                             |         x         |         x        |
|                             RTC                              |         x         |         x        |
|                     Temperature Sensors                      |         x         |         x        |
|                       IMU / Gyroscope                        |         x         |         x        |
|                        Port expander                         |         x         |         x        |
|                           **ENET**                           |                   |                  |
|               GigE / FEC via Phy on MBa93xxCA                |         x         |         x        |
|               GigE / EQOS via Phy on MBa93xxCA               |         x         |         x        |
|                           **USB**                            |                   |                  |
|                      USB 2.0 Host / Hub                      |         x         |         x        |
|             USB DRD (USB 2.0 Cable Detect, VBUS)             |         x         |         x        |
|                         **QSPI NOR**                         |                   |                  |
|                     Read with 1-1-4 SDR                      |         x         |         x        |
|                  PP / Erase with 1-1-4 SDR                   |         x         |         x        |
|                         **Display**                          |                   |                  |
|                             LVDS                             |         x         |         x        |
|                          **CAN-FD**                          |                   |                  |
|                            CAN-FD                            |         x         |         x        |
|                           **SPI**                            |                   |                  |
|                       spidev at all CS                       |                   |         x        |
|                       **internal ADC**                       |                   |                  |
|                             ADC                              |         x         |         x        |
|                        **Cortex M33**                        |                   |                  |
|                  examples running from TCM                   |                   |  prerelease, on request
|           use UART3 as debug console (see issues)            |                   |         x        |
|                           LPB boot                           |                   |                  |
|              **MIPI CSI (see Issues section)**               |                   |                  |
|   Gray with Vision Components GmbH camera (Sensor OV9281)    |                   |                  |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |                   |                  |

## TODO

* MIPI-DSI
* MIPI-CSI
* WiFi (driver and firmware loading OK, needs additional testing)
* Bluetooth firmware on MBa93xxLA does not initialize
* Cortex M33 (prerelease on request)
* NPU (prerelease on request)
* LPB Boot modes
* DVFS not implemented
* optee support

## Important Notes

* U-Boot: USB Type-C port (X17) is usable as device-only under U-Boot

## Known Issues

* CPU mask 1.0 and older print an error when loading Edglock driver. Driver loads successful
  but the system may lack secure boot features.
* Not all USB sticks are detected properly in U-Boot
* Using `usb reset` in U-Boot will give a warning from Type-C port controller.
* eth1 does not work after suspend, needs `ip link set down/up dev eth1` to be functional
* When using `i2c probe` command in U-Boot all valid addresses respond instead of addresses
  used by a physical connected device.
* Some Linux kernel boot warnings regarding missing optional supplies in DTB.

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
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_singleboot: boot stream for UUU

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1.

| Bootmode | Description           | S1-4 | S1-3 | S1-2 | S1-1 |
| :------: | :-------------------: | :--: | :--: | :--: | :--: |
| 0000     | Boot from fuses       | OFF  | OFF  | OFF  | OFF  |
| 0001     | Serial Downloader     | OFF  | OFF  | OFF  | ON   |
| 0010     | e-MMC (USDHC1)        | OFF  | OFF  | ON   | OFF  |
| 0011     | SD Card (USDHC2)      | OFF  | OFF  | ON   | ON   |
| 0100     | QSPI (FlexSPI NOR)    | OFF  | ON   | OFF  | OFF  |

**NOTE:** LPB boot modes not supported / tested yet.

## Boot device initialisation and update

See [here](./README.BootMediaTQMa8.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.TQMa8.UUU.md) for details about using Serial Download mode and UUU.

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

CAN1/2 are enabled and configured by default with CAN-FD when using with
MBa93xx\[CA,LA\] and meta-tq / systemd.

##### CAN bus termination MBa93xx\[CA/LA\]

| Interface | Connector | DIP                             |
| --------- | --------- | ------------------------------- |
| CAN1      | X8        | S4.1 (CAN1\_H) / S4.2 (CAN1\_L) |
| CAN2      | X9        | S5.1 (CAN1\_H) / S5.2 (CAN1\_L) |

#### Enable without CAN-FD

Configure CAN1/2 per commandline without CAN-FD:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

__Note:__ Value for bitrate depends on your hardware setup.


To (permanently) configure CAN1/2 with CAN-FD disabled using systemd network files,
modify following files

* /lib/systemd/network/20-can0.network
* /lib/systemd/network/20-can1.network

and set:

`FDMode=no`

#### Enable CAN-FD

If using a carrier board with FD capable transceiver one can configure CAN1/2
per commandline with CAN-FD:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can ,  500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

__Note:__ Values for bitrate, sample-point, dbitrate and dsample-point depend
on your hardware setup.

### RS485

TODO

### Cortex M33

TODO

### High Assurance Boot (Secure Boot)

TODO

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### Chip revision A0/1.0

Early release samples of TQMa93xx use i.MX93 chip revision 1.0.
[AN13997 Migration Guide from i.MX 93 A0 to A1](https://www.nxp.com/webapp/Download?colCode=AN13997)
lists differences between A0 to A1 parts. An alternative description is "Sillicon Rev" 1.0 and 2.0 as mentioned in Figure 1 "Part number nomenclature - i.MX93" in Datasheet Rev. 3 12/1023.

A0 and A1 need a different sentinel firmware files, defaulting for A1.
In order to set the older firmware, add the following line to `conf/local.conf`:
```
IMX_SOC_REV = "A0"
```

## Support Wiki

See [TQ Embedded Wiki for TQMa93xxCA](https://support.tq-group.com/en/arm/tqma93xxca).

See [TQ Embedded Wiki for TQMa93xxLA](https://support.tq-group.com/en/arm/tqma93xxla).
