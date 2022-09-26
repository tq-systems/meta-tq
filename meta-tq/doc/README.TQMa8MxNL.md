# TQMa8MxNL

This README contains some useful information for TQMa8MxNL on MBa8Mx REV.030x

[[_TOC_]]

## Variants

* TQMa8MQNL REV.020x
* TQMa8MDLNL REV.020x

## Version information for software components

See [here](./README.TQMa8.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

_MBa8x HW Rev.030x only_

| Feature                                          |                       |
| :----------------------------------------------- | :-------------------: |
| RAM configs                                      |        1 GiB          |
| CPU variants                                     |  i.MX8MNQ / i.MX8MNDL |
| Fuses / OCRAM                                    |          x            |
| speed grade / temperature grade detection        |          x            |
| UART (console on UART3)                          |          x            |
| **GPIO**                                         |                       |
| LED                                              |          x            |
| Button                                           |          x            |
| BOOT_CFG                                         |          x            |
| MUX                                              |          x            |
| **I2C**                                          |                       |
| GPIO expander                                    |          x            |
| system EEPROM parsing                            |          x            |
| **e-MMC / SD**                                   |                       |
| Read                                             |          x            |
| Write                                            |          x            |
| **Ethernet**                                     |                       |
| GigE via Phy on MBa8Mx                           |          x            |
| **Bootdevices**                                  |                       |
| SD-Card on USDHC2                                |          x            |
| e-MMC on USDHC3                                  |          x            |
| QSPI-NOR on FlexSPI                              |          x            |
| **USB**                                          |                       |
| USB Host (USB1 via hub 2.0)                      |          x            |
| USB Dual Role (USB0, 2.0)                        |          x            |
|   Cable Detect / ID                              |          x            |
|   switchable VBUS                                |          x            |
| **QSPI NOR**                                     |                       |
| Read with 1-1-1 SDR                              |          x            |
| PP / Erase with 1-1-1 SDR                        |          x            |
| **Cortex M7**                                    |                       |
| env settings for starting from TCM               |          x            |
| examples with UART4 as debug console             |          x            |

**TODO or not tested / supported**

* RAM 2 GB
* CPU variants i.MX8MNS

### Linux

| Feature                                          |                      |
| :----------------------------------------------- | :------------------: |
| RAM configs                                      |        1 GiB         |
| CPU variants                                     | i.MX8MNQ / i.MX8MNDL |
| Fuses / OCRAM                                    |          x           |
| speed grade / temperature grade detection        |          x           |
| DVFS (CPU overdrive mode)                        |          x           |
| suspend (deep / s2idle)                          |          x           |
| **UART**                                         |                      |
| console on UART3 (via USB / UART converter)      |          x           |
| 2 x UART via pin head or X15                     |          x           |
| **GPIO**                                         |                      |
| LED                                              |          x           |
| Button                                           |          x           |
| HOG                                              |          x           |
| **I2C**                                          |                      |
| EEPROMs                                          |          x           |
| PMIC                                             |          x           |
| GPIO expanders                                   |          x           |
| RTC (for wakealarm see HowTo below)              |          x           |
| Temperature Sensors                              |          x           |
| **ENET**                                         |                      |
| GigE via Phy on MBa8Mx                           |          x           |
| **USB**                                          |                      |
| USB 2.0 Host / Hub                               |          x           |
| USB DRD (USB 2.0 DR only, Cable Detect, VBUS)    |          x           |
| **PWM**                                          |                      |
| PWM Buzzer                                       |          x           |
| LVDS Backlight                                   |          x           |
| **QSPI NOR**                                     |                      |
| Read with 1-1-4 SDR                              |          x           |
| PP / Erase with 1-1-1 SDR                        |          x           |
| **GRAPHICS**                                     |                      |
| GPU                                              |          x           |
| **Display**                                      |                      |
| DSI to LVDS bridge                               |          x           |
| **Audio**                                        |                      |
| Codec (Line IN X14 / Line OUT X13)               |          x           |
| **SPI**                                          |                      |
| 2 x via spidev in userland                       |          x           |
| **Cortex M7**                                    |                      |
| examples running from TCM                        |          x           |
| use UART4 as debug console                       |          x           |
| **MIPI CSI (see Issues section)**                |                      |
| Gray with Vision Components GmbH camera (Sensor OV9281) |         x     |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |    x     |

## TODO

* MIKRO Bus
* SIM
* Audio
  * Audio codec mic in not tested
* DSI
  * DSI to DP bridge

## Important Notes

* UART4: needs ATF modification, to make it usable for linux. Primary used as
  debug UART for Cortex M7. Modification needed in file
  `plat/imx/imx8m/imx8mn/imx8mn_bl31_setup.c`. Disable assignment of UART4 -
  delete statement `	RDC_PDAPn(RDC_PDAP_UART4, D1R | D1W),` in rdc configuration
  table.
* MBa8Mx before REV.0300 is not supported.

## Known Issues

* Default setting for `fdt_file` in u-boot does not match new naming scheme. See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
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
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-mfgtool.bin-flash\_spl\_uboot:  boot stream for UUU
* hello\_world.bin (Cortex M7 demo, UART4, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M7 demo, UART4, TCM)

## Boot DIP Switches

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

See [here](./README.TQMa8.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### RTC wakeup alarm
By default the `RTC_EVENT#` is not connect to any input, but left open on `X4` pin 11 on TQMa8MxML-ADAP. In order to use the RTC IRQ feature this output has to be connected to a GPIO input with IRQ support, e.g. `GPIO01_06` on `X17` pin 9 on MBa8Mx.

#### DT changes
For the example from above the following DT change has to be applied in order to support RTC IRQ support.

```diff
diff --git a/arch/arm64/boot/dts/freescale/imx8mn-tqma8mqnl-mba8mx.dts b/arch/arm64/boot/dts/freescale/imx8mn-tqma8mqnl-mba8mx.dts
index e166e34c3cdf..a1a24e20b17e 100644
--- a/arch/arm64/boot/dts/freescale/imx8mn-tqma8mqnl-mba8mx.dts
+++ b/arch/arm64/boot/dts/freescale/imx8mn-tqma8mqnl-mba8mx.dts
@@ -63,6 +63,14 @@ expander2: gpio@27 {
        };
 };
 
+&pcf85063 {
+       /* RTC_EVENT# is connected on MBa8Mx GPIO01_IO06 */
+       pinctrl-names = "default";
+       pinctrl-0 = <&pinctrl_rtc>;
+       interrupt-parent = <&gpio1>;
+       interrupts = <6 IRQ_TYPE_EDGE_FALLING>;
+};
+
 &sai3 {
        assigned-clocks = <&clk IMX8MN_CLK_SAI3>;
        assigned-clock-parents = <&clk IMX8MN_AUDIO_PLL1_OUT>;
@@ -160,6 +168,10 @@ pinctrl_pwm4: pwm4grp {
                fsl,pins = <MX8MN_IOMUXC_GPIO1_IO15_PWM4_OUT            0x14>;
        };
 
+       pinctrl_rtc: rtcgrp {
+               fsl,pins = <MX8MN_IOMUXC_GPIO1_IO06_GPIO1_IO6           0x1c0>;
+       };
+
        pinctrl_sai3: sai3grp {
                fsl,pins = <MX8MN_IOMUXC_SAI3_MCLK_SAI3_MCLK            0x94>,
                           <MX8MN_IOMUXC_SAI3_RXC_SAI3_RX_BCLK          0x94>,
```

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
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! videorate ! video/x-raw,format=GRAY8,framerate=1/1 ! \
	jpegenc ! multifilesink location=test%d.jpg
# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 ! videoconvert ! \
	autovideosink -v sync=false
```

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mn-tqma8mqnl-mba8mx-lcdif-lvds-tm070jvhg33-imx327.dtb`
* gstreamer example:

```
# configure
yavta -f SRGGB10 -s 1280x720  /dev/video0

# show live video
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 v4l2src device=/dev/video0 force-aspect-ratio=false ! \
	video/x-bayer,format=rggb,bpp=10,width=1280,height=720,framerate=25/1 ! \
	bayer2rgbneon show-fps=t reduce-bpp=t ! autovideosink sync=false
```

### Cortex M7

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).

### U-Boot: switch between using of USB OTG (X19) and USB HUB (X6)

|               | SEL_USB_HUB_B (GPIO2_1) |
| ------------- | ----------------------- |
| USB OTG (X19) | 0 (default)             |
| USB HUB (X6)  | 1                       |

Change by switching from GPIO2_1 (SEL_USB_HUB_B)
`gpio toggle GPIO2_1` followed by `usb reset `

## Support Wiki

See [TQ Embedded Wiki for TQMa8MxNL](https://support.tq-group.com/en/arm/tqma8mxnl)
