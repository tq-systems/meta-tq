# TQMLS10xxA

[[_TOC_]]

## Overview

### Version information for software components

#### U-Boot

* based on qoriq-u-boot (https://github.com/nxp-qoriq/u-boot/)
* branched from lf-5.15.71-2.2.0 (v2022.04)

#### ATF

* based on qoriq-atf (https://github.com/nxp-qoriq/atf/)
* branched from lf-5.15.5-1.0.0 (v2.4)

#### Linux

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git/)
* branched from linux-6.1.y

### Supported Hardware:

 * TQMLS1043A with 1 GiB / 2 GiB RAM, HW REV.020x on MBLS10xxa, HW REV.020x
 * TQMLS1046A with 2 GiB / 8 GiB RAM, HW REV.020x on MBLS10xxa, HW REV.020x
 * TQMLS1088A with 2 GiB RAM, HW REV.020x on MBLS10xxa HW REV.020x

## HowTo

### MBLS10xxA DIP-Switch Settings for boot

#### SD-Card

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |     |  x  |     |
| OFF     |  x  |  x  |     |     |

#### eMMC

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |  x  |     |  x  |     |
| OFF     |     |  x  |     |     |

#### SPI-NOR

|         |  S5 |     |     |     |
| ------- | :-: | :-: | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |
| ON      |     |  x  |  x  |     |
| OFF     |     |     |     |  x  |

#### Notes:

* SD-Card/eMMC:
SD-Card and eMMC are mutually exclusive. DIP-Switch S5 controls SD-Card/eMMC
access. With S5-1 `ON` eMMC on TQMLS10xxA module is accesible. With S5-1 `OFF`
SD-Card on MBLS10xxA is accessible.

## Build Artifacts

### TQMLS1043A
* atf/
  * 1GiB
	* fip_uboot.bin: U-Boot
	* bl2_[sd|qspi].pbl: Boot-media dependend Primary Boot Loader with RCW
  * 2GiB
	* fip_uboot_tqmls1043a_2gb.bin: U-Boot
	* bl2_[sd|qspi]_tqmls1043a_2gb.pbl: Boot-media dependend Primary Boot
	  Loader with RCW
* atf/variants/: contains RCW-PBL for all supported RCW serdes-configurations
  and all supported boot sources.
* rcw/: different rcw configurations to use with atf-recipe
* fsl_fman_ucode_ls1043_r1.1_106_4_18.bin: Fman Ethernet Firmware
* Image: Kernel
* fsl-ls1043a-tqmls1043a-mbls10xxa.dtb: Device Tree Blob.
* u-boot-tfa-2022.04-r0.bin: U-Boot Binary
* tq-image-generic-tqmls1043a-mbls10xxa.wic: Complete eMMC / SD-Card Image
* tq-image-generic-tqmlx1043a-mbls10xxa.ubi: RootFS UBI-Image

### TQMLS1046A
* atf/
  * 2GiB
    * fip_uboot.bin: U-Boot
    * bl2_[sd|qspi].pbl: Boot-media dependend Primary Boot Loader with RCW
  * 8GiB
    * fip_uboot_tqmls1046a_8gb.bin: U-Boot
    * bl2_[sd|qspi]_tqmls1046a_8gb.pbl: Boot-media dependend Primary Boot Loader with RCW
* atf/variants/: contains RCW-PBL for all supported RCW serdes-configurations
  and all supported boot sources.
* rcw/: different rcw configurations to use with atf-recipe
* fsl_fman_ucode_ls1046_r1.0_106_4_18.bin: Fman Ethernet Firmware
* Image: Kernel
* fsl-ls1046a-tqmls1046a-mbls10xxa.dtb: Device Tree Blob.
* u-boot-tfa-2022.04-r0.bin: U-Boot Binary
* tq-image-generic-tqmls1046a-mbls10xxa.wic: Complete eMMC / SD-Card Image
* tq-image-generic-tqmlx1046a-mbls10xxa.ubi: RootFS UBI-Image

### TQMLS1088a
* atf/
  * fip_uboot.bin: U-Boot
  * bl2_[auto|qspi].pbl: Boot-media dependend Primary Boot Loader with RCW
* atf/variants/: contains RCW-PBL for all supported RCW serdes-configurations
  and all supported boot sources.
* rcw/: different rcw configurations to use with atf-recipe
* mc_app/: the DPAA2-Ethernet Firmware
* mc-utils: the DPAA2-Ethernet Configuration files
* Image: Kernel
* fsl-ls1088a-tqmls1088a-mbls10xxa.dtb: Device Tree Blob.
* u-boot-tfa-2022.04-r0.bin: U-Boot Binary
* tq-image-generic-tqmls1088a-mbls10xxa.wic: Complete eMMC / SD-Card Image
* tq-image-generic-tqmls1088a-mbls10xxa.ubi: RootFS UBI-Image

## Serdes Configuration

The following tables show the supported Serdes configrations.
* Ethernet protocols: `[Protocoll].[Mac-nr]`
* PCIe: `PCIe.[Controller-Nr] x[Lanes]`

### TQMLS1043A

The following tables show the supported Serdes configurations.

#### Serdes 1
| Lane / Config | A         | B         | C         | D         |
| ------------- | --------- | --------- | --------- | --------- |
| 1355          | XFI.2     | SGMII.2   | PCIe.2    | PCIe.3    |
| 1455          | XFI.2     | QSGMIIb   | PCIe.2    | PCIe.3    |
| 3355          | SGMII.9   | SGMII.2   | PCIe.2    | PCIe.3    |
| 3358          | SGMII.9   | SGMII.2   | PCIe.2    | SATA      |

### TQMLS1046A

#### Serdes 1
| Lane / Config | D - 0     | C - 1     | B - 2     | A - 3     |
| ------------- | --------- | --------- | --------- | --------- |
| 1040          | XFI.2     |           | QSGMIIb   |           |
| 1133          | XFI.2     | SGMII.10  | SGMII.5   | SGMII.6   |
| 3333          | SGMII.9   | SGMII.10  | SGMII.5   | SGMII.6   |

#### Serdes 2
| Lane / Config | A - 0     | B - 1     | C - 2     | D - 3     |
| ------------- | --------- | --------- | --------- | --------- |
| 5506          | PCIe.1    | PCIe.2    |           | PCIe.3    |
| 5559          | PCIe.1    | PCIe.2    | PCIe.3    | SATA      |
| 5577          | PCIe.1    | PCIe.2    | PCIe.3 x 2| PCIe.3 x 2|
| 5A06          | PCIe.1    | SGMII.2   |           | PCIe.3 x 1|
| 5A59          | PCIe.1    | SGMII.2   | PCIe.3 x 1| SATA      |

### TQMLS1088A

#### Serdes 1
| Lane / Config | D - 0     | C - 1     | B - 2     | A - 3     |
| ------------- | --------- | --------- | --------- | --------- |
| 3333 (18)     | SGMII.2   | SGMII.1   | SGMII.7   | SGMII.3   |
| 1133 (21)     | XFI.2     | XFI.1     | SGMII.7   | SGMII.3   |
| 1143 (25)     | XFI.2     | XFI.1     | QSGMIIb   | SGMII.3   |
| 1144 (29)     | XFI.2     | XFI.1     | QSGMIIb   | QSGMIIa   |

#### Serdes 2
| Lane / Config | A - 0     | B - 1     | C - 2     | D - 3     |
| ------------- | --------- | --------- | --------- | --------- |
| 5506 (59)     | PCIe.1    | PCIe.2    |           | PCIe.3    |
| 5559 (13)     | PCIe.1    | PCIe.2    | PCIe.3    | SATA      |
| 5577 (20)     | PCIe.1    | PCIe.2    | PCIe.3 x 2| PCIe.3 x 2|

## DIP-Switch settings
The DIP-switches should match the used RCW, otherwise interfaces will not work,
or will not start. See [Serdes Config](#serdes-configuration)
Pay attention to the following DIP-Switches:

| DIP-Switch | Function              | On         | Off     |
| ---------- | --------------------- | ---------- | ------- |
| S6-1       | SD1 Lane A            | SGMII      | QSGMIIa |
| S6-2       | SD1 Lane B            | SGMII      | QSGMIIb |
| S6-3       | SD1 Lane D            | SGMII      | XFI2    |
| S6-4       | SD2 Lane B            | PCIe       | SGMII   |
|            |                       |            |         |
| S8-1       | SD2 Lane D            | PCIe       | S8-2    |
| S8-2       | SD2 Lane D            | SATA       | MPCIe   |
| S8-3       | Demuxing              | off        | on      |
| S8-4       | SD1 Refclk 2          | 156.25 Mhz | 125 Mhz |
|            |                       |            |         |
| S7-1       | QSGMIIa Phy 1 CLK Sel | 156.25 MHz | 125 Mhz |
| S7-2       | QSGMIIa Phy 1 Config  | QSGMII     | SGMII   |
| S7-3       | QSGMIIb Phy 2 CLK Sel | 156.25 MHz | 125 Mhz |
| S7-4       | QSGMIIb Phy 2 Config  | QSGMII     | SGMII   |
|            |                       |            |         |
| S10-1      | DVDD                  | 3.3V       | 1.8V    |
| S10-2      | EVDD                  | 3.3V       | 1.8V    |
|            |                       |            |         |
| S5-1       | SD-Card / eMMC        | eMMC       | SD-Card |

## Support Wiki

See [TQ Embedded Wiki for TQMLS10xxA](https://support.tq-group.com/en/layerscape/tqmls10xxa)