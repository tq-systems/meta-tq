# TQMa8MPxL

This README contains some useful information for TQMa8MPxL on MBa8MPxL

## Variants

* TQMa8MPQL REV.010x

## Version information for software components

See [here](doc/README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:


* RAM configs: 2 GiB
* CPU variants i.MX8MPQ
* Fuses
* speed grade / temperature grade detection
* UART (console on UART4)
* GPIO
  * LED
  * Button
* I2C
  * system EEPROM parsing
* e-MMC / SD
  * Read
  * Write
* ENET
  * GigE / FEC via Phy on MBa8MPxL
  * GigE / Eqos via Phy on MBa8MPxL
* Bootdevices:
  * SD-Card on USDHC2
  * e-MMC on USDHC3
  * QSPI-NOR on FlexSPI
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 3.0 Cable Detect, VBUS)
* QSPI NOR
  * Read with 1-1-1 SDR
  * PP / Erase with 1-1-1 SDR

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite

### Linux:

* RAM configs: 2 GiB
* CPU variants i.MX8MPQ
* speed grade / temperature grade detection
* I2C
  * Temperature Sensors
  * RTC
  * EEPROMS
* GPIO
  * LED
  * Button
* UART
  * console on UART4
  * 2 x UART via pin head
* ENET
  * GigE / FEC via Phy on MBa8MPxL
  * GigE / Eqos via Phy on MBa8MPxL
* USB
  * USB 3.0 Host / Hub
  * USB DRD (USB 3.0 Cable Detect, VBUS)

## TODO:

* Audio
* DSI
* MIPI CSI
* Display
* GPU / VPU
* CortexM

## Known Issues

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
  * imx8mp-mba8mpxl.dtb
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin: boot stream for SD / e-MMC

## Boot Dip Switches

_Note:_


_BOOT\_MODE_


Serial Downloader

BOOT\_MODE: 0001


e-MMC

BOOT\_MODE: 0010

```

SD Card (USDHC2)

BOOT\_MODE: 0011

FlexSPI / 3B Read

BOOT\_MODE: 0110

```


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

