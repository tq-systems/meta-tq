# TQMa64xxL on MBaX4XxL carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6442L: Module revisions REV.010x
* MBaX4XxL: Board revisions REV.010x

### Versions

_Bootloader:_

* uboot-ti-tq-2021.01 (based on ti-u-boot 2021.01)

_Kernel:_

* linux-ti-tq-5.10 (based on ti-rt-linux-5.10.y)

### Known issues

* The USB OTG port does not work in host mode in U-Boot
  - The OTG port works in host mode after booting Linux.
* The linux-ti-tq-5.10 recipe is not based on linux-yocto, so the kernel
  defconfig cannot be extended using config fragments.

## Support Wiki

A link to the TQ support Wiki will be added here when a page for the TQMa64xxL
is available.

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
* tiboot3.bin: first-stage bootloader (R5 core, includes system controller firmware)
* tispl.bin: second-stage bootloader (A53 core, includes ATF and OPTEE)
* u-boot.img: last-stage bootloader

## HowTo

### MBaX4XxL DIP switch settings for boot

#### SD card

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |  x  |     |     |
| OFF     |     |     |  x  |  x  |    |     |     |     |     |

#### eMMC

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |     |     |     |
| OFF     |     |     |  x  |  x  |    |     |  x  |     |     |

Note: This configuration uses the MMCSD mode with port 0 to boot a full
filesystem image from the eMMC user partition, which is not officially supported
by TI for the AM64x. Refer to the SoC documentation provided by TI for
information on the use of the eMMC boot partitions.

#### SPI-NOR

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |  x  |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |  x  |    |     |     |     |     |
| OFF     |     |     |  x  |     |    |  x  |     |     |     |

### OS boot

By default, U-Boot will boot the OS from the same medium that it was started
from (eMMC, SD card or SPI-NOR). The default `bootcmd` allows changing the OS
boot source by modifying the following environment variables:

- `boot`: Set to `mmc`, `ubi` or `net`
- `mmcdev`: Set to `0` (eMMC) or `1` (SD card)

Network boot does not use TFTP. Instead, kernel and Device Trees are loaded from
the `/boot` directory of the root filesystem via NFS.

### WLAN support

To enable support for the integrated ath10k WLAN adapter of the MBaX4XxL, the
Device Tree overlay to load must be changed from U-Boot by setting the variable
`name_overlay`:
```
setenv name_overlay overlay.dtbo
```
The `saveenv` command is used to persist this setting.

The following overlays are currently available:

| Device Tree overlay                        | Interface         |
|--------------------------------------------|-------------------|
| k3-am64-tqma64xxl-mbax4xxl-sdcard.dtbo     | SD card (default) |
| k3-am64-tqma64xxl-mbax4xxl-wlan.dtbo       | WLAN              |

As the SD card and WLAN adapter require exclusive control of the same SDHC/SDIO
bus, they can't be enabled at the same time. A different OS boot source (usually
eMMC or SPI-NOR) must be chosen for this reason when WLAN is enabled.

### Program system image

**Note:** Do not use the commands described in the following to overwrite the
root filesystem you are currently running from.

#### SD card / e-MMC

To program a complete system image, write the [WIC image](#artifacts) to
SD card / e-MMC at offset 0. The following command can be used to write a file
`/mnt/image.wic` to the e-MMC:
```
dd if=/mnt/image.wic of=/dev/mmcblk0 bs=1M
```

#### SPI-NOR

To program the root filesystem, format `/dev/mtd5` as a UBI volume and write
the UBI image to it. If the image is stored at `/mnt/rootfs.ubi` (for example
on a USB drive), use the following command:
```
ubiformat /dev/mtd5 -f /mnt/rootfs.ubi
```

To check check usability of the programmed root filesystem, the following
commands can be used:
```
ubiattach -p /dev/mtd5
mount -t ubifs ubi0:rootfs /mnt
```

### Updates

When booting from e-MMC / SD card, the bootloader and system firmware are loaded
from a FAT partition (the first partition of the boot medium by default). They
can be updated by replacing the files on this partition.

For SPI-NOR boot, each stage is loaded from a fixed offset in the flash. The MTD
partition list is configured to match these offsets.

For convenience, the following commands can be used in U-Boot to update these
files via TFTP:
```
run update_uboot_mmc # Update bootloader on active MMC device (eMMC/SD card)
run update_uboot_spi # Update bootloader on SPI-NOR flash
```
The environment variables `tiboot3`, `tispl` and `u-boot` can be modified
to control the filenames requested via TFTP.

Kernel and Device Trees are part of the root filesystem. They cannot be updated
from U-Boot separately from the filesystem.
