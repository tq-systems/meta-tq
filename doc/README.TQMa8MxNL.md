# TQMa8MxNL

This README contains some useful information for TQMa8MxNL on MBa8Mx

## Versions

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from lf-5.4.y-1.0.0

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from lf-5.4.y-1.0.0

## Supported Features

### U-Boot:

_MBa8x HW Rev.020x only_

* RAM configs: 1 GiB
* CPU variants i.MX8MNQ
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
* e-MMC / SD
  * Read
  * Write
* ENET (GigE via Phy on MBa8Mx)
* Bootdevices:
  * SD-Card on USDHC2
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
* e-MMC Boot (needs fusing to support USDHC1)
* CPU variants i.MX8MND/S and Lite

### Linux:

* RAM configs 1 GiB
* CPU variants i.MX8MNQ
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
* Cortex M7
  * examples running from TCM
  * use UART4 as debug console

## TODO:

* Audio
  * Audio codec mic in
* DSI
  * DSI to DP bridge
* MIPI CSI
* MIKRO Bus
* SIM

## Known Issues

* Mikrobus Modul RTC5 on ecspi1 don't answer
* UART4: needs ATF modification, to make it usable for linux. Primary used as
  debug UART for Cortex M7

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mn-mba8mx.dtb
  * imx8mn-mba8mx-lcdif-lvds-tm070jvhg33.dtb
  * imx8mn-mba8mx-rpmsg.dtb
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin: boot stream for SD / e-MMC

## Boot Dip Switches

_Note:_

* S5 / S6: not used for TQMa8MxNL
* S9:2: BOOT\_MODE0
* S9:3: BOOT\_MODE1
* S9:1 and S9:4: not needed for booting
* S10: Board config
* X means position of DIP, - means don't care

_Board config_

```
	S10
DIP 	1 2 3 4
ON 	X X    
OFF 	    X X
```

_BOOT\_MODE_

BOOT Mode \[3:0\] is mapped to the following DIP Switches:

* 3: DIP Switch BOOT\_MODE 3 (TQMa8MxML ADAP)
* 2: DIP Switch BOOT\_MODE 2 (TQMa8MxML ADAP)
* 1: DIP Switch S9:2 (MBa8Mx)
* 0: DIP Switch S9:1 (MBa8Mx)

Boot from Fuses (needs boot fuses to be set)

BOOT\_MODE: 0000

```
	BOOT MODE (ADAP)	MBa8Mx
				S9
DIP 	3 2			4 3 2 1
ON 				       
OFF 	X X			- X X -
```

Serial Downloader

BOOT\_MODE: 0001

```
	BOOT MODE (ADAP)	MBa8Mx
				S9
DIP 	3 2			4 3 2 1
ON 				    X  
OFF 	X X			- X   -
```

e-MMC (needs fuses to be set to use USDHC1)

BOOT\_MODE: 0010

```
	BOOT MODE (ADAP)	MBa8Mx
				S9
DIP 	3 2			4 3 2 1
ON 				  X    
OFF 	X X			-   X -
```

SD Card (USDHC2)

BOOT\_MODE: 0011

```
	BOOT MODE (ADAP)	MBa8Mx
				S9
DIP 	3 2			4 3 2 1
ON 				  X X  
OFF 	X X			-     -
```

FlexSPI / 3B Read

BOOT\_MODE: 0110

```
	BOOT MODE (ADAP)	MBa8Mx
				S9
DIP 	3 2			4 3 2 1
ON 	  X			  X    
OFF 	X  			-   X -
```

## Functional DIP Switches

_S7_

* 1/2: unused
* 3: UART2\_MUX\_CTRL
  * ON: UART3/UART_SYSC -> USB (X16)
  * OFF: UART3/UART_SYSC -> Pin head (X17)
  * BSP default: ON
* 4: UART1\_MUX\_CTRL
  * ON: UART1/UART2 -> USB (X16)
  * OFF: UART1/UART2 -> Pin head (X17)
  * BSP default: OFF

_S8_

* 1: TQMa8M\_SYS\_RST#
  * BSP default: OFF
* 2: TQMa8M\_ONOFF
  * BSP default: OFF
* 3: SD\_MUX\_CTRL
  * ON: SD Signals to X8 (Micro SD Slot)
  * OFF: SD Signals to X17
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

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-${MACHINE}-sd.bin of=/dev/sd<x> bs=1k seek=33 conv=fsync`

### Update components via U-Boot

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot_mmc`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt_mmc`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel_mmc`

## e-MMC Boot

### Bootable e-MMC

write *.wic image to e-MMC (offset 0)

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin`

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-${MACHINE}-sd.bin of=/dev/mmcblk0 bs=1k seek=33 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 Blocks -> 0x40

tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

### Update components via U-Boot

To update components on boot media following u-boot environment scripts are
prepared. These can be used to update the items using a network connection.

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot_mmc`

Device tree blob: set env var `fdt_file` to name of your device tree blob,
provide the blob via TFTP and update via `run update_fdt_mmc`

Linux kernel: set env var `image` to name of your kernel image,
provide the file via TFTP and update via `run update_kernel_mmc`

## FlexSPI / QSPI Boot

### Bootable QSPI NOR

Write bootstream only:

Bootstreams built using yocto are named `imx-boot-<module>-<baseboard>-sd.bin-flash_evk_flexspi`
and are padded to be written at offset 0x0 of QSPI-NOR.

Boot from SD-Card and write bootstream at offset 0x0 to QSPI-NOR

Example for U-Boot:

```
tftp <bootstream>
sf probe
sf update ${loadaddr} 0 ${filesize}
```

### Update components via U-Boot

To update components on boot media following u-boot environment scripts are
prepared. These can be used to update the items using a network connection.

Bootstream: set env var `uboot` to name of your bootstream image, provide the
bootstream via TFTP and update via `run update_uboot_spi`

### Cortex M7

Demos are compiled to use UART4 (MBa8Mx X17:56,58 + X17:54 for GND) with
115200 8N1.

To start a demo stored on SD / e-MMC from U-Boot:

```
setenv fdt_file imx8mqnl-mba8mx-rpmsg.dtb
setenv cm_image <demo>
run boot_cm_mmc
```

To connect from running linux to rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```
