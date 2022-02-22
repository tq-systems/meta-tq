# TQMa65xx on MBa65xx carrier board

## Overview

### Supported Hardware:

* TQMa6548: Module revisions REV.010x (2GB & 4GB RAM variants)
* MBa65xx: Board revisions REV.020x

### Versions

_Bootloader:_

* uboot-ti-tq-2020.01 (based on ti-u-boot 2020.01)

_Kernel:_

* linux-ti-tq-5.4.109 (based on ti-rt-linux-5.4.y)

### Known issues

* USB3 is not working on OTG port.
  - In host mode, USB3 devices connected using an USB3 OTG cable are not
    detected. Use a USB2 OTG cable.
  - In device mode (gadget driver), USB2 is used even when both sides and the
    cable should support USB3.
* The USB OTG port does not work in host mode in U-Boot
  - Use one of the hub ports to connect USB media in U-Boot. The OTG port works
    in host mode after booting Linux.
* The 6 PRU Ethernet ports only work in PHY master mode. This makes it
  impossible to connect these ports to other devices that enforce master mode
  (like the PRU ports of other MBa65xx boards).
* The linux-ti-tq-5.4.109 recipe is not based on linux-yocto, so the kernel
  defconfig cannot be extended using config fragments.
* Macronix Octal SPI-NOR flash is currently unsupported. TQMa65xx variants with
  an OSPI flash run in single SPI mode.

## Support Wiki

See [TQ Support Wiki for TQMa65xx](https://support.tq-group.com/en/arm/tqma65xx)

## Artifacts

Artifacts can be found at the usual locations for Bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: Device Tree blobs
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* sysfw.itb: system controller firmware
* tiboot3.bin: first-stage bootloader (R5 core)
* tispl.bin: second-stage bootloader (A53 core, includes ATF and OPTEE)
* u-boot.img: last-stage bootloader

## HowTo

### MBa65xx DIP switch settings for boot

#### SD Card

|         |  S8 |     |     |     |     |     |     |     |    |  S9 |     |     |     |     |     |     |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ON      |     |  x  |  x  |     |     |     |     |     |    |     |     |     |     |  x  |     |     |     |
| OFF     |  x  |     |     |  x  |     |     |     |  x  |    |  x  |  x  |  x  |  x  |     |  x  |     |     |

#### eMMC

|         |  S8 |     |     |     |     |     |     |     |    |  S9 |     |     |     |     |     |     |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ON      |     |  x  |  x  |     |     |     |     |     |    |     |     |     |     |     |  x  |     |     |
| OFF     |  x  |     |     |  x  |     |     |     |  x  |    |  x  |  x  |  x  |  x  |  x  |     |     |     |

#### SPI-NOR

|         |  S8 |     |     |     |     |     |     |     |    |  S9 |     |     |     |     |     |     |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |
| ON      |  x  |     |     |     |     |     |     |     |    |     |     |  x  |     |     |     |  x  |     |
| OFF     |     |  x  |  x  |  x  |     |     |     |  x  |    |  x  |  x  |     |  x  |  x  |  x  |     |  x  |

### OS boot

By default, U-Boot will boot the OS from the same medium that it was started
from (eMMC, SD card or SPI-NOR). The default `bootcmd` allows changing the OS
boot source by modifying the following environment variables:

- `boot`: Set to `mmc`, `ubi` or `net`
- `mmcdev`: Set to `0` (eMMC) or `1` (SD card)

Network boot does not use TFTP. Instead, kernel and Device Trees are loaded from
the `/boot` directory of the root filesystem via NFS.

### Display support

To enable display support, a Device Tree overlay must be loaded from U-Boot
by setting the variable `name_overlay_extra`:
```
setenv name_overlay_extra overlay.dtbo
```
The `saveenv` command is used to persist this setting.

The following overlays are currently available:

| Device Tree overlay                        | Interface     | Type                          |
|--------------------------------------------|---------------|-------------------------------|
| k3-am65-tqma65xx-mba65xx-dmb-fc21.dtbo     | RGB           | DMB / CDTech S070PWS19HP-FC21 |
| k3-am65-tqma65xx-mba65xx-dmb-dc44.dtbo     | RGB           | DMB / CDTech S070SWV29HG-DC44 |
| k3-am65-tqma65xx-mba65xx-lvds-display.dtbo | LVDS          | Tianma TM070JVHG33            |

### Program system image

#### SD card / e-MMC

To program a complete system image, write the [WIC image](#artifacts) to
SD card / e-MMC at offset 0.

#### SPI-NOR

__Attention__: This section is subject to change.

To program the root filesystem, format `/dev/mtd6` as a UBI volume and write
the UBI image to it. If the image is stored at `/mnt/rootfs.ubi` (for example
on a USB drive), use the following command:
```
ubiformat /dev/mtd6 -f /mnt/rootfs.ubi
```

To check check usability of the programmed root filesystem, the following
commands can be used:
```
ubiattach -p /dev/mtd6
mount -t ubifs ubi0:rootfs /mnt
```

### Updates

When booting from e-MMC / SD card, the bootloader and system firmware are loaded
from a FAT partition (the first partition of the boot medium by default). They
can be updated by replacing the files on this partition.

For convenience, the following commands can be used in U-Boot to update these
files via TFTP:
```
run update_firmware_mmc
run update_uboot_mmc
```
The environment variables `sysfw`, `tiboot3`, `tispl` and `u-boot` can be
modified to control the filenames requested via TFTP.

Kernel and Device Trees are part of the root filesystem. They can not be updated
separately from U-Boot.
