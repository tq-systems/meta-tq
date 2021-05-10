# TQMa8XxS

This README contains some useful information for TQMa8XxS on MB-SMARC-2

## Variants

* TQMa8XQPS REV.020x
* TQMa8XQPS REV.030x
* TQMa8XDPS REV.030x

## Version information for software components

See [here](doc/README.SoftwareVersions.md) for the software base versions.

### SCFW:

* version: tq-TQMa8.NXP-v1.6.0.B4894.0030

## Supported machine configurations:

See top level README.md for configurations usable as MACHINE.

## Supported Features

### U-Boot:

* RAM configs:
  * 2GB / TQMa8X\[D,Q\]PS
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
  * USB 3.0 (Hub on TQMa8XxS)
* ENET (GigE via Phy on TQMa8XxS)
  * ENET 1
  * ENET 2
* Bootstreams
  * FlexSPI
  * SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* Cortex M4
* temperature grade
  * SCU limitation
* CPU variants i.MX8DX/i.MX8DXP cannot be detected automatically
  (limitation of cpu driver / SCU firmware)

### Linux:

* RAM configs:
  * 2GB / TQMa8X\[D,Q\]PS
* CPU variants:
  * i.MX8QXP C0
  * i.MX8QXP C0
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
* GPIO
  * SMARC GPIO pins
* ENET (GigE via Phy on TQMa8XxS)
  * ENET 1
  * ENET 2
* QSPI NOR
* UART
  * console
  * LPUART3 via unused SCU GPIO pins
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8Mx)
* LVDS
* GPU
* VPU
* PCIe
  * mini-PCIe on MB-SMARC-2
  * wifi with Network Card (Silex Technology SX-PCEAC2-HMC-SP)
* CAN
  * can0/1 as network interface
* CPU / PMIC Thermal sensors
  * via thermal-zone
* Audio
  * Line In
  * Line Out
* DVFS
* Suspend
  * mem / freeze

**TODO or not tested with new BSP**

* temperature grade
  * SCU limitation
* Audio
  * Mic In untested
* DSI - DP bridge
* GPIO
  * Suspend / Wakeup GPIO

## Known Issues

* CAN
  * CAN FD can not be automatically configured (systemd limitation)
  * CAN FD supported on MB-SMARC-2 (system limitation)
* USB
  * Port USB3 (X3 on MB-SMARC-2) is host only. Do only use a matching adapter
    on MB-SMARC-2
  * U-Boot: USB 3.0 port does not initialize USB 2.0 subsystem after USB reset
  * Linux: overcurrent with some USB Sticks on MB-SMARC-2
* FlexSPI
  * erase of ranges >= 16 MB fails under linux
* UUU compatible bootstream is not built by default (yocto recipe limitation)
* Suspend / Wakeup
  * RTC Alarm IRQ via GPIO leads to system stall during resume

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU

## Boot Dip Switches

_Note:_

* S3 is for Boot Mode.
* X means position of DIP, - means don't care

_SD Card_

```
            1 2 3 4
DIP (S3)    0 1 1 0

```

_e-MMC_

```
            1 2 3 4
DIP (S3)    1 0 0 0
```

_QSPI_

```
            1 2 3 4
DIP (S3)    1 1 0 0
```

_Serial Downloader_

```
            1 2 3 4
DIP (S3)    0 0 0 1
```

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
UBOOT_CONFIG_tqma8xxs = "mfgtool"
IMXBOOT_TARGETS_tqma8xxs = "flash_spl"
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

## Support for REV.020x Modules

To support older REV.020x modules, use the respective machine config. This automatically

- builds the correct bootloader
- build devicetrees correct device trees (containing `r0200` in its name)

## Test sleepmode and wakeup

Use rtc1 (RTC in CPU SNVS domain) to wakeup after 20 seconds:

```
RTC=rtc1
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc//${RTC}/wakealarm
echo mem > /sys/power/state
```

## Display Support

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.

| Interface       | Device tree                              | Type        ----   |
|-----------------|------------------------------------------|--------------------|
| LVDS0           | imx8qxp-mb-smarc-2-lvds0-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS0, dual     | imx8qxp-mb-smarc-2-lvds0-g133han01.dtb   | AUO G133HAN.01     |
| LVDS1           | imx8qxp-mb-smarc-2-lvds1-tm070jvhg33.dtb | Tianma TM070JVHG33 |

Please note manual for backlight power supply. For MB-SMARC-2 you can bridge
X14 pin 1 and 2 to provide 12V.

## CAN

### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP         |
| --------- | --------- | ----------- |
| CAN0      | X29       | `TERM CAN0` |
| CAN1      | X30       | `TERM CAN1` |

### Enable without CAN-FD

CAN1/2 should be enabled and configured by default when using with MB-SMARC-2
and meta-tq / systemd

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

### Enable CAN-FD

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```
