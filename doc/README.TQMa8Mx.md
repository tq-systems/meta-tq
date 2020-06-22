# TQMa8Mx

This README contains some useful information for TQMa8Mx on MBa8Mx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from rel\_imx\_4.14.98\_2.2.0\_ga / imx\_v2018.03\_4.14.98\_2.2.0\_ga

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from rel\_imx\_4.14.98\_2.2.0

## Supported Features

### U-Boot:

_MBa8x HW Rev.020x only_

* RAM configs 1GB / 2 GB
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
  * system EEPROM parsing
  * GPIO expander
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
* Bootaux / Cortex M4

### Linux:

* RAM configs 1GB / 2 GB
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
* QSPI NOR
  * Read with 1-1-4 SDR
  * PP / Erase with 1-1-1
  * see Known Issues
* ENET (GigE via Phy on MBa8Mx)
* Audio
  * via codec / audio out
  * HDMI audio (with pulse audio)
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 2.0 OTG only, Cable Detect, VBUS)
* HDMI
* GPU
* VPU (tested with h264 and vp8)
* DSI
  * LVDS on DCSS
  * LVDS on eLCDIF
* PCIe
  * mini-PCIe on MBa8Mx
  * PCIe (Slot)
* UART
  * console on UART3
  * 2 x UART via pin head or X15
  * 1 x UART via mikroBUS
* SPI
  * 2 x via spidev in userland

## TODO:

* DSI to DP bridge
* MIPI CSI
* MIKRO Bus
* SIM
* Audio codec line in / mic in
* Cortex M4 / RPMSG

## Known Issues

* LVDS over DSI @ eLCDIF with shifted display under weston
* LVDS shows wrong colors on Tianma display kit (HW issue on display)
* USB OTG / DRD
  * USB OTG OC not handled (polarity mismatch)
* QSPI limited to SDR (driver / chip compatibility)
* Mikrobus Modul RTC5 on ecspi1 don't answer

## SD-Card Boot

### Dip Switches

SW   12345678  
S6 : 11010111  
S5 : 11111111  
SW   1234  
S7 : 1110 (UART1/2 -> X17, UART3 -> X16)
S8 : 0010 (SD-Card -> X8)  
S9 : 0100 (Boot Mode 10b -> Internal Boot, DSI -> LVDS bridge)  
S10: 0000  

### Bootable SD-Card

Complete system image:

write *.wic Image to SD (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

write bootstream at offset 33 kiB (0x8400) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel`

## e-MMC Boot

### Dip Switches

SW   12345678  
S6 : 11111011  
S5 : 11111111  
SW   1234  
S7 : 1110 (UART1/2 -> X17, UART3 -> X16)  
S8 : 0010 (SD-Card -> X8)  
S9 : 0100 (Boot Mode 10b -> Internal Boot, DSI -> LVDS bridge)  
S10: 0000  

### Bootable e-MMC

write *.wic image to e-MMC (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

Boot from SD-Card and write bootstream at offset 33 kiB (0x8400) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

Example for U-Boot:

```
# 33k -> 66 Blocks -> 0x42

tftp <bootstream>
setexpr bsz $filesize + 1ff
setexpr bsz $bsz / 200
printenv bsz
mmc dev 0
mmc write 42 $bsz
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

To test audio using gstreamer / pulse audio:

```
pactl list sinks

pacmd set-default-sink 0
gst-launch-1.0 audiotestsrc ! pulsesink

pacmd set-default-sink 1
gst-launch-1.0 audiotestsrc ! pulsesink
```

### Audio Line In (codec)

```
arecord -f cd --duration=12 /tmp/test.wav &
speaker-test -D hw:0 -l 1 -c 2 -f 500 -t sine
```

### VPU support

```
pacmd set-default-sink 1
gst-play-1.0 /mnt/sd/tears_of_steel_1080p.webm
```
