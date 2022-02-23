# TQMa8MPxL

This README contains some useful information for TQMa8MPxL on MBa8MPxL

[[_TOC_]]

## Variants

* TQMa8MPQL REV.010x on MBa8MPxL REV.010x
* TQMa8MPQL REV.020x on MBa8MPxL REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |    REV.010x |     REV.020x |
| ------------------------------------------------ | ----------- | ------------ |
| RAM configs                                      |       2 GiB |        2 GiB |
| CPU variants                                     |    i.MX8MPQ |     i.MX8MPQ |
| Fuses / OCRAM                                    |       x     |       x      |
| speed grade / temperature grade detection        |       x     |       x      |
| UART (console on UART4)                          |       x     |       x      |
| **GPIO**                                         |             |              |
| LED                                              |       x     |       x      |
| Button                                           |       x     |       x      |
| 24V IO                                           |             |       x      |
| **I2C**                                          |             |              |
| system EEPROM parsing                            |       x     |       x      |
| PMIC                                             |       x     |       x      |
| **e-MMC / SD**                                   |             |              |
| Read                                             |       x     |       x      |
| Write                                            |       x     |       x      |
| **ENET**                                         |             |              |
| GigE / FEC via Phy on MBa8MPxL                   |       x     |       x      |
| GigE / EQOS via Phy on MBa8MPxL                  |       x     |       x      |
| **Bootdevices:**                                 |             |              |
| SD-Card on USDHC2                                |       x     |       x      |
| e-MMC on USDHC3                                  |       x     |       x      |
| QSPI-NOR on FlexSPI                              |       x     |       x      |
| Serial Downloader                                |       x     |       x      |
| **USB**                                          |             |              |
| USB 3.0 Host / Hub                               |       x     |       x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)             |       x     |              |
| **QSPI NOR**                                     |             |              |
| Read with 1-1-1 SDR                              |       x     |       x      |
| PP / Erase with 1-1-1 SDR                        |       x     |       x      |
| **Cortex M7**                                    |             |              |
| env settings for starting from TCM               |       x     |              |
| examples with UART3 as debug console             |       x     |              |

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite
* Cortex M7 with REV.020x

### Linux

| Feature                                          |    REV.010x |     REV.020x |
| ------------------------------------------------ | ----------- | ------------ |
| RAM configs                                      |       2 GiB |        2 GiB |
| CPU variants                                     |    i.MX8MPQ |     i.MX8MPQ |
| Fuses / OCRAM                                    |       x     |       x      |
| speed grade / temperature grade detection        |       x     |       x      |
| **UART**                                         |             |              |
| console on UART4 (via USB / UART converter       |       x     |       x      |
| 3 x UART via pin head / USB UART converter       |       x     |              |
| **GPIO**                                         |             |              |
| LED                                              |       x     |       x      |
| Button                                           |       x     |       x      |
| 24V IO                                           |             |       x      |
| **I2C**                                          |             |              |
| EEPROMs                                          |       x     |       x      |
| PMIC                                             |       x     |       x      |
| RTC                                              |       x     |       x      |
| Temperature Sensors                              |       x     |       x      |
| **ENET**                                         |             |              |
| GigE / FEC via Phy on MBa8MPxL                   |       x     |       x      |
| GigE / EQOS via Phy on MBa8MPxL                  |       x     |       x      |
| **USB**                                          |             |              |
| USB 3.0 Host / Hub                               |       x     |       x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)             |       x     |              |
| **QSPI NOR**                                     |             |              |
| Read with 1-1-4 SDR                              |       x     |       x      |
| PP / Erase with 1-1-1 SDR                        |       x     |       x      |
| GPU                                              |       x     |       x      |
| VPU                                              |       x     |       x      |
| **Display**                                      |             |              |
| LVDS                                             |       x     |       x      |
| HDMI                                             |       x     |       x      |
| **Audio**                                        |             |              |
| HDMI                                             |       x     |              |
| Codec (Line IN / Line OUT)                       |       x     |       x      |
| **PCIe**                                         |             |              |
| network card at M.2                              |       x     |              |
| **SPI**                                          |       x     |       x      |
| spidev at all CS                                 |       x     |       x      |
| ADC                                              |       x     |       x      |
| **Cortex M7**                                    |             |              |
| examples running from TCM                        |       x     |              |
| use UART3 as debug console (see issues)          |       x     |              |
| **MIPI CSI (see Issues section)**                |             |              |
| Gray with Vision Components GmbH camera (Sensor OV9281) |       x     |     |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |   x    |     |

## TODO:

* Audio
  * Codec Microphone in
* Display
  * DSI / DSI DP bridge
* PCIe (REV.020x)
  * not tested
* UART1-3
  * not tested on REV.0200
* Cortex M7 (REV.020x)

## Known Issues

* UART 1,2,3 (REV.010x)
  * UART are in DTE mode, not DCE
  * to use UART with FTDI RS232 / USB some hardware modification are needed for REV.010x, see
    manual
  * to use UART for M7, patch for UART DTE mode is needed
* MIPI CSI
  * driver stack is not completely v4l2-compliance test proof. The IOCTLS for format / resolution
    enumeration and query can return invalid / wrong values depending of the internal state
    of the driver stack. Please follow given examples for a working setup.
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer
  * IMX327: when configuring to SRGGB12 reboot may be needed to get a working
    capture again
  * OV9281: gstreamer: capture not starting out of the box, need to use  `yavta` to
    capture some frames, `gstreamer` starts afterwards
* Possible communication error to PHY attached to FEC, reboot required to fix
* A reset using button S7 will not reset the SD card properly. Especially if the SD
  card is operating on 1.8V it will be non-functional after reset. Use button S8 instead.

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

See [here] for details about using Serial Download mode and UUU.

## Howto

### MIPI-CSI

#### Vision Components GmbH cameras

*Note*: see known issue section above.

__Gray with Omnivision OV9281__

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-hdmi-ov9281.dtb`
* gstreamer example:

```
# configure
yavta -f Y8 -s 1280x800 -c20 /dev/video0
# grab to file
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! videorate ! video/x-raw,format=GRAY8,framerate=1/1 ! \
	jpegenc ! multifilesink location=test%d.jpg
# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! videoconvert ! \
	autovideosink -v sync=false
```

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-hdmi-imx327.dtb`
* gstreamer example:

```
# configure
yavta -f SRGGB10 -s 1280x720  /dev/video0

# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 force-aspect-ratio=false ! \
	video/x-bayer,format=rggb,bpp=10,width=1280,height=720,framerate=25/1 ! \
	bayer2rgbneon show-fps=t reduce-bpp=t ! autovideoconvert ! \
	autovideosink sync=false
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
