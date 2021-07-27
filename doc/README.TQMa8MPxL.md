# TQMa8MPxL

This README contains some useful information for TQMa8MPxL on MBa8MPxL

## Variants

* TQMa8MPQL REV.010x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:

* RAM configs: 2 GiB
* CPU variants i.MX8MPQ
* Fuses
* speed grade / temperature grade detection
* UART (console on UART4)
* GPIO
  * LED
  * Button
* I2C
  * system EEPROM parsing
* e-MMC / SD
  * Read
  * Write
* ENET
  * GigE / FEC via Phy on MBa8MPxL
  * GigE / Eqos via Phy on MBa8MPxL
* Bootdevices:
  * SD-Card on USDHC2
  * e-MMC on USDHC3
  * QSPI-NOR on FlexSPI
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 3.0 Cable Detect, VBUS)
* QSPI NOR
  * Read with 1-1-1 SDR
  * PP / Erase with 1-1-1 SDR
* Cortex M7
  * env settings for starting from TCM
  * examples with UART3 as debug console

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite

### Linux:

* RAM configs: 2 GiB
* CPU variants i.MX8MPQ
* speed grade / temperature grade detection
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* GPIO
  * LED
  * Button
* UART
  * console on UART4
  * 3 x UART via pin head / FTDI 4 port USB / UART converter (see issues)
* ENET
  * GigE / FEC via Phy on MBa8MPxL
  * GigE / Eqos via Phy on MBa8MPxL
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 3.0 Cable Detect, VBUS)
* GPU
* VPU
* Display
  * LVDS
  * HDMI
* Audio
  * HDMI
  * Codec (Line IN / Line OUT)
* PCIe
  * network card at M.2
* SPI
  * spidev at all CS
* Cortex M7
  * examples running from TCM
  * use UART3 as debug console (see issues)
* MIPI CSI (see Issues section)
  * Raw Bayer with Vision Components GmbH camera (Sensor IMX327)

## TODO:

* Audio
  * Codec Microphone in
* Display
  * DSI / DSI DP bridge
* ADC

## Known Issues

* CAN
  * CAN FD can not be automatically configured (systemd limitation in this version)
* UART 1,2,3
  * UART are in DTE mode, not DCE
  * to use UART with FTDI RS232 / USB some hardware modification are needed for REV.010x, see
    manual
  * to use UART for M7, patch for UART DTE mode is needed
* MIPI CSI
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer
  * IMX327: when configuring to SRGGB12 reboot may be needed to get a working
    capture again
  * gstreamer: capture sometimes not starting, when using `yavta -c20 /dev/video0` to
    capture some frames, `gstreamer` starts afterwards

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mp-mba8mpxl.dtb
  * imx8mp-mba8mpxl-lvds-tm070jvhg33.dtb
  * imx8mp-mba8mpxl-hdmi.dtb
  * imx8mp-mba8mpxl-hdmi-imx290.dtb
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* hello\_world.bin (Cortex M7 demo, UART3, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M7 demo, UART3, TCM)

## Boot Dip Switches

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

## Boot device initialisation

### Bootable SD-Card

Complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=32 conv=fsync`

### Bootable e-MMC

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 blocks -> 0x40

tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

### Bootable QSPI NOR

To create a bootable QSPI NOR with boot stream only (file name see above)

Example for U-Boot, booting from SD-Card:

```
tftp <bootstream>
sf probe
sf update ${loadaddr} 0 ${filesize}
```

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
e-MMC.

_U-Boot environment variables_

* `uboot`: name of bootstream image (default = bootstream.bin)
* `mmcdev`: 0 for e-MMC, 1 for SD-Card (automatically generated,
  can be overwritten)
  `mmcpart`: partition number for kernel and devicetree (default = 1)
  `mmcpath`: path to kernel and device tree (default = /)
* `fdt_file`: device tree blob,
* `image`: kernel image,

_SD / e-MMC_

Download bootstream from TFTP and update:

`run update_uboot_mmc`

Download device tree blob from TFTP and update:

`run update_fdt_mmc`

Download kernel image from TFTP and update:

`run update_kernel_mmc`

Cortex M7 image: set env var `cm_image` to name of your Cortex M7 image,
provide the file via TFTP and update via `run update_cm_mmc`

_FLEXSPI_

Download bootstream from TFTP and update:

`run update_uboot_spi`

## Use UUU Tool

To build bootstream adapt yocto configuration, modify _local.conf_ or machine
config file:

```
UBOOT_CONFIG_tqma8mpxl = "mfgtool"
IMXBOOT_TARGETS_tqma8mpxl = "flash_spl_uboot"
```

Rebuild boot stream:

```
bitbake imx-boot
```

Use new compiled bootstream containing U-Boot capable of handling SDP together
with UUU tool:

```
sudo uuu -b spl <bootstream>
```

## Howto

### MIPI-CSI

#### Vision Components GmbH cameras

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mp-mba8mpxl-hdmi-imx327.dtb`
* gstreamer example:

```
# configure
yavta -f SRGGB10 -s 1280x720  /dev/video0
# show live video
gst-launch-1.0 v4l2src device=/dev/video0 force-aspect-ratio=false '!' \
	video/x-bayer,format=rggb,bpp=10,width=1280,height=720,framerate=25/1 '!' \
	bayer2rgbneon show-fps=t reduce-bpp=t '!' autovideoconvert '!' \
	autovideosink sync=false
```

## Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                           | Type        ----   |
|-----------------|---------------------------------------|--------------------|
| LVDS            | imx8mp-mbba8mpxl-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| HDMI            | imx8mp-mbba8mpxl-hdmi.dtb             | compatible monitor |

## CAN

### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      | X18       | S12 |
| CAN1      | X19       | S11 |

### Enable without CAN-FD

CAN1/2 should be enabled and configured by default when using with MBa8MPxL
and meta-tq / systemd

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

### Enable CAN-FD

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### Cortex M7

Demos are compiled to use UART3 with 115200 8N1.

To start a demo stored on SD / e-MMC from U-Boot:

```
setenv fdt_file imx8mp-mba8mpxl-rpmsg.dtb
setenv cm_image <demo>
run boot_cm_mmc
```

To connect from running linux to rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```
