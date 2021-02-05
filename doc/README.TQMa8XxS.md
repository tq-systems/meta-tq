# TQMa8XxS

This README contains some useful information for TQMa8XxS on MB-SMARC-2

## Variants

* TQMa8XQPS REV.020x
* TQMa8XQPS REV.030x

## Versions

### SCFW:

* version: tq-TQMa8.NXP-v1.6.0.B4894.0030

### U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/imx/uboot-imx)
* branched from imx-5.4.47-2.2.0

### ATF:

* imx-atf (https://source.codeaurora.org/external/imx/imx-atf)
* v2.2 / imx-5.4.47_2.2.0

### Linux:

* based on linux-imx (https://source.codeaurora.org/external/imx/linux-imx)
* branched from lf-5.4.y-1.0.0

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:

* RAM configs:
  * 2GB / TQMa8XQPS
* CPU variants:
  * i.MX8QXP C0
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
  * USB Hub
  * USB OTG
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2
* Bootstreams
  * FlexSPI
  * SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* Cortex M4
* CPU variants i.MX8DX not detected (limitation of cpu driver))
* USB
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset

### Linux:

* RAM configs:
  * 2GB / TQMa8XQPS
* CPU variants:
  * i.MX8QXP C0
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* ENET (GigE via Phy on TQMa8XxS)
  * ENET 1
  * ENET 2
* QSPI NOR
* UART
  * console
* LVDS
* GPU
* VPU
* PCIe (mini-PCIe)
* CAN
  * can0/1 as network interface

**TODO or not tested with new BSP**

* temperature grade
* Audio
* DSI - DP bridge
* LED
* GPIO
  * Suspend / Wakeup via GPIO button
* DVFS
  * speed grade

## Known Issues

* CAN
  * CAN FD is not automatically configured (systemd limitation)
* USB
  * Port USB3 (X3 on MB-SMARC-2) is host only. Do not plugin an USB 3.0 Micro B plug.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU

## Boot Dip Switches

_Note:_

* S3 is for Boot Mode.
* X means position of DIP, - means don't care

_SD Card_

```
            1 2 3 4
DIP (S3)    0 1 1 0

```

_e-MMC_

```
            1 2 3 4
DIP (S3)    1 0 0 0
```

_QSPI_

```
            1 2 3 4
DIP (S3)    1 1 0 0
```

_Serial Downloader_

```
            1 2 3 4
DIP (S3)    0 0 0 1
```

## SD-Card Boot

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin-flash_spl of=/dev/sd<x> bs=1k seek=32 conv=fsync`

## e-MMC Boot

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=imx-boot-<module>-<baseboard>-sd.bin-flash_spl of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

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

* `uboot`: name of bootstream image (default =  bootstream.bin)
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

## Use UUU Tool

To build bootstream adapt yocto configuration, modify _local.conf_ or machine
config file:

```
UBOOT_CONFIG_tqma8xxs = "mfgtool"
IMXBOOT_TARGETS_tqma8xxs = "flash_spl"
```

Rebuild boot stream:

```
bitbake imx-boot
```

Use new compiled bootstream containing U-Boot capable of handling SDP together
with UUU tool:

```
sudo uuu -b spl imx-boot-<machine>-mfgtool.bin
```

## Support for REV.020x Modules

To support older REV.020x modules, use the respective machine config. This automatically

- builds the correct bootloader
- build devicetrees correct device trees (containing `r0200` in its name)

## Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                             | Type        ----   |
|-----------------|-----------------------------------------|--------------------|
| LVDS0           | imx8qxp-mb-smarc-2-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS0, dual     | imx8qxp-mb-smarc-2-lvds-g133han01.dtb   | AUO G133HAN.01     |

## CAN

### Troubleshooting

In case of problems first check the bus termination:

* CAN0: (X29): DIP `TERM CAN0`
* CAN1: (X30): DIP `TERM CAN1`

### Enable CAN-FD

To enable CAN-FD the following command can be used:

```
CANIF="can[0,1]"
ip link set "${CANIF}" up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on‍‍‍‍‍‍‍`
```

