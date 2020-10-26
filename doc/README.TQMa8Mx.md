# TQMa8Mx

This README contains some useful information for TQMa8Mx on MBa8Mx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from lf-5.4.y-1.0.0

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from lf-5.4.y-1.0.0

## Supported Features

### U-Boot:

_MBa8x HW Rev.020x/30x only_

* RAM configs 1 GiB / 2 GiB / 4 GiB
* CPU variants i.MX8MQ / i.MX8MQL
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
* Bootdevices: e-MMC / SD-Card
* HDMI (fixed resolution)
* QSPI NOR
  * 4 byte adressing, SPI mode
  * see Known Issues
* USB Host (USB1 via hub 2.0 and 3.0)
* USB Dual Role (USB0, 2.0 host only - hard coded in driver)
  * Cable Detect / ID
  * switchable VBUS
* Cortex M4
  * env settings for starting from TCM
  * examples with UART4 as debug console

### Linux:

_MBa8x HW Rev.020x/30x only_

* RAM configs 1 GiB / 2 GiB / 4 GiB
* CPU variants i.MX8MQ / i.MX8MQL
* speed grade / temperature grade detection
* DVFS (CPU overdrive mode)
* suspend (deep / s2idle)
* I2C
  * Temperature Sensors
  * RTC (with wakealarm)
  * EEPROMS
  * GPIO expanders
* GPIO
  * LED
  * Button
  * HOG
* UART
  * console on UART3
  * 2 x UART via pin head or X15
  * 1 x UART via mikroBUS
* SPI
  * 2 x via spidev in userland
* ENET (GigE via Phy on MBa8Mx)
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 2.0 OTG only, Cable Detect, VBUS)
* DSI
  * LVDS on DCSS
  * LVDS on eLCDIF
* PCIe
  * mini-PCIe on MBa8Mx
  * PCIe (Slot)
* Audio
  * Codec Line In (X14)
  * Codec Line Out (X13)
* Cortex M4
  * examples running from TCM
  * use UART4 as debug console
* QSPI NOR
  * Read with 1-1-4 SDR
  * PP / Erase with 1-1-1
* GPU
* VPU
* HDMI

## TODO:

* MIPI CSI
* MIKRO Bus
* SIM
* QSPI NOR
  * see Known Issues
* Audio
  * HDMI audio not tested
  * Audio codec mic in not tested
* DSI
  * DSI to DP bridge

## Known Issues

* LVDS shows wrong colors on older Tianma display kit (HW issue on display)
* USB OTG / DRD
  * USB OTG OC not handled for host role
  * USB OTG: only host is working in U-Boot
  * USB OTG: some USB 3 memory sticks not working in U-Boot
* QSPI limited to SDR (driver / chip compatibility)
* Mikrobus Modul RTC5 on ecspi1 don't answer

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mq-mba8mx.dtb
  * imx8mq-mba8mx-hdmi.dtb
  * imx8mq-mba8mx-lcdif-lvds-tm070jvhg33.dtb
  * imx8mq-mba8mx-dcss-lvds-tm070jvhg33.dtb
  * imx8mq-mba8mx-rpmsg.dtb
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin: boot stream for SD / e-MMC
* hello\_world.bin (Cortex M4 demo, UART4, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M4 demo, UART4, TCM)

## Boot Dip Switches

_Note:_

* S5 / S6: Boot Config (inverted)
* S9:2: BOOT\_MODE0 (inverted)
* S9:3: BOOT\_MODE1 (inverted)
* S9:1 and S9:4: not needed for booting
* S10: Board config
* X means position of DIP, - means don't care

_Board config_

```
	S10
DIP 	1 2 3 4
ON 	       
OFF 	X X X X
```

_BOOT\_MODE_

* Boot from Fuses (needs boot fuses to be set)

```
	S9
DIP 	1 2 3 4
ON 	       
OFF 	- X X -
```

* Serial Downloader

```
	S9
DIP 	1 2 3 4
ON 	    X  
OFF 	- X   -
```

* Internal Boot (no boot fuses set, use boot config pins)

```
	S9
DIP 	1 2 3 4
ON 	  X    
OFF 	-   X -
```

_SD Card_

BOOT\_MODE: Internal Boot

*Attention:* Differences from MBa8Mx REV.020x to MBa8Mx REV.030x

MBa8Mx REV.020x:

```
	S6			S5			S9
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4
ON 	X X   X   X X X		X X X X X X X X 	  X    
OFF 	    X   X      		               		-   X -
```

MBa8Mx REV.030x:

```
	S6			S5			S9
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4
ON 	X     X   X X X		X X X X X X X X 	  X    
OFF 	  X X   X      		               		-   X -
```

_e-MMC_

BOOT\_MODE: Internal Boot

```
	S6			S5			S9
DIP 	1 2 3 4 5 6 7 8		1 2 3 4 5 6 7 8		1 2 3 4
ON 	X X X X X   X X		X X X X X X X X 	  X    
OFF 	          X    		               		-   X -
```

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

## SD-Card Boot

### Bootable SD-Card

Complete system image:

write *.wic Image to SD (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-${MACHINE}-sd.bin`

write bootstream at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-${MACHINE}-sd.bin of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

Cortex M4 image: set env var `cm_image` to name of your Cortex M4 image,
provide the file via TFTP and update via `run update_cm_mmc`

## e-MMC Boot

### Bootable e-MMC

write *.wic image to e-MMC (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

Boot from SD-Card and write bootstream at offset 33 kiB (0x8400) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-${MACHINE}-sd.bin of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

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

### Update components via U-Boot

To update components on boot media following u-boot environment scripts are
prepared. These can be used to update the items using a network connection.

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

Cortex M4 image: set env var `cm_image` to name of your Cortex M4 image,
provide the file via TFTP and update via `run update_cm_mmc`

## Howto

### Sleep Modes

Read supported sleep modes:

```
cat /sys/power/state
```

Test sleepmode and wakeup:

Use rtc\[0,1\] to wakeup after 20 seconds:

```
RTC=rtc[0,1]
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc/${RTC}/wakealarm
echo mem > /sys/power/state
```

Send linux to sleep mode and press one of the gpio buttons S\[1,2,3\] afterwards:

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
gst-launch-1.0 audiotestsrc ! alsasink
```

### Audio Line In (codec)

```
arecord -f cd --duration=12 /tmp/test.wav &
speaker-test -D hw:0 -l 1 -c 2 -f 500 -t sine
```

### VPU support

```
gst-play-1.0 /mnt/sd/tears_of_steel_1080p.webm
```

### Cortex M4

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with
115200 8N1.

To start a demo stored on SD / e-MMC from U-Boot:

```
setenv fdt_file imx8mq-mba8mx-rpmsg.dtb
setenv cm_image <demo>
run boot_cm_mmc
```

To connect from running linux to rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```
