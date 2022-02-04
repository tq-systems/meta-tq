# TQMaRZG2x

This README contains some useful information for TQMaRZG2x on MBaRZG2x

[[_TOC_]]

## Variants
* TQMaRZG2M (r8a774a1) 8GiB RAM Rev.02xx
* TQMaRZG2M (r8a774a1) 2GiB RAM Rev.02xx
* TQMaRZG2N (r8a774b1) 2GiB RAM Rev.02xx
* TQMaRZG2H (r8a774e1) 4GiB RAM Rev.02xx

## Version information for software components

### ATF
* ATF v2.3 based on Renesas Verified Linux Package VLP64 1.06

### U-Boot
* U-Boot 2018.09 based on Renesas Verified Linux Package VLP64 1.06

### Linux
* Linux 4.19-CIP based on Renesas Verified Linux Package VLP64 1.06

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:
* GPIO Status
* eMMC
  * Boot
  * Read
  * Write
* SDHC
  * Read
  * Write
* I2C
  * Board EEPROM Read
  * Board EEPROM Read/Write
  * Bus probing
* Ethernet
* USB

### Linux:
* GPIOs
  * LEDs
* eMMC
* SDHC
* I2C
* Ethernet
* CAN
* USB
* USB - OTG
* HDMI
* LVDS
* GPU
* Video CODECs

**TODO or not tested / supported**
* ECC
* NFS boot
* U-Boot
  * LEDs (not configured in device tree)
* Linux
  * SIM-Card
  * Audio
  * RGB-Display-Interface
  * Mikro-Modul
  * Wakeup 
  * MIPI-CSI
  * PCIe
  * SATA (RZG2H and RZG2N only)
  * SPI Nor - Flash (except tqmarzg2m_aa-mbarzg2x)

## Known Issues
* U-Boot update script 'update_uboot_mmc' does not write to boot partition, but data partition instead.
* After a hardware reset (S9), PCIe does not work any more on all module variants.
* After a hardware reset (S9), USB3 does not work any more on the TQMaRZG2N variant.
* U-Boot command 'gpio status' works, but generates an error messsage: 'Error reading output register'
* Audio codec and driver are not configured
* ID parsing in module eeprom causes 'unknown hardware variant' due to wrong
string comparison in U-Boot
* 4K resolution is laggy
* Powersave (Sleeping works but no wakeup support through RTC or button)
* USER_BUTTONs are not configured in device tree
* Devices on I2C Bus 4 cannot be detected with 'i2cdetect -l'

## Notes
* SDHC:
  * TQMaRZG2x on a MBaRZG2x cannot boot from SD-Card
  * The only bootable SD/eMMC-Interface of the CPU is connected to eMMC
* SPI Nor - Flash:
  * TQMaRZG2M_AA does not have a SPI NOR-Flash

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* Flash-Writer artifacts
  * AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_TQMARZG2[H_C|M_E|M_AA|N_B].mot
  * bl2-${MACHINE}.srec: Primary Boot Loader for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
  * bl31-${MACHINE}.srec: Socondary Boot Loader for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
  * bootparam_sa0.srec: ATF Boot parameters. Needed by Flash-Writer.
  * cert\_header_sa6\_spi-nor.srec: Cerificate header for SPI NOR boot. Needed by Flash-Writer.
* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)* u-boot-elf-${MACHINE}.srec: U-Boot Binary for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
* u-boot-${MACHINE}.bin: U-Boot Binary for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by emmc update scripts.

## Dip Switches

### Boot Mode

#### eMMC

| DIP S2 |  8  |  7  |  6  |  5  |  4  |
| ------ | --- | --- | --- | --- | --- |
| ON     |     |  X  |     |  X  |  X  |
| OFF    |  X  |     |  X  |     |     |


#### SPI-Nor-Flash

| DIP S2 |  8  |  7  |  6  |  5  |  4  |
| ------ | --- | --- | --- | --- | --- |
| ON     |     |     |     |  X  |     |
| OFF    |  X  |  X  |  X  |     |  X  |


### M.2-PCIe/SATA (X22)

#### PCIe

| DIP S3 |  4  |
| ------ | --- |
| ON     |     |
| OFF    |  X  |

| DIP S10 |  1  |
| ------- | --- |
| ON      |     |
| OFF     |  X  |

#### SATA

| DIP S3 |  4  |
| ------ | --- |
| ON     |  X  |
| OFF    |     |

| DIP S10 |  1  |
| ------- | --- |
| ON      |  X  |
| OFF     |     |

## Boot device initialisation

_TODO_

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

There are scripts to update the U-Boot, Kernel and Devicetree.
These scripts are named `update_[uboot|fdt|kernel]_[mmc|sd]`.

U-Boot update only works for emmc, because there is no driver for SPI-NOR in U-Boot.
To write to SPI-MOR, use the Flash-Writer tool.

_Note_: Update and start scripts expect a partitioned / initialized SD-Card or
e-MMC.

_U-Boot environment variables_

* `uboot`: name of bootstream image (default = bootstream.bin)
* `mmcdev`: 0 for e-MMC, 1 for SD-Card (automatically generated,
  can be overwritten)
  `mmcpart`: partition number for kernel and devicetree (default = 1)
  `mmcpath`: path to kernel and device tree (default = /)
* `fdt_file`: device tree blob,
* `image`: kernel image,

_SD / e-MMC_

Download bootstream from TFTP and update:

`run update_uboot_mmc`

Download device tree blob from TFTP and update:

`run update_fdt_mmc`

Download kernel image from TFTP and update:

`run update_kernel_mmc`

## Memory Layout

### SPI-NOR
* 0x000000000000-0x000000040000 : "bootparam"
* 0x000000040000-0x000000180000 : "bl2"
* 0x000000180000-0x0000001c0000 : "cert_header_sa6"
* 0x0000001c0000-0x000000200000 : "bl31"
* 0x000000200000-0x000000300000 : "tee"
* 0x000000300000-0x0000003f0000 : "uboot"
* 0x0000003f0000-0x000000400000 : "uboot-env"
* 0x000000400000-0x000001000000 : "user"

### eMMC, boot partition 1
* 0x000000 : "bootparam"
* 0x003C00 : "bl2"
* 0x030000 : "cert\_header\_sa6"
* 0x040000 : "bl31"

### eMMC, boot partition 2
* 0x000000 : "uboot"
* 0x3e0000 : "uboot-env"

## HOWTOs

### Prepare Build-Environment for Renesas images

To build the Renesas images, make sure to have meta-rzg2 checked out at
`${BSPDIR}/sources/`.

In order to use GPU and video codecs, proprietary drivers from Renesas are
needed. After a registration on www.renesas.com, these can be downloaded here:
https://www.renesas.com/us/en/products/microcontrollers-microprocessors/rz-cortex-a-mpus/rzg-linux-platform/rzg-marketplace/verified-linux-package/rzg2-mlp-eva

After downloading the multimedia package, extract the files to the sources
directory `${BSPDIR}/sources/`:
```
tar -C <BSPDIR>/sources -zxf <PATH/TO>/RZG2_Group_*_Software_Package_for_Linux_*.tar.gz
```

Now, the extracted files need to be deployed to the right places. This is done by a script
in meta-rzg2. Change directory to the meta-rzg2 layer and run the script:
```
cd <BSPDIR>/sources/meta-rzg2
sh docs/sample/copyscript/copy_proprietary_softwares.sh -f <BSPDIR>/sources/proprietary
```

### Configure Renesas images to fit your needs

The buildprocess can be configured with multiple options. For a detailed
explanation see:
https://www.renesas.com/document/mat/linux-interface-specification-yocto-recipe-start-guide-rev109?language=en&r=1054546
pages 15-17 or https://github.com/renesas-rz/meta-rzg2#build-configs.
According to meta-rzg2 these options should be set in your local.conf. To allow
reproducible builds, these options are split into distro and machine
configuration files.

The described options are located in
`${BSPDIR}/sources/meta-tq/conf/machine/tqmarzg2x.inc`
and
`${BSPDIR}/sources/meta-dumpling/conf/distro/dumpling[-wayland]-rzg2.conf`.

### Configure gstreamer video devices

To configure other video devices than the predefined devices, one has to change
the config files in
`${BSPDIR}/sources/meta-tq/dynamic-layers/rzg2/recipes-multimedia/gstreamer/gstreamer1.0-plugin-vspfilter/`
or add new custom configuration files and append the FILESEXTRAPATHS variable
for the recipe `gstreamer1.0-plugin-vspfilter` in a `bbappend`, for exaple:

*gstvspfilter-custom.conf:*
```
input-device-name=/dev/video6
output-device-name=/dev/video7
```

*gstreamer1.0-plugin-vspfilter_%.bbappend:*
```
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

VSPFILTER_CONF = "gstvspfilter-custom.conf"
```
