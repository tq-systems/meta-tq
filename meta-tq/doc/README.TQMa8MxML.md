# TQMa8MxML

This README contains some useful information for TQMa8MxML on MBa8Mx REV.030x

[[_TOC_]]

## Variants

* TQMa8MQML REV.020x

## Version information for software components

See [here](./README.TQMa8.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

_MBa8x HW Rev.030x only_

* RAM configs:
  * 1 GiB
  * 2 GiB
* CPU
  * variants i.MX8MMD
  * variants i.MX8MMQ
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
* Cortex M4
  * env settings for starting from TCM
  * examples with UART4 as debug console

**TODO or not tested / supported**

* CPU variants i.MX8MMS and Lite

### Linux

* RAM configs:
  * 1 GiB
  * 2 GiB
* CPU
  * variants i.MX8MMD
  * variants i.MX8MMQ
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
* PCIe Slot on MBa8Mx (X36)
* SPI
  * 2 x via spidev in userland
* ENET (GigE via Phy on MBa8Mx)
* Audio
  * Codec Line In (X14)
  * Codec Line Out (X13)
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
* Cortex M4
  * examples running from TCM
  * use UART4 as debug console
* MIPI CSI
  * Gray with Vision Components GmbH camera (Sensor OV9281)
  * Raw Bayer with Vision Components GmbH camera (Sensor IMX327)
* PCIe
  * tested with several network cards

## TODO

* Audio
  * Audio codec mic in not tested
* DSI
  * DSI to DP bridge
* MIKRO Bus
* SIM
* VPU (test with h264 and vp8)
* PCIe
  * wifi not tested, adapter to mini PCIe needed

## Important Notes

* UART4: needs ATF modification, to make it usable for linux. Primary used as
  debug UART for Cortex M7. Modification is available with current ATF version.
* MBa8Mx before REV.0300 is not supported.

## Known Issues

* Default setting for `fdt_file` in u-boot does not match new naming scheme. See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
* USB hub disconnects after suspend
* LVDS shows wrong colors on older Tianma display kit (HW issue on older
  display kit revisions)
* Mikrobus Modul RTC5 on ecspi1 don't answer
* Bootstream for QSPI on FlexSPI: not buildable out of the box. U-Boot SPL needs different
  linker settings for SD / e-MMC and FlexSPI. Current recipes for boot stream generation
  can only use a single U-Boot config. See further notes under Bootable QSPI NOR
* Audio does not work after suspend / resume (clocking problem)
* MIPI CSI
  * driver stack is not completely v4l2-compliance test proof. The IOCTLS for format / resolution
    enumeration and query can return invalid / wrong values depending of the internal state
    of the driver stack. Please follow given examples for a working setup.
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mm-tqma8mqml-mba8mx.dtb
  * imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33.dtb (LVDS support with TIANMA TM070JVHG33)
  * imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb (LVDS support with TIANMA TM070JVHG33
    plus Vision Components CSI camera with Sony IMX327)
  * imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb (LVDS support with TIANMA TM070JVHG33
    plus Vision Components CSI camera with OmniVision OV9281)
  * imx8mm-tqma8mqml-mba8mx-rpmsg.dtb (CortexM / RPMSG Support)
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-fspi.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_spl\_uboot:  boot stream for UUU
* hello\_world.bin (Cortex M4 demo, UART4, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M4 demo, UART4, TCM)

## Boot DIP Switches

_Note:_

* S5: Boot Config\[0 .. 7\] (inverted)
* S6: Boot Config\[8 .. 15\] (inverted)
* S9:2: BOOT\_MODE0 (inverted)
* S9:3: BOOT\_MODE1 (inverted)
* S9:1 and S9:4: not needed for booting
* S10: Board config
* X means position of DIP, - means don't care

### Board config

| DIP S10  | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| ON       | x | x |   |   |
| OFF      |   |   | x | x |

### BOOT\_MODE

#### Boot from Fuses (needs boot fuses to be set)

BOOT\_MODE: 00b

| BOOT\_MODE |   | 0 | 1 |   |
| ---------- | - | - | - | - |
| DIP S9     | 1 | 2 | 3 | 4 |
| ON         |   | X | X |   |
| OFF        | - |   |   | - |

#### Serial Downloader

BOOT\_MODE: 01b

| BOOT\_MODE |   | 0 | 1 |   |
| ---------- | - | - | - | - |
| DIP S9     | 1 | 2 | 3 | 4 |
| ON         |   |   | X |   |
| OFF        | - | X |   | - |

#### Internal Boot (no boot fuses set, use boot config pins)

BOOT\_MODE: 10b

| BOOT\_MODE |   | 0 | 1 |   |
| ---------- | - | - | - | - |
| DIP S9     | 1 | 2 | 3 | 4 |
| ON         |   | X |   |   |
| OFF        | - |   | X | - |

##### SD Card (USDHC2)

BOOT\_MODE: Internal Boot

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |     |      |  x   |      |  x   |  x   |  x   |    |  x  |     |  x  |  x  |     |  x  |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |  x  |  x   |      |  x   |      |      |      |    |     |  x  |     |     |  x  |     |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[0\]      - 0 - reserved
* BOOT_CFG\[3:1\]    - 001 - SD speed mode (SDR25)
* BOOT_CFG\[4\]      - 1 - Bus width 4 Bit
* BOOT_CFG\[6:5\]    - 00 - reserved
* BOOT_CFG\[7\]      - 0 - Fast Boot
* BOOT_CFG\[8\]      - 0 - USDHC loopback clock source
* BOOT_CFG\[9\]      - 1 - Power cycle enable
* BOOT_CFG\[\11:10\] - 01 - USDHC2
* BOOT_CFG\[\15:12\] - 0001 - SD Card

##### e-MMC (USDHC3)

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |  x  |  x   |      |  x   |      |  x   |  x   |    |  x  |     |  x  |  x  |  x  |     |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |     |      |  x   |      |  x   |      |      |    |     |  x  |     |     |     |  x  |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[0\]      - 0 - USDHC2 IO VOLTAGE: 3.3 V
* BOOT_CFG\[1\]      - 1 - USDHC1 IO VOLTAGE: 1.8 V
* BOOT_CFG\[3:2\]    - 00 - MMC Speed Mode
* BOOT_CFG\[\6:4\]   - 010 - Bus width 8 Bit
* BOOT_CFG\[7\]      - 0 - Fast boot support
* BOOT_CFG\[8\]      - 0 - USDHC loopback clock through SD pad
* BOOT_CFG\[9\]      - 0 - eMMC reset enable
* BOOT_CFG\[\11:10\] - 10 - USDHC3
* BOOT_CFG\[\15:12\] - 0010 - MMC / e-MMC

##### FlexSPI

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |  x  |  x   |  x   |  x   |  x   |      |  x   |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |     |      |      |      |      |  x   |      |    |     |     |     |     |     |     |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[3:0\]    - 0000 - SPI FLASH Dummy Cycle (auto probed)
* BOOT_CFG\[5:4\]    - 00   - QuadSPI NOR
* BOOT_CFG\[7:6\]    - 00   - Hold time before read from device (500us)
* BOOT_CFG\[\10:8\]  - 000  - Flash Type (Device supports 3B read by default)
* BOOT_CFG\[11\]     - 0    - SPI FLASH Auto Probe (disable)
* BOOT_CFG\[14:12\]  - 100  - Boot device selection (Serial NOR)

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

write bootstream at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Bootable e-MMC

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

Boot from SD-Card and write bootstream at offset 33 kiB (0x8400) to e-MMC

Example for Linux:

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

Example for U-Boot:

```
# 33k -> 66 Blocks -> 0x42

tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 42 ${bsz}
```

### Bootable QSPI NOR

To build bootstream adapt yocto configuration, modify _local.conf_ or machine
config file:

```
UBOOT_CONFIG:tqma8mxml = "fspi"
IMXBOOT_TARGETS:tqma8mxml = "flash_evk_flexspi"
```

Rebuild boot stream:

```
bitbake imx-boot
```

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

Cortex M4 image: set env var `cm_image` to name of your Cortex M4 image,
provide the file via TFTP and update via `run update_cm_mmc`

_FLEXSPI_

Download bootstream from TFTP and update:

`run update_uboot_spi`

## Use UUU Tool

See [here](./README.TQMa8.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### MIPI-CSI

#### Vision Components GmbH cameras

*Note*: see known issue section above.

__Gray with Omnivision OV9281__

* Devicetree: `imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-ov9281.dtb`
* gstreamer example:

```
# grab to file
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
	videorate ! video/x-raw,format=GRAY8,framerate=1/1 ! jpegenc ! multifilesink location=test%d.jpg
# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
	videoconvert ! autovideosink -v sync=false
```

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mm-tqma8mqml-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb`
* gstreamer example:

```
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 force-aspect-ratio=false ! \
	video/x-bayer,format=rggb,bpp=12,width=1280,height=720,framerate=25/1 ! \
	bayer2rgbneon show-fps=t reduce-bpp=t ! autovideoconvert ! \
	autovideosink sync=false
```

### Cortex M4

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8MxML](https://support.tq-group.com/en/arm/tqma8mxml)