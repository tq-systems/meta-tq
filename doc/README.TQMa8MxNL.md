# TQMa8MxNL

This README contains some useful information for TQMa8MxNL on MBa8Mx REV.030x

[[_TOC_]]

## Variants

* TQMa8MQNL REV.020x
* TQMa8MDLNL REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot

_MBa8x HW Rev.030x only_

* RAM configs: 1 GiB
* CPU variants:
  * i.MX8MNQ
  * i.MX8MNDL
* Fuses
* speed grade / temperature grade detection
* UART (console on UART3)
* GPIO
  * LED
  * Button
  * BOOT_CFG
  * MUX CFG
* I2C
  * GPIO expander
  * system EEPROM parsing
* e-MMC / SD
  * Read
  * Write
* ENET (GigE via Phy on MBa8Mx)
* Bootdevices:
  * SD-Card on USDHC2
  * e-MMC on USDHC3
  * QSPI-NOR on FlexSPI
* USB
  * USB 2.0 Host / Hub
  * USB DRD (USB 2.0 OTG, Cable Detect, VBUS)
* QSPI NOR
  * Read with 1-1-1 SDR
  * PP / Erase with 1-1-1 SDR
* Cortex M7
  * env settings for starting from TCM
  * examples with UART4 as debug console

**TODO or not tested / supported**

* RAM 2 GB
* CPU variants i.MX8MNS

### Linux

* RAM configs 1 GiB
* CPU variants:
  * i.MX8MNQ
  * i.MX8MNDL
* speed grade / temperature grade detection
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
  * GPIO expanders
* GPIO
  * LED
  * Button
  * HOG
* UART
  * console on UART3
  * 2 x UART via pin head or X15
* SPI
  * 2 x via spidev in userland
* ENET (GigE via Phy on MBa8Mx)
* ~~Audio~~
  * ~~Codec Line In (X14)~~
  * ~~Codec Line Out (X13)~~
* USB
  * USB 2.0 Host / Hub
  * USB DRD (USB 2.0 OTG, Cable Detect, VBUS)
* PWM
  * Buzzer
  * Backlight for LVDS
* DSI
  * DSI to LVDS bridge
* GPU
* QSPI NOR
  * Read with 1-1-4 SDR
  * PP / Erase with 1-1-1 SDR
* Cortex M7
  * examples running from TCM
  * use UART4 as debug console
* MIPI CSI (see Issues section)
  * Gray with Vision Components GmbH camera (Sensor OV9281)
  * Raw Bayer with Vision Components GmbH camera (Sensor IMX327)

## TODO

* Audio
  * Audio codec mic in not tested
* DSI
  * DSI to DP bridge
* MIKRO Bus
* SIM

## Important Notes

* UART4: needs ATF modification, to make it usable for linux. Primary used as
  debug UART for Cortex M7. Modification needed in file
  `plat/imx/imx8m/imx8mn/imx8mn_bl31_setup.c`. Disable assignment of UART4 -
  delete statement `	RDC_PDAPn(RDC_PDAP_UART4, D1R | D1W),` in rdc configuration
  table.
* MBa8Mx before REV.0300 is not supported.

## Known Issues

* Audio playback freezes complete system
* LVDS shows wrong colors on older Tianma display kit (HW issue on older
  display kit revisions)
* Mikrobus Modul RTC5 on ecspi1 don't answer
* Audio does not work after suspend / resume (clocking problem)
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

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mn-tqma8mqnl-mba8mx.dtb
  * imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33.dtb (LVDS support with TIANMA TM070JVHG33)
  * imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb (LVDS support with TIANMA TM070JVHG33
    plus Vision Components CSI camera with Sony IMX327)
  * imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb (LVDS support with TIANMA TM070JVHG33
    plus Vision Components CSI camera with OmniVision OV9281)
  * imx8mn-tqma8mqnl-mba8mx-rpmsg.dtb (CortexM / RPMSG Support)
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_spl\_uboot:  boot stream for UUU
* hello\_world.bin (Cortex M7 demo, UART4, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M7 demo, UART4, TCM)

## Boot Dip Switches

_Note:_

* S5 / S6: not used for TQMa8MxNL
* S9:2: BOOT\_MODE0
* S9:3: BOOT\_MODE1
* S9:1 and S9:4: not needed for booting
* S10: Board config
* X means position of DIP, - means don't care

### Board config

| DIP S10  | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| ON       | x | x | x | x |
| OFF      |   |   |   |   |

### BOOT\_MODE

BOOT Mode \[3:0\] is mapped to the following DIP Switches:

* 3: DIP Switch BOOT\_MODE 3 (TQMa8MxML ADAP)
* 2: DIP Switch BOOT\_MODE 2 (TQMa8MxML ADAP, inverted)
* 1: DIP Switch S9:3 (MBa8Mx, inverted)
* 0: DIP Switch S9:2 (MBa8Mx, inverted)

#### Boot from Fuses (needs boot fuses to be set)

BOOT\_MODE: 0000

| BOOT MODE<br>(ADAP) | 4 | 3 | 2 | 1 | MBa8Mx<br>S9 | 4 | 3 | 2 | 1 |
| ------------------- | - | - | - | - | ------------ | - | - | - | - |
| ON                  |   |   | x |   |              |   | x | x |   |
| OFF                 | - | x |   | - |              | - |   |   | - |

#### Serial Downloader

BOOT\_MODE: 0001

| BOOT MODE<br>(ADAP) | 4 | 3 | 2 | 1 | MBa8Mx<br>S9 | 4 | 3 | 2 | 1 |
| ------------------- | - | - | - | - | ------------ | - | - | - | - |
| ON                  |   |   | x |   |              |   | x |   |   |
| OFF                 | - | x |   | - |              | - |   | x | - |

#### e-MMC (USDHC3)

BOOT\_MODE: 0010

| BOOT MODE<br>(ADAP) | 4 | 3 | 2 | 1 | MBa8Mx<br>S9 | 4 | 3 | 2 | 1 |
| ------------------- | - | - | - | - | ------------ | - | - | - | - |
| ON                  |   |   | x |   |              |   |   | x |   |
| OFF                 | - | x |   | - |              | - | x |   | - |

#### SD Card (USDHC2)

BOOT\_MODE: 0011

| BOOT MODE<br>(ADAP) | 4 | 3 | 2 | 1 | MBa8Mx<br>S9 | 4 | 3 | 2 | 1 |
| ------------------- | - | - | - | - | ------------ | - | - | - | - |
| ON                  |   |   | x |   |              |   |   |   |   |
| OFF                 | - | x |   | - |              | - | x | x | - |

#### FlexSPI / 3B Read

BOOT\_MODE: 0110

| BOOT MODE<br>(ADAP) | 4 | 3 | 2 | 1 | MBa8Mx<br>S9 | 4 | 3 | 2 | 1 |
| ------------------- | - | - | - | - | ------------ | - | - | - | - |
| ON                  |   |   |   |   |              |   |   | x |   |
| OFF                 | - | x | x | - |              | - | x |   | - |

## Functional DIP Switches

_S7_

* 1/2: unused
* 3: UART2\_MUX\_CTRL
  * ON: UART3/UART_SYSC -> USB (X16)
  * OFF: UART3/UART_SYSC -> Pin head (X17)
  * BSP default: ON
* 4: UART1\_MUX\_CTRL
  * ON: UART1/UART2 -> USB (X15)
  * OFF: UART1/UART2 -> Pin head (X17)
  * BSP default: OFF

_S8_

* 1: TQMa8M\_SYS\_RST#
  * BSP default: OFF
* 2: TQMa8M\_ONOFF
  * BSP default: OFF
* 3: I2C\_ADDR\_SW (I2C Address of GPIO Expander D31)
  * BSP default: OFF
* 4: SPI\_MUX\_CTRL
  * ON: SPI1 Signals to X20 (MikroBus)
  * OFF: SPI1 Signals to X34
  * BSP default: OFF

_S9_

* 1: EN\_VCC\_FAN
* 2: BOOT\_MODE0
* 3: BOOT\_MODE1
* 4: DSI\_MUX\_CTL
  * ON: DSI to eDP bridge
  * OFF: DSI to LVDS bridge

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
# SPI-NOR @ FlexSPI, offset 0

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
UBOOT_CONFIG_tqma8mxnl = "mfgtool"
IMXBOOT_TARGETS_tqma8mxnl = "flash_spl_uboot"
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

*Note*: see known issue section above.

__Gray with Omnivision OV9281__

* Devicetree: `imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb`
* gstreamer example:

```
# configure
yavta -f Y8 -s 1280x800 -c20 /dev/video0
# grab to file
gst-launch-1.0 v4l2src device=/dev/video0 ! videorate ! video/x-raw,format=GRAY8,framerate=1/1 ! \
	jpegenc ! multifilesink location=test%d.jpg
# show live video
gst-launch-1.0 v4l2src device=/dev/video0 ! videoconvert ! \
	autovideosink -v sync=false
```

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb`
* gstreamer example:

```
# configure
yavta -f SRGGB10 -s 1280x720  /dev/video0

# show live video
gst-launch-1.0 v4l2src device=/dev/video0 force-aspect-ratio=false ! \
	video/x-bayer,format=rggb,bpp=10,width=1280,height=720,framerate=25/1 ! \
	bayer2rgbneon show-fps=t reduce-bpp=t ! autovideoconvert ! \
	autovideosink sync=false
```

### Cortex M7

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).
