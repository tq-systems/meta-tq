# TQMa91xxCA and TQMa91xxLA

This README contains some useful information for TQMa91xxCA and TQMa91xxLA

[[_TOC_]]

## Variants

* TQMa91xxCA / TQMa91xxLA (1 GiB RAM) REV.010x on MBa91xxCA REV.010x
* TQMa91xxCA / TQMa91xxLA (1 GiB RAM) REV.010x on MBa93xxCA REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

* tqma91xx-mba91xxca
* tqma91xx-mba93xxca

## Supported Features

### U-Boot

Support matrix for `MBa91xxCA` REV.010x / `MBa93xxCA` REV.020x

|                    Feature                      | u-boot-imx-tq_2024.04 |
| :---------------------------------------------: | :-------------------: |
|                  RAM configs                    |        1 GiB          |
|                 CPU variants                    |        i.MX91         |
|                 Fuses / OCRAM                   |          x            |
|   speed grade / temperature grade detection     |          x            |
|            UART (console on UART1)              |          x            |
|                   **GPIO**                      |                       |
|                      LED                        |          x            |
|                    Button                       |          x            |
|                    **I2C**                      |                       |
|             system EEPROM parsing               |          x            |
|                     PMIC                        |          x            |
|                **eMMC / SD**                    |                       |
|                     Read                        |          x            |
|                     Write                       |          x            |
|                 **Ethernet**                    |                       |
|        GigE / FEC via Phy on MBa91xxCA          |          x            |
|       GigE / EQOS via Phy on MBa91xxCA          |          x            |
|                **Bootdevices**                  |                       |
|               SD-Card on USDHC2                 |          x            |
|                eMMC on USDHC1                   |          x            |
|              QSPI-NOR on FlexSPI                |          x            |
|               Serial Downloader                 |          x            |
|                    **USB**                      |                       |
|              USB 2.0 Host / Hub                 |          x            |
|     USB DRD (USB 2.0 Cable Detect, VBUS)        |          x            |
| (configured as device to be usable with UUU )   |                       |
|                 **QSPI NOR**                    |                       |
|              Read with 1-1-4 SDR                |          x            |
|           PP / Erase with 1-1-4 SDR             |          x            |

### Linux

Support matrix for `MBa91xxCA` REV.010x / `MBa93xxCA` REV.020x


|                   Feature                     | linux-imx-tq_6.6   |
| :-------------------------------------------: | :----------------: |
|                 RAM configs                   |      1 GiB         |
|                CPU variants                   |      i.MX91        |
|                Fuses / OCRAM                  |        x           |
|  speed grade / temperature grade detection    |                    |
|                  **UART**                     |                    |
| console on UART1 (via USB / UART converter)   |        x           |
|                  UART2                        |        x           |
|                  **GPIO**                     |                    |
|                     LED                       |        x           |
|                   Button                      |        x           |
|                   **I2C**                     |                    |
|                   EEPROMs                     |        x           |
|                    PMIC                       |        x           |
|                     RTC                       |        x           |
|             Temperature Sensors               |        x           |
|               IMU / Gyroscope                 |        x           |
|                Port expander                  |        x           |
|                  **ENET**                     |                    |
|       GigE / FEC via Phy on MBa9[1,3]xxCA     |        x           |
|      GigE / EQOS via Phy on MBa9[1,3]xxCA     |        x           |
|                   **USB**                     |                    |
|             USB 2.0 Host / Hub                |        x           |
|    USB DRD (USB 2.0 Cable Detect, VBUS)       |        x           |
|                **QSPI NOR**                   |                    |
|             Read with 1-1-4 SDR               |                    |
|             Read with 1-4-4 SDR               |                    |
|          PP / Erase with 1-1-4 SDR            |                    |
|          PP / Erase with 1-4-4 SDR            |                    |
|          **Display (MBa91xxCA only)**         |                    |
|               LVDS via Bridge                 |        x           |
|                  DPI / RGB                    |        x           |
|                 **CAN-FD**                    |                    |
|                   CAN-FD                      |        x           |
|                   **SPI**                     |                    |
|              spidev at all CS                 |        x           |
|              **internal ADC**                 |                    |
|                     ADC                       |        x           |

## TODO

* WiFi (driver and firmware loading OK, needs additional testing)
* Bluetooth firmware on MBa91xxLA does not initialize
* optee support

## Important Notes

* U-Boot: USB Type-C port (X17) is usable as device-only under U-Boot
* DVFS is not supported using cpu-freq framework. See [here](#frequency-scaling)
  on how to use frequency scaling
* Ethernet device order is defined by DT aliases. Linux and bootloader DT need to match

## Known Issues

* NFS boot: The interface to be used for NFS boot (`netdev`) has inverted order, compared
  to u-boot and Linux. Device renaming in Linux happens after mounting rootfs.
* U-Boot:
  * Not all USB sticks are detected properly.
  * Using `usb reset` in U-Boot will give a warning from Type-C port controller.  
    The USB controller is configured as device only via device tree to support
    serial download use case.
  * boot from USB using `uuu` config displays misleading pinctrl / iomux warning.  
    The UDC gadget driver warns not only for failed pinmux but also when no pinmux group
    is assigned in device tree.
  * watchdog will reset the system after using `wdt start [timeout]`.  
    Watchdog is enabled but not configured for automatic servicing.
    If needed, `CONFIG_WATCHDOG` can be activated in defconfig.

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * imx91-tqma9131-mba91xxca*.dtb
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI
* imx-boot-${MACHINE}-uuu.bin-flash\_singleboot: boot stream for UUU

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1 on mainboard.

| Bootmode | Description           | S1-4 | S1-3 | S1-2 | S1-1 |
| :------: | :-------------------: | :--: | :--: | :--: | :--: |
| 0000     | Boot from fuses       | OFF  | OFF  | OFF  | OFF  |
| 0001     | Serial Downloader     | OFF  | OFF  | OFF  | ON   |
| 0010     | eMMC (USDHC1)         | OFF  | OFF  | ON   | OFF  |
| 0011     | SD Card (USDHC2)      | OFF  | OFF  | ON   | ON   |
| 0100     | QSPI (FlexSPI NOR)    | OFF  | ON   | OFF  | OFF  |

## Boot device initialisation and update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Frequency scaling

See [here](./README.TQMa9-non-scmi-dvfs.md) for details about frequency scaling.

### OS boot

See the [Distroboot README](README.Distroboot.md).

__Note:__ Default u-boot environment variable `bootcmd` has to be set to `run distro_bootcmd`

### OS updates

See [RAUC](RAUC.md).

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

**Note**: On this platform `rtc0` is the I2C RTC on SoM and `rtc1` is the RTC in CPU BBNS domain

### Display Support (MBa91xxCA)

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.


| Interface | Device tree                                   | Type        ----   |
| --------- | --------------------------------------------- | ------------------ |
| LVDS      | imx91-tqma9131-mba91xxca-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| DPI/RGB   | imx91-tqma9131-mba91xxca-rgb-cdtech-dc44.dtb  | CDTECH DC44 (DMB)  |

### CAN

#### Troubleshooting

In case of problems first check the bus termination

| Interface | Connector | DIP                             |
| --------- | --------- | ------------------------------- |
| CAN1      | X8        | S4.1 (CAN1\_H) / S4.2 (CAN1\_L) |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

__Note:__ Values for bitrate, sample-point, dbitrate and dsample-point depend
on your hardware setup.

### RS485

TODO

### High Assurance Boot (Secure Boot)

TODO

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa91xxCA](https://support.tq-group.com/en/arm/tqma91xxca).
