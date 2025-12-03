# TQMLX2160A-MBLX2160A

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMLX2080A/TQMLX2160A: module revisions REV.010x
* MBLX2160A:  board revisions REV.010x .. REV.020x

## Versions

### ATF
* based on qoriq-atf (https://github.com/nxp-qoriq/atf/)
* branched from lf_v2.10

### U-Boot

* U-Boot 2019.04 based on https://github.com/nxp-qoriq/u-boot
* Based on Tag lx2160a-early-access-bsp0.7

### Linux

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git/)
* branched from linux-6.12.y

## Supported Features

### U-Boot

| Feature                                   |                 |
|:------------------------------------------|:---------------:|
| RAM configs                               |    16, 32 GiB   |
| CPU variants                              | LX2160A, LX2080A|
| GPIO                                      |        x        |
| I2C                                       |        x        |
| **UART**                                  |                 |
| console on UART1 (X21)                    |        x        |
| **QSPI-NOR**                              |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| Boot                                      |        x        |
| **eMMC / SD card**                        |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| Boot                                      |        x        |
| **USB**                                   |                 |
| USB 3.0 Hub (X20)                         |        x        |
| **SATA**                                  |                 |
| SATA connector (X18)                      |        \*       |
| M.2 (X42, X43)                            |        \*       |
| **PCIe**                                  |                 |
| PCIe x4/x8 (X35, X36, X37)                |        \*       |
| mPCIe (X16, X17)                          |        \*       |
| **Ethernet**                              |                 |
| SGMII / RGMII 1G                          |        \*       |
| XFI 10G                                   |        \*       |
| CAUI4 100G                                |        \*       |
| **Bootstreams**                           |                 |
| FlexSPI                                   |        x        |
| SD / eMMC                                 |        x        |

\*: In some SerDes configurations; see the User Manual and Support Wiki for
details

### Linux

| Feature                                   |      6.12       |
|:------------------------------------------|:---------------:|
| RAM configs                               |    16, 32 GiB   |
| CPU variants                              | LX2160A, LX2080A|
| **UART**                                  |                 |
| console on UART1 (X21)                    |        x        |
| additional UARTs 2-4 on pin header (X27)  |        x        |
| **GPIO**                                  |                 |
| LED                                       |        x        |
| Button                                    |        x        |
| **I2C**                                   |                 |
| Temperature Sensors                       |        x        |
| RTC                                       |        x        |
| EEPROMS                                   |        x        |
| **QSPI-NOR**                              |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| **eMMC / SD card**                        |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| **USB**                                   |                 |
| USB 3.0 Hub (X20)                         |        x        |
| USB 3.0 OTG (X38)                         |        x        |
| **SATA**                                  |                 |
| SATA connector (X18)                      |        \*       |
| M.2 (X42, X43)                            |        \*       |
| **PCIe**                                  |                 |
| PCIe x4/x8 (X35, X36, X37)                |        \*       |
| mPCIe (X16, X17)                          |        \*       |
| **Ethernet**                              |                 |
| SGMII / RGMII 1G                          |        \*       |
| XFI 10G                                   |        \*       |
| CAUI4 100G                                |        \*       |
| DPAA2                                     |        x        |
| **CAN**                                   |                 |
| CAN-FD (X33, X34)                         |        x        |

\*: In some SerDes configurations; see the User Manual and Support Wiki for
details

## Not Supported

* SIM card (X15)

## Known issues

* SDHC:
  * On MBLX2160A REV.0100, the SD card only works for a few start-ups and is
    therefore not properly tested. On REV.0200, the SD card interface works properly.

## Build Artifacts

* atf/
  * 32GiB
	  * fip_uboot.bin: TF-A / U-Boot Firmware Image Package
	  * bl2_[auto|flexspi_nor].pbl: Boot-media dependend Primary Boot Loader with RCW
  * 16GiB
	  * fip_uboot_tqmlx2160a_16gb.bin: TF-A / U-Boot Firmware Image Package
	  * bl2_[auto|flexspi_nor]_tqmlx2160a_16gb.pbl: Boot-media dependend Primary Boot Loader with RCW
* atf/variants/: contains RCW-PBL for all supported RCW serdes-configurations and all supported boot sources.
* ddr-phy/
	* fip_ddr.bin: Firmware for DDR-Controller Phy
* rcw/: different rcw configurations to use with atf-recipe
* mc_app/: the DPAA2-Ethernet Firmware
* mc-utils: the DPAA2-Ethernet Configuration files
* Image: Kernel
* fsl-lx2160a-tqmlx2160a-mblx2160a.dtb: Device Tree Blob.
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.wic[.<compress>]: Complete eMMC / SD-Card Image

Note: As U-Boot use the fip_uboot.bin from the atf directory.

## HowTo

### DIP-Switch Settings

BOOT\_MODE can be configured using DIP switch S1

* SPI-Nor-Flash

| DIP S1 | 1 | 2 | 3 | 4 |
|--------|---|---|---|---|
| On     |   |   |   |   |
| Off    | x | x | x | x |

* SD-Card

| DIP S1 | 1 | 2 | 3 | 4 |
|--------|---|---|---|---|
| On     | x |   |   |   |
| Off    |   | x | x | x |

* eMMC

| DIP S1 | 1 | 2 | 3 | 4 |
|--------|---|---|---|---|
| On     |   | x |   |   |
| Off    | x |   | x | x |

## Boot Media

### Update Scripts

In U-Boot update scripts are provided to easily update components.

There are scripts to update the PBL and TF-A / U-Boot FIP.

These scripts are named `update_[pbl|uboot]_[spi|mmc|sd]`.

**Attention**

* use PBL/RCW image to update PBL
* use FIP to update TF-A / U-Boot

### Flash UBI to SPI-NOR

```
ubiformat /dev/mtd7 -f tq-image-generic-tqmlx2160a-mblx2160a.ubi
ubiattach /dev/ubi_ctrl -m 7
```

### SD / eMMC images

See [Layerscape Boot Media](./README.ls.BootMedia.md) for details.

## Memory Layout

### SPI-NOR

|     start |       end | content    |
| --------: | --------: | ---------- |
|       0x0 |  0x100000 | RCW-PBL    |
|  0x100000 |  0x300000 | U-Boot     |
|  0x500000 |  0x600000 | U-Boot-Env |
|  0x800000 |  0xa00000 | DDR-PHY    |
|  0xa00000 |  0xd00000 | DPAA2-MC   |
|  0xd00000 |  0xe00000 | DPAA2-DPL  |
|  0xe00000 |  0xf00000 | DPAA2-DPC  |
| 0x1000000 | 0x8000000 | RootFS UBI |

### eMMC / SD-Card

|     start | content        |
| --------: | -------------- |
|    0x1000 | RCW-PBL        |
|  0x100000 | U-Boot         |
|  0x800000 | DDR-PHY        |
| 0x1000000 | Boot Partition |
| 0x3000000 | RootFS ext4    |


## Build-Time Configuration

* RCWXSPI: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SPI-NOR Boot
* RCWAUTO: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SD/eMMC Boot
* ATF_RCW_VARIANTS: List of RCW binaries used to build variants of the Primary Boot Loader
* MC_DPC: DPAA2 Configuration File
* MC_DPL: DPAA2 Data Path Layout file.
* BL2_IMAGE: ATF BL2 (PBL) file used for WIC image generation.
* BL3_IMAGE: ATF BL3 (U-Boot) file used for WIC image generation.

Set BL2_IMAGE to `bl2_auto${ATF_SECURE_SUFFIX}_tqmlx2160a_16gb.pbl` and BL3_IMAGE
to `fip_uboot${ATF_SECURE_SUFFIX}_tqmlx2160a_16gb.bin` to create an SD/eMMC image
for the 16GiB variant.

### Reset Configuration Word

The Reset Configuration Word (RCW) is a data block that is built into the primary bootloader (BL2)
and contains basic hardware configuration for the LX2160A, including pinmuxing and selection of the
[SerDes Configuration](#serdes-configuration).

The `RCWXSPI` and `RCWAUTO` variables, set in `meta-tq/conf/machine/tqmlx2160a-mblx2160a.conf` by
default, can be modified to select an RCW variant at build time. To provide additional configuration
variants not included in the BSP, the *rcw* recipe must be extended through a `.bbappend` file.

### Secure Boot

Secure Boot is enabled by adding "secure" to `DISTRO_FEATURES`. With this setting, signed variants
of all ATF components are generated and built into the SD/eMMC system image.

By default, a newly generated keypair will be used for signing, which may be lost when certain
packages are rebuilt. To enable the build of secured images with a pregenerated keypair, the
following settings can be added to a distro configuration or `local.conf`:

```
DISTRO_FEATURES:append = " secure"

SRK_PATH = "/path/to/my/srk/keypair"
SRC_URI:append:pn-qoriq-cst-native = " file://${SRK_PATH}/srk.pri file://${SRK_PATH}/srk.pub"
SECURE_PRI_KEY:pn-qoriq-cst-native = "${SRK_PATH}/srk.pri"
SECURE_PUB_KEY:pn-qoriq-cst-native = "${SRK_PATH}/srk.pub
```

To boot a secured image, a LX2160A\[C\]E CPU (with cryptography support) with
programmed OTPMK fuses is required. Please refer to the
[LSDK User Guide](https://www.nxp.com/design/software/embedded-software/linux-software-and-development-tools/layerscape-linux-distribution-poc:LAYERSCAPE-SDK#documentation)
for more information on the Secure Boot process and the required hardware preparation.

For now, only the early boot stages (up to U-Boot) are signed and verified.
Additional configuration of U-Boot is required to verify subsequent boot images
like the Linux kernel.

## Ethernet and DPAA2

### RCW - SerDes Configuration

The RCW Configuration specifies the Ethernet Configuration.
The currently available serdes configurations are (naming scheme `<Serdes1>_<Serdes2>_<Serdes3>`):

* 0_0_0
* 12_7_3
* 12_8_3
* 12_11_3
* 14_7_2
* 14_7_3
* 14_8_2
* 14_8_3
* 14_11_2
* 14_11_3

To add another configuration the rcw sources have to be modified.
To use a specific Serdes Configuration on build-time for the boot firmware image
use the `rcw` variable to specify the configuration to use.

### Ethernet in U-Boot

For working ethernet the DPAA2 firmware has to be loaded in U-Boot. It needs the DPC file when loaded.
The command `fsl_mc start mc ${addr_mc} ${addr_dpc}` loads the firmware with the DPC file.

For Ethernet in Linux additionaly a Data-Path-Layout file (DPL) has to be loaded before starting the kernel.
This is done with the command `fsl_mc lazyapply DPL ${addr_dpl}`.

### Ethernet in Linux

The ethernet configuration in Linux is determined by the DPL file. In the BSP a basic setup is configured in the dpl-min.dts file.
With the restool command a more specific setup can be configured.

Some useful restool commands are:

* `restool dpmac create --mac-id=<mac-nr>`: create mac.
* `ls-addni dpmac.<mac-nr>`: Add Interface with Mac.
* `ls-listmac`: Show current Macs
* `ls-listni`: Show current Interfaces
* `restool dprc generate-dpl dprc.1 > <my_dpl>.dts`: generate a dpl file from the current configuration.
* `dtc -I dts -O dtb <my_dpl>.dts -o <my_dpl>.dtb`: To generate a dtb file of the dts file.

### Ethernet Interfaces:

The following table shows which MAC is connected to which port depending on the interface.
|  MAC  | RGMII | SGMII | XFI | CAUI4 |
| ----- | ----- | ----- | --- | ----- |
| MAC1  |   -   |   -   |  -  |  X29  |
| MAC9  |   -   | X13.A |  -  |   -   |
| MAC10 |   -   | X13.B |  -  |   -   |
| MAC12 |   -   | X10.A |  -  |   -   |
| MAC13 |   -   | X12.A |  X8 |   -   |
| MAC14 |   -   | X12.B |  X9 |   -   |
| MAC16 |   -   | X11.B |  -  |   -   |
| MAC17 | X14.A | X10.B |  -  |   -   |
| MAC18 | X14.B | X11.A |  -  |   -   |

Interfaces in U-Boot are named like this: DPMACxx@interface (e.g. DPMAC17@rgmii-id).
Note: On MAC.17 and MAC.18 RGMII configuration takes precedence over SGMII.

## Serdes Configuration

The following tables show the supported Serdes configrations.
For Ethernet protocols: `[Protocoll].[Mac-nr]` for PCIe: `PCIe.[Controller-Nr] x[Width]`

### Serdes 1

| Lane / Config | H - 0   | G - 1   | F - 2   | E - 3   | D - 4     | C - 5     | B - 6     | A - 7     |
| ------------- | ------- | ------- | ------- | ------- | --------- | --------- | --------- | --------- |
| 12            | -       | -       | -       | -       | PCIe.2 x2 | PCIe.2 x2 | SGMII.9   | SGMII.10  |
| 14            | CAUI4.1 | CAUI4.1 | CAUI4.1 | CAUI4.1 | PCIe.2 x2 | PCIe.2 x2 | PCIe.2 x2 | PCIe.2 x2 |

### Serdes 2

| Lane / Config | A - 0     | B - 1    | C - 2    | D - 3    | E - 4     | F - 5    | G - 6    | H - 7    |
| ------------- | --------- | -------- | -------- | -------- | --------- | -------- | -------- | -------- |
| 7             | PCIe.3 x1 | SGMII.12 | SGMII.17 | SGMII.18 | PCIe.4 x1 | SGMII.16 | XFI.13   | XFI.14   |
| 8             | -         | -        | SATA.1   | SATA.2   | SATA.3    | SATA.4   | XFI.13   | XFI.14   |
| 11            | PCIe.3 x1 | SGMII.12 | SGMII.17 | SGMII.18 | PCIe.4 x1 | SGMII.16 | SGMII.13 | SGMII.14 |

### Serdes 3

| Lane / Config | A - 0     | B - 1     | C - 2     | D - 3     | E - 4     | F - 5     | G - 6     | H -   7   |
| ------------- | --------- | --------- | --------- | --------- | --------- | --------- | --------- | --------- |
| 2             | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 | PCIe.5 x8 |
| 3             | PCIe.5 x4 | PCIe.5 x4 | PCIe.5 x4 | PCIe.5 x4 | PCIe.6 x4 | PCIe.6 x4 | PCIe.6 x4 | PCIe.6 x4 |

## DIP-Switch settings

The DIP-switches should match the used RCW, otherwise interfaces will not work.
Pay attention to the following DIP-Switches:

| DIP-Switch | Serdes Lane | On  | Off |
| ---------- | ----------- | --- | ---- |
| S3-1       | SD1 Lane 6 & 7 | PCIe.2 | SGMII.9 & 10 |
| S3-2       | SD2 Lane 2 | SGMII.17 | SATA.1 |
| S3-3       | SD2 Lane 3 | SGMII.18 | SATA.2 |
| S3-4       | SD2 Lane 4 | PCIe.4 | SATA.3 |
| S4-1       | SD2 Lane 6 | XFI.13 | SGMII.13 |
| S4-2       | SD2 Lane 7 | XFI.14 | SGMII.14 |
| S4-3       | SD3 Lane 4 -7 | PCIe.5 x8 | PCIe.6 x4 |
| S5-1       | EC2| ETH.1588 | RGMII.18 |

## PCIe Configuration

* X35 -> SD3 Land 4-7
* X36 -> SD1 Lane 4-7
* X37 -> SD3 Lane 0-3 and additional Lane 4-7 when x8 (Pay attention to DIP switch)
* X16 -> SD2 Lane 0
* X17 -> SD2 Lane 4

### PREEMPT-RT / Realtime support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMLX2160A](https://support.tq-group.com/en/layerscape/tqmlx2160a)
