# TQMaRZG2x

This README contains some useful information for TQMaRZG2x on MBaRZG2x

## Variants
* TQMaRZG2M (r8a774a1) 8GiB RAM Rev.01xx
* TQMaRZG2M (r8a774a1) 2GiB RAM Rev.01xx
* TQMaRZG2N (r8a774b1) 2GiB RAM Rev.01xx
* TQMaRZG2H (r8a774e1) 4GiB RAM Rev.01xx

## Version information for software components

### ATF
* ATF v2.3 based on Renesas Verified Linux Package VLP64 1.06

### U-Boot
* U-Boot 2018.09 based on Renesas Verified Linux Package VLP64 1.06

### Linux
* Linux 4.19-CIP based on Renesas Verified Linux Package VLP64 1.06

## Supported machine configurations:
* tqmarzg2m_e-mbarzg2x
* tqmarzg2m_aa-mbarzg2x
* tqmarzg2n_b-mbarzg2x
* tqmarzg2h_c-mbarzg2x

## Supported Features

### U-Boot:
* GPIOs
* eMMC
* SDHC
* I2C
* Ethernet
* USB

### Linux:
* GPIOs
* eMMC
* SDHC
* SPI Nor - Flash (except tqmarzg2m_aa-mbarzg2x)
* I2C
* Ethernet
* CAN
* PCIe
* SATA (RZG2H and RZG2N only)
* USB
* USB - OTG
* HDMI
* LVDS

## Not yet Supported
* ECC
* SIM-Card
* Sound
* RGB-Display-Interface
* Mikro-Modul
* NFS boot
* Wakeup

## Known Issues
* U-Boot update script 'update_uboot_mmc' does not write to boot partition, but data partition instead.
* After a hardware reset (S9), PCIe does not work any more on all module variants. 
* After a hardware reset (S9), USB3 does not work any more on the TQMaRZG2N variant. 
* U-Boot command 'gpio status' works, but generates an error messsage: 'Error reading output register' 

## Notes
* SDHC:
TQMaRZG2x on a MBaRZG2x cannot boot from SD-Card.
The only bootable SD/eMMC-Interface of the CPU is connected to eMMC.
* SPI Nor - Flash:
TQMaRZG2M_AA does not have a SPI NOR-Flash.

## Build Artifacts
* AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_TQMARZG2[H_C|M_E|M_AA|N_B].mot
* bl2-${MACHINE}.srec: Primary Boot Loader for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
* bl31-${MACHINE}.srec: Socondary Boot Loader for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
* bootparam_sa0.srec: ATF Boot parameters. Needed by Flash-Writer.
* cert\_header_sa6\_spi-nor.srec: Cerificate header for SPI NOR boot. Needed by Flash-Writer.
* Image: Kernel
* r8a774a1-tqmarzg2m_e-mbarzg2x.dtb: Device Tree Blob for TQMaRZG2M_E without display support.
* r8a774a1-tqmarzg2m_e-mbarzg2x-lvds-tm070jvhg33.dtb: Device Tree Blob for TQMaRZG2M_E with LVDS display support.
* r8a774a1-tqmarzg2m_aa-mbarzg2x.dtb: Device Tree Blob for TQMaRZG2M_AA without display support. 
* r8a774a1-tqmarzg2m_aa-mbarzg2x-lvds-tm070jvhg33.dtb: Device Tree Blob for TQMaRZG2M_AA with LVDS display support.
* r8a774b1-tqmarzg2n_b-mbarzg2x.dtb: Device Tree Blob for TQMaRZG2N_B without display support.
* r8a774b1-tqmarzg2n_b-mbarzg2x-lvds-tm070jvhg33.dtb: Device Tree Blob for TQMaRZG2N_B with LVDS display support.
* r8a774e1-tqmarzg2h_c-mbarzg2x.dtb: Device Tree Blob for TQMaRZG2H_C without display support.
* r8a774e1-tqmarzg2h_c-mbarzg2x-lvds-tm070jvhg33.dtb: Device Tree Blob for TQMaRZG2H_C with LVDS display support.
* u-boot-elf-${MACHINE}.srec: U-Boot Binary for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by Flash-Writer.
* u-boot-${MACHINE}.bin: U-Boot Binary for TQMaRZG2[H_C|M_E|M_AA|N_B]. Needed by emmc update scripts.
* tq-image-weston-${MACHINE}.wic: Complete eMMC / SD-Card Image


## Boot DIP Switches

_BOOT\_MODE_

* eMMC

S2:

| DIP |  8  |  7  |  6  |  5  |  4  |
| :-- | :-: | :-: | :-: | :-: | :-: |
| OFF |  X  |     |  X  |     |     |
| ON  |     |  X  |     |  X  |  X  |


* SPI-Nor-Flash

S2:

| DIP |  8  |  7  |  6  |  5  |  4  |
| :-- | :-: | :-: | :-: | :-: | :-: |
| OFF |  X  |  X  |  X  |     |  X  |
| ON  |     |     |     |  X  |     |


_M.2-PCIe/SATA (X22)_


* PCIe

S3:

| DIP |  4  |
| :-- | :-: |
| OFF |  X  |
| ON  |     |

S10:

| DIP |  1  |
| :-- | :-: |
| OFF |  X  |
| ON  |     |

* SATA

S3:

| DIP |  4  |
| :-- | :-: |
| OFF |     |
| ON  |  X  |

S10:

| DIP |  1  |
| :-- | :-: |
| OFF |     |
| ON  |  X  |


## Update Scripts

In U-Boot update scripts are provided to easily update components.

There are scripts to update the U-Boot, Kernel and Devicetree.
These scripts are named `update_[uboot|fdt|kernel]_[mmc|sd]`.

U-Boot update only works for emmc, because there is no driver for SPI-NOR in U-Boot.
To write to SPI-MOR, use the Flash-Writer tool.


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
