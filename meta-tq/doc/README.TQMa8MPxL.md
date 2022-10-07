# TQMa8MPxL

This README contains some useful information for TQMa8MPxL on MBa8MPxL

[[_TOC_]]

## Variants

* TQMa8MPQL REV.020x on MBa8MPxL REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See [top level README.md](./../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |   REV.020x   |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1,2,4,8 GiB |
| CPU variants                                     |     i.MX8MPQ |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |       x      |
| UART (console on UART4)                          |       x      |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| 24V IO                                           |       x      |
| **I2C**                                          |              |
| system EEPROM parsing                            |       x      |
| PMIC                                             |       x      |
| **e-MMC / SD**                                   |              |
| Read                                             |       x      |
| Write                                            |       x      |
| **Ethernet**                                     |              |
| GigE / FEC via Phy on MBa8MPxL                   |       x      |
| GigE / EQOS via Phy on MBa8MPxL                  |       x      |
| **Bootdevices**                                  |              |
| SD-Card on USDHC2                                |       x      |
| e-MMC on USDHC3                                  |       x      |
| QSPI-NOR on FlexSPI                              |       x      |
| Serial Downloader                                |       x      |
| **USB**                                          |              |
| USB 3.0 Host / Hub                               |       x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)             |              |
| **QSPI NOR**                                     |              |
| Read with 1-1-1 SDR                              |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |
| **Cortex M7**                                    |              |
| env settings for starting from TCM               |       x      |
| examples with UART3 as debug console             |       x      |

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite

### Linux

| Feature                                          |    REV.020x  |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1,2,4,8 GiB |
| CPU variants                                     |     i.MX8MPQ |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |       x      |
| **UART**                                         |              |
| console on UART4 (via USB / UART converter)      |       x      |
| UART3 via USB UART converter                     |       x      |
| UART1 / UART2 via pin header                     |              |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| 24V IO                                           |       x      |
| **I2C**                                          |              |
| EEPROMs                                          |       x      |
| PMIC                                             |       x      |
| RTC                                              |       x      |
| Temperature Sensors                              |       x      |
| **ENET**                                         |              |
| GigE / FEC via Phy on MBa8MPxL                   |       x      |
| GigE / EQOS via Phy on MBa8MPxL                  |       x      |
| **USB**                                          |              |
| USB 3.0 Host / Hub                               |       x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)             |       x      |
| **QSPI NOR**                                     |              |
| Read with 1-1-4 SDR                              |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |
| GPU                                              |       x      |
| VPU                                              |       x      |
| **Display**                                      |              |
| LVDS                                             |       x      |
| HDMI                                             |       x      |
| **Audio**                                        |              |
| HDMI                                             |              |
| Codec (Line IN / Line OUT)                       |       x      |
| **PCIe**                                         |              |
| wireless card at M.2                             |       x      |
| **CAN-FD**                                       |              |
| CAN-FD                                           |       x      |
| **SPI**                                          |              |
| spidev at all CS                                 |              |
| ADC                                              |       x      |
| **Cortex M7**                                    |              |
| examples running from TCM                        |       x      |
| use UART3 as debug console (see issues)          |       x      |
| **MIPI CSI (see Issues section)**                |              |
| Gray with Vision Components GmbH camera (Sensor OV9281) |   x    |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) | x |

## TODO:

* Audio
  * Codec Microphone in
* Display
  * DSI / DSI DP bridge
* UART1/UART2 via pin header
* SPI via pin header
* I²C interface of PCIe Clock generator not tested

## Known Issues

* REV.020x SoM without variant data in EEPROM (prototypes)
  * With the default U-Boot configuration the boot flow is interrupted in
    SPL and waits for input of the assembled RAM size (1, 2, 4, 8).
  * Use fixed 2GB U-Boot configuration. This is built by default `UBOOT_CONFIG`
    entries `sd-2gb` and `mfgtool-2gb`
    **Note:** the generated wic-File uses the U-Boot multi RAM config
* MIPI CSI
  * driver stack is not completely v4l2-compliance test proof. The IOCTLS for format / resolution
    enumeration and query can return invalid / wrong values depending of the internal state
    of the driver stack. Please follow given examples for a working setup.
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer
  * IMX327: when configuring to SRGGB12 reboot may be needed to get a working
    capture again
  * OV9281: gstreamer does not support Y10 format
* Ethernet
  * Possible communication error to PHY attached to FEC, reboot required to fix
  * ETH1 looses manual assigned IP after suspend/resume. Default systemd network
    configuration uses DHCP with fallback. Has to be adjusted if needed.
* A reset using button S7 will not reset the SD card properly. Especially if the SD
  card is operating on 1.8V it will be non-functional after reset. Use button S8 instead.
* PCIe
  * Some WiFi modules might not be supported
  * Suspend to RAM not supported. Disable PCIe RC node in device tree
  ```
  &pcie {
    status = "disabled";
  };
  ```
* Audio
  * Suspend to RAM does not work reliably. Disable `sound` node in device tree
  ```
  sound {
    compatible = "fsl,imx-audio-tlv320aic32x4";
    [...]
    status = "disabled";
  };
  ```
* USB Host
  * USB Superspeed U3 powersave mode is broken
  * U-Boot: some USB stick types are not working correctly in U-Boot and
    fail to enumerate correctly after `usb reset`
* USB Bluetooth:
  * Some adapters cause the following error during bootup

    `Bluetooth: hci0: unexpected event for opcode 0xfc2f`

    According to https://lkml.org/lkml/2019/6/6/868 this can be ignored

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mp-tqma8mpql-mba8mpxl.dtb
  * imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb (LVDS support with TIANMA TM070JVHG33)
  * imx8mp-tqma8mpql-mba8mpxl-hdmi.dtb (HDMI support)
  * imx8mp-tqma8mpql-mba8mpxl-hdmi-imx327.dtb (HDMI support plus Vision Components CSI camera
    with Sony IMX327)
  * imx8mp-tqma8mpql-mba8mpxl-hdmi-ov9281.dtb (HDMI support plus Vision Components CSI camera
    with OmniVision OV9281)
  * imx8mp-tqma8mpql-mba8mpxl-rpmsg.dtb (CortexM / RPMSG Support)
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_evk\_uboot:  boot stream for UUU
* hello\_world.bin (Cortex M7 demo, UART3, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M7 demo, UART3, TCM)

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1.

### Serial Downloader

*BOOT\_MODE: 0001*

| DIP S1     | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         | x |   |   |   |
| Off        |   | x | x | x |

### e-MMC (USDHC3)

BOOT\_MODE: 0010

| DIP S1     | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         |   | x |   |   |
| Off        | x |   | x | x |

### SD Card (USDHC2)

BOOT\_MODE: 0011

| DIP S1     | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         | x | x |   |   |
| Off        |   |   | x | x |

### FlexSPI / 3B Read

BOOT\_MODE: 0110

| DIP S1     | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         |   | x | x |   |
| Off        | x |   |   | x |

## Boot device initialisation and update

See [here](./README.BootMediaTQMa8.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.TQMa8.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### MIPI-CSI

#### Vision Components GmbH cameras

*Note*: see known issue section above.

##### Gray with Omnivision OV9281

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-hdmi-ov9281.dtb`

gstreamer examples:

```
# configure
yavta -f Y8 -s 1280x800 -c20 /dev/video0
# grab to file
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! videorate ! \
 video/x-raw,format=GRAY8,width=1280,height=800,framerate=1/1 ! jpegenc ! multifilesink location=test%d.jpg

# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! videoconvert ! autovideosink -v sync=false
```

* OpenGL accelerated pipeline:
```
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! glupload ! glcolorconvert ! glcolorscale ! glcolorconvert ! gldownload ! autovideosink -v sync=false
```
* Show FPS on screen
```
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! glupload ! glcolorconvert ! glcolorscale ! glcolorconvert ! gldownload ! fpsdisplaysink video-sink="waylandsink" sync=false -v
```
Add `text-overlay=false` to fpsdisplaysink for console output only

##### Raw Bayer with Sony IMX327

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-hdmi-imx327.dtb`
* gstreamer example:

```
# configure
yavta -f SRGGB10 -s 1280x720  /dev/video0

# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! \
  video/x-bayer,format=rggb,bpp=10,width=1280,height=720 ! bayer2rgbneon show-fps=t reduce-bpp=t ! \
  autovideoconvert ! autovideosink sync=false
```

#### Basler camera

__daA3840-30mc__

##### Build

In order to avoid any unforseen side effects for different cameras, basler support has to be enabled explicitely by adding the following lines to `local.conf`
```
# Basler camera support
ACCEPT_BASLER_EULA = "1"
IMAGE_INSTALL:append = " packagegroup-fsl-isp packagegroup-dart-bcon-mipi"
DISTRO_FEATURES:append = " x11"
MACHINE_FEATURES:append = " basler"
```

This enabled additional patches required for this camera as well as a dedicated DT and additional pacages.

Also several meta layers have to be added in bblayers.conf (if not already done):
```
  ${BSPDIR}/sources/meta-basler-imx8 \
  ${BSPDIR}/sources/meta-basler-tools \
  ${BSPDIR}/sources/meta-freescale \
  ${BSPDIR}/sources/meta-qt5 \
```

##### Usage
* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-lvds-basler.dtb`
* gstreamer example:

```
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 -v v4l2src device=/dev/video0 ! waylandsink
```

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                                    | Type        ----   |
|-----------------|------------------------------------------------|--------------------|
| LVDS            | imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| HDMI            | imx8mp-tqma8mpql-mba8mpxl-hdmi.dtb             | compatible monitor |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      | X18       | S12 |
| CAN1      | X19       | S11 |

#### Enable without CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with MBa8MPxL
and meta-tq / systemd

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

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with MBa8MPxL
and meta-tq / systemd.

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### Cortex M7

Demos are compiled to use UART3 with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8MPxL](https://support.tq-group.com/en/arm/tqma8mpxl)
