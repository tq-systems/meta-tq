# TQMa8x

This README contains some useful information for TQMa8x on MBa8x

## Versions

### SCFW:

* version: tq-TQMa8.NXP-v1.3.1.B4124.0029

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from lf-5.4.y-1.0.0

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from lf-5.4.y-1.0.0

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:

* RAM configs:
  * 4GB / TQMa8QM
  * 8GB / TQMa8QM
* CPU variants i.MX8QM
* Fuses
* GPIO
* QSPI
  * Read
  * Write
  * Boot
* I2C
* e-MMC / SD-Card
  * Read
  * Write
  * Boot
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8x)
* ENET (GigE via Phy on MBa8x)
  * ENET 0
  * ENET 1
* Bootstreams
  * FlexSPI
  * SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* Cortex M4
* more CPU variants
* speed grade / temperature grade detection (current SCU limitation)

### Linux:

* RAM configs:
  * 4GB / TQMa8QM
  * 8GB / TQMa8QM
* CPU variants i.MX8QM
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
* GPIO
  * LED
  * Button
  * wakeup from GPIO button
  * GPIO on pin heads
* ENET (GigE via Phy on MBa8x)
  * ENET 0
  * ENET 1
* QSPI NOR
* UART
  * console
  * additional UARTS on pin heads
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8Mx)
* Graphic
  * GPU
  * LVDS0/LVDS1
  * Display Port
* CAN
  * can0/1 as network interface
* PWM
  * PWM in LVDS IP
* SATA
* PCI

**TODO or not tested with new BSP**

* Audio (including Display Port)
* VPU
* Mikrobus (Module RTC5)
* ADC
* FTM (PWM)
* temperature grade detection
* HDMI in
* Cortex M4
* DVFS
  * speed grade
* PWM
  * generic PWM IP

## Known Issues

* sometimes hangs in linux during first start with a fresh image
* random hangs starting boot loader when cold boot (only SCU comes up)
* counting of i2c devices bus starts at i2c-2 (because i2c-0 and i2c-1
  are reserved for i2c_rpmsgbus)
* can bitrate limited to 125000 (in can0/can1.service), no CAN FD
  due to hardware limitations on mainboard Rev01xx
* environment from QSPI fails sometimes
* VPU codecs not avaiable [TQMAACHTX-103]
* PWM only works after the second enable command (echo 1 > /pwmX/enable)

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd-flash\_spl.bin: boot stream for SD / e-MMC
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU

## Boot Dip Switches

_Note:_

* S1 is for Boot Mode.
* X means position of DIP, - means don't care

_SD Card_

```
BootMode	5 4 3 2 1 0

OFF 		X X     X X
ON 		    X X
```

_e-MMC_

```
BootMode	5 4 3 2 1 0

OFF 		X X   X X X
ON 		    X
```

_FLEXSPI_

```
BootMode	5 4 3 2 1 0

OFF 		X         X
ON 		  X X X X
```

## SD-Card Boot

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/sd<x> bs=1k seek=32 conv=fsync`

## e-MMC Boot

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 blocks -> 0x40

tftp <bootstream>
setexpr bsz $filesize + 1ff
setexpr bsz $bsz / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

## QSPI Boot

### Write bootstream to QSPI

To install QSPI bootstream from U-Boot running on SD, copy the QSPI bootstream from
deploy folder onto SD-Card partition 1 (firmware partition) or load via tftp.
File name see above.

```
setenv uboot xxx.bin-flash_spl_flexspi
# load from firmware partition
load mmc 1:1 ${loadaddr} ${uboot}
# load via tftp
tftp ${uboot}
sf probe
sf update ${loadaddr} 0x00 ${filesize}
# optional verfify
sf read 0x80300000 0x00 ${filesize}
cmp.b 0x80300000 ${loadaddr} ${filesize}
```

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

_U-Boot environment variables_

* `uboot`: name of bootstream image (aka flash.bin)
* `mmcdev`: 0 for e-MMC, 1 for SD-Card
* `fdt_file`: device tree blob,
* `image`: kernel image,

_SD / e-MMC_

Download bootstream from TFTP and update:

`run update_uboot_mmc`

Download device tree blob from TFTP and update:

`run update_fdt_mmc`

Download kernel image from TFTP and update:

`run update_kernel_mmc`

_FLEXSPI_

Download bootstream from TFTP and update:

`run update_uboot_spi`


## Test sleepmode and wakeup

Use rtc1 (on module) to wakeup after 20 seconds:
```
echo enabled > /sys/class/rtc/rtc1/device/power/wakeup
echo 0 > /sys/class/rtc/rtc1/wakealarm
echo +20 > /sys/class/rtc/rtc1/wakealarm
echo mem > /sys/power/state
```
Send linux to sleep mode and press one of the gpio buttons SWITCH\_A or SWITCH\_B afterwards

```
echo mem > /sys/power/state
```

## Display Support

Each Display output could be activated independend by using the corresponding device tree.

| Interface | Device tree                        | Type           |
|-----------|------------------------------------|----------------|
| LVDS0     | imx8qm-mba8x-lvds0-tm070jvhg33.dtb | Tianma Display |
| LVDS1     | imx8qm-mba8x-lvds1-tm070jvhg33.dtb | Tianma Display |
| DP        | imx8qm-mba8x-dp.dtb                | Displayport    |
