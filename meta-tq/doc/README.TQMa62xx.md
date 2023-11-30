# TQMa62xx on MBa62xx carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6234, TQMa6254: Module revisions REV.010x
* MBa62xx: Board revisions REV.010x

### Versions

_Bootloader:_

* uboot-ti-tq-2023.01 (based on ti-u-boot 2023.01)

_Kernel:_

* linux-ti-tq-6.1 (based on ti-rt-linux-6.1.y)

### Known issues

* Waking up from Suspend-to-RAM currently does not work on the MBa62xx.
* Under high system load, the AM62x may become unstable during audio playback or
  recording.
* During audio playback or recording, the kernel prints various error messages
  regarding DMA controller configuration timeouts.
* Very rarely, under high system load, the I2C controller of the AM62x has been
  seen to get stuck in a state in which it permanently pulls down the SCL pin
  until the controller is reset.
* The OTG ID pin of the Micro-USB port (X10) is ignored in U-Boot. The mode of
  the port is determined by the boot mode:
  - When booting in USB host mode (from mass storage), the port uses host
    mode. Access to the connected USB storage device is possible from the
    U-Boot command line.
  - For all other boot modes, the port will be in device mode. The "dfu"
    command can be used to flash the boot media from a connected host.

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

* tiboot3-am62x-gp-evm.bin (AM62x General Purpose variant)
* tiboot3-am62x-hs-fs-evm.bin (AM62x High Security variant, field-securable)
* tiboot3-am62x-hs-evm.bin (AM62x High Security variant, security enforced)

Please refer to the Secure Device Processor SDK documention for more information
on the High Security CPU variants. This documentation must be obtained directly
from TI.

To select the variant to use, set the `SYSFW_DEFAULT_VARIANT` variable to
"am62x-gp", "am62x-hs-fs" or "am62x-hs" (in `local.conf` or a custom
machine definition), to match the used AM62x CPU variant and security
enforcement status. The default value is "am62x-gp".

The selected variant will be installed to the boot partition of the generated
WIC images as `tiboot3.bin`. It is possible to change an existing image to boot
on a different CPU variant by mounting the boot partition and renaming one of
the bootloader binaries to `tiboot3.bin`.

## HowTo

### MBa62xx DIP switch settings for boot

#### SD card

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |     |     |
| OFF     |     |     |     |     |

#### eMMC

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |     |  x  |     |     |

#### SPI-NOR

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |  x  |
| OFF     |     |     |  x  |     |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |     |     |
| OFF     |     |  x  |  x  |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |     |     |
| OFF     |  x  |     |     |     |

#### USB host (mass storage)

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |     |     |

#### USB device (dfu-util)

|         |  S4 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |  x  |     |     |
| OFF     |     |     |  x  |  x  |

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |  x  |

|         |  S6 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |     |     |
| OFF     |  x  |     |     |     |

The `dfu-util` command can be used to load U-Boot from a connected USB host:

```
# Load all U-Boot stages in sequence
dfu-util -R -a bootloader -D tiboot3.bin
dfu-util -R -a tispl.bin -D tispl.bin
dfu-util -R -a u-boot.img -D u-boot.img
```

Please refer to the
[AM62x Processor SDK Documentation](https://downloads.ti.com/processor-sdk-linux/esd/AM62X/09_00_00_03/exports/docs/linux/Foundational_Components/U-Boot/Users-Guide.html)
for information on the usage of `dfu-util` to flash boot media.

### OS boot

See the [Distroboot README](README.Distroboot.md).

### Display and camera support

The `extlinux.conf` generated by this BSP's default configuration will display
a menu to choose between a number of different LVDS display and MIPI-CSI camera
configurations. The default can be modified by setting the `pxe_label_override`
environment variable:
```
setenv pxe_label_override 'label' # Set default boot option to 'label'
saveenv # Persist configuration
```

The following labels are currently defined:

| Label                       |  Display            | Camera                                     |
| --------------------------- | ------------------- | ------------------------------------------ |
| default                     | none                | none                                       |
| lvds-tm070jdhg30            | Tianma TM070JDHG30  | none                                       |
| lvds-vesa-fhd               | Generic VESA FullHD | none                                       |
| csi-imx327                  | none                | VisionComponents module with IMX327 sensor |
| csi-ov9281                  | none                | VisionComponents module with OV9281 sensor |
| lvds-tm070jdhg30-csi-imx327 | Tianma TM070JDHG30  | VisionComponents module with IMX327 sensor |
| lvds-tm070jdhg30-csi-ov9281 | Tianma TM070JDHG30  | VisionComponents module with OV9281 sensor |
| lvds-vesa-fhd-csi-imx327    | Generic VESA FullHD | VisionComponents module with IMX327 sensor |
| lvds-vesa-fhd-csi-ov9281    | Generic VESA FullHD | VisionComponents module with OV9281 sensor |

The following commands can be used to capture the camera picture and display it
on the display:

For IMX327:
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:SRGGB10/1280x720]'
media-ctl -V '"imx327 1-001a":0[fmt:SRGGB10/1280x720 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb,bpp=10,width=1280,height=720 ! \
  bayer2rgbneon show-fps=t reduce-bpp=t ! video/x-raw,format=RGBx ! autovideoconvert ! waylandsink
```

For OV9281:
```
media-ctl -V '"30102000.ticsi2rx":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"cdns_csi2rx.30101000.csi-bridge":0[fmt:Y8_1X8/1280x800]'
media-ctl -V '"ov9281 1-0060":0[fmt:Y8_1X8/1280x800 field:none]'
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
  autovideoconvert ! waylandsink
```

`autovideosink` will render the video using Wayland by default. Alternatively,
the video can be displayed in fullscreen directly on a Linux framebuffer device
by stopping the Wayland compositor with `systemctl stop weston.service` and
replacing `autovideosink sync=false` with `fbdevsink` in the gst-launch-1.0
command.

Note that the TQMa62xx does not have hardware acceleration for the conversion
from the Bayer pixel format to RGB. Even when using the optimized bayer2rgbneon
plugin provided in our BSP, framerates with the IMX327 are very low.

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

The R5 core of the AM62x is used for Device Management (clock and power
configuration). The firmware running on the R5 core is embedded in `tispl.bin`
together with the bootloader's A53 SPL. The firmware also includes an IPC echo
example. It is possible to replace the R5 firmware with a custom program as long
as it also includes the Device Management code.

The TQ BSP images for the MBa62xx also contain a simple RPMsg IPC echo test
program for the M4 core.

For both the R5 and the M4 cores, inter-processor communication is possible
using RPMsg. The RemoteProc driver setting up the RPmsg channel on the Linux
side is also responsible for booting a firmware on the M4 if one is found under
a path specified in the Device Tree. By running `modprobe rpmsg_client_sample`,
a communication test with the echo programs can be run, which will write its
results to the kernel log.

The TQMa62xx DTSI defines a number of reserved memory regions that are used by
by these progams and the other the M4/R5 examples provided with the AM62x MCU+
SDK (per-core `main_r5fss_*_memory_region` and `mcu_m4fss_*_memory_region`, as
well as the common `rtos_ipc_memory_region` used for inter-processor
communication).

When running M4/R5 programs with a different memory layout, the reserved regions
must be adjusted accordingly in the board DTS. When the MCU cores are unused, it
is also possible to disable the reserved regions using `status = "disabled"` to
free up the memory for use by Linux. Note that the R5 core is always used for
Device Management and cannot be disabled completely.

## Support Wiki

See [TQ Support Wiki for TQMa62xx](https://support.tq-group.com/en/arm/tqma62xx)
