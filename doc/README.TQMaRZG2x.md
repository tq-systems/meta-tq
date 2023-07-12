**ATTENTION**: This branch is only maintained for TQMaRZG2x machines, use
a more recent branch for all other machines

# TQMaRZG2x

This README contains some useful information for TQMaRZG2x on MBaRZG2x

[[_TOC_]]

## Variants

* TQMaRZG2x REV.020x on MBaRZG2x REV.010x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations:

See [top level README.md](./../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |                     REV.020x |
| ------------------------------------------------ | ---------------------------- |
| CPU variants                                     |        RZ/G2 (N\|M\|H)       |
| UART (console on UART0)                          |               x              |
| **GPIO**                                         |                              |
| LED                                              |               x              |
| **I2C**                                          |                              |
| system EEPROM parsing                            |               x              |
| **e-MMC / SD**                                   |                              |
| Read                                             |               x              |
| Write                                            |               x              |
| **ENET**                                         |                              |
| GigE / RAVB via Phy on MBaRZG2x                  |               x              |
| **Bootdevices:**                                 |                              |
| e-MMC on SDHI3                                   |               x              |
| ROM Loader (over UART0)                          |               x              |
| **USB**                                          |                              |
| USB 3.0 Host / Hub                               |               x              |
| USB OTG port                                     |               x              |

**TODO or not tested / supported**

* Read/Write/Boot from QSPI NOR

### Linux

| Feature                                          |                     REV.020x |
| ------------------------------------------------ | ---------------------------- |
| CPU variants                                     |        RZ/G2 (N\|M\|H)       |
| **UART**                                         |                              |
| console on UART0 (via USB / UART converter)      |               x              |
| **GPIO**                                         |                              |
| LED                                              |               x              |
| Button                                           |               x              |
| **I2C**                                          |                              |
| EEPROMs                                          |               x              |
| **ENET**                                         |                              |
| GigE / RAVB via Phy on MBaRZG2x                  |               x              |
| **USB**                                          |                              |
| USB 3.0 Host / Hub                               |               x              |
| USB OTG port                                     |               x              |
| GPU                                              | only with Multimedia Package |
| VPU                                              | only with Multimedia Package |
| **Display**                                      |                              |
| LVDS                                             |               x              |
| HDMI                                             |               x              |
| **Audio**                                        |                              |
| HDMI                                             |               x              |
| **PCIe**                                         |                              |
| network card at mPCIe                            |               x              |

**TODO or not tested / supported**

* Audio
  * Codec not working
* CAN
  * only CAN-FD supported
* Display
  * RGB not tested
* JTAG
  * not tested
* M.2 (PCIe/SATA)
  * not tested
* QSPI
  * not tested
  * QSPI boot is currently not supported
* RTC
  * not supported
* SIM card
  * not tested
* UART1-3
  * not tested
* Wakeup
  * `disk` mode not working
  * no wakeup button defined

## Known Issues / TODO

* U-Boot: second and further resets on USB OTG port not working (port disabled)
* MAC address must be set in U-Boot environment for working Ethernet
  * **TODO**: use fallback to set MAC address (e.g. manufacturer EEPROM)
* precise U-Boot version not shown
  * **TODO**: show precise U-Boot version (e.g. git commit hash)
* default boot device is SD card (can be changed via U-Boot environment)
  * **TODO**: use eMMC as default boot device, implement automatic boot device
    selection
* NFS root path in U-Boot is set to `/srv/nfs`
  * **TODO**: use module specific path, i.e. `/srv/nfs/tqmarzg2x`
* no update script for U-Boot
  * **TODO**: implement update script for U-Boot
* wrong Linux kernel timestamp `SMP PREEMPT Wed Apr 6 01:00:00 CEST 2011`
* RTC driver causes Watchdog hangup in U-Boot
  * if the Linux RTC driver (`rtc-pcf85063`) is loaded, U-Boot Watchdog driver
    is looping endless on next boot
* sleep mode `disk` cannot be entered
* ALSA utilities show `no soundcards found...`
  * HDMI sound working
  * Codec sound not working
* Only CAN-FD supported (configuration via device tree)
  * **TODO**: add dtsi for CAN without FD

## Build Artifacts

Artifacts can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`

* r8a774\*.dtb: device tree blobs
* r8a774\*-lvds-tm070jvhg33.dtb: device tree blobs with LVDS support for TIANMA TM070JVHG33
* Image: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_TQMARZG2X.mot: Flash Writer binary for SCIF Download Mode
* bl2-${MACHINE}.srec: Trusted Firmware A BL2 for Flash Writer (SCIF Download Mode)
* bl2-${MACHINE}.bin: Trusted Firmware A BL2 binary
* bl2-${MACHINE}.elf: Trusted Firmware A BL2 ELF file
* bl31-${MACHINE}.srec: Trusted Firmware A BL31 for Flash Writer (SCIF Download Mode)
* bl31-${MACHINE}.bin: Trusted Firmware A BL31 binary
* bl31-${MACHINE}.elf: Trusted Firmware A BL31 ELF file
* bootparam_sa0.srec: Boot parameters for Flash Writer (SCIF Download Mode)
* cert_header_sa6.srec: Certificate header for Flash Writer (SCIF Download Mode)
* u-boot-elf-${MACHINE}.srec: boot stream for Flash Writer (SCIF Download Mode)
* u-boot-${MACHINE}.bin: boot stream binary

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S2.

### ROM Loader (SCIF Download mode)

| DIP S2     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| ---------- | - | - | - | - | - | - | - | - |
| On         |   |   | x | x | x | x | x |   |
| Off        | x | x |   |   |   |   |   | x |

### e-MMC (SDHI3)

| DIP S2     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| ---------- | - | - | - | - | - | - | - | - |
| On         |   |   | x | x | x |   | x |   |
| Off        | x | x |   |   |   | x |   | x |

### QSPI

| DIP S2     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| ---------- | - | - | - | - | - | - | - | - |
| On         |   |   | x |   | x |   |   |   |
| Off        | x | x |   | x |   | x | x | x |

## Boot device initialisation and update

### Use ROM Loader (SCIF Download Mode)

Select ROM Loader boot mode, connect to MBaRZG2x via UART0, serial port
settings 115200-8-N-1. On power on you should see the following message
via UART0:
```
 SCIF Download mode (w/o verification)
 (C) Renesas Electronics Corp.

-- Load Program to SystemRAM ---------------
please send !
```

Send the Flash Writer binary
(`AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_TQMARZG2X.mot`) via UART0:
```
dd if=AArch64_Flash_writer_SCIF_DUMMY_CERT_E6300400_TQMARZG2X.mot of=/dev/tty<YOUR TTY DEVICE> status=progress
```

After the Flash Writer is successfully written, you should see the following
prompt, (with RZ/G2M, RZ/G2H respective):
```
Flash writer for RZ/G2N V1.05 Aug.16,2021
>
```

Increase baud rate for faster downloads (command `sup`):
```
>sup
Scif speed UP
Please change to 921.6Kbps baud rate setting of the terminal.
```

Reconnect to UART0 with baud rate 921600. The prompt character `>` is not shown
after reconnect, press `Return` once to verify you are connected.


#### Flash eMMC

Five files in S-record format are written to the eMMC:

| Filename                   | eMMC Partition Area | Start Address in sector | Program Start Address |
| -------------------------- | ------------------- | ----------------------- | --------------------- |
| bootparam_sa0.srec         |                   1 |                     0   |              e6320000 |
| bl2-${MACHINE}.srec        |                   1 |                     1e  |              e6304000 |
| cert_header_sa6.srec       |                   1 |                     180 |              e6320000 |
| bl31-${MACHINE}.srec       |                   1 |                     200 |              44000000 |
| u-boot-elf-${MACHINE}.srec |                   2 |                     0   |              50000000 |

Example for bootparam_sa0.srec:
```
>em_w
EM_W Start --------------
---------------------------------------------------------
Please select,eMMC Partition Area.
 0:User Partition Area   : 7636800 KBytes
  eMMC Sector Cnt : H'0 - H'00E90E7F
 1:Boot Partition 1      : 4096 KBytes
  eMMC Sector Cnt : H'0 - H'00001FFF
 2:Boot Partition 2      : 4096 KBytes
  eMMC Sector Cnt : H'0 - H'00001FFF
---------------------------------------------------------
  Select area(0-2)>1
-- Boot Partition 1 Program -----------------------------
Please Input Start Address in sector :0
Please Input Program Start Address : e6320000
Work RAM(H'50000000-H'50FFFFFF) Clear....
please send ! ('.' & CR stop load)

# send bootparam_sa0.srec with dd on your development host:
# dd if=bootparam_sa0.srec of=/dev/tty<YOUR TTY DEVICE> status=progress

SAVE -FLASH.......
EM_W Complete!
>
```

Flash the other files with the same procedure and the respective values.

After all files are flashed to eMMC, reconnect to UART0 with 115200 baud rate
and power cycle the device with boot mode set back to eMMC.

#### Flash QSPI NOR

QSPI boot is currently not supported.

#### Further reading

See https://github.com/renesas-rz/rzg2_flash_writer for more information.

### Select eMMC/SD for booting Linux

Linux kernel, device tree and root filesystem can be loaded from eMMC and SD
card. By default, the SD card is selected as boot device for Linux and is not
automatically switched to eMMC if no SD card is present. The following commands
can be used to manually switch the boot device for Linux to eMMC (MMC device 1):
```
setenv mmcdev 1
setenv mmcblkdev 1
saveenv
```

## Howto

### Display Support

| Interface | Device tree             | Type               |
| --------- | ----------------------- |--------------------|
| HDMI      | \*.dtb                  | compatible monitor |
| LVDS      | \*-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |

To change the used device tree, set the U-Boot variable `fdt_file`, e.g.:
```
setenv fdt_file r8a774b1-tqmarzg2n-mbarzg2x-lvds-tm070jvhg33.dtb
```

### CAN

By default, CAN-FD is enabled. CAN and CAN-FD are provided by
separate hardware controllers and are mutually exclusive. CAN can be activated
by changing the device tree.

#### Troubleshooting

In case of problems first check the bus termination on S1:

| S1-1 | S1-2 | Termination state                   |
| ---- | ---- | ----------------------------------- |
| Off  | Off  | Not terminated                      |
| On   | Off  | CAN1 terminated with 120 Ohm        |
| Off  | On   | CAN2 terminated with 120 Ohm        |
| On   | On   | CAN1 & CAN2 terminated with 120 Ohm |

#### Enable CAN-FD

To enable CAN-FD the following command can be used:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

#### Enable CAN without FD

##### Device tree changes

```diff
diff --git a/arch/arm64/boot/dts/renesas/mbarzg2x.dtsi b/arch/arm64/boot/dts/renesas/mbarzg2x.dtsi
index 24699092f754f..00c0b62dafac8 100644
--- a/arch/arm64/boot/dts/renesas/mbarzg2x.dtsi
+++ b/arch/arm64/boot/dts/renesas/mbarzg2x.dtsi
@@ -192,18 +192,16 @@ phy0: ethernet-phy@0 {
 	};
 };
 
-&canfd {
-	pinctrl-0 = <&canfd_pins>;
-	pinctrl-names = "default";
-	status = "okay";
-
-	channel0 {
-		status = "okay";
-	};
-
-	channel1 {
-		status = "okay";
-	};
+&can0 {
+	pinctrl-0 = <&can0_pins>;
+	pinctrl-names = "default";
+	status = "okay";
+};
+
+&can1 {
+	pinctrl-0 = <&can1_pins>;
+	pinctrl-names = "default";
+	status = "okay";
 };
 
 &du {
@@ -396,15 +394,14 @@ pins_mii_tx {
 		};
 	};
 
-	canfd_pins: canfd {
-		channel0 {
-			groups = "canfd0_data_b";
-			function = "canfd0";
-		};
-		channel1 {
-			groups = "canfd1_data";
-			function = "canfd1";
-		};
+	can0_pins: can0 {
+		groups = "can0_data_b";
+		function = "can0";
+	};
+
+	can1_pins: can1 {
+		groups = "can1_data";
+		function = "can1";
 	};
 
 	scif0_pins: scif0 {
```

##### Configuration

Configure CAN1/2 per commandline without CAN-FD:
```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

### GPU and VPU support with RZ MPU Multimedia Package

The RZ/G2 GPU and VPU are supported by proprietary Renesas drivers and firmware
provided in the [RZ MPU Multimedia Package](https://www.renesas.com/us/en/products/microcontrollers-microprocessors/rz-mpus/rzg-linux-platform/rzg-marketplace/verified-linux-package/rz-mpu-multimedia-package-rzg2h-rzg2m-rzg2n-and-rzg2e).

**Note**: Only RZU MPU Multimedia Package V1.0 is currently supported.

This package must be obtained directly from Renesas. The package contains the
Yocto layer `meta-rz-features` with drivers, firmware and codecs as binary
files. To include this layer for your build, add it to your Yocto build path
(i.e. the same path as `meta-tq`) and your `bblayers.conf` file.

**Note**: `meta-rz-features` uses `DISTRO_FEATURES_append` without spacing
for new items in `include/codec/plugin.inc`. This results in possibly broken
`DISTRO_FEATURES` distro features. To fix this issue, add spaces around the
distro features in `plugin.inc`.

## Support Wiki

See [TQ Embedded Wiki for TQMaRZG2x](https://support.tq-group.com/en/arm/tqmarzg2x)
