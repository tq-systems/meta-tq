# TQMa64xxL on MBaX4XxL carrier board

[[_TOC_]]

## Overview

See also: [Common features of TQMa62xx\[L\]/TQMa64xxL/TQMa67xx\[L\]](README.TQMa6xxx.md)

### Supported Hardware:

* TQMa6442L, TQMa6411L: Module revisions 020x
* MBaX4XxL: Board revisions 020x

### Versions

_Bootloader:_

* uboot-ti-tq-2025.10 (based on U-Boot 2025.10)

_Kernel:_

* linux-ti-tq-6.12 (based on ti-linux-6.12.y; default)
* linux-ti-tq-6.6 (based on ti-rt-linux-6.6.y)

### Known issues

* *In U-Boot,* USB functions are limited:
  - USB 3.0 is disabled in U-Boot, only USB 2.0 is available
  - The OTG ID pin is ignored in U-Boot. The mode of the port is determined
    by the boot mode:
    - When booting in USB host mode (from mass storage), the port uses host
      mode. Access to the connected USB storage device is possible from the
      U-Boot command line.
    - For all other boot modes, the port will be in device mode. The "dfu"
      command can be used to flash the boot media from a connected host.
* *In U-Boot,* there is no PRU Ethernet support. Only the primary Ethernet port
  X5 is usable in the bootloader.
* *From U-Boot,* most GPIOs can't be read or set, as they are disabled in the
  default pinmuxing. This also affects LED and button GPIOs. LEDs and buttons
  are working as expected on Linux.
* There is currently no driver support for the Bluetooth module of the MBaX4XxL.
* In rare cases, the USB3 PHY initialization has been observed to fail with the
  message "Timeout waiting for CMN ready" during boot, leaving the USB
  controller unusable. A power cycle is necessary to recover from this state.
* On variants with only a single R5 core (TQMa6412L, TQMa6411L), booting
  R5 programs and communicating with them using the Linux remoteproc/rpmsg
  drivers is currently unsupported.
* The AM64x currently doesn't support Suspend-to-RAM

## Supported features

### U-Boot

| Feature                                          |                       |
| :----------------------------------------------- | :-------------------: |
| RAM configs                                      | 1 GiB, 2GiB           |
| CPU variants                                     | AM6442, AM6411        |
| UART (console on UART0)                          | x                     |
| GPIO                                             | x                     |
| I2C                                              | x                     |
| System EEPROM parsing                            | x                     |
| eMMC / SD                                        | x                     |
| SPI-NOR                                          | x                     |
| Ethernet                                         | Port X5 only          |
| Cortex-R5F                                       | no                    |
| Cortex-M4F                                       | no                    |
| **Boot devices**                                 |                       |
| eMMC                                             | x                     |
| SD card                                          | x                     |
| SPI-NOR                                          | x                     |
| USB Mass Storage (X1)                            | x                     |
| USB DFU (X1)                                     | x                     |
| **USB**                                          |                       |
| USB Host (X1, via OTG adapter)                   | USB Mass Storage boot only |
| USB Device (X1)                                  | All boot devices execpt for USB Mass Storage |
| USB Cable Detect / ID (X1)                       | no                    |
| USB switchable VBUS (X1)                         | no (fixed setting for boot device) |

### Linux

| Feature                                                      |             |
| :----------------------------------------------------------- | :---------: |
| Suspend (deep / s2idle)                                      | no          |
| **UART**                                                     |             |
| Console (UART0, via X2 USB / UART converter)                 | x           |
| RS485 (MCU\_UART0)                                           | x           |
| BG95 IoT module (MAIN_UART1/4/5)                             | x           |
| **GPIO**                                                     |             |
| LED                                                          | x           |
| Button                                                       | x           |
| **I2C**                                                      |             |
| EEPROMs                                                      | x           |
| PMIC                                                         | x           |
| RTC                                                          | x           |
| Temperature sensor                                           | x           |
| **Ethernet**                                                 |             |
| 1x Gigabit Ethernet on MBaX4XxL (X5)                         | x           |
| 2x PRU Gigabit Ethernet on MBaX4XxL (X6)                     | x           |
| **WLAN/Bluetooth**                                           |             |
| Marvell/NXP 88W8987-based WLAN/BT                            | WLAN only   |
| **CAN**                                                      |             |
| 2x CAN with CAN FD                                           | x           |
| **USB**                                                      |             |
| USB (X1: Dual role, Cable Detect, VBUS)                      | x           |
| **PWM**                                                      |             |
| Fan control                                                  | x (disabled by default) |
| **SPI-NOR**                                                  |             |
| Read with 1-4-4 SDR                                          | x           |
| Write / erase with 1-4-4 SDR                                 | x           |
| **SPI**                                                      |             |
| Analog frontend (NAFE1338)                                   | x           |

### First-stage bootloader variants

The first-stage bootloader comes in three variants, each including a different
version of the system controller firmware:

* tiboot3-am64x-gp-tqma64xxl.bin (General Purpose variant)
* tiboot3-am64x_sr2-hs-fs-tqma64xxl.bin (High Security variant, field-securable)
* tiboot3-am64x_sr2-hs-tqma64xxl.bin (High Security variant, security enforced)

Please refer to the Secure Device Processor SDK documentation for more
information on the High Security CPU variants. This documentation must be
obtained directly from TI.

To select the variant to use, set the `SYSFW_DEFAULT_VARIANT` variable to
"am64x-gp", "am64x_sr2-hs-fs" or "am64x_sr2-hs" (in `local.conf` or a custom
machine definition), to match the used AM64x CPU variant and security
enforcement status. The default value is "am64x_sr2-hs-fs".

The selected variant will be installed to the boot partition of the generated
WIC images as `tiboot3.bin`. It is possible to change an existing image to boot
on a different CPU variant by mounting the boot partition and renaming one of
the bootloader binaries to `tiboot3.bin`.

## HowTo

### MBaX4XxL DIP switch settings for boot

#### SD card

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |  x  |     |     |
| OFF     |     |     |  x  |  x  |    |     |     |     |     |

#### eMMC

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |     |     |     |
| OFF     |     |     |  x  |  x  |    |     |  x  |     |     |

#### SPI-NOR

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |  x  |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |  x  |    |     |     |     |     |
| OFF     |     |     |  x  |     |    |  x  |     |     |     |

#### USB host (mass storage)

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |  x  |     |     |     |
| OFF     |     |     |  x  |  x  |    |     |  x  |     |     |

#### USB device (dfu-util)

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |     |     |     |
| OFF     |     |     |  x  |  x  |    |  x  |  x  |     |     |

The `dfu-util` command can be used to load U-Boot from a connected USB host:

```
# After initial power-on, U-Boot will reset the device as a part of an Errata
# workaround, so tiboot3.bin needs to be loaded twice. After a warm boot (reset
# from U-Boot or reboot from Linux), the initial load of tiboot3.bin must be
# skipped.
dfu-util -a bootloader -D tiboot3.bin

# Load both U-Boot stages in sequence
dfu-util -R -a bootloader -D tiboot3.bin
dfu-util -R -a u-boot.img -D u-boot.img
```

Please refer to the
[AM64x Processor SDK Documentation](https://software-dl.ti.com/processor-sdk-linux/esd/AM64X/08_02_00_23/exports/docs/linux/Foundational_Components/U-Boot/Users-Guide.html)
for information on the usage of `dfu-util` to flash boot media.

### OS boot

See the [Distroboot README](README.Distroboot.md).

### OS updates

See [RAUC](RAUC.md).

### WLAN support

The `extlinux.conf` generated by this BSP's default configuration will display
a menu to choose between SD card (default) and WLAN support. The default
can be modified by setting the `pxe_label_override` environment variable:
```
setenv pxe_label_override sdcard # Boot with SD card
setenv pxe_label_override wlan # Boot with WLAN
saveenv # Persist configuration
```

As the SD card and WLAN adapter require exclusive control of the same SDHC/SDIO
bus, they can't be enabled at the same time. A different OS boot source (usually
eMMC or SPI-NOR) must be used when WLAN is enabled.

## Support Wiki

See [TQ Support Wiki for TQMa64xxL](https://support.tq-group.com/en/arm/tqma64xxl)
