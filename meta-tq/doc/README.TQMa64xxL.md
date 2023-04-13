# TQMa64xxL on MBaX4XxL carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6442L: Module revisions REV.010x / 020x
* MBaX4XxL: Board revisions REV.010x / 020x

### Versions

_Bootloader:_

* uboot-ti-tq-2021.01 (based on ti-u-boot 2021.01)

_Kernel:_

* linux-ti-tq-5.10 (based on ti-rt-linux-5.10.y)

### Known issues

* USB functions are limited in U-Boot:
  - USB 3.0 is disabled in U-Boot, only USB 2.0 is available
  - The OTG ID pin is ignored in U-Boot. The mode of the port is determined
    by the boot mode:
    - When booting in USB host mode (from mass storage), the port uses host
      mode. Access to the connected USB storage device is possible from the
      U-Boot command line.
    - For all other boot modes, the port will be in device mode. The "dfu"
      command can be used to flash the boot media from a connected host.
* There is no PRU Ethernet support in U-Boot. Only the primary Ethernet port
  is usable in the bootloader.
* Most GPIOs can't be read or set from U-Boot, as they are disabled in the
  default pinmuxing. This also affects LED and button GPIOs. LEDs and buttons
  are working as expected on Linux.
* There is currently no driver support for the Bluetooth module of the MBaX4XxL
* On the TQMa64xxL REV.010x, there is an address conflict on I2C addresses
  0x51 and 0x54. Access to the EEPROMs and the RTC may not work correctly.
* On the MBaX4XxL REV.010x, the SD card can't be reset by software. This can
  make the redetection of an SD card unreliable after it has been switched
  to UHS mode.
* The WLAN/bluetooth adapter of the MBaX4XxL REV.010x is unsupported

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
* tiboot3-*-evm.bin: first-stage bootloader (R5 core)
* tispl.bin: second-stage bootloader (A53 core, includes ATF and OPTEE)
* u-boot.img: last-stage bootloader

### First-stage bootloader variants

The first-stage bootloader comes in three variants, each including a different
version of the system controller firmware:

* tiboot3-am64x-gp-evm.bin (AM64x Silicon Revision 1.0 or 2.0, General Purpose variant)
* tiboot3-am64x_sr2-hs-fs-evm.bin (AM64x Silicon Revision 2.0, High Security variant, field-securable)
* tiboot3-am64x_sr2-hs-evm.bin (AM64x Silicon Revision 2.0, High Security variant, security enforced)

Please refer to the Secure Device Processor SDK documention for more information
on the High Security CPU variants. This documentation must be obtained directly
from TI.

To select the variant to use, set the `SYSFW_DEFAULT_VARIANT` variable to
"am64x-gp", "am64x_sr2-hs-fs" or "am64x_sr2-hs" (in `local.conf` or a custom
machine definition), to match the used AM64x CPU variant and security
enforcement status. The default value is "am64x-gp".

The selected variant will be installed to the boot partition of the generated
WIC images as `tiboot3.bin`. It is possible to change an existing image to boot
on a different CPU variant by mounting the boot partition and renaming one of
the bootloader binaries to `tiboot3.bin`.

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

#### USB host (mass storage)

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |  x  |     |     |     |
| OFF     |     |     |  x  |  x  |    |     |  x  |     |     |

Only boot of U-Boot is currently supported from USB mass storage. The U-Boot
default environment does not provide commands to load kernel and root filesystem
from USB devices.

#### USB device (dfu-util)

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S4 |     |     |     |    |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |    |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |    |     |     |     |     |
| OFF     |     |     |  x  |  x  |    |  x  |  x  |     |     |

The `dfu-util` command can be used to load U-Boot from a connected USB host:

```
# After initial power-on, U-Boot will reset the device as a part of an Errata
# workaround, so tiboot3.bin needs to be loaded twice. After a warm boot (reset
# from U-Boot or reboot from Linux), the initial load of tiboot3.bin must be
# skipped.
dfu-util -a bootloader -D tiboot3.bin

# Load all U-Boot stages in sequence
dfu-util -R -a bootloader -D tiboot3.bin
dfu-util -R -a tispl.bin -D tispl.bin
dfu-util -R -a u-boot.img -D u-boot.img
```

Please refer to the
[AM64x Processor SDK Documentation](https://software-dl.ti.com/processor-sdk-linux/esd/AM64X/08_02_00_23/exports/docs/linux/Foundational_Components/U-Boot/Users-Guide.html)
for information on the usage of `dfu-util` to flash boot media.

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

## Support Wiki

See [TQ Support Wiki for TQMa64xxL](https://support.tq-group.com/en/arm/tqma64xxl)
