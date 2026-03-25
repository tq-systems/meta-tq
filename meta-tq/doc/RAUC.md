# BSP Updates Using RAUC

`meta-tq` and `meta-dumpling` contain basic configuration and example recipes
to allow for easy integration of the software update tool
[RAUC](https://rauc.readthedocs.io/). This is supported ouf-of-the-box for
platforms using [Distroboot](README.Distroboot.md), which are:

- TQMa62xx\[L\]
- TQMa64xxL
- TQMa8MPxL
- TQMa91xx
- TQMa93xx

## Features

The provided example sets up two rootfs partitions (including a kernel image
and `extlinux.conf` each) and extends the boot script run by U-Boot to select
one of these "boot slots" during boot.

RAUC will always write its update bundle to the currently inactive partition and
only switch the boot order in the U-Boot environment after the update has
finished, allowing reliable updates that will not result in an unbootable system
even if the update process is interrupted.

The boot script will also keep track of the number of attempted boots for each
boot slot, allowing to fall back to the previously active slot after a number
of failed attempts.

Updating the bootloader, boot script or other contents of the boot partition
is currently not supported by the example configuration.

### Data Partition

When using the example distros from meta-dumpling, a persistent data partition
that is not touched by updates will be mounted during boot (under `/srv/data` by
default, adjustable using `TQ_DATA_MOUNT_TARGET`).

For demonstration purposes, the mount source is selected automatically, mounting
it from the same device as the root filesystem (eMMC or SD card); in a
production environment, a static fstab entry may be more appropriate.

Without further configuration, no data is stored in this partition. See the
section [Read-only rootfs](#read-only-rootfs) for examples of files that may
be useful to persist across updates.

## Prerequisites

- Check out the [meta-rauc](https://github.com/rauc/meta-rauc/) branch matching
  the used Yocto version into your `sources` directory and add it to the
  `bblayers.conf` of your build directory
- Enable the "rauc" distro feature, in a custom distro config or `local.conf`:

      DISTRO_FEATURES:append = " rauc"

The examples found in the "dumpling*" distros in `meta-dumpling` provide a
starting point for project-specific configuration.

The bootloader / boot flow needs to interact with RAUC and the A/B partitioning scheme
as described by RAUC documentation. For `meta-tq` an example implementation is given
using U-Boot and [Distroboot](README.Distroboot.md).

To allow fallback from non booting OS in an A/B setup it is important to make sure,
that the bootloader activates the hardware watchdog of the CPU und configures the
watchdog timeout long enough that the OS to be booted can service the watchdog and mark
the boot as successful before the watchdog times out.

## Configuration

Enabling the "rauc" distro feature will result in a number of changes in the
BSP:

- A different partitioning template (found under `meta-tq/wic`, marked by the
  suffix `-rauc-ab.wks.in`) will be used to provide two identically sized rootfs
  partitions and a separate data partition
- The `tq-bootscripts` package will install `boot-blockdev-rauc.scr` instead of
  `boot-blockdev.scr` to the boot partition to select one of the two root
  filesystems during boot based on the U-Boot environment and provide fallback
  to the other partition after 3 failed boots

Partition sizes can be adjusted using the `WIC_ROOTPART_SIZE` and
`WIC_DATAPART_SIZE` variables. These values should be selected carefully, as
the partitioning can't be modified during updates, so enough space for future
additions must be reserved.

### Example Configuration and Update Bundle

The dumpling distros come with a full example configuration for RAUC:

- An example X.509 self-signed certificate and private key used for upgrade
  bundle signing
- Two `system.conf` variants (for boot from eMMC or SD card, selected
  automatically during boot)
- A bundle recipe `tq-bundle`

`tq-bundle` is based on `tq-image-generic-debug` by default. The
`RAUC_SLOT_rootfs` variable can be set to select a different image.

The compatible string in the bundle and `system.conf` defaults to
`dumpling-${MACHINE}`, which can be modified by setting the
`RAUC_BUNDLE_COMPATIBLE` variable.

### Testing RAUC

Before using RAUC on the eMMC make sure that the U-Boot supports
Distroboot and configure the environment accordingly:
```sh
setenv bootcmd "run distro_bootcmd"
setenv boot_targets "mmc0"
```

On the running system, the update bundle can be installed into the inactive boot
slot by running
```sh
rauc install <BUNDLE-FILE>
```
after copying the file onto the device. After successful installation, the
U-Boot environment is modified to activate the other slot, so a following reboot
will boot into the newly installed system.

To verify which slot is in use one can query using
```sh
rauc status
```

### rauc-mark-good.service

To allow rollback/fallback from a non-bootable boot slot,
`boot-blockdev-rauc.scr` keeps track of the number of attempted boots for each
slot. A service `rauc-mark-good.service` or initscript rauc-mark-good is
installed by default to reset the counter to 3 remaining attempts after every
successful boot.

In systemd-based distros, the service is ordered after the special
`boot-complete.target` target, however what it means for a boot to be
"successful" is a more difficult question that needs to be answered for each
project individually.

A software upgrade may also involve a "point of no return" after which no
rollback is possibly anymore (for example because of data migration), which
would require the other slot to be marked invalid (using a command like
`rauc status mark-bad other` or, more permanently, deleting the contents of the
inactive rootfs using `blkdiscard`).

### Read-Only Rootfs

As the root filesystem will be replaced on each update, variable files must be
stored in a separate data partition to persist across updates.

It is recommended to combine RAUC updates with a read-only root filesystem.
This requires the following adjustments:
- Add `read-only-rootfs` to `IMAGE_FEATURES` (in an image recipe, or using
  `EXTRA_IMAGE_FEATURES` in a distro config or `local.conf`)
- Change `extlinux.conf` to mount the rootfs read-only by changing
  `UBOOT_EXTLINUX_KERNEL_ARGS` from "rootwait rw" to "rootwait"

Enabling `read-only-rootfs` will move some files from `/var` into a tmpfs
mounted under `/var/volatile`, causing them to be regenerated on each boot.
This may not always be desirable (for example for systemd machine IDs, SSH host
keys or log/journal files), so some customization may be necessary to store
them on the persistent data partition instead.

`CONFIG_OVERLAY_FS` should be enabled in the kernel configuration when
`read-only-rootfs` is used, as it will make the setup of `/var/volatile` during
boot more efficient.
