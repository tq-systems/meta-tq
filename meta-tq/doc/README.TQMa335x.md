# TQMa335x\[L\]

This README contains some useful information for TQMa335x\[L\] on MBa335x

[[_TOC_]]

## Variants

* TQMa335x / TQMa335xL REV.020x 256 MiB DDR3
* TQMa335x / TQMa335xL REV.020x 512 MiB DDR3
* MBa335x REV.020x

## Version Information for Software Components

### U-Boot

* uboot-tq (based on mainline 2019.04)

### Linux

* linux-tq (based on mainline 6.12.y)

## Supported Features

### U-Boot

| Feature                                          |   REV.020x   |
| :----------------------------------------------: | :----------: |
| UART (console on X15)                            |       x      |
| GPIO                                             |       x      |
| I2C                                              |       x      |
| eMMC / SD card                                   |       x      |
| SPI-NOR                                          |       x      |
| Ethernet                                         | Port X11 only |
| **Boot devices**                                 |              |
| SD card                                          |       x      |
| eMMC                                             |       x      |
| SPI-NOR                                          |       x      |
| **USB**                                          |              |
| USB 2.0 Host / Hub                               |       x      |

### Linux

| Feature                              | linux-tq-6.12 |
| :----------------------------------: | :-----------: |
| UART4 (console on X15)               |      x        |
| UART0 (RS485 on X16)                 |      x        |
| GPIO                                 |      x        |
| Button (S5, S6, S7)                  |      x        |
| I2C                                  |      x        |
| GPIO expander                        |      x        |
| EEPROM                               |      x        |
| RTC                                  |      x        |
| SPI-NOR                              |      x        |
| USB 2.0 Host / Hub (X7/X8)           |      x        |
| USB-OTG (X9)                         | Peripheral only |
| eMMC/SD                              |      x        |
| Ethernet (X11, X12)                  |      x        |
| CAN (X13, X14)                       |      x        |
| Parallel LCD (X4)                    |      x        |
| LVDS (X17, X18)                      |               |
| GPU                                  |      x        |
| Audio Line In (X19, X20, X21)        |      x        |
| Mini-PCIe (USB only) (X22)           |      x        |
| PRU                                  |               |

## Supported Machine Configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Known Issues / Limitations

* TQMa335x[L] on MBa335x: sometimes after booting the ethernet phy shows
  up wrong addresses or can not be found. This needs a hardware fix on MBa335x.
  Please contact TQ-Systems support
* TQMa335x[L] on MBa335x: U-Boot: only the first port of the CPSW is working.
  This is a driver limitation in the used U-Boot version, under Linux both ports
  are working.
* TQMa335xL: warnings for non exisiting chips when booting Linux. Disable
  RTC / EEPROM / temperature sensor in DT.
* The generated UBIFS does not fit into the default SPI-NOR (16 MiB). If
  rootfs on SPI NOR is required, following solutions:
  * tailor image recipe and kernel configuration to get real tiny
  * use SoM variant with larger SPI-NOR
* Role switching does not work on the USB-OTG port (X9); only peripheral mode
  is usable.
* No Device Trees for LVDS displays are provided.
* U-Boot:
  * USB mass storage support may vary among different USB flash drive models/vendors
  * `usb reset` (or `usb start` / `usb stop`) fails every second time
    Workaround: Repeat command
* Suspend/Resume is currently not supported

## Build Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO-${MACHINE}: U-Boot MLO (SPL image for SD / eMMC)
* MLO-${MACHINE}.byteswap: U-Boot MLO (SPL image for SPI NOR flash)
* u-boot-${MACHINE}.img: U-Boot image to be booted by MLO

## DIP Switches

| Switch  | Description       |
| ------- | :---------------: |
| S1      | Oscilator         |
| S2      | Boot device       |
| S3      | CAN termination   |
| S4      | RS485 Termination |

### Boot Sequence

#### SD Card

Boot sequence: MMC0 (SD) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |     |     |     |  x  |     |     |     |     |
| OFF     |  x  |  x  |  x  |     |  x  |  x  |  x  |  x  |

#### eMMC

Boot sequence: MMC1 (eMMC) → SPI0 (NOR) → UART0 (N/A) → USB0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |     |     |     |     |     |     |
| OFF     |     |     |  x  |  x  |  x  |  x  |  x  |  x  |

#### SPI NOR

Boot sequence: SPI0 (NOR) → MMC0 (SD) → USB0 (N/A) → UART0 (N/A)

| S2      |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| ON      |  x  |  x  |  x  |     |     |     |     |     |
| OFF     |     |     |     |  x  |  x  |  x  |  x  |  x  |

## Boot Device Initialisation

### Prerequisites for Block Devices

Compressed WIC images and matching BMAP-files (block map files) are created by default.
To make use of this feature, install the `bmap-tools` package to use `bmaptool`.
The packed WIC can also be decompressed and used with `dd` or other disk image tools.

### Bootable SD card

Write the `*.wic` image to SD card to create a bootable SD card with complete system image.
The following command can be used (the example assumes an SD card reader on PC and
`bmap-tools` package is installed):

```bash
bmaptool copy <image>.wic[.compress] --bmap <image>.bmap /dev/sd<x>
```

To create a bootable SD card with boot stream only write `*.wic.bootonly` to SD (offset 0x0)

Example for Linux:

`sudo dd if=<image> of=/dev/sd<x> bs=4M conv=fsync`

### Bootable eMMC

To create a bootable eMMC with complete system image use the generated
[wic image](#build-artefacts):

write *.wic image to eMMC (offset 0)

To create a bootable eMMC with minimum boot image use the generated
[minimal wic image](#build-artefacts):

write *.wic.bootonly to eMMC (offset 0)

Example for Linux:

`sudo dd if=<image> of=/dev/mmcblk1 bs=4M conv=fsync`

Example for U-Boot:

```
tftp <image>
setexpr bsz ${filesize} + 0x1ff
setexpr bsz ${bsz} / 0x200
printenv bsz
mmc dev 1
mmc write ${loadaddr} 0 ${bsz}
```

### Bootable SPI NOR

To create a bootable SPI NOR with boot loader only use the generated
[bootloader images](#build-artefacts). Example for U-Boot, booting from SD card:

```
sf probe
tftp MLO.byteswap
sf update ${loadaddr} 0 ${filesize}
tftp u-boot.img
sf update ${loadaddr} 0x20000 ${filesize}
```

## Update Components via U-Boot

### U-Boot Environment Variables

For ease of development a set of variables and scripts are in default env.
Depending on your configuration some variable values needs to bet changend
to the right values. For files to use see the [artefacts](#build-artefacts) section.

_Note_: Update and start scripts expect a partitioned / initialized SD card or
eMMC.

* `uboot`: name of U-Boot payload image for SD / eMMC (default = u-boot.img)
* `mlo`: name of U-Boot SPL image for SD / eMMC (default = MLO)
* `uboot_spi`: name of U-Boot payload image for SPI flash (default = u-boot.img)
* `mlo_spi`: name of U-Boot SPL image for SPI flash (default = MLO.byteswap)
* `mmcdev`: 1 for eMMC, 2 for SD card (automatically generated when booting
   from SD / eMMC with the index of the boot device, can be overwritten;
   must be set if needed when booting from SPI NOR)
* `fdtfile`: device tree blob,
* `bootfile`: kernel image,

### SD / eMMC

Download bootloader from TFTP and update (make sure `mmcdev` is correctly set):

```
run update_uboot_mmc
```

### SPI

Download bootloader from TFTP and update:

`run update_uboot_spi`

## HowTo

### Display Support

Set the `fdtfile` variable in the U-Boot environment to select one of the
supported displays:

| Interface  | Device tree                     | Type                |
|------------|---------------------------------|---------------------|
| Parallel   | am335x-mba335x-dmb-ct44.dtb     | CDTECH DC44 (DMB)   |
| Parallel   | am335x-mba335x-glyn-etm0700.dtb | GLYN ETM0700G0EDH6  |

### Booting Linux OS

To boot a Linux OS from a running U-Boot following scripts are implemented in
environment:

* `mmcboot`: load kernel and dtb from SD/eMMC instance given with variable `mmcdev`
  * Boot device is SD / eMMC: `mmcdev` is set to device index of the boot device if
    `mmcautodetect` is `yes` (default)
  * Boot device is not SD / eMMC: `mmcdev` has to be set before using `mmcboot`
* `netboot`: load kernel and dtb using tftpboot and boots into rootfs on a NFS
  mount.

## Support Wiki

See [TQ Embedded Wiki for TQMa335x](https://support.tq-group.com/en/arm/tqma335x)
