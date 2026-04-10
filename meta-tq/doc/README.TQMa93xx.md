# TQMa93xxCA and TQMa93xxLA

This README contains some useful information for TQMa93xxCA and TQMa93xxLA

[[_TOC_]]

## Variants

* TQMa93xxLA REV.010x on MBa91xxCA REV.010x (1 / 1.5 / 2 GiB RAM)
* TQMa93xxCA REV.010x on MBa91xxCA REV.010x (1 / 2 GiB RAM)
* TQMa93xxCA REV.010x on MBa93xxCA REV.020x (1 / 2 GiB RAM)
* TQMa93xxLA REV.010x on MBa93xxCA REV.020x (1 / 1.5 / 2 GiB RAM)
* TQMa93xxLA REV.010x on MBa93xxLA REV.020x (1 / 1.5 / 2 GiB RAM)
* TQMa93xxLA REV.010x on MBa93xxLA-MINI REV.020x (1 / 1.5 / 2 GiB RAM)

__Note__: Depending on the SoM revision different CPU mask variants may be assembled.
CPU mask revisions 1.0 (bitbake variable `IMX_SOC_REV = "A0"`) and older are protoypes,
have additional erratas and are not supported.

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

* tqma93xx-mba91xxca
* tqma93xx-mba93xxca
* tqma93xxla-mba93xxla
* tqma93xxla-mba93xxla-mini

## Supported Features

### U-Boot

| Feature                                          | MBa93xxCA  REV.020x   | MBa93xxLA  REV.020x   | MBa93xxLA-MINI REV.020x |
| :----------------------------------------------: | :-------------------: | :-------------------: | :---------------------: |
| RAM configs                                      |   1 / 1.5 / 2 GiB     |   1 / 1.5 / 2 GiB     |     1 / 1.5 / 2 GiB     |
| inline ECC                                       |          x            |       x               |         x               |
| CPU variants                                     |     i.MX93            |     i.MX93            |       i.MX93            |
| Fuses / OCRAM                                    |       x               |       x               |         x               |
| speed grade / temperature grade detection        |       x               |       x               |         x               |
| UART (console on UART1)                          |       x               |       x               |         x               |
| **GPIO**                                         |                       |                       |                         |
| LED                                              |       x               |       x               |                         |
| Button                                           |       x               |       x               |                         |
| **I2C**                                          |                       |                       |                         |
| system EEPROM parsing                            |       x               |       x               |         x               |
| PMIC                                             |       x               |       x               |         x               |
| **eMMC / SD**                                    |                       |                       |                         |
| Read                                             |       x               |       x               |         x               |
| Write                                            |       x               |       x               |         x               |
| **Ethernet**                                     |                       |                       |                         |
| GigE / FEC via Phy on MBa93xxCA                  |       x               |       x               |         x               |
| GigE / EQOS via Phy on MBa93xxCA                 |       x               |       x               |         x               |
| **Bootdevices**                                  |                       |                       |                         |
| SD card on USDHC2                                |       x               |       x               |         x               |
| eMMC on USDHC1                                   |       x               |       x               |         x               |
| QSPI-NOR on FlexSPI                              |       x               |       x               |   see Known Issues      |
| Serial Downloader                                |                       |       x               |         x               |
| **USB**                                          |                       |                       |                         |
| USB 2.0 Host / Hub                               |       x               |       x               |         x               |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |       x               |       x               |         x               |
| **QSPI NOR**                                     |                       |                       |                         |
| Read with 1-1-4 SDR                              |       x               |       x               |   see Known Issues      |
| PP / Erase with 1-1-4 SDR                        |       x               |       x               |   see Known Issues      |
| **Cortex M33**                                   |                       |                       |                         |
| env settings for starting from TCM               |                       |                       |                         |
| examples with UART3 as debug console             |                       |                       |                         |

**NOTE:** for MBa93xxLA-MINI REV.010x is only be used for IBPQ. Customers will get REV.020x or later.

**TODO or not tested / supported**

* CPU variants with single core and without NPU

### Linux

Support matrix for `MBa93xxCA` REV.020x and `MBa93xxLA`  REV.010x

|                           Feature                            |  linux-tq-6.18  |
|:------------------------------------------------------------:|:---------------:|
|                         RAM configs                          | 1 / 1.5 / 2 GiB |
|                         CPU variants                         |     i.MX93      |
|                        Fuses / OCRAM                         |        x        |
|          speed grade / temperature grade detection           |        -        |
|                      Frequency scaling                       |        -        |
|                           **UART**                           |                 |
|         console on UART1 (via USB / UART converter)          |        x        |
|                           UART2/3                            |        x        |
|                           **GPIO**                           |                 |
|                             LED                              |        x        |
|                            Button                            |        x        |
|                           **I2C**                            |                 |
|                           EEPROMs                            |        x        |
|                             PMIC                             |        x        |
|                             RTC                              |        x        |
|                     Temperature Sensors                      |        x        |
|                       IMU / Gyroscope                        |        x        |
|                        Port expander                         |        x        |
|                           **ENET**                           |                 |
|               GigE / FEC via Phy on MBa93xxCA                |        x        |
|               GigE / EQOS via Phy on MBa93xxCA               |        x        |
|                           **USB**                            |                 |
|                      USB 2.0 Host / Hub                      |        x        |
|             USB DRD (USB 2.0 Cable Detect, VBUS)             |        x        |
|                         **QSPI NOR**                         |                 |
|                     Read with 1-4-4 SDR                      |        x        |
|                  PP / Erase with 1-4-4 SDR                   |        x        |
|                         **Display**                          |                 |
|                             LVDS                             |        x        |
|                          **CAN-FD**                          |                 |
|                            CAN-FD                            |        x        |
|                           **SPI**                            |                 |
|                       spidev at all CS                       |                 |
|                       **internal ADC**                       |                 |
|                             ADC                              |        x        |
|                        **Cortex M33**                        |                 |
|                  examples running from TCM                   |                 |
|           use UART3 as debug console (see issues)            |                 |
|                           LPB boot                           |                 |
|                           **NPU**                            |                 |
|                   Firmware for CORTEX M33                    |                 |
|                             Demo                             |                 |
|              **MIPI CSI (see Issues section)**               |                 |
|   Gray with Vision Components GmbH camera (Sensor OV9281)    |                 |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |                 |

## TODO

* MIPI-DSI
* MIPI-CSI
* WiFi (driver and firmware loading OK, needs additional testing)
* Bluetooth firmware on MBa93xxLA does not initialize
* Cortex M33 (prerelease on request)
* NPU (prerelease on request)
* LPB Boot modes
* optee support
* TRNG performance in linux-fslc-6.6 is less than of linux-fslc-6.1

## Important Notes

* U-Boot: USB Type-C port (X17) is usable as device-only under U-Boot
* DVFS is not supported using cpu-freq framework. See [here](#frequency-scaling)
  on how to use frequency scaling
* Ethernet device order is defined by DT aliases. Linux and bootloader DT need to match
* CPU mask 1.0 and older is not longer supported. No up to date firmare available from NXP.
  Variants with old mask will not boot.
* The SPI UBI rootfs Volume has been renamed from `rootfs` to `root`
  to conform with distroboot settings (scarthgap.TQ.ARM.BSP.0007, u-boot 2024.04)

## Known Issues / Limitations

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
* eth1 does not work after suspend, needs `ip link set down/up dev eth1` to be functional
* When using `i2c probe` command in U-Boot all valid addresses respond instead of addresses
  used by a physical connected device.
* Suspend/Resume
  * When resuming using wakeup GPIO the following error can occur:
    `pca953x 2-0070: failed reading register`. The (wakeup) IRQ handler is executed before the expander
    has been resumed. This message can be ignored.
* The NPU driver `ethosu` assumes the Cortex-M33 is not running. Starting Cortex-M33 from e.g. u-boot
  or using remoteproc is not supported by `ethosu` driver. The driver is not working with `linux-imx-tq`
  based on NXP `lf-6.6.52-2.2.0` release. Switching back to old release is posible with following
  changes in `linux-imx-tq_6.6.bb` recipe:

```
KBRANCH:tqma93xx = "TQMa-fslc-6.6-2.0.x-imx"
SRCREV:tqma93xx = "6d8a66ee71659362646d93f2752354858c04f205"
LINUX_VERSION:tqma93xx = "${LINUX_RELEASE}.23"
```
* Watchdog is not enabled by default
* on MBa93xxLA-MINI the QSPI pins are used for SDIO
  If a module with QSPI is required, a specially adapted mainboard devicetree must be created.

## MBa91 Differences

The SoM TQMa93xx can be mounted on MBa91xxCA as well. Due to the support for TQMa91xx not all
features provided by TQMa93xx are supported. The differences are listed below:
* Parallel Display support
  * alternatively LVDS display support with LVDS serializer
* No CAN2
* No DisplayPort
* No dedicated LVDS display port
* No MIPI-CSI
* No UART2
* GPIO-based fan, not PWM-based
* Extension Headers X1/X2
  * different pin layout
  * No I2C5
  * No SPI6
  * No SAI3
  * No UART6/8
  * No PWM
* Cortex-M33 will not be supported
  * it's technically possible, but there is no dedicated UART available

## Build Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * imx93-tqma9352-mba91xxca*.dtb
  * imx93-tqma9352-mba93xxca*.dtb
  * imx93-tqma9352-mba93xxla*.dtb
  * imx93-tqma9352-mba93xx*-rpmsg.dtb (NPU enabled)
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-ecc.bin-flash\_spl\_uboot: boot stream with inline ECC for SD / eMMC
* imx-boot-${MACHINE}-ecc.bin-flash\_evk\_flexspi: boot stream with inline ECC for FlexSPI
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI
* imx-boot-${MACHINE}-uuu.bin-flash\_singleboot: boot stream for UUU

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1.

| Bootmode | Description           | S1-4 | S1-3 | S1-2 | S1-1 |
| :------: | :-------------------: | :--: | :--: | :--: | :--: |
| 0000     | Boot from fuses       | OFF  | OFF  | OFF  | OFF  |
| 0001     | Serial Downloader     | OFF  | OFF  | OFF  | ON   |
| 0010     | eMMC (USDHC1)         | OFF  | OFF  | ON   | OFF  |
| 0011     | SD card (USDHC2)      | OFF  | OFF  | ON   | ON   |
| 0100     | QSPI (FlexSPI NOR)    | OFF  | ON   | OFF  | OFF  |

**NOTE:** LPB boot modes not supported / tested yet.

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

**Note:** For SPI boot it is required to update the script partition once using the following command sequence:

```
tftp boot-ubi.scr
sf probe
sf update ${loadaddr} script ${filesize}
```

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Frequency Scaling

*Attention*: only with CPU stepping A1! Older variants will stall due to CPU
errata.

See [here](./README.TQMa9-non-scmi-dvfs.md) for details about frequency scaling.

### OS Boot

See the [Distroboot README](README.Distroboot.md).

### OS Updates

See [RAUC](RAUC.md).

### Using RTC for Wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

**Note**: On this platform `rtc0` is the I2C RTC on SoM and `rtc1` is the RTC in CPU BBNS domain

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtso overlay.

| Interface       | Device tree                                          | Type        ----   |
|-----------------|------------------------------------------------------|--------------------|
| LVDS            | imx93-tqma9352-mba93xxca-lvds-tm070jvhg33.dtb        | Tianma TM070JVHG33 |
| LVDS            | imx93-tqma9352la-mba93xxla-lvds-tm070jvhg33.dtb      | Tianma TM070JVHG33 |
| LVDS            | imx93-tqma9352la-mba93xxla-mini-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |

### CAN

#### Troubleshooting

In case of problems first check the bus termination

| Interface | Connector | DIP                             |
| --------- | --------- | ------------------------------- |
| CAN1      | X8        | S4.1 (CAN1\_H) / S4.2 (CAN1\_L) |
| CAN2      | X9        | S5.1 (CAN1\_H) / S5.2 (CAN1\_L) |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

__Note:__ Values for bitrate, sample-point, dbitrate and dsample-point depend
on your hardware setup.

#### MBa93xxLA-MINI (Wifi)

Support for Ezurio Wlan/Bluetooth Module (LWB5+ M.2) on MBa93xxLA-MINI.

| Interface        | Device tree                                   | Type      |
|------------------|-----------------------------------------------|-----------|
| M.2 Key E Socket | imx93-tqma9352-mba93xxla-mini-ezurio-wlan.dtb | LWB5+ M.2 |

Ezurio recommends using their own yocto layer (especially for radio certification), which includes adaptations to the core modules and its own firmware variants.

The Ezurio firmware in combination with the current kernel modules was sufficient to get WLAN and Bluethooth up and running.

### RS485

TODO

### Cortex M33

TODO

### NPU

__Note:__ For using the NPU, the Cortex-M33 needs to be loaded with a firmware controlling the NPU and the rpmsg-DeviceTree (`imx93-tqma9352-mba93xx*-rpmsg.dtb` has to be used.

Before running, translate tensorflow lite model to ETHOS-U format using vela compiler:

```
cd /usr/bin/tensorflow-lite-2.15.0/examples
vela mobilenet_v1_1.0_224_quant.tflite
./label_image -m output/mobilenet_v1_1.0_224_quant_vela.tflite --external_delegate_path=/usr/lib/libethosu_delegate.so
```

__Note:__ Due to API incompatibilities in the ethos-u driver stack and Cortex-M33 firmware only kernel v6.6.23 is supported.

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Inline ECC

See [Inline ECC](README.imx9.Inline-ECC.md).

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime Support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa93xxCA](https://support.tq-group.com/en/arm/tqma93xxca).

See [TQ Embedded Wiki for TQMa93xxLA](https://support.tq-group.com/en/arm/tqma93xxla).
