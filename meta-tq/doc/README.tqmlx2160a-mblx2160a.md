# TQMLX2160A-MBLX2160A

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMLX2160A: module revisions REV.010x
* MBLX2160A:  board revisions REV.010x .. REV.020x

## HowTo

### DIP-Switch Settings

_BOOT\_MODE_

* SPI-Nor-Flash

```
	S1
DIP 	1 2 3 4
ON
OFF 	X X X X
```


* SD-Card

```
	S1
DIP 	1 2 3 4
ON	X
OFF 	  X X X
```

* eMMC

```
	S1
DIP 	1 2 3 4
ON	  X
OFF 	X   X X
```

## Versions

### ATF
* based on qoriq-atf (https://source.codeaurora.org/external/qoriq/qoriq-components/atf/)
* branched from lf-5.15.5-1.0.0

### U-Boot
* U-Boot 2019.04 based on https://source.codeaurora.org/external/qoriq/qoriq-components/u-boot
* Based on Tag lx2160a-early-access-bsp0.7

### Linux
* Linux 5.4.33 based on https://source.codeaurora.org/external/qoriq/qoriq-components/linux
* Based on Tag LSDK-20.04-V5.4-update-290520

## Supported Interfaces:

### U-Boot:
* GPIOs
* eMMC
* ESDHC
* SPI Nor - Flash
* I2C
* Ethernet
  * SGMII / RGMII
  * XFI 10G
  * CAUI4 100G
* PCIe
* USB

### Linux:
* GPIOs
* eMMC
* ESDHC
* SPI Nor - Flash
* I2C
* Ethernet - SGMII / RGMII
* Ethernet - XFI 10G
* Ethernet - CAUI4 100G
* CAN
* PCIe
* USB
* USB - OTG

## Not Supported
* SIM-Card

## Notes:
* SDHC:
On MBLX2160A.0100 the SD-Card only works a few start-ups and is therefore not properly tested.
On MBLX2160A.0200 the SD-Card interface works properly.

## Build Artifacts

* atf/
	* fip_uboot.bin: U-Boot
	* bl2_[emmc|flexspi_nor|sd].pbl: Boot-media dependend Primary Boot Loader with RCW
	* variants: contains RCW-PBL for all supported RCW serdes-configurations and all supported boot sources.
* ddr-phy/
	* fip_ddr.bin: Firmware for DDR-Controller Phy
* rcw/: different rcw configurations to use with atf-recipe
* mc_app/: the DPAA2-Ethernet Firmware
* mc-utils: the DPAA2-Ethernet Configuration files
* Image: Kernel
* fsl-lx2160a-tqmlx2160a-mblx2160a.dtb: Device Tree Blob.
* u-boot-tfa-2019.04-r0.bin: U-Boot Binary
* tq-image-generic-tqmlx2160a-mblx2160a.wic: Complete eMMC / SD-Card Image
* tq-image-generic-tqmlx2160a-mblx2160a.ubi: RootFS UBI-Image

Note: As U-Boot use the fip_uboot.bin from the atf directory.

### Update Scripts

In U-Boot update scripts are provided to easily update components.

There are scripts to update the PBL, U-Boot, Kernel and Devicetree.

These scripts are named `update_[pbl|uboot|fdt|kernel]_[spi|mmc|sd]`.

### Flash UBI to SPI-NOR

`ubiformat /dev/mtd9 -f tq-image-generic-tqmlx2160a-mblx2160a.ubi`
`ubiattach /dev/ubi_ctrl -m 9`

## Memory Layout
### SPI-NOR
* 0x000000000000-0x000000100000 : "RCW-PBL"
* 0x000000100000-0x000000300000 : "U-Boot"
* 0x000000500000-0x000000600000 : "U-Boot-Env"
* 0x000000800000-0x000000a00000 : "DDR-PHY"
* 0x000000a00000-0x000000d00000 : "DPAA2-MC"
* 0x000000d00000-0x000000e00000 : "DPAA2-DPL"
* 0x000000e00000-0x000000f00000 : "DPAA2-DPC"
* 0x000000f00000-0x000001000000 : "Linux-DTB"
* 0x000001000000-0x000003000000 : "Kernel"
* 0x000003000000-0x000008000000 : "RootFS UBI"

### eMMC / SD-Card
* 0x1000 : "RCW-PBL"
* 0x100000 : "U-Boot"
* 0x800000 : "DDR-PHY"
* 0x1000000 : "Boot Partition"
* 0x3000000 : "RootFS ext4"

Note: eMMC and SD-Card Image differ only in RCW-PBL. So a SD-Card Image can be used for eMMC when pbl is replaced.

## Build-Time Configuration
* RCWXSPI: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SPI-NOR Boot
* RCWSD: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SD
* RCWEMMC: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for e-MMC Boot
* ATF_RCW_VARIANTS: List of RCW binaries used to build variants of the Primary Boot Loader
* MC_DPC: DPAA2 Configuration File
* MC_DPL: DPAA2 Data Path Layout file.
* BL2_IMAGE: ATF pbl file used for wic Image generation.

## Ethernet and DPAA2
### RCW - SerDes Configuration
The RCW Configuration specifies the Ethernet Configuration.
The currently available serdes configurations are (Serdes1_Serdes2_Serdes3):
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
To use a specific Serdes Configuration on build-time use the rcw variable to specify a supported configuration.

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
Note: When MAC.17 or MAC.18 is configured as SGMII device the RGMII ports won't work.

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

## Support Wiki

See [TQ Embedded Wiki for TQMLX2160A](https://support.tq-group.com/en/layerscape/tqmlx2160a)
