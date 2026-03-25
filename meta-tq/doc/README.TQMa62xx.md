# TQMa62xx on MBa62xx carrier board

[[_TOC_]]

## Overview

See also: [Common features of TQMa62xx\[L\]/TQMa64xxL/TQMa67xx\[L\]](README.TQMa6xxx.md)

### Supported Hardware:

* TQMa6234\[L\], TQMa6254\[L\]: Module revisions REV.010x / 020x
* MBa62xx: Board revisions REV.010x / 020x

### Versions

_Bootloader:_

* uboot-tq-2026.01 (based on U-Boot 2026.01)

_Kernel:_

* linux-ti-tq-6.12 (based on ti-linux-6.12.y)

### Known issues

* Waking up from Suspend-to-RAM currently does not work on the MBa62xx.
* Under high system load, the AM62x may become unstable during audio playback or
  recording.
* During audio playback or recording, the kernel prints various error messages
  regarding DMA controller configuration timeouts.
* Very rarely, under high system load, the I2C controller of the AM62x has been
  seen to get stuck in a state in which it permanently pulls down the SCL pin
  until the controller is reset.
* The OTG ID pin of the Micro-USB port (X10) is ignored in U-Boot. The mode of
  the port is determined by the boot mode:
  - When booting in USB host mode (from mass storage), the port uses host
    mode. Access to the connected USB storage device is possible from the
    U-Boot command line.
  - For all other boot modes, the port will be in device mode. The "dfu"
    command can be used to flash the boot media from a connected host.
* On TQMa62xx variants with AM62x GP SoC, SD card boot is slow after
  power-on, taking several seconds before the first messages of the
  bootloader
* On TQMa62xx variants without user EEPROM, the EEPROM device is not disabled
  in the Linux Device Tree, resulting in a non-critical error message in the
  kernel log during boot

## Supported features

### U-Boot

| Feature                                          |                       |
| :----------------------------------------------- | :-------------------: |
| RAM configs                                      | 1 GiB, 2GiB           |
| CPU variants                                     | AM6234, AM6254        |
| UART (console on UART0)                          | x                     |
| GPIO                                             | x                     |
| I2C                                              | x                     |
| System EEPROM parsing                            | x                     |
| eMMC / SD                                        | x                     |
| SPI-NOR                                          | x                     |
| Ethernet                                         | Port X14 only         |
| Cortex-R5F                                       | Booted with DM firmware |
| Cortex-M4F                                       | no                    |
| **Boot devices**                                 |                       |
| eMMC                                             | x                     |
| SD card                                          | x                     |
| SPI-NOR                                          | x                     |
| USB Mass Storage (X10)                           | x                     |
| USB DFU (X10)                                    | x                     |
| **USB**                                          |                       |
| USB Host (X10, via OTG adapter)                  | USB Mass Storage boot only |
| USB Device (X10)                                 | All boot devices execpt for USB Mass Storage |
| USB Cable Detect / ID (X10)                      | no                    |
| USB switchable VBUS (X10)                        | no (fixed setting for boot device) |
| USB Host (X11, internal HUB)                     | x                     |

### Linux

| Feature                                                      |             |
| :----------------------------------------------------------- | :---------: |
| Suspend (deep / s2idle)                                      | no          |
| **UART**                                                     |             |
| Console (UART0, via X7 USB / UART converter)                 | x           |
| RS485 (MCU\_UART0)                                           | x           |
| **GPIO**                                                     |             |
| LED                                                          | x           |
| Button                                                       | x           |
| **I2C**                                                      |             |
| EEPROMs                                                      | x           |
| PMIC                                                         | x           |
| RTC                                                          | x           |
| Temperature sensor                                           | x           |
| **Ethernet**                                                 |             |
| 2x Gigabit Ethernet on MBa62xx                               | x           |
| **WLAN/Bluetooth**                                           |             |
| Marvell/NXP SD8997-based WLAN/BT                             | x           |
| **CAN**                                                      |             |
| 2x CAN with CAN FD                                           | x           |
| **USB**                                                      |             |
| USB (X10: Dual role, Cable Detect, VBUS)                     | x           |
| USB (X11: Host ports on internal hub)                        | x           |
| BG95 IoT module (Virtual UART on internal hub)               | x           |
| **PWM**                                                      |             |
| LVDS Backlight                                               | x           |
| Fan control                                                  | x (disabled by default) |
| **SPI-NOR**                                                  |             |
| Read with 1-4-4 SDR                                          | x           |
| Write / erase with 1-4-4 SDR                                 | x           |
| **Graphics**                                                 |             |
| GPU                                                          | x           |
| **Display**                                                  |             |
| LVDS                                                         | x           |
| **Audio**                                                    |             |
| Analog audio (Line-out, Line-in, Microphone)                 | x           |
| **MIPI-CSI**                                                 |             |
| Grayscale with Vision Components camera (Sensor OV9281)      | x           |
| Raw Bayer with Vision Components camera (Sensor IMX327)      | x           |
| **SPI**                                                      |             |
| Analog frontend (NAFE1338)                                   | x           |

### First-stage bootloader variants

The first-stage bootloader comes in three variants, each including a different
version of the system controller firmware:

* tiboot3-am62x-gp-tqma62xx.bin (AM62x General Purpose variant)
* tiboot3-am62x-hs-fs-tqma62xx.bin (AM62x High Security variant, field-securable)
* tiboot3-am62x-hs-tqma62xx.bin (AM62x High Security variant, security enforced)

Please refer to the Secure Device Processor SDK documentation for more
information on the High Security CPU variants. This documentation must be
obtained directly from TI.

To select the variant to use, set the `SYSFW_DEFAULT_VARIANT` variable to
"am62x-gp", "am62x-hs-fs" or "am62x-hs" (in `local.conf` or a custom
machine definition), to match the used AM62x CPU variant and security
enforcement status. The default value is "am62x-hs-fs".

The selected variant will be installed to the boot partition of the generated
WIC images as `tiboot3.bin`. It is possible to change an existing image to boot
on a different CPU variant by mounting the boot partition and renaming one of
the bootloader binaries to `tiboot3.bin`.

## HowTo

### MBa62xx DIP switch settings for boot

#### SD card

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |     |     |
| OFF     |     |     |     |     |

#### eMMC

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |     |  x  |     |     |

#### SPI-NOR

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |  x  |
| OFF     |     |     |  x  |     |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |  x  |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |  x  |     |     |     |

#### USB host (mass storage)

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |     |     |

#### USB device (dfu-util)

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |     |     |
| OFF     |  x  |     |     |     |

The `dfu-util` command can be used to load U-Boot from a connected USB host:

```
# Load both U-Boot stages in sequence
dfu-util -R -a bootloader -D tiboot3.bin
dfu-util -R -a u-boot.img -D u-boot.img
```

Please refer to the
[AM62x Processor SDK Documentation](https://downloads.ti.com/processor-sdk-linux/esd/AM62X/09_00_00_03/exports/docs/linux/Foundational_Components/U-Boot/Users-Guide.html)
for information on the usage of `dfu-util` to flash boot media.

### OS boot

See the [Distroboot README](README.Distroboot.md).

### OS updates

See [RAUC](RAUC.md).

### Display and camera support

The `extlinux.conf` generated by this BSP's default configuration will display
a menu to choose between a number of different LVDS display and MIPI-CSI camera
configurations. The default can be modified by setting the `pxe_label_override`
environment variable:
```
setenv pxe_label_override 'label' # Set default boot option to 'label'
saveenv # Persist configuration
```

The following labels are currently defined:

| Label                       |  Display            | Camera                                     |
| --------------------------- | ------------------- | ------------------------------------------ |
| default                     | none                | none                                       |
| lvds-tm070jdhg30            | Tianma TM070JDHG30  | none                                       |
| lvds-vesa-fhd               | Generic VESA FullHD | none                                       |
| csi-imx327                  | none                | VisionComponents module with IMX327 sensor |
| csi-ov9281                  | none                | VisionComponents module with OV9281 sensor |
| lvds-tm070jdhg30-csi-imx327 | Tianma TM070JDHG30  | VisionComponents module with IMX327 sensor |
| lvds-tm070jdhg30-csi-ov9281 | Tianma TM070JDHG30  | VisionComponents module with OV9281 sensor |
| lvds-vesa-fhd-csi-imx327    | Generic VESA FullHD | VisionComponents module with IMX327 sensor |
| lvds-vesa-fhd-csi-ov9281    | Generic VESA FullHD | VisionComponents module with OV9281 sensor |

The following commands can be used to capture the camera picture and display it
on the display:

For IMX327:
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"imx327 1-001a":0[fmt:SRGGB10/1280x720 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1280,height=720 ! \
  bayer2rgb ! waylandsink sync=false
```

For OV9281:
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"ov9281 1-0060":0[fmt:Y8_1X8/1280x800 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
  videoconvert ! waylandsink sync=false
```

`waylandsink` will render the video using Wayland by default. Alternatively,
the video can be displayed in fullscreen directly on a Linux framebuffer device
by stopping the Wayland compositor with `systemctl stop weston.service` and
replacing `waylandsink sync=false` with `fbdevsink sync=false` in the
gst-launch-1.0 command.

Note that the TQMa62xx does not have hardware acceleration for the conversion
from the Bayer pixel format to RGB. Even when using the optimized bayer2rgbneon
plugin provided in our BSP, framerates with the IMX327 are very low.

## Support Wiki

See [TQ Support Wiki for TQMa62xx](https://support.tq-group.com/en/arm/tqma62xx)
