# TQMa95xxSA

This README contains some useful information for TQMa95xxSA on MB-SMARC-2

[[_TOC_]]

## Variants

* TQMa95xxSA REV.0102 (i.MX95 B0)

## Version Information for Software Components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and Linux kernel.

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                               |                            |
|:--------------------------------------|:--------------------------:|
| **RAM configs**                       |                            |
| TQMa95xxSA                            |          2 / 4 GB          |
|                                       |                            |
| CPU variants                          |          A1 / B0           |
| Fuses                                 |             x              |
| GPIO                                  |             x              |
| I2C                                   |             x              |
| **QSPI**                              |                            |
| Read                                  |          1-4-4-4B          |
| Write                                 |          1-1-4-4B          |
| Erase                                 |          1-1-1-4B          |
| Boot                                  |             -              |
| **eMMC / SD card**                    |                            |
| Read                                  |             x              |
| Write                                 |             x              |
| Boot                                  |     SD: ok / eMMC: no      |
| **USB**                               |                            |
| USB 2.0 Dual Role                     |  disabled (REV.010x SDP)   |
| USB 3.0 (Hub on TQMa95xxSA)           | Host disabled, USB 2.0 SDP |
| **ENET (GigE via Phy on TQMa95xxSA)** |                            |
| ENET 0                                |             x              |
| ENET 1                                |             x              |
| **Bootstreams**                       |                            |
| FlexSPI                               |   error (hardware issue)   |
| SD / eMMC                             |             x              |
| UUU                                   |  (TBD: use SD card image)  |


**TODO or not tested / supported**

* SCMI access to board resources from system manager

### Linux

| Feature                               | fslc-6.12 |
|:--------------------------------------|:---------:|
| **RAM configs**                       |           |
| TQMa95xxSA                            |  2/4 GiB  |
|                                       |           |
| CPU variants                          |  A1 / B0  |
| Fuses / OCRAM                         |           |
| speed grade                           |           |
| **UART**                              |           |
| console on LPUART7 (X20)              |     x     |
| LPUART1 on Cortex-M33 (X39)           |     x     |
| **GPIO**                              |           |
| SMARC GPIO pins                       |     x     |
| **I2C**                               |           |
| Temperature Sensors                   |     x     |
| RTC                                   |     x     |
| EEPROMS                               |     x     |
| **ENET (GigE via Phy on TQMa93xxSA)** |           |
| ENET 0                                |     x     |
| ENET 1                                |     x     |
| **USB**                               |           |
| USB 2.0 Dual Role (X3/X4)             |           |
| USB 3.0 (Hub on TQMa93xxSA) (X7/X8)   |     x     |
| **QSPI NOR**                          |           |
| Read with 1-4-4 SDR                   |     x     |
| PP / Erase with 1-1-4 SDR             |     x     |
| **Graphic**                           |           |
| GPU                                   |     x     |
| VPU                                   |     x     |
| **Display**                           |           |
| LVDS                                  |     x     |
| **Audio**                             |           |
| Line IN / Line Out (X17, X18)         |     x     |
| **PCIe**                              |           |
| mini-PCIe on MB-SMARC-2 (X44)         |     x     |
| PCIe x1 on MB-SMARC-2 (X22)           |     x     |
| **CAN-FD**                            |           |
| CAN-FD                                |     x     |
| **SPI**                               |           |
| SPI user space device on all CS (X24) |     x     |
| **PWM**                               |           |
| PWM in TPM3                           |     x     |
| **CPU/PMIC thermal sensors**          |           |
| via thermal zone                      |     x     |
| **Cortex M7**                         |           |
| examples running from TCM             |           |
| use UART as debug console             |           |

## Known Issues / Limitations

* Following GPIOs are not accessible from Cortex-A (only from SM):
  * GPIO7
  * GPIO8
  * GPIO9
* GPIO13 is not connected on MB-SMARC-2
* SMARC-2 HDA/I2S2 interface not supported
* USB
  * Port USB3 (X3 on MB-SMARC-2) is host only. Do only use a matching adapter
    on MB-SMARC-2
  * USB0 (X4 on MB-SMARC-2) currently only supports USB device mode, without overcurrent detection (!)
  * USB host is not supported with serial download mode, USB0 controller is routed to X4 in this mode
  * USB dual role port on X4 via USB1 is not supported with serial download mode, USB1 controller is disabled in this mode
  * Serial download mode needs S3:4 set to on, automatic fallback does not work
* Boot from SPI-NOR is currently unsupported (needs redesign)
* Currently no separate boot image for serial download

## Build Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* imx95-tqma9596sa\*.dtb: device tree blobs for TQMa95xxSA
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* imx-boot-${MACHINE}-sd.bin-flash\_a55: boot stream for SD / eMMC / uuu
* imx-boot-${MACHINE}-sd.bin-flash\_a55_flexspi: boot stream for FlexSPI

<!-- FIXME: update list -->

## Boot DIP Switches

_Note:_

* DIP S3 (MB-SMARC-2) is for `BOOT\_MODE`.

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

## Boot Device Initialisation and Update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

Write complete image to eMMC:

```
sudo uuu -bmap -b emmc_all imx-boot-tqma95xxsa-4gb-mb-smarc-2-sd.bin-flash_a55  tq-image-generic-debug-tqma95xxsa-4gb-mb-smarc-2.rootfs.wic.zst
```

## Howto

### OS Boot

See the [Distroboot README](README.Distroboot.md).

__Note:__ Default u-boot environment variable `bootcmd` has to be set to `run distro_bootcmd`

### Test Sleepmode and Wakeup

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

| Interface       | Device tree                                      | Type               |
|-----------------|--------------------------------------------------|--------------------|
| LVDS0           | imx95-tqma9596sa-mb-smarc-2-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |

Please note manual for backlight power supply. For MB-SMARC-2 you can bridge
X14 pin 1 and 2 to provide 12V.

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP                |
| --------- | --------- | ------------------ |
| CAN0      | X29       | `TERM CAN0` / S4:1 |
| CAN1      | X30       | `TERM CAN1` / S4:2 |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

**Note**: There is an absolute hardware limit on the bitrate of 1MBit/s, independently from CAN-FD.
  For that reason CAN-FD is disabled by default. While technically possible using CAN-FD with 1MBit/s, the non-datarate
  has to be lowered accordingly.

  When CAN-FD is enabled, the non-datarate needs to be lower than the datarate. The Linux kernel will emit a warning if it is deemed the `brp` setting do not match.

### Cortex M7

<!-- TODO -->

### Revision A0/A1 Support

Revision A0/A1 chips need a special ELE firmware and also uses a different DDR-RAM timing.
There the following lines need to be added to your `conf/local.conf`:

```
IMX_SOC_REV:${MACHINE} ?= "A0"
OEI_DDR_CONFIG = "TQMa95xxSA.DDR-Timing.${OEI_RAM_SIZE}GB.V16.0005"
```

### Access U-Boot Environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa95xxSA](https://support.tq-group.com/en/arm/tqma95xxsa)
