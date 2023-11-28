# TQMa64xxL on MBaX4XxL carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6442L, TQMa6411L: Module revisions REV.010x / 020x
* MBaX4XxL: Board revisions REV.010x / 020x

### Versions

_Bootloader:_

* uboot-ti-tq-2023.01 (based on ti-u-boot 2023.01)

_Kernel:_

* linux-ti-tq-6.1 (based on ti-rt-linux-6.1.y; default)
* linux-ti-tq-5.10 (based on ti-rt-linux-5.10.y)

### Known issues

* *In U-Boot,* USB functions are limited:
  - USB 3.0 is disabled in U-Boot, only USB 2.0 is available
  - The OTG ID pin is ignored in U-Boot. The mode of the port is determined
    by the boot mode:
    - When booting in USB host mode (from mass storage), the port uses host
      mode. Access to the connected USB storage device is possible from the
      U-Boot command line.
    - For all other boot modes, the port will be in device mode. The "dfu"
      command can be used to flash the boot media from a connected host.
* *In U-Boot,* there is no PRU Ethernet support. Only the primary Ethernet port
  X5 is usable in the bootloader.
* *From U-Boot,* most GPIOs can't be read or set, as they are disabled in the
  default pinmuxing. This also affects LED and button GPIOs. LEDs and buttons
  are working as expected on Linux.
* There is currently no driver support for the Bluetooth module of the MBaX4XxL.
* In rare cases, the USB3 PHY initialization has been observed to fail with the
  message "Timeout waiting for CMN ready" during boot, leaving the USB
  controller unusable. A power cycle is necessary to recover from this state.
* On variants with only a single R5 core (TQMa6412L, TQMa6411L), booting
  R5 programs and communicating with them using the Linux remoteproc/rpmsg
  drivers is currently unsupported.
* The AM64x currently doesn't support Suspend-to-RAM
* TQMa64xxL *REV.010x only*:
  * There is an address conflict on I2C addresses 0x51 and 0x54. Access to the
    EEPROMs and the RTC may not work correctly.
* MBaX4XxL *REV.010x only*:
  * The SD card can't be reset by software. This can make the redetection of an
    SD card unreliable after it has been switched to UHS mode.
  * The WLAN/bluetooth adapter is unsupported

## Build artifacts

Artifacts can be found at:
`deploy-ti/images/${MACHINE}`

* fitImage: Combined kernel image with Device Trees and overlays
* \*.dtb: Device Tree blobs
* \*.dtbo: Device Tree overlays
* Image: Linux kernel image
* \*.wic: SD/eMMC/USB storage system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* tiboot3-*-evm.bin: first-stage bootloader (R5 core)
* tispl.bin: second-stage bootloader (A53 core, includes ATF and OPTEE)
* u-boot.img: last-stage bootloader
* extlinux.conf: Boot configuration for U-Boot distroboot
* boot-blockdev.scr: U-Boot boot script image for boot from eMMC/SD/USB
* boot-ubi.scr: U-Boot boot script image for boot from UBIFS (SPI-NOR)

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
enforcement status. The default value is "am64x_sr2-hs-fs".

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
from (eMMC, SD card or SPI-NOR). The boot source can be modified by setting
the `boot_targets` variable to `mmc0` (eMMC), `mmc1` (SD card), `sf0` (SPI-NOR),
`usb0` (USB mass storage) or `pxe` (netboot). Multiple boot sources separated
with spaces will be tried in the order they are specified.

For MMC and USB devices, U-Boot will load a script image `boot.scr` from the
boot partition of the selected medium. The script provided in this BSP's
default configuration then selects the kernel and its configuration based on
`/boot/extlinux/extlinux.conf`.

For netboot, a file in the same format will be loaded via TFTP from
`/pxelinux.cfg/default-arm-k3-tqma64xxl` after an IP address has been obtained
via DHCP.

### WLAN support

The `extlinux.conf` generated by this BSP's default configuration will display
a menu to choose between SD card (default) and WLAN support. The default
can be modified by setting the `pxe_label_override` environment variable:
```
setenv pxe_label_override sdcard # Boot with SD card
setenv pxe_label_override wlan # Boot with WLAN
saveenv # Persist configuration
```

As the SD card and WLAN adapter require exclusive control of the same SDHC/SDIO
bus, they can't be enabled at the same time. A different OS boot source (usually
eMMC or SPI-NOR) must be used when WLAN is enabled.

### Program system image

**Note:** Do not use the commands described in the following to overwrite the
root filesystem you are currently running from.

#### SD card / eMMC

To program a complete system image, write the [WIC image](#build-artifacts) to
SD card / eMMC at offset 0. The following command can be used to write a file
`/mnt/image.wic` to the eMMC:
```
dd if=/mnt/image.wic of=/dev/mmcblk0 bs=1M
```

#### SPI-NOR

To program the root filesystem, format `/dev/mtd/by-name/ospi.rootfs` as a UBI volume and write
the UBI image to it. If the image is stored at `/mnt/rootfs.ubi` (for example
on a USB drive), use the following command:
```
ubiformat /dev/mtd/by-name/ospi.rootfs -f /mnt/rootfs.ubi
```

To check check usability of the programmed root filesystem, the following
commands can be used:
```
ubiattach -p /dev/mtd/by-name/ospi.rootfs
mount -t ubifs ubi0:rootfs /mnt
```

### Updates

When booting from eMMC / SD card, the bootloader and system firmware are loaded
from a FAT partition (the first partition of the boot medium by default). They
can be updated by replacing the files on this partition. See the
[build artifacts](#build-artifacts) section for a list of relevant files.

For SPI-NOR boot, each stage is loaded from a fixed offset in the flash. The MTD
partition list is configured to match these offsets.

For convenience, the following commands can be used in U-Boot to update these
files via TFTP:
```
run update_uboot_mmc0 # Update bootloader on eMMC
run update_bootscript_mmc0 # Update boot.scr on eMMC
run update_uboot_mmc1 # Update bootloader on SD card
run update_bootscript_mmc1 # Update boot.scr on SD card
run update_uboot_sf0 # Update bootloader on SPI-NOR flash
run update_bootscript_sf0 # Update ospi.script partition on SPI-NOR flash
```
The environment variables `tiboot3_name`, `tispl_name`, `uboot_name` and
`bootscript_name` can be modified to control the filenames requested via TFTP.

By default, the files `tiboot3.bin`, `tispl.bin` and `u-boot.img` are requested.
The 3 stages of the bootloader are always updated at the same time, so potential
incompatiblities between stages of old and new versions are avoided.

Kernel and Device Trees are part of the root filesystem. They cannot be updated
from U-Boot separately from the filesystem.

### M4/R5 cores

The M4 and R5 example programs provided by TI can be run out-of-the-box using
the Linux RemoteProc driver. When a program is found at the location specified
in the Device Tree, it will be started on the corresponding core automatically
during Linux boot. The TQ BSP images for the MBaX4XxL contain a simple RPMsg IPC
echo test program. By running `modprobe rpmsg_client_sample`, a communication
test with the echo programs can be run, which will write its results to the
kernel log.

The TQMa64xxL DTSI defines a number of reserved memory regions that are used by
by these progams and the other the M4/R5 examples provided with the AM64x MCU+
SDK (per-core `main_r5fss_*_memory_region` and `mcu_m4fss_*_memory_region`, as
well as the common `rtos_ipc_memory_region` used for inter-processor
communication).

When running M4/R5 programs with a different memory layout, the reserved regions
must be adjusted accordingly in the board DTS. When the MCU cores are unused, it
is also possible to disable the reserved regions using `status = "disabled"` to
free up the memory for use by Linux.

## Support Wiki

See [TQ Support Wiki for TQMa64xxL](https://support.tq-group.com/en/arm/tqma64xxl)
