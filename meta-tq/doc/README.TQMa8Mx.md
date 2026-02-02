# TQMa8Mx

This README contains some useful information for TQMa8Mx on MBa8Mx

[[_TOC_]]

## Variants

* TQMa8MQ / TQMa8MD / TQMa8MQL REV.020x 1GiB LPDDR4
* TQMa8MQ / TQMa8MD / TQMa8MQL REV.020x 2GiB LPDDR4
* TQMa8MQ / TQMa8MD / TQMa8MQL REV.020x 4GiB LPDDR4

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

_MBa8x HW Rev.020x/30x only / TQMa8Mx HW Rev.020x only_

| Feature                                          |                    |
| :----------------------------------------------- | :----------------: |
| RAM configs                                      |      1,2,4 GiB     |
| CPU variants                                     | i.MX8MQ / i.MX8MQL |
| Fuses / OCRAM                                    |         x          |
| speed grade / temperature grade detection        |         x          |
| UART (console on UART3)                          |         x          |
| **GPIO**                                         |                    |
| LED                                              |         x          |
| Button                                           |         x          |
| BOOT_CFG                                         |         x          |
| MUX                                              |         x          |
| **I2C**                                          |                    |
| GPIO expander                                    |         x          |
| system EEPROM parsing                            |         x          |
| **eMMC / SD**                                    |                    |
| Read                                             |         x          |
| Write                                            |         x          |
| **Ethernet**                                     |                    |
| GigE via Phy on MBa8Mx                           |         x          |
| **Bootdevices**                                  |                    |
| SD-Card on USDHC2                                |         x          |
| eMMC on USDHC1                                   |         x          |
| **Display**                                      |                    |
| HDMI (fixed resolution)                          |         x          |
| **USB**                                          |                    |
| USB Host (USB1 via hub 2.0 and 3.0)              |         x          |
| USB DRD (USB 2.0 Cable Detect, VBUS)             |         x          |
| **QSPI NOR**                                     |                    |
| 4 byte adressing, SPI mode                       |         x          |
| see Known Issues                                 |                    |
| **Cortex M4**                                    |                    |
| env settings for starting from TCM               |         x          |
| examples with UART4 as debug console             |         x          |


### Linux

_MBa8x HW Rev.020x/30x only_

| Feature                                                      |      fslc-6.1      |      fslc-6.6      |    inux-tq-6.1     |
| :----------------------------------------------------------- | :----------------: | :----------------: | :----------------: |
| RAM configs                                                  |     1,2,4 GiB      |     1,2,4 GiB      |     1,2,4 GiB      |
| CPU variants                                                 | i.MX8MQ / i.MX8MQL | i.MX8MQ / i.MX8MQL | i.MX8MQ / i.MX8MQL |
| Fuses / OCRAM                                                |         x          |         x          |                    |
| speed grade / temperature grade detection                    |         x          |         x          |                    |
| DVFS (CPU overdrive mode)                                    |         x          |         x          |                    |
| suspend (deep / s2idle)                                      |         x          |         x          |                    |
| **UART**                                                     |                    |                    |                    |
| console on UART3 (via USB / UART converter)                  |         x          |         x          |         x          |
| 2 x UART via pin head or X15                                 |         x          |         x          |         x          |
| 1 x UART via mikroBUS                                        |                    |                    |                    |
| **GPIO**                                                     |                    |                    |                    |
| LED                                                          |         x          |         x          |         x          |
| Button                                                       |         x          |         x          |         x          |
| HOG                                                          |         x          |         x          |                    |
| **I2C**                                                      |                    |                    |                    |
| EEPROMs                                                      |         x          |         x          |         x          |
| PMIC                                                         |         x          |         x          |         x          |
| GPIO expanders                                               |         x          |         x          |         x          |
| RTC (with wakealarm)                                         |         x          |         x          |         x          |
| Temperature Sensors                                          |         x          |         x          |         x          |
| **ENET**                                                     |                    |                    |                    |
| GigE via Phy on MBa8Mx                                       |         x          |         x          |         x          |
| **USB**                                                      |                    |                    |                    |
| USB 3.0 Host / Hub                                           |         x          |         x          |         x          |
| USB DRD (USB 2.0 DR only, Cable Detect, VBUS)                |         x          |         x          |         x          |
| **QSPI NOR**                                                 |                    |                    |                    |
| Read with 1-1-4 SDR                                          |         x          |         x          |         x          |
| PP / Erase with 1-1-1 SDR                                    |         x          |         x          |         x          |
| **GRAPHICS**                                                 |                    |                    |                    |
| GPU                                                          |         x          |         x          |         x          |
| VPU                                                          |         x          |         x          |                    |
| **Display**                                                  |                    |                    |                    |
| LVDS on DCSS                                                 |         x          |         x          |                    |
| LVDS on eLCDIF                                               |         x          |         x          |                    |
| HDMI                                                         |         x          |         x          |                    |
| **Audio**                                                    |                    |                    |                    |
| HDMI                                                         |         x          |         x          |                    |
| Codec (Line IN / Line OUT)                                   |         x          |         x          |         x          |
| **PCIe**                                                     |                    |                    |                    |
| mini-PCIe on MBa8Mx                                          |         x          |         x          |         x          |
| PCIe slot with Network Card                                  |         x          |         x          |         x          |
| **SPI**                                                      |                    |                    |                    |
| 2 x via spidev in userland                                   |         x          |         x          |                    |
| **Cortex M4**                                                |                    |                    |                    |
| examples running from TCM                                    |         x          |         x          |                    |
| use UART4 as debug console                                   |         x          |         x          |                    |
| **MIPI CSI (see Issues section)**                            |                    |                    |                    |
| Gray with Vision Components GmbH camera (Sensor OV9281)      |         x          |         x          |                    |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |                    |         x          |                    |

## TODO

* MIKRO Bus
* SIM
* QSPI NOR
  * see Known Issues
* DSI
  * DSI to DP bridge
* HDMI ARC is untested

## Known Issues

* Linux: operating points for DDR controller missing in device tree.
  running at lower DDR frequencies does not work in this version of BSP.
* Default setting for `fdt_file` in u-boot from older BSP version does
  not match kernel naming scheme since linux 5.10. Use U-Boot from this
  BSP release or see [Build Artefacts](#artefacts) for complete
  list of supported Device Tree files.
* LVDS shows wrong colors on older Tianma display kit (HW issue on older
  display kit revisions)
* USB OTG / DRD
  * USB OTG OC not handled for host role
  * USB OTG: only host is working in U-Boot
  * U-Boot: some USB stick types are not working correctly in U-Boot.
    After `usb reset` they may fail to enumerate correctly or causing
    a system reset during enumeration with errors like:
    ```
    WARN halted endpoint, queueing URB anyway.
    Unexpected XHCI event TRB, skipping... (fbf120f0 00000000 13000000 03008401)
    BUG at drivers/usb/host/xhci-ring.c:496/abort_td()!
    BUG!
    �esetting ...
    ```
* QSPI limited to SDR (driver / chip compatibility)
* Mikrobus Modul RTC5 on ecspi1 don't answer
* MIPI CSI
  * driver stack is not completely v4l2-compliance test proof. The IOCTLS for format / resolution
    enumeration and query can return invalid / wrong values depending of the internal state
    of the driver stack. Please follow given examples for a working setup.
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer
  * MIPI CSI does not work reliably. It may work, it may fail or even lockup the system
* When using HDMI the default Audio device changes to HDMI output.
  For using DAI codec `aplay` requires the parameter `-Dsysdefault:CARD=tqmtlv320aic32`
* The HDMI audio device has to be selected explicitely by passing `-Dsysdefault:CARD=imxaudiohdmi` to `aplay` & friends
* HDMI: When using large displays, module variants with> = 2 GiB RAM are recommended.
  It is known that some use cases will not work with less memory. Like Weston on a 4K monitor
* Note: GPU temperature observation uses CPU thermal-zone! Playing 4K Videos on 4K Display will raise the thermal-zone (CPU) temperature >80°C, reducing VPU clock. Apparently there is no dedicated VPU sensor
* PCIe driver causes several warnings during suspend
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.
* DT-Overlays `imx8mq-tqma8mq-mba8mx-dcss-hdmi.dtso` and `imx8mq-tqma8mq-mba8mx-dcss-lvds.dtso` are mutual exlusive.
  * Only one display can be used by DCSS at the same time.

## Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * imx8mq-tqma8mq-mba8mx.dtb
  * imx8mq-tqma8mq-mba8mx-dcss-hdmi.dtb (HDMI support)
  * imx8mq-tqma8mq-mba8mx-dcss-hdmi-imx327.dtb (HDMI support plus Vision Components CSI camera
    with Sony IMX327)
  * imx8mq-tqma8mq-mba8mx-dcss-hdmi-imx327-csi2.dtb (HDMI support plus Vision Components CSI camera
    with Sony IMX327 on CSI-2 channel)
  * imx8mq-tqma8mq-mba8mx-dcss-hdmi-ov9281.dtb (HDMI support plus Vision Components CSI camera
    with OmniVision OV9281)
  * imx8mq-tqma8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb (LVDS support over LCDIF with TIANMA TM070JVHG33)
  * imx8mq-tqma8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb (LVDS support over DCSS with TIANMA TM070JVHG33)
  * imx8mq-tqma8mq-mba8mx-rpmsg.dtb (CortexM / RPMSG Support)
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_hdmi\_spl\_uboot: boot stream for SD / eMMC
* imx-boot-${MACHINE}-uuu.bin-flash\_spl\_uboot: boot stream for UUU
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

| DIP S10 | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   |   |   |
| OFF     | X | X | X | X |

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

##### SD Card

BOOT\_MODE: Internal Boot

*Attention:* Differences from MBa8Mx REV.020x to MBa8Mx REV.030x

###### MBa8Mx REV.020x

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |  x  |      |  x   |      |  x   |  x   |  x   |    |  x  |     |  x  |  x  |     |  x  |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |     |  x   |      |  x   |      |      |      |    |     |  x  |     |     |  x  |     |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[0\] - 0 - reserved
* BOOT_CFG\[3:1\] - 001 - SD speed mode (SDR25)
* BOOT_CFG\[4\] - 1 - Bus width 4 Bit
* BOOT_CFG\[6:5\] - 00 - reserved
* BOOT_CFG\[7\] - 0 - Fast Boot
* BOOT_CFG\[8\] - 0 - USDHC loopback clock source
* BOOT_CFG\[9\] - 0 - Power cycle enable
* BOOT_CFG\[\11:10\] - 01 - USDHC2
* BOOT_CFG\[\15:12\] - 0001 - SD Card

###### MBa8Mx REV.030x

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |     |      |  x   |      |  x   |  x   |  x   |    |  x  |     |  x  |  x  |     |  x  |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |  x  |  x   |      |  x   |      |      |      |    |     |  x  |     |     |  x  |     |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[0\] - 0 - reserved
* BOOT_CFG\[3:1\] - 001 - SD speed mode (SDR25)
* BOOT_CFG\[4\] - 1 - Bus width 4 Bit
* BOOT_CFG\[6:5\] - 00 - reserved
* BOOT_CFG\[7\] - 0 - Fast Boot
* BOOT_CFG\[8\] - 0 - USDHC loopback clock source
* BOOT_CFG\[9\] - 1 - Power cycle enable
* BOOT_CFG\[\11:10\] - 01 - USDHC2
* BOOT_CFG\[\15:12\] - 0001 - SD Card

##### eMMC

BOOT\_MODE: Internal Boot

|         |  S6  |     |      |      |      |      |      |      | S5 |     |     |     |     |     |     |     |     | S9 |     |     |     |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| BOOTCFG |  8   |  9  |  10  |  11  |  12  |  13  |  14  |  15  |    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |    |     |     |     |     |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |
| ON      |  x   |  x  |  x   |  x   |  x   |      |  x   |  x   |    |  x  |     |  x  |  x  |  x  |     |  x  |  x  |    |     |  x  |     |     |
| OFF     |      |     |      |      |      |  x   |      |      |    |     |  x  |     |     |     |  x  |     |     |    |  -  |     |  x  |  -  |

* BOOT_CFG\[0\] - 0 - USDHC2 IO VOLTAGE: 3.3 V
* BOOT_CFG\[1\] - 1 - USDHC1 IO VOLTAGE: 1.8 V
* BOOT_CFG\[3:2\] - 00 - MMC Speed Mode
* BOOT_CFG\[\6:4\] - 010 - Bus width 8 Bit
* BOOT_CFG\[7\] - 0 - Fast boot support
* BOOT_CFG\[8\] - 0 - USDHC loopback clock through SD pad
* BOOT_CFG\[9\] - 0 - eMMC reset enable
* BOOT_CFG\[\11:10\] - 00 - USDHC1
* BOOT_CFG\[\15:12\] - 0010 - MMC / eMMC

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

*Attention:* Differences from MBa8Mx REV.020x to MBa8Mx REV.030x

* 1: TQMa8M\_SYS\_RST#
  * BSP default: OFF
* 2: TQMa8M\_ONOFF
  * BSP default: OFF
* 3: SD\_MUX\_CTRL (MBa8Mx REV.020x)
  * ON: SD Signals to X8 (Micro SD Slot)
  * OFF: SD Signals to X17
  * BSP default: ON
* 3: I2C\_ADDR\_SW (MBa8Mx REV.030x) (I2C Address of GPIO Expander D31)
  * BSP default: ON
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

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Bootable eMMC

To create a bootable eMMC with complete system image:

write *.wic image to eMMC (offset 0)

To create a bootable eMMC with boot stream only (file name see above)

Boot from SD-Card and write bootstream at offset 33 kiB (0x8400) to eMMC

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

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
eMMC.

_U-Boot environment variables_

* `uboot`: name of bootstream image (default = bootstream.bin)
* `mmcdev`: 0 for eMMC, 1 for SD-Card (automatically generated,
  can be overwritten)
  `mmcpart`: partition number for kernel and devicetree (default = 1)
  `mmcpath`: path to kernel and device tree (default = /)
* `fdt_file`: device tree blob,
* `image`: kernel image,

_SD / eMMC_

Download bootstream from TFTP and update:

`run update_uboot`

Download device tree blob from TFTP and update:

`run update_fdt`

Download kernel image from TFTP and update:

`run update_kernel`

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

**Note**: On this platform `rtc0` is the I2C RTC on SoM and `rtc1` is the RTC in CPU SNVS domain

### Using GPIOs for wakeup

Send Linux to sleep mode and press one of the gpio buttons S\[1,2,3\] afterwards:

```
echo mem > /sys/power/state
echo freeze > /sys/power/state
```

### Audio output

To test audio output using alsa:

```
speaker-test -D hw:<n> -l 1 -c 2 -f 500 -t sine
```

To test audio using gstreamer / alsa:

```
WAYLAND_DISPLAY=/run/wayland-0 gst-launch-1.0 audiotestsrc ! alsasink
```

### Audio Line In (codec)

```
arecord -f cd --duration=12 /tmp/test.wav &
speaker-test -D hw:0 -l 1 -c 2 -f 500 -t sine
```

### VPU support

```
WAYLAND_DISPLAY=/run/wayland-0 gst-play-1.0 /mnt/sd/tears_of_steel_1080p.webm
```

Add `--audiosink='alsasink device=hw:1'` for selecting a specific audio device

### MIPI-CSI

*Note*: see known issue section above.

#### Vision Components GmbH cameras

__Gray with Omnivision OV9281__

* Devicetree: `imx8mq-tqma8mq-mba8mx-hdmi-ov9281.dtb`
* gstreamer example:

```
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
  videoconvert ! waylandsink sync=false
```

__Raw Bayer with Sony IMX327__

* Devicetree: `imx8mq-tqma8mq-mba8mx-hdmi-imx327.dtb`
* gstreamer example:

```
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1280,height=720 ! \
  bayer2rgb ! waylandsink sync=false
```

### Cortex M4

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artefacts section](#build-artefacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8Mx](https://support.tq-group.com/en/arm/tqma8mx)
