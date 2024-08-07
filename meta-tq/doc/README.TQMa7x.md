# TQMa7x on MBa7x carrier board (aka STKa7x)

This README contains some useful information for TQMa7x on MBa7x

[[_TOC_]]

## Variants

* TQMa7S / TQMa7D module revisions REV.020x 512 MiB RAM
* TQMa7S / TQMa7D module revisions REV.020x 1024 MiB RAM
* TQMa7S / TQMa7D module revisions REV.020x 2048 MiB RAM
* MBa7x:  board revisions REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### Linux

|                            | linux-tq-5.15 | linux-tq-6.1 |
| :------------------------- | :-----------: | :----------: |
| Fuses                      |       x       |      x       |
| UART (console, X13 or X14) |       x       |      x       |
| GPIO                       |       x       |      x       |
| Button (S11, S12, S13)     |       x       |      x       |
| I2C                        |       x       |      x       |
| GPIO expander              |       x       |      x       |
| EEPROM                     |       x       |      x       |
| RTC                        |       x       |      x       |
| SPI NOR                    |       x       |      x       |
| Buzzer                     |       x       |      x       |
| LEDs                       |       x       |      x       |
| SPI                        |       x       |      x       |
| USB Host (X4)              |       x       |      x       |
| USB Dual Role (X5)         |       x       |      x       |
| USB on Mini PCIe (X17)     |       x       |      x       |
| eMMC/SD (on-board/X7)      |       x       |      x       |
| Ethernet GigE (X8/X9)      |       x       |      x       |
| CAN (X10/X11)              |       x       |      x       |
| RS-485 (X12)               |       x       |      x       |
| LVDS (X15, X16)            |       x       |      x       |
| PCIe (X17)                 |               |              |
| Audio Line In (X20)        |       x       |      x       |
| Audio Line Out (x21)       |       x       |      x       |
| Parallel LCD (X23)         |       x       |      x       |
| Touch (X23)                |       x       |      x       |
| ADC (X23/X24)              |               |      x       |

## ToDo / Untested

* Smart card (X6)
* SIM card (X18)
* Mic In (X19)

## Known issues

* Using internal PCIe PHY clock is currently not supported by the Linux
  mainline and newer NXP vendor kernel. PCIe can not be used on MBa7x
  with these kernel versions.
* `asound.state` is not compatible with `linux-5.4`
  (`linux-imx-tq` as well as `linux-tq`). Use newer kernel - default is
  based on 5.15.y stable
* USB Dual Role gadget: Causing a device disconnect is not possible. D+ is
  erroneously supplying VBUS as well preventing a device disconnect per software.
  Occurs when gadget is disabled again. USB host might fail to detect a new USB
  descriptor once gadget is restarted.
* U-Boot v2016.03 needs special environment setting for booting a mainline
  kernel, see [Mainline Kernel](#mainline_kernel)
* Writing to FAT filesystems may cause warnings and errors in U-Boot
  based on v2016.03.
* UBI / UBIFS images will not be built out of the box since `imx-base.inc` from
  meta-freescale override machine specific assignment for `MACHINE_FEATURES`.
  Use following bitbake assignment in one of your `local.conf` / `auto.conf` /
  `<machine>.conf` files:
  ```
  MACHINE_FEATURES:append = " ubi"
  ```

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* u-boot-${MACHINE}.imx-sd: boot stream for SD / e-MMC
* u-boot-${MACHINE}.imx-qspi: boot stream for QSPI

## Boot DIP Switches

### MBa7x DIP Switches

_Note:_

* S2/3/4 are for BOOT_CFG 0..20.
* S1 is for Boot Mode.

### SD Card

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |  x  |     |     |  x  |     |     |     |    |      |     |     |     |  x  |     |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x  |  x  |     |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

### e-MMC

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |  x  |     |     |    |      |     |     |  x  |     |  x  |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |     |  x  |  x  |    |  x   |  x  |  x  |     |  x  |     |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

### QSPI

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |     |     |     |    |      |     |     |     |     |     |  x  |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |  x  |  x  |     |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

## Boot device initialisation

### QSPI NOR

To initialize QSPI NOR with bootloader, write the [bootloader image](#artifacts)
to QSPI NOR at offset 0x00:

```
setenv uboot <U-Boot QSPI boot image>
tftp ${loadaddr} ${uboot}
sf probe
sf update ${loadaddr} 0 ${filesize}
```

### SD / e-MMC

To initialize SD / e-MMC with bootloader, write the [bootloader image](#artifacts)
for SD / e-MMC to SD / e-MMC at offset 0x400 / block #2

```
setenv uboot <U-Boot SD/e-MMC boot image>
tftp ${loadaddr} ${uboot}
mmc dev [0,1]
mmc rescan
setenv blkc ${filesize} + 1ff
setenv blkc ${blkc} / 200
mmc write ${loadaddr} 2 ${blkc}
setenv blkc
```

### Program system image

#### QSPI NOR

Not supported. Only kernel and DTB can be stored at the moment.

#### SD / e-MMC

To program complete system image to SD / e-MMC, write [WIC image](#artifacts)
to SD / e-MMC at offset 0x00 / block #0

### Update parts of system

To update parts of system using U-Boot / TFTP following shortcuts exist, to
update the part on the active boot device.

```
setenv zimage <name of linux zimage>
run update_kernel
```

```
setenv fdt_file <name of fdt image>
run update_fdt
```

```
setenv uboot <name of u-boot image>
run update_uboot
```

## Howto

### Mainline Kernel

Mainline kernel depends on the presence of running firmware in TrustZone
that implements the PSCI. Default U-Boot does not leave TrustZone before
booting linux, resulting in only one CPU core available with mainline kernel.

In order to be able to use both CPU cores of i.MX7 with the TQMa7D, the
kernel has to be started outside of TrustZone. To achieve this, following
change has to be made to the U-boot environment:

```
setenv bootm_boot_mode nonsec
```

The number of running CPUs can be checked with `nproc` under linux.

## Support Wiki

See [TQ Embedded Wiki for TQMa7x](https://support.tq-group.com/en/arm/tqma7x)
