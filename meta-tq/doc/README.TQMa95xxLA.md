# TQMa95xxLA

This README contains some useful information for TQMa95xxLA on MBa95xxCA

[[_TOC_]]

## Variants

* TQMa95xxLA REV.0102 (i.MX95 B0)

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions of TF-A (atf),
bootloader and Linux kernel.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                               |                            |
|:--------------------------------------|:--------------------------:|
| **RAM configs**                       |                            |
| TQMa95xxLA                            |              4 GB          |
|                                       |                            |
| CPU variants                          |          (A1) / B0         |
| Fuses                                 |             x              |
| GPIO                                  |             x              |
| I2C                                   |             x              |
| **QSPI**                              |                            |
| Read                                  |          1-4-4-4B          |
| Write                                 |          1-1-4-4B          |
| Erase                                 |          1-1-1-4B          |
| Boot                                  |             x              |
| **eMMC / SD-Card**                    |                            |
| Read                                  |             x              |
| Write                                 |             x              |
| Boot                                  |     SD: ok / eMMC: ok      |
| **USB**                               |                            |
| USB 2.0 Dual Role                     |  disabled (REV.010x SDP)   |
| USB 3.0 (Hub on TQMa95xxSA)           | Host disabled, USB 2.0 SDP |
| **ENET (GigE via Phy on MBa95xxCA)**  |                            |
| ENET 0                                |             x              |
| ENET 1                                |             x              |
| **ENET (10GigE via SFP on MBa95xxCA)** |           |
| ENET 2                                 |     -     |
| **Bootstreams**                       |                            |
| FlexSPI                               |             x              |
| SD / eMMC                             |             x              |
| UUU                                   |  (TBD: use SD-Card image)  |

### Linux

| Feature                                | fslc-6.12 |
|:---------------------------------------|:---------:|
| **RAM configs**                        |           |
| TQMa95xxLA                             |    4 GiB  |
|                                        |           |
| CPU variants                           | (A1) / B0 |
| Fuses / OCRAM                          |           |
| speed grade                            |           |
| **UART**                               |           |
| console on LPUART1 (X26)               |     x     |
| RS485 on LPUART8 (X15)                 |     x     |
| System manager debug (LPUART2) (X26)   |     x     |
| **GPIO**                               |           |
| GPIO used for several functions        |     x     |
| **I2C**                                |           |
| Temperature Sensors                    |     x     |
| RTC                                    |     x     |
| EEPROMS                                |     x     |
| GPIO expander                          |     x     |
| Fan                                    |     -     |
| **ENET (GigE via Phy on MBa95xxCA)**   |           |
| ENET 0                                 |     x     |
| ENET 1                                 |     x     |
| **ENET (10GigE via SFP on MBa95xxCA)** |           |
| ENET 2                                 |     x     |
| **USB**                                |           |
| USB 2.0 Device (X9)                    |     x     |
| USB 3.0 Host (Hub on MBa95xxCA) (X8)   |     x     |
| **QSPI NOR**                           |           |
| Read with 1-4-4 SDR                    |     x     |
| PP / Erase with 1-1-4 SDR              |     x     |
| **Graphic**                            |           |
| GPU                                    |     x     |
| VPU                                    |     x     |
| **Display**                            |           |
| LVDS                                   |     x     |
| **Audio**                              |           |
| Line IN / Line OUT (X23, X24)          |     x     |
| Headphone out (X22)                    |           |
| Microphone in (X22)                    |           |
| **PCIe**                               |           |
| M.2 on MBa95xxCA (X16)                 |     x     |
| M.2 on MBa95xxCA (X17)                 |     x     |
| **SDIO**                               |           |
| M.2 on MBa95xxCA (X16)                 |           |
| **CAN-FD**                             |           |
| CAN-FD                                 |     x     |
| **SPI**                                |           |
| SPI user space device on all CS (X4)   |     x     |
| **PWM**                                |           |
| PWM backlight                          |     x     |
| **CPU/PMIC thermal sensors**           |           |
| via thermal zone                       |     x     |
| **Cortex M7**                          |           |
| examples running from TCM              |           |
| use UART as debug console              |           |

## Known Issues / Limitations

* SCMI access to board resources from system manager not completely implemented
 
* USB
  * The USB Type-C connector (X9 on MBa95xxCA) currently only supports USB device mode.
  * USB host is not supported when booting in serial download mode

    USB0 signals are routed to X9 when booting with [serial download mode](#serial-downloader)
  * USB dual role port X9 via USB1 is not supported with [serial download mode](#serial-downloader)

    USB1 controller is disabled in serial download mode to allow using USB0 as device on X9.
  * Serial download mode needs to be selected explicitely on [dip switch S1](#serial-downloader),

    The [dip switch S1](#serial-downloader) controls routing of USB signals to the
    USB dual role port X9. In case of no / invalid boot image on primary boot device
    the ROM loader automatic fallback to serial download mode does not work.
  * USB host support is disabled in U-Boot
* Currently no separate boot image for serial downloader support is built.

  All required features are configured in the default boot image.
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.

  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.

## Build Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* imx95-tqma9596la\*.dtb: device tree blobs for TQMa95xxLA
* imx95-tqma9596la\*.dtbo: device tree overlays for TQMa95xxLA
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* imx-boot-${MACHINE}-sd.bin-flash\_a55: boot stream for SD / eMMC / uuu
* imx-boot-${MACHINE}-sd.bin-flash\_a55_flexspi: boot stream for FlexSPI

<!-- FIXME: update list -->

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1 on MBa95xxCA.

__Note:__ modes with `S1:4` set to OFF are reserved and shall not be used.

### SD Card

| DIP S1  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      | x | x |   | x |
| OFF     |   |   | x |   |

### eMMC

| DIP S1  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   | x |   | x |
| OFF     | x |   | x |   |

### FLEXSPI

| DIP S1  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   | x | x |
| OFF     | x | x |   |   |

### Serial Downloader

__Note:__ (REV.010x) This setting is mandatory for recovery. When no valid
bootimage is present on the selected boot device, automatic fallback of the
ROM loader to serial downloader is restricted by the MBa95xxCA boot logic.

| DIP S1  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      | x |   |   | x |
| OFF     |   | x | x |   |

### Boot from Fuses

__Note:__ preferred mode for production hardware.

| DIP S1  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   |   | x |
| OFF     | x | x | x |   |

## Boot device initialisation and update

See [here](./README.imx.BootMedia.md) for detailed information on how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

Write complete image to eMMC:

```
sudo uuu -bmap -b emmc_all imx-boot-tqma95xxla-4gb-mba95xxca-sd.bin-flash_a55  tq-image-generic-debug-tqma95xxla-4gb-mba95xxca.rootfs.wic.zst
```

## Howto

### OS boot

See the [Distroboot README](README.Distroboot.md).

__Note:__ Default u-boot environment variable `bootcmd` has to be set to `run distro_bootcmd`

### Test sleepmode and wakeup

Use rtc0 (external RTC on TQMa95xxSA module) or rtc1 (RTC in CPU SNVS domain) to wakeup after 20 seconds:

```
RTC=rtc0
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc//${RTC}/wakealarm
echo mem > /sys/power/state
```

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                                           | Type               |
|-----------------|-------------------------------------------------------|--------------------|
| LVDS0           | imx95-tqma9596la-mba95xxca-lvds-tm070jvhg33.dtb       | Tianma TM070JVHG33 |
| LVDS0, dual     | imx95-tqma9596la-mba95xxca-lvds-lvds-g133han01.dtb    | AUO G133HAN.01     |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP                |
| --------- | --------- | ------------------ |
| CAN0      | X29       | `TERM CAN0` / S4:1 |
| CAN1      | X30       | `TERM CAN1` / S4:2 |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

  When CAN-FD is enabled, the non-datarate needs to be lower than 
  the datarate. The Linux kernel will emit a warning when it detects
  that the `brp` setting does not match.

### RS485

RS485 is enabled with `linux,rs485-enabled-at-boot-time` in MBa95xxCA devicetree.
Use DIP S8 for Termination.

### Cortex M7

<!-- TODO -->

### Revision A0/A1 support

Revision A0/A1 chips need a special ELE firmware and also uses a different DDR-RAM timing.
There the following lines need to be added to your `conf/local.conf`:

```
IMX_SOC_REV:${MACHINE} ?= "A0"
OEI_DDRCONFIG = "TQMa95xxSA.DDR-Timing.${OEI_RAM_SIZE}GB.V16.0005"
```

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa95xxCA](https://support.tq-group.com/en/arm/tqma95xxca)
