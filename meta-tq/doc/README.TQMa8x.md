# TQMa8x

This README contains some useful information for TQMa8x on MBa8x

[[_TOC_]]

## Variants

* TQMa8QM 4 GB RAM REV.020x / 0102 and newer
* TQMa8QM 8 GB RAM REV.020x / 0102 and newer

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and Linux kernel.

## Supported machine configurations

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |              |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |   4,8 GiB    |
| CPU variants                                     |  i.MX8QM B0  |
| Fuses                                            |      x       |
| GPIO                                             |      x       |
| I2C                                              |      x       |
| **QSPI**                                         |              |
| Read                                             |      x       |
| Write                                            |      x       |
| Boot                                             |      x       |
| **e-MMC / SD-Card**                              |              |
| Read                                             |      x       |
| Write                                            |      x       |
| Boot                                             |      x       |
| **USB**                                          |              |
| USB 2.0 Dual Role                                |      x       |
| USB 3.0 (Hub on MBa8x)                           |      x       |
| **ENET (GigE via Phy on MBa8x)**                 |              |
| ENET 0                                           |      x       |
| ENET 1                                           |      x       |
| **Bootstreams**                                  |              |
| FlexSPI                                          |      x       |
| SD / e-MMC                                       |      x       |
| UUU / mfgtool                                    |      x       |

**TODO or not tested / supported**

* more CPU variants
* temperature grade
  * SCU limitation

### Linux

| Feature                                          |  fslc-5.15   |   fslc-6.1   |
| :----------------------------------------------- | :----------: | :----------: |
| RAM configs                                      |     4,8 GiB  |     4,8 GiB  |
| CPU variants                                     |  i.MX8QM B0  |  i.MX8QM B0  |
| Fuses / OCRAM                                    |       x      |       x      |
| speed grade                                      |       x      |       x      |
| **UART**                                         |              |              |
| console on LPUART0 (X24)                         |       x      |       x      |
| additional UARTS on pin heads                    |       x      |       x      |
| **GPIO**                                         |              |              |
| LED                                              |       x      |       x      |
| Button                                           |       x      |       x      |
| wakeup from GPIO button                          |       x      |       x      |
| GPIO on pin heads                                |       x      |       x      |
| **I2C**                                          |              |              |
| Temperature Sensors (without cpu-temp)           |       x      |       x      |
| RTC                                              |       x      |       x      |
| EEPROMS                                          |       x      |       x      |
| **ENET (GigE via Phy on MBa8x)**                 |              |              |
| ENET 0                                           |       x      |       x      |
| ENET 1                                           |       x      |       x      |
| **USB**                                          |              |              |
| USB 2.0 Dual Role                                |       x      |       x      |
| USB 3.0 (Hub on MBa8x)                           |       x      |       x      |
| **QSPI NOR**                                     |              |              |
| Read with 1-1-4 SDR                              |       x      |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |       x      |
| **Graphic**                                      |              |              |
| GPU                                              |       x      |       x      |
| VPU                                              |       x      |       x      |
| **Display**                                      |              |              |
| LVDS0/LVDS1                                      |       x      |       x      |
| Dual-Channel LVDS                                |              |       x      |
| Display Port                                     |       x      |       x      |
| **Audio**                                        |              |              |
| Line IN / Line Out (X16, X17)                    |       x      |       x      |
| Display Port                                     |       x      |       x      |
| **PCIe**                                         |              |              |
| mini-PCIe on MBa8x (SX-PCEAC2-HMC-SP)            |       x      |       x      |
| M.2 PCIe with WiFi Card (SX-PCEAC2-M2-SP)        |       x      |       x      |
| **SATA**                                         |              |              |
| M.2 SATA                                         |              |       x      |
| **CAN**                                          |              |              |
| CAN                                              |       x      |       x      |
| **SPI**                                          |              |              |
| SPI user space device on all CS                  |       x      |       x      |
| Mikrobus (Module RTC5)                           |              |              |
| **ADC**                                          |              |              |
| ADC                                              |       x      |       x      |
| **PWM**                                          |              |              |
| PWM in LVDS IP                                   |       x      |       x      |
| PWM in LSIO subsystem                            |              |       x      |
| **CPU/PMIC thermal sensors**                     |              |              |
| via thermal zone                                 |       x      |       x      |
| **Cortex M4**                                    |              |              |
| examples running from TCM                        |       x      |       x      |
| use UART as debug console                        |       x      |       x      |

**TODO or not tested with new BSP**

* MIKRO Bus
* temperature grade
  * SCU limitation
* Audio
  * Microphone
* FTM
  * PWM (missing in CPU DT)
* HDMI in
* linux-imx-tq_5.15: Dual-Channel LVDS is untested

## Known Issues

* Default setting for `fdt_file` in u-boot does not match older linux kernel
  naming scheme. Current naming scheme is `<cpu>-<som>-<baseboard>[-feature].dtb`,
  old scheme was `<cpu>-<baseboard>[-feature].dtb`.
  See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
* counting of i2c devices bus starts at i2c-2 (because i2c-0 and i2c-1
  are reserved for i2c_rpmsgbus)
* PWM only works after the second enable command
  (`echo 1 > /sys/class/pwm<X>/enable`)
* USB
  * U-Boot: `EHCI timed out on TD - token` with some USB sticks on USB 2.0 OTG
  * runtime suspend disabled for USB Hub TUSB8041
* DT file for rpmsg is too big and needs `fdt_high` to be set to `0xffffffffffffffff`
* `linux-imx-tq_5.15`: SATA is broken due to link issues
* SPI: Hardware-controlled chipselects are not driven as expected
  * Toggle after each Byte when using DMA
  * Inbetween each `spi_transfer`
  * Use of GPIO controlled chip-selects instead is recommended
  * By default all chip-selects are configured as GPIO
* UBI / UBIFS images will not be built out of the box since `imx-base.inc` from
  meta-freescale override machine specific assignment for `MACHINE_FEATURES`.
  Use following bitbake assignment in one of your `local.conf` / `auto.conf` /
  `<machine>.conf` files:
  ```
  MACHINE_FEATURES:append = " ubi"
  ```

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4: boot stream for SD / e-MMC + M4 Demo
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote\_m40.bin: CortexM4 demo for device 0
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote\_m41.bin: CortexM4 demo for device 1

## Boot DIP Switches

_Note:_

* S1 is for Boot Mode.
* X means position of DIP, - means don't care

### SD Card

| DIP S1   | 6 | 5 | 4 | 3 | 2 | 1 |
| -------- | - | - | - | - | - | - |
| BOOTMode | 5 | 4 | 3 | 2 | 1 | 0 |
| ON       |   |   | x | x |   |   |
| OFF      | x | x |   |   | x | x |

### e-MMC

| DIP S1   | 6 | 5 | 4 | 3 | 2 | 1 |
| -------- | - | - | - | - | - | - |
| BOOTMode | 5 | 4 | 3 | 2 | 1 | 0 |
| ON       |   |   | x |   |   |   |
| OFF      | x | x |   | x | x | x |

### FLEXSPI (4 Byte Address)

| DIP S1   | 6 | 5 | 4 | 3 | 2 | 1 |
| -------- | - | - | - | - | - | - |
| BOOTMode | 5 | 4 | 3 | 2 | 1 | 0 |
| ON       |   | x | x | x | x |   |
| OFF      | x |   |   |   |   | x |

### Serial Downloader

| DIP S1   | 6 | 5 | 4 | 3 | 2 | 1 |
| -------- | - | - | - | - | - | - |
| BOOTMode | 5 | 4 | 3 | 2 | 1 | 0 |
| ON       |   |   |   | x |   |   |
| OFF      | x | x | x |   | x | x |

### Boot from Fuses

| DIP S1   | 6 | 5 | 4 | 3 | 2 | 1 |
| -------- | - | - | - | - | - | - |
| BOOTMode | 5 | 4 | 3 | 2 | 1 | 0 |
| ON       |   |   |   |   |   |   |
| OFF      | x | x | x | x | x | x |

## Boot device initialisation and update

See [here](./README.BootMediaTQMa8.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

To build bootstream for UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

```
UBOOT_CONFIG_tqma8x = "mfgtool"
IMXBOOT_TARGETS_tqma8x = "flash_spl"
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

### Test sleepmode and wakeup

Use rtc1 (RTC in CPU SNVS domain) to wakeup after 20 seconds:

```
RTC=rtc1
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc//${RTC}/wakealarm
echo mem > /sys/power/state
```

Send Linux to sleep mode and press one of the gpio buttons SWITCH\_A or SWITCH\_B
afterwards

```
echo mem > /sys/power/state
```

### PWM fan

The PWM fan can optionally be used for cooling the SoC. Due to electrical connection
the configuration value `3` for the `hwmon` attribute `pwm1_enable` is required.
This can be achieved using the following `udev` rule:
```
SUBSYSTEM=="hwmon", DRIVERS=="pwm-fan", ATTR{pwm1_enable}="3"
```

Refer to `Documentation/hwmon/pwm-fan.rst` in Linux source code tree for possible
configuration values.

### Display Support

Each Display output could be activated independend by using the corresponding device tree.

| Interface   | Device tree                               | Type           |
|-------------|-------------------------------------------|----------------|
| LVDS0       | imx8qm-tqma8x-mba8x-lvds0-tm070jvhg33.dtb | Tianma Display |
| LVDS1       | imx8qm-tqma8x-mba8x-lvds1-tm070jvhg33.dtb | Tianma Display |
| LVDS0, dual | imx8qm-tqma8qm-mba8x-lvds0-g133han01.dtb  | AUO G133HAN.01 |
| LVDS1, dual | imx8qm-tqma8qm-mba8x-lvds1-g133han01.dtb  | AUO G133HAN.01 |
| DP          | imx8qm-tqma8x-mba8x-dp.dtb                | Displayport    |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      |    X18    | S10 |
| CAN1      |    X19    | S11 |

#### Enable without CAN-FD

CAN1/2 should be enabled and configured by default when using with MB-SMARC-2
and meta-tq / systemd

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

#### Enable CAN-FD

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### Cortex M4

Demos are compiled to use Cortex M4 0/1 UARTS with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8x](https://support.tq-group.com/en/arm/tqma8x)
