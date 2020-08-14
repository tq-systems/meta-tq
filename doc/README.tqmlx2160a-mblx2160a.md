# TQMLX2160A-MBLX2160A

## DIP-Switch Settings

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

### U-Boot
* U-Boot 2019.04 based on https://source.codeaurora.org/external/qoriq/qoriq-components/u-boot 
* Based on Tag lx2160a-early-access-bsp0.7

### Linux
* Linux 5.4.33 based on https://source.codeaurora.org/external/qoriq/qoriq-components/linux
* Based on Tag LSDK-20.04-V5.4-update-290520


## Supported Interfaces:

### U-Boot:
* GPIOs
* emmc
* SPI Nor - Flash
* I2C

### Not Supported:
* SDHC:
Due to a HW-Issue SD-Card only works a few start-ups and is therefore not properly tested.

## Todo

* CAN

## HowTo
### RCW - SerDes Configuration
### Ethernet in Linux

## Build Artifacts

* atf/
	* fip_uboot.bin: U-Boot
	* bl2_[emmc|flexspi_nor|sd].pbl: Boot-media dependend Primary Boot Loadwer with RCW
* ddr-phy/
	* fip_ddr.bin: Firmware for DDR-Controller Phy
* rcw/: different rcw configurations to use with atf-recipe
* mc_app/: the DPAA2-Ethernet Firmware
* mc-utils: the DPAA2-Ethernet Configuration files
* Image: Kernel
* fsl-lx2160a-tqmlx2160a-mblx2160a.dtb: Device Tree Blob.
* u-boot-tfa-2019.04-r0.bin: U-Boot Binary
* tq-image-generic-tqmlx2160a-mblx2160a.wic: Complete eMMC / SD-Card Image

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

## DPAA2 Firmware
In U-Boot the firmware is loaded with the env-command 'mcinitcmd' with the
DPAA2-DPC Configuration file found in the DPAA2-DPC Partition on Nor-Flash
respecitvely the dpc-warn.dtb file in boot-Partition.

### Build-Time Configuration
* rcw: Used RCW-Binary to build ATF
* MC_DPC: DPAA2 Configuration File
* MC_DPL: DPAA2 Data Path Layout file.
* BL2_IMAGE: ATF bl2 file used for wic Image generation.
