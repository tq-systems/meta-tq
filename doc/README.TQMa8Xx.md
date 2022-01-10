# TQMa8Xx / TQMa8Xx4

This README contains some useful information for TQMa8Xx and TQMa8Xx4 on MBa8Xx

[[_TOC_]]

## Variants

* TQMa8XDP REV.020x / 0x030x
* TQMa8XQP REV.020x / 0x030x
* TQMa8XDP4 REV.010x
* TQMa8XQP4 REV.010x

## Version information for software components

### SCFW:

Version: tq-TQMa8.NXP-v1.6.0.B4894.0033

### Other components

See [here](./README.SoftwareVersions.md) for the software base versions of atf,
bootloader and linux kernel.

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot

* RAM configs:
  * 1GB DDR3L ECC / TQMa8X\[D,Q\]P
  * 2GB DDR3L ECC / TQMa8X\[D,Q\]P
  * 2GB LPDDR4 / TQMa8X\[D,Q\]P4
* CPU variants:
  * i.MX8QXP C0
  * i.MX8DXP C0
* Fuses
* GPIO
* QSPI
  * Read
  * Write
  * Boot
* I2C
* e-MMC / SD-Card
  * Read
  * Write
  * Boot
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8Xx)
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2
* Bootstreams
  * FlexSPI
  * SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* temperature grade
  * SCU limitation
* CPU variants i.MX8DX/i.MX8DXP cannot be detected automatically from hardware
  (limitation of cpu driver / SCU firmware, currently fixed with U-Boot Kconfig)

### Linux

* RAM configs:
  * 1GB DDR3L ECC / TQMa8X\[D,Q\]P
  * 2GB DDR3L ECC / TQMa8X\[D,Q\]P
  * 2GB LPDDR4 / TQMa8X\[D,Q\]P4
* CPU variants:
  * i.MX8QXP C0
  * i.MX8DXP C0
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
* GPIO
  * LED
* ENET (GigE via Phy on MBa8Xx)
  * ENET 1
  * ENET 2
* QSPI NOR
* UART
  * console
  * LPUART3 via unused SAI pins
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8Mx)
* Graphic
  * GPU
  * VPU
  * LVDS
* CAN
  * can0/1 as network interface
* PWM
  * PWM in LVDS IP
* CPU / PMIC Thermal sensors
  * via thermal-zone
* PCIe
  * mini-PCIe on MBa8Xx
  * wifi with Network Card (Silex Technology SX-PCEAC2-HMC-SP)
* Audio
  * Line In
  * Line Out
* DVFS
* Suspend
  * mem / freeze

**TODO or not tested with new BSP**

* TQMa8XDP REV.020x
* temperature grade
  * SCU limitation
* Audio
  * Mic In untested
* DSI - DP bridge

## Known Issues

* Default setting for `fdt_file` in u-boot does not match new naming scheme.
  See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
* USB
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset
* Upon resume ethernet PHY will not establish a new link again

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* imx8qxp\*.dtb: device tree blobs for TQMa8XQP\[4\]
* imx8dxp\*.dtb: device tree blobs for TQMa8XDP\[4\]
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4: boot stream for SD / e-MMC + M4 Demo
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU
* hello\_world.bin (Cortex M4 demo, CM4 UART, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M4 demo, CM4 UART, TCM)

## Boot DIP Switches

_Note:_

* S1 is for Boot Mode.
* X means position of DIP, - means don't care

### SD Card

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   | x | x |
| OFF      | x | x |   |   |

### e-MMC

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   | x |   |
| OFF      | x | x |   | x |

### FLEXSPI

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   | x | x |   |
| OFF      | x |   |   | x |

### Serial Downloader

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   |   | x |
| OFF      | x | x | x |   |

### Boot from Fuses

| DIP S1   | 1 | 2 | 3 | 4 |
| -------- | - | - | - | - |
| BOOTMode | 3 | 2 | 1 | 0 |
| ON       |   |   |   |   |
| OFF      | x | x | x | x |

## Boot device initialisation

### Bootable SD-Card

To create a bootable SD-Card with complete system image:

write *.wic Image to SD (offset 0)

To create a bootable SD-Card with boot stream only (file name see above):

write bootstream at offset 32 kiB (0x8000) to SD-Card

Example for Linux:

`sudo dd if=<bootstream> of=/dev/sd<x> bs=1k seek=32 conv=fsync`

### Bootable e-MMC

To create a bootable e-MMC with complete system image:

write *.wic image to e-MMC (offset 0)

To create a bootable e-MMC with boot stream only (file name see above)

Boot from SD-Card and write bootstream at offset 32 kiB (0x8000) to e-MMC

Example for Linux:

`sudo dd if=<bootstream> of=/dev/mmcblk0 bs=1k seek=32 conv=fsync`

Example for U-Boot:

```
# 32k -> 64 blocks -> 0x40

tftp <bootstream>
setexpr bsz ${filesize} + 1ff
setexpr bsz ${bsz} / 200
printenv bsz
mmc dev 0
mmc write ${loadaddr} 40 ${bsz}
```

### Bootable QSPI NOR

To create a bootable QSPI NOR with boot stream only (file name see above)

Example for U-Boot, booting from SD-Card:

```
tftp <bootstream>
sf probe
sf update ${loadaddr} 0 ${filesize}
```

## Update components via U-Boot

For ease of development a set of variables and scripts are in default env.

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

_FLEXSPI_

Download bootstream from TFTP and update:

`run update_uboot_spi`

## Use UUU Tool

To build bootstream adapt yocto configuration, modify _local.conf_ or machine
config file:

```
UBOOT_CONFIG_tqma8xx = "mfgtool"
IMXBOOT_TARGETS_tqma8xx = "flash_spl"
```

Rebuild boot stream:

```
bitbake imx-boot
```

Use new compiled bootstream containing U-Boot capable of handling SDP together
with UUU tool:

```
sudo uuu -b spl imx-boot-<machine>-mfgtool.bin
```

## Howto

### Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

*Note:* With MBa8Xx only one control interface for backlight is available (X22).

| Interface       | Device tree                                               | Type        ----   |
|-----------------|-----------------------------------------------------------|--------------------|
| LVDS0           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS1           | imx8\[d,q\]xp-tqma8x\[d,q\]p-mba8xx-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      |    X11    | SW1 |
| CAN1      |    X12    | SW2 |

#### Enable without CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with
MBa8Xx and meta-tq / systemd

Configure CAN1/2 per commandline without CAN-FD:
```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

To (permanently) configure CAN1/2 in systemd network file, set in files
* /lib/systemd/network/20-can0.network
* /lib/systemd/network/20-can1.network

`FDMode=no` to disable CAN-FD.

#### Enable CAN-FD

CAN1/2 should be enabled (with CAN-FD) and configured by default when using with
MBa8Xx and meta-tq / systemd.

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

### Cortex M4

Demos are compiled to use Cortex M4 UART with 115200 8N1 on Pins SCU\_GPIO\_00 and SCU\_GPIO\_01
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8Xx](https://support.tq-group.com/en/arm/tqma8xx)  
See [TQ Embedded Wiki for TQMa8Xx4](https://support.tq-group.com/en/arm/tqma8xx4)
