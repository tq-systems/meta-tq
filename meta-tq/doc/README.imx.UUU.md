# Using the Universal Update Utility (UUU)

[[_TOC_]]

## Bootstream

### ARMv7a / 32 Bit i.MX SoC

To build a bootstream usable with UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations, which
supports UUU enabled images):

```
UBOOT_CONFIG += "uuu"
# or
UBOOT_CONFIG += "uuu_lga"
```

Rebuild boot loader:

```
bitbake virtual/bootloader
```

### ARMv8a / 64 Bit i.MX SoC

To build a bootstream usable with UUU tool the following settings needs to be in your
configuration. (This is already the case for starterkit machine configurations):

|  SOC      |  SOM       | IMXBOOT_TARGET   | U-Boot Config    |
|-----------|------------|------------------|------------------|
| i.MX8MQ   | TQMa8Mx    | flash_spl_uboot  | <size>gb_uuu |
| i.MX8MMini| TQMa8MxML  | flash_spl_uboot  | <size>gb_uuu |
| i.MX8MNano| TQMa8MxNL  | flash_spl_uboot  | uuu          |
| i.MX8MPlus| TQMa8MPxL  | flash_spl_uboot  | uuu          |
|           | TQMa8MPxS  | flash_spl_uboot  | uuu          |
| i.MX8     | TQMa8QM    | flash_spl        | uuu          |
| i.MX8X    | TQMa8Xx    | flash_spl        | uuu          |
|           | TQMa8Xx4   | flash_spl        | uuu          |
|           | TQMa8XxS   | flash_spl        | uuu          |
| i.MX91    | TQMa91xxCA | flash_singleboot | uuu          |
|           | TQMa91xxLA | flash_singleboot | uuu          |
| i.MX93    | TQMa93xxCA | flash_singleboot | uuu          |
|           | TQMa93xxLA | flash_singleboot | uuu          |


```
UBOOT_CONFIG += "<U-Boot Config>"
IMXBOOT_TARGETS += "<IMXBOOT_TARGET>"
```

Rebuild boot stream:

```
bitbake imx-boot-tq
```

## Usage

### Preparation

You need to set your board to Boot Mode for `Serial Downloader` or make sure that
no bootstream can be found on the selected boot media and on eventually configured
fallback media. See your boards Boot DIP switches section how to configure Boot Mode
for `Serial Downloader`.

Please note, that the builtin scripts of UUU make some assumtions that may or may not fit
your use case. Check if your U-Boot configuration and the builtins fit your use case.
You can use the cmdlist support of UUU to create tailored workflows. Some board specific
examples are given below.

### BSP Example Configuration

For all boards with support for `Serial Download Mode` multiple U-Boot configurations
are given:

- Configuration with support for `Serial Download Mode`. The resulting image has features enabled
  for provisioning / service use:
  * random MAC address if none is in environment / fuses
  * no access to stored environments
  * SDP (serial download protocol)
  * fastboot (also preconfigured as bootcmd, can be terminated with `CTRL_C`)
- Configuration(s) with support for boot devices. The images have the above features disabled
  since they are mostly not needed or undesired for production firmware.

### Use Builtin Commands

#### Boot a Board

Use the bootstream containing U-Boot capable of handling SDP together with
UUU tool to boot a new / bricked board (see Artefacts section for your board for
the exact name of the bootstream image for uuu configuration):

If using U-Boot with SPL:

```
sudo uuu -b spl <bootstream for uuu>
```

If using U-Boot without SPL:

```
sudo uuu <bootstream for uuu>
```

#### Program Image to eMMC

Use the bootstream containing U-Boot capable of handling SDP together with
UUU tool to boot a new / bricked board and use the booted system to programm
the bootstream and the wic image to eMMC (see Artefacts section for your board
for the exact names of the images to use):

```
sudo uuu -b emmc_all <bootstream for uuu> <wic image>
```

**Attention:** The bootstream from first argument will be written to eMMC boot partition
and eMMC is configured to use boot partition. This bootstream is expected to be able
to boot from eMMC and to handle serial download.

#### Use a Custom Workflow

One can collect `uuu` commands in a cmdlist file for a custom workflow.
The following example can be used to program a wic image to eMMC without
writing the boot stream to the eMMC boot partition. 

##### TQMa8MxML on MBa8Mx

Following example is tested with `uuu` version 1.5.4.

File `uuu.tqma8mxml`:

```
uuu_version 1.5.4

SDP: delay 500
SDP: @ boot -f @BOOTSTREAM@

SDPV: delay 1000
SDPV: @ write -f @BOOTSTREAM@ -skipspl -scanterm -scanlimited 0x800000
SDPV: jump -scanlimited 0x800000

FB: ucmd setenv fastboot_dev mmc
FB: ucmd setenv mmcdev ${emmc_dev}
FB: ucmd mmc dev ${emmc_dev}
FB: @ flash -raw2sparse all @WICIMAGE@
FB: done
```

The cmdlist file can be used with the following command line:

`uuu -e BOOTSTREAM=<bootstream for uuu> -e WICIMAGE=<wic image for eMMC> uuu.tqma8mxml`

##### TQMa8MPxL on MBa8MPxL

Following example is tested with `uuu` version 1.5.4.

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
