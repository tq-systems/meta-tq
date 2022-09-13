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

You need to set your board to Boot Mode for `Serial Downloader` or make sure that
no bootstream can be found on the selected boot media and on eventually configured
fallback media. See your boards Boot DIP Switches section how to configure Boot Mode
for `Serial Downloader`.

Use the bootstream containing U-Boot capable of handling SDP together with
UUU tool to boot a new / bricked board (see Artifacts section for your board for
name of the bootstream image):

```
sudo uuu -b spl <bootstream>
```

Detailed documentation for UUU can be found [here](https://github.com/NXPmicro/mfgtools/wiki).
