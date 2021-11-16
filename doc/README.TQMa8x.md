# TQMa8x

This README contains some useful information for TQMa8x on MBa8x

[[_TOC_]]

## Variants

* TQMa8QM 4 GB RAM REV.020x / 0102 and newer
* TQMa8QM 8 GB RAM REV.020x / 0102 and newer

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
  * 4GB / TQMa8QM
  * 8GB / TQMa8QM
* CPU variants i.MX8QM B0
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
  * USB 3.0 (Hub on MBa8x)
* ENET (GigE via Phy on MBa8x)
  * ENET 0
  * ENET 1
* Bootstreams
  * FlexSPI
  * SD / e-MMC
  * UUU / mfgtool

**TODO or not tested / supported**

* more CPU variants
* temperature grade
  * SCU limitation

### Linux

* RAM configs:
  * 4GB / TQMa8QM
  * 8GB / TQMa8QM
* CPU variants
  * i.MX8QM B0
* I2C
  * Temperature Sensors (without cpu-temp)
  * RTC
  * EEPROMS
* SPI
  * spi user space device on all CS
  * Mikrobus (Module RTC5)
* GPIO
  * LED
  * Button
  * wakeup from GPIO button
  * GPIO on pin heads
* ENET (GigE via Phy on MBa8x)
  * ENET 0
  * ENET 1
* QSPI NOR
* UART
  * console
  * additional UARTS on pin heads
* USB
  * USB 2.0 Dual Role
  * USB 3.0 (Hub on MBa8Mx)
* Graphic
  * GPU
  * VPU
  * LVDS0/LVDS1
  * Display Port
* CAN
  * can0/1 as network interface
* PWM
  * PWM in LVDS IP
* CPU / PMIC Thermal sensors
  * via thermal-zone
* SATA
* PCIe
  * mini-PCIe on MBa8x
  * wifi with Network Card (Silex Technology SX-PCEAC2-HMC-SP)
* ADC
* Audio
  * DisplayPort
  * Line In
  * Line Out
* DVFS
* Suspend
  * mem / freeze

**TODO or not tested with new BSP**

* temperature grade
  * SCU limitation
* Audio
  * Microphone
* FTM
  * PWM (missing in CPU DT)
* HDMI in
* PWM
  * generic PWM IP (missing in CPU DT)

## Known Issues

* Default setting for `fdt_file` in u-boot does not match new naming scheme. See [Build Artifacts](#Build-Artifacts) for complete list of supported Device Tree files
* counting of i2c devices bus starts at i2c-2 (because i2c-0 and i2c-1
  are reserved for i2c_rpmsgbus)
* PWM only works after the second enable command
  (`echo 1 > /sys/class/pwm<X>/enable`)
* UUU compatible bootstream is not built by default (yocto recipe limitation)
* USB
  * U-Boot: `EHCI timed out on TD - token` with some USB sticks on USB 2.0 OTG
  * runtime suspend disabled for USB Hub TUSB8041
* DT file for rpmsg is too big and needs `fdt_high` to be set to `0xffffffffffffffff`

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* \*.dtb: device tree blobs
* Image: linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* imx-boot-${MACHINE}-sd.bin-flash\_spl: boot stream for SD / e-MMC
* imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4: boot stream for SD / e-MMC + M4 Demo
* imx-boot-${MACHINE}-sd.bin-flash\_spl_flexspi: boot stream for QSPI
* imx-boot-mfgtool-${MACHINE}-mfgtool.bin-flash\_spl: boot stream for UUU
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote\_m40.bin: CortexM4 demo for device 0
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote\_m41.bin: CortexM4 demo for device 1

## Boot Dip Switches

_Note:_

* S1 is for Boot Mode.
* X means position of DIP, - means don't care

### SD Card

| DIP S1  | 5 | 4 | 3 | 2 | 1 | 0 |
| ------- | - | - | - | - | - | - |
| ON      |   |   | x | x |   |   |
| OFF     | x | x |   |   | x | x |

### e-MMC

| DIP S1  | 5 | 4 | 3 | 2 | 1 | 0 |
| ------- | - | - | - | - | - | - |
| ON      |   |   | x |   |   |   |
| OFF     | x | x |   | x | x | x |

### FLEXSPI

| DIP S1  | 5 | 4 | 3 | 2 | 1 | 0 |
| ------- | - | - | - | - | - | - |
| ON      |   | x | x | x | x |   |
| OFF     | x |   |   |   |   | x |

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
UBOOT_CONFIG_tqma8x = "mfgtool"
IMXBOOT_TARGETS_tqma8x = "flash_spl"
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

### Test sleepmode and wakeup

Use rtc1 (RTC in CPU SNVS domain) to wakeup after 20 seconds:

```
RTC=rtc1
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc//${RTC}/wakealarm
echo mem > /sys/power/state
```

Send linux to sleep mode and press one of the gpio buttons SWITCH\_A or SWITCH\_B
afterwards

```
echo mem > /sys/power/state
```

### Display Support

Each Display output could be activated independend by using the corresponding device tree.

| Interface | Device tree                        | Type           |
|-----------|------------------------------------|----------------|
| LVDS0     | imx8qm-tqma8x-mba8x-lvds0-tm070jvhg33.dtb | Tianma Display |
| LVDS1     | imx8qm-tqma8x-mba8x-lvds1-tm070jvhg33.dtb | Tianma Display |
| DP        | imx8qm-tqma8x-mba8x-dp.dtb                | Displayport    |

### CAN

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      |           | S10 |
| CAN1      |           | S11 |

#### Enable without CAN-FD

CAN1/2 should be enabled and configured by default when using with MB-SMARC-2
and meta-tq / systemd

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

#### Enable CAN-FD

To enable CAN-FD the following command can be used, if using a carrier board with
FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on‍‍‍‍‍‍‍`
```

### Cortex M4

Demos are compiled to use Cortex M4 0/1 UARTS with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8X.md).
