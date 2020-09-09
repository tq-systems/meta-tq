# TQMa8Xx / TQMa8Xx4

This README contains some useful information for TQMa8Xx and TQMa8Xx4 on MBa8Xx

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
  * 1GB / TQMa8XQP
  * 2GB / TQMa8XQP4
* CPU variants:
  * i.MX8QXP
* Fuses
* GPIO
* QSPI
  * Read
  * Write
  * Boot
* I2C
* e-MMC / SD
  * Read
  * Write
  * Boot
* USB Hub
* USB OTG
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2
* Bootstreams
  * FlexSPI + SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* CPU variants i.MX8DX
* speed grade / temperature grade detection (current SCU limitation)

### Linux:

* RAM configs:
  * 1GB / TQMa8XQP
  * 2GB / TQMa8XQP4
* CPU variants:
  * i.MX8QXP
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
* GPU
* LED
* GPIO
* ENET (GigE via Phy)
  * ENET 1
  * ENET 2
* QSPI NOR
* UART
  * console
  * LPUART3 via unused SAI pins
* LVDS
* GPU
* PCIe (mini-PCIe)
* Audio
  * Line Out
* CAN
  * can0/1 as network interface

**TODO or not tested with new BSP**

* temperature grade
* Audio
  * Mic In untested
  * Line In untested
* DSI - DP bridge
* GPIO
  * Suspend / Wakeup via GPIO button
* DVFS
  * speed grade

## Known Issues

* CAN
  * CAN FD is not automatically configured (systemd limitation)
* SPI
  * some spidev are configured with to high max frequency

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
DIP (S1)	1 2 3 4
BootMode	3 2 1 0
ON 		    X X
OFF 		X X    
```

_e-MMC_

```
DIP (S1)	1 2 3 4
BootMode	3 2 1 0
ON 		    X  
OFF 		X X   X
```

_FLEXSPI_

```
DIP (S1)	1 2 3 4
BootMode	3 2 1 0
ON 		  X X  
OFF 		X     X
```

_Serial Downloader_

```
DIP (S1)	1 2 3 4
BootMode	3 2 1 0
ON 		      X
OFF 		X X X  
```

_Boot from Fuses_

```
DIP (S1)	1 2 3 4
BootMode	3 2 1 0
ON 		       
OFF 		X X X X
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

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

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

## CAN

### Troubleshooting

In case of problems first check the bus termination:

* CAN0: SW1
* CAN1: SW2

### Enable CAN-FD

To enable CAN-FD the following command can be used:

```
CANIF="can[0,1]"
ip link set "${CANIF}" up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on‍‍‍‍‍‍‍`
```

