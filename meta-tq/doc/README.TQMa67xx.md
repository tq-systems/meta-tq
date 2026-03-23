# TQMa67xx on MBa67xx carrier board

[[_TOC_]]

## Overview

See also: [Common features of TQMa62xx\[L\]/TQMa64xxL/TQMa67xx\[L\]](README.TQMa6xxx.md)

### Supported Hardware:

* TQMa67A94\[L\]: Module revisions REV.020x
* MBa67xx: Board revisions REV.020x

### Versions

_Bootloader:_

* uboot-tq-2026.01 (based on U-Boot 2026.01)

_Kernel:_

* linux-ti-tq-6.12 (based on ti-linux-6.12.y)

### Known issues

* The OTG ID pin of the Micro-USB port (X10) is ignored in U-Boot. The mode of
  the port is determined by the boot mode:
  - When booting in USB host mode (from mass storage), the port uses host
    mode. Access to the connected USB storage device is possible from the
    U-Boot command line.
  - For all other boot modes, the port will be in device mode. The "dfu"
    command can be used to flash the boot media from a connected host.
* Some SD cards do not reset properly to their initial state when the system
  is reset from U-Boot (using the `reset` command or similar), resulting in the
  subsequent boot to fail. If such issues are encountered, it is recommended to
  switch to an SD card from a different manufacturer or try the workarounds
  described in the
  [Processor SDK Linux for J722S documentation](https://software-dl.ti.com/jacinto7/esd/processor-sdk-linux-j722s/11_01_00_03/exports/docs/linux/Foundational_Components/U-Boot/UG-Memory-K3.html#steps-for-working-around-sd-card-issues-in-u-boot).

## Supported features

### U-Boot

| Feature                                          |                       |
| :----------------------------------------------- | :-------------------: |
| RAM configs                                      | 2 GiB, 8GiB           |
| CPU variants                                     | AM67A94               |
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
| 2x Gigabit Ethernet                                          | x           |
| 1x Single-pair Gigabit Ethernet                              | x           |
| **WLAN/Bluetooth**                                           |             |
| TI CC3351 WLAN/BT                                            | x           |
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
| DisplayPort                                                  | x           |
| DSI (X24: RasberryPi 7"; replaces DisplayPort)               | x           |
| **Audio**                                                    |             |
| Analog audio (Line-out, Line-in, Microphone)                 | x           |
| **MIPI-CSI**                                                 |             |
| Grayscale with Vision Components camera (X18: Sensor OV9281) | x           |
| Raw Bayer with Vision Components camera (X18: Sensor IMX327) | x           |
| Raw Bayer with RaspberryPi camera module (X19/X20: Sensor IMX219) | x      |

### First-stage bootloader variants

The first-stage bootloader comes in three variants, each including a different
version of the system controller firmware:

* tiboot3-j722s-hs-fs-tqma67xx.bin (AM67x High Security variant, field-securable)
* tiboot3-j722s-hs-tqma67xx.bin (AM67x High Security variant, security enforced)

Please refer to the Secure Device Processor SDK documentation for more
information on the High Security CPU variants. This documentation must be
obtained directly from TI.

To select the variant to use, set the `SYSFW_DEFAULT_VARIANT` variable to
"j722s-hs-fs" or "j722s-hs" (in `local.conf` or a custom machine definition),
to match the used AM67x CPU security enforcement status. The default value is
"j722s-hs-fs".

The selected variant will be installed to the boot partition of the generated
WIC images as `tiboot3.bin`. It is possible to change an existing image to boot
on a different CPU variant by mounting the boot partition and renaming one of
the bootloader binaries to `tiboot3.bin`.

## HowTo

### MBa67xx DIP switch settings for boot

#### SD card

|         |  S1 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S2 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S3 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |     |     |
| OFF     |     |     |     |     |

#### eMMC

|         |  S1 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S2 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S3 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |     |  x  |     |     |

#### SPI-NOR

|         |  S1 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |  x  |
| OFF     |     |     |  x  |     |

|         |  S2 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |  x  |  x  |

|         |  S3 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |  x  |     |     |     |

#### USB host (mass storage)

|         |  S1 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S2 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S3 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |     |     |

#### USB device (dfu-util)

|         |  S1 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S2 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S3 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |  x  |  x  |     |     |

The `dfu-util` command can be used to load U-Boot from a connected USB host:

```
# Load both U-Boot stages in sequence
dfu-util -R -a bootloader -D tiboot3.bin
dfu-util -R -a u-boot.img -D u-boot.img
```

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

| Label                        | Display             | Camera                                             |
| ---------------------------- | ------------------- | -------------------------------------------------- |
| default                      | none                | none                                               |
| dsi-rpi-7inch-panel          | RaspberryPi 7"      | none                                               |
| lvds-tm070jdhg30             | Tianma TM070JDHG30  | none                                               |
| lvds-vesa-fhd                | Generic VESA FullHD | none                                               |
| csi0-imx327                  | none                | VisionComponents module with IMX327 sensor (X18)   |
| csi0-ov9281                  | none                | VisionComponents module with OV9281 sensor (X18)   |
| csi2-imx219                  | none                | RaspberryPi camera module with IMX219 sensor (X19) |
| csi3-imx219                  | none                | RaspberryPi camera module with IMX219 sensor (X20) |
| lvds-tm070jdhg30-csi0-imx327 | Tianma TM070JDHG30  | VisionComponents module with IMX327 sensor (X18)   |
| lvds-tm070jdhg30-csi0-ov9281 | Tianma TM070JDHG30  | VisionComponents module with OV9281 sensor (X18)   |
| lvds-tm070jdhg30-csi2-imx219 | Tianma TM070JDHG30  | RaspberryPi camera module with IMX219 sensor (X19) |
| lvds-tm070jdhg30-csi3-imx219 | Tianma TM070JDHG30  | RaspberryPi camera module with IMX219 sensor (X20) |
| lvds-vesa-fhd-csi0-imx327    | Generic VESA FullHD | VisionComponents module with IMX327 sensor (X18)   |
| lvds-vesa-fhd-csi0-ov9281    | Generic VESA FullHD | VisionComponents module with OV9281 sensor (X18)   |
| lvds-vesa-fhd-csi2-imx219    | Generic VESA FullHD | RaspberryPi camera module with IMX219 sensor (X19) |
| lvds-vesa-fhd-csi3-imx219    | Generic VESA FullHD | RaspberryPi camera module with IMX219 sensor (X20) |
| spe                          | none                | none                                               |

Most configurations support DisplayPort in addition to the other display options. Only the
`dsi-rpi-7inch-panel` configuration disables the DisplayPort, as both are connected to the same
DSI interface of the AM67x internally.

Simultaneous operation of multiple cameras is possible in some combinations, but
no labels are defined in `extlinux.conf` for such configurations at the moment.

#### RaspberryPi 7" DSI display

The RaspberryPi display unit requires jumper cables to be connected to the
X30 pin header of the MBa67xx in addition to the DSI cable for power supply and
interrupt signaling.

| Display unit pin  | MBa67xx pin |
| ----------------- | ----------- |
| 5V                | X30.4       |
| GND               | X30.6       |
| INT               | X30.12      |

The INT pin is only required for touch input and can be omitted if only display
output is needed.

#### Camera commands

The following commands can be used to capture the camera picture and display it
on the display:

For IMX327 (X18):
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"imx327 3-001a":0[fmt:SRGGB10/1280x720 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1280,height=720 ! \
  bayer2rgb ! waylandsink sync=false
```

For OV9281 (X18):
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"ov9281 3-0060":0[fmt:Y8_1X8/1280x800 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
  videoconvert ! waylandsink sync=false
```

For IMX219 (X19):
```
media-ctl -V '"30142000.ticsi2rx":0[fmt:SRGGB10/1920x1080]'
media-ctl -V '"cdns_csi2rx.30141000.csi-bridge":0[fmt:SRGGB10/1920x1080]'
media-ctl -V '"imx219 3-0010":0[fmt:SRGGB10/1920x1080 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1920,height=1080 ! \
  bayer2rgb ! waylandsink sync=false
```

For IMX219 (X20):
```
media-ctl -V '"30162000.ticsi2rx":0[fmt:SRGGB10/1920x1080]'
media-ctl -V '"cdns_csi2rx.30161000.csi-bridge":0[fmt:SRGGB10/1920x1080]'
media-ctl -V '"imx219 5-0010":0[fmt:SRGGB10/1920x1080 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1920,height=1080 ! \
  bayer2rgb ! waylandsink sync=false
```

`waylandsink` will render the video using Wayland by default. Alternatively,
the video can be displayed in fullscreen directly on a Linux framebuffer device
by stopping the Wayland compositor with `systemctl stop weston.service` and
replacing `waylandsink sync=false` with `fbdevsink sync=false` in the
gst-launch-1.0 command.

Hardware acceleration for the conversion from the Bayer pixel format to RGB is
currently unsupported, resulting in very low achievable framerates with the
IMX219 and IMX327 camera sensors.

### Single-Pair Ethernet

The boot menu displayed by U-Boot allows to select an a Device Tree overlay
for Single-Pair Ethernet; this will switch interface `end1` from the regular
1000BASE-T Ethernet port X14 to 1000BASE-T1 (automotive) Single-Pair Ethernet
port X16. The primary Ethernet interface `end0` always corresponds to 1000BASE-T
port X15.

It is also possible to select the overlay persistently by setting
`pxe_label_override` to `spe` (see
[Display and camera support](#display-and-camera-support)).

## Support Wiki

See [TQ Support Wiki for TQMa67xx](https://support.tq-group.com/en/arm/tqma67xx)
