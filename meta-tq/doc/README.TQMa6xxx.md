# Common features of TQMa62xx\[L\]/TQMa64xxL/TQMa67xx\[L\]

[[_TOC_]]

## Build artifacts

Artifacts can be found at:
`deploy-ti/images/${MACHINE}`

* fitImage: Combined kernel image with Device Trees and overlays
* \*.dtb: Device Tree blobs
* \*.dtbo: Device Tree overlays
* Image: Linux kernel image
* \*.wic[.<compress>]: SD/eMMC/USB storage system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* tiboot3-*.bin: first-stage bootloader (R5 core)
* u-boot.img: last-stage bootloader (A53 core, includes ATF and OPTEE)
* extlinux.conf: Boot configuration for U-Boot distroboot
* boot-blockdev.scr: U-Boot boot script image for boot from eMMC/SD/USB
* boot-ubi.scr: U-Boot boot script image for boot from UBIFS (SPI-NOR)

## HowTo

### Program system image

**Note:** Do not use the commands described in the following to overwrite the
root filesystem you are currently running from.

#### SD card / eMMC

To program a complete system image, write the [WIC image](#build-artifacts) to
SD card / eMMC at offset 0. The following command can be used to write a file
`/mnt/image.wic` to the eMMC:
```sh
dd if=/mnt/image.wic of=/dev/mmcblk0 bs=1M
```

#### SPI-NOR

To program the root filesystem, format `/dev/mtd/by-name/ospi.rootfs` as a UBI volume and write
the UBI image to it. If the image is stored at `/mnt/rootfs.ubi` (for example
on a USB drive), use the following command:
```sh
ubiformat /dev/mtd/by-name/ospi.rootfs -f /mnt/rootfs.ubi
```

To check check usability of the programmed root filesystem, the following
commands can be used:
```sh
ubiattach -p /dev/mtd/by-name/ospi.rootfs
mount -t ubifs ubi0:root /mnt
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
The environment variables `tiboot3_name`, `uboot_name` and `bootscript_name`
can be modified to control the filenames requested via TFTP.

By default, the files `tiboot3.bin` and `u-boot.img` are requested. The 2 stages
of the bootloader are always updated at the same time, so potential
incompatiblities between stages of old and new versions are avoided.

Kernel and Device Trees are part of the root filesystem. They cannot be updated
from U-Boot separately from the filesystem.

### Inline ECC support

Inline ECC can be enabled in the U-Boot configuration, at the cost of slightly
increasing boot time and reducing usable memory by 1/8. A config snippet for
this configuration is provided in meta-tq and can be enabled by adding the
following line to `local.conf`:
```
SRC_URI:append:pn-u-boot-ti-tq:k3r5 = " file://inline-ecc.cfg"
```

### M4/R5 cores

On the AM62x, the R5 core has a special role and is used for Device Management
(clock and power configuration). The firmware running on the R5 core is embedded
in `tispl.bin` together with the bootloader's A53 SPL. The firmware also
includes an IPC echo example. It is possible to replace the R5 firmware with a
custom program as long as it also includes the Device Management code. On the
AM64x, the R5 can be programmed freely.

The M4 and R5 example programs provided by TI can be run out-of-the-box using
the Linux RemoteProc driver. When a program is found at the location specified
in the Device Tree, it will be started on the corresponding core automatically
during Linux boot.

If a program is started by the RemoteProc driver, or a program already running
is detected, their RPMsg devices can be found as symlinks under
`/sys/bus/rpmsg/devices` (with the symlink targets showing which RemoteProc
device they're associated with). Linux will automatically probe kernel drivers
for such devices based on their names, but it is also possible manually bind a
driver to a device.

The TQ BSP images contain simple RPMsg echo test programs for all R5 cores.
By running the following commands, communication tests with the echo programs
can be run, which will write their results to the kernel log:
```sh
modprobe rpmsg_client_sample

# Set one of the following:

# R5 core on TQMa62xx[L]
virtios='virtio0'
# 4x R5 core on TQMa64xxL
virtios='virtio0 virtio1 virtio2 virtio3'

for virtio in ${virtios}; do
    echo rpmsg_client_sample > /sys/bus/rpmsg/devices/${virtio}.ti.ipc4.ping-pong.-1.13/driver_override
    echo ${virtio}.ti.ipc4.ping-pong.-1.13 > /sys/bus/rpmsg/drivers/rpmsg_client_sample/bind
done
```

The TQMa6xxx DTSIs define a number of reserved memory regions that are used by
these programs and the other the M4/R5 examples provided with the
AM62x/AM64x/J722S MCU+ SDKs (per-core `main_r5fss_*_memory_region` and
`mcu_m4fss_*_memory_region`, as well as the common `rtos_ipc_memory_region` used
for inter-processor communication).

When running M4/R5 programs with a different memory layout, the reserved regions
must be adjusted accordingly in the board DTS. When the MCU cores are unused, it
is also possible to disable the reserved regions using `status = "disabled"` to
free up the memory for use by Linux. Note that on the AM62x, the R5 core is
always used for Device Management and cannot be disabled completely.

### Digital I/O

The boards' digital I/O pins on connector X16 (MBa62xx) and X35 (MBaX4XxL) are
unpowered by default. To use them, they can either be powered from the board's
24V rail by connecting pins 1A and 2A, or by connecting an external power supply
to 2A.

The digital outputs (pins 3A to 6A) can be set using the `gpioset` command:
```sh
gpioset EN_DIG_OUT_1=1
gpioset EN_DIG_OUT_2=1
gpioset EN_DIG_OUT_3=1
gpioset EN_DIG_OUT_4=1
gpioset EN_DIG_OUT_1=0
gpioset EN_DIG_OUT_2=0
gpioset EN_DIG_OUT_3=0
gpioset EN_DIG_OUT_4=0
```

Each of the `EN_DIG_OUT_n` pins has a corresponding `STATUS_OUT_n` pin for
fault detection:
```sh
gpioget STATUS_OUT_1
gpioget STATUS_OUT_2
gpioget STATUS_OUT_3
gpioget STATUS_OUT_4
```

`gpioget` is also used to query the digital inputs (pins 7A to 10A):
```sh
gpioget DIG_IN_1
gpioget DIG_IN_2
gpioget DIG_IN_3
gpioget DIG_IN_4
```
