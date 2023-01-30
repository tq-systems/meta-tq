# Using the Universal Update Utility (UUU)

[[_TOC_]]

## Bootstream

To build a bootstream usable with UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

```
UBOOT_CONFIG = "mfgtool"
IMXBOOT_TARGETS = "flash_spl_uboot"
```

Rebuild boot stream:

```
bitbake imx-boot
```

## Usage

### Preparation

You need to set your board to Boot Mode for `Serial Downloader` or make sure that
no bootstream can be found on the selected boot media and on eventually configured
fallback media. See your boards Boot DIP Switches section how to configure Boot Mode
for `Serial Downloader`.

### use builtin commands

#### Boot a board

Use the bootstream containing U-Boot capable of handling SDP together with
UUU tool to boot a new / bricked board (see Artifacts section for your board for
name the exact name of the bootstream image for mfgtool/uuu configuration):

```
sudo uuu -b spl <bootstream for mfgtool/uuu>
```

#### Program eMMC

Use the bootstream containing U-Boot capable of handling SDP together with
UUU tool to boot a new / bricked board and use the booted system to programm
the bootstream and the wic image to eMMC (see Artifacts section for your board
for the exact names of the images to use):

```
sudo uuu -b emmc_all <bootstream for mfgtool/uuu> <wic image>
```

**Attention:** The bootstream is written to eMMC boot partition and eMMC is
configured to use boot partition. Simple update script for bootstream updates
only bootstream in user area of eMMC.

#### Use a custom workflow

One can collect `uuu` commands in a cmdlist file for a custom workflow.
The following example can be used to program a wic image to eMMC without
writing the boot stream to the eMMC boot partition. Following example is
for TQMa8MPxL on MBa8MPxL, tested with `uuu` version 1.5.4.

File `uuu.tqma8mpxl`:

```
uuu_version 1.5.4

SDPS: delay 500
SDPS: @ boot -f @BOOTSTREAM@
SDPS: delay 1000
FB: ucmd setenv fastboot_dev mmc
FB: ucmd setenv mmcdev ${emmc_dev}
FB: ucmd mmc dev ${emmc_dev}
FB: @ flash -raw2sparse all @WICIMAGE@
FB: done
```

The cmdlist file can be used with the following command line:

`uuu -e BOOTSTREAM=<bootstream for uuu> -e WICIMAGE=<wic image for eMMC> uuu.tqma8mpxl`

## Further Reading

Detailed documentation for UUU can be found [here](https://github.com/NXPmicro/mfgtools/wiki).
