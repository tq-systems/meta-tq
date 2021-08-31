# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## zeus.TQMa8.BSP.SW.0055

### Added

* TQMa8Xx\[S\]: support for Cortex M4 enabled bootstream
* add new variant tqma8xqp-2gb-mba8xx
* linux: TQMa8Xx\[S\]: device trees for Cortex M4 demos
* U-Boot: TQMa8Xx\[S\]: add reserved memory nodes in DT to prevent U-Boot
  accessing M4 reserved memory

### Changed

* rename machines tqma8x\[d,q\]p-mba8xx to tqma8x\[d,q\]p-1gb-mba8xx to allow
  new variants
* imx-boot: allow building bootstream with Cortex M4 demo for TQ Systems SOM
* Update SCFW version: basic CortexM4 support for TQMa8Xx\[S\] and new
  2GB DDR3L / ECC variant of TQMa8Xx
* Update tqma8-cortexm-demos version: basic CortexM4 support for TQMa8Xx\[S\]

### Fixed

* linux: TQMa8Xx: set CAN PDRV to low
* linux: upstream fixes from linux-imx
* U-Boot: TQMa8\[X\]x\[S\]: fix location U-Boot config node in DT

## zeus.TQMa8.BSP.SW.0054

### Added

* TQMa8MxNL: prepare support for Vision Components GmbH MIPI CSI cameras
  - add device trees
  - add 'camera' MACHINE\_FEATURE
* TQMa8MPxL: prepare support for Vision Components GmbH MIPI CSI cameras
  - add device trees
  - add 'camera' MACHINE\_FEATURE
* linux: TQMa8MxNL / TQMa8MPxL: add devicetrees for Vision Components GmbH
         MIPI CSI cameras
* linux: add RAW/Bayer formats for `imx8_mipi_csi2` and `mx8-isi-cap`

* u-boot: TQMa8MPxL: mfgtool defconfig for MBa8MPxL

### Changed

* linux: kernelconfig: build camera and support drivers into kernel instead as
         module
* linux: TQMa8Mx / TQMa8QM / TQMa8Xx / TQMa8XxS: label sys EEPROM as read only
* linux: TQMa8MPxL: optimize pad settings for GPIO
* linux: TQMa8MPxL: use verified pad settings for SD / ENET and QSPI
* u-boot: TQMa8MPxL: use verified pad settings for ENET and QSPI
* u-boot: TQMa8MPxL: use pad setting from kernel for GPIO / I2C / QSPI

### Fixed

* linux: port and implement fixes for `imx8_mipi_csi2` and `mx8-isi-cap`
* linux: TQMa8\[xyz\]:  add GPIO\_OPEN\_DRAIN to I2C GPIO to prevent warnings
* linux: TQMa8Xx: make backlight for LVDS work again. When using PWM from
         different power domain, this domain needs to be enabled
* u-boot: TQMa8Xx / TQMa8XxS / TQMa8QM: prevent voltage switch error message
          for e-MMC. This is not a real error but ugly behaviour which was fixed
          for other SOM before
* u-boot: TQMa8MxML: improve pad settings for QSPI, needed for stable function
          at higher temperature
* u-boot: TQMa8\[xyz\]: add GPIO\_OPEN\_DRAIN to I2C GPIO to prevent warnings
          (sync with kernel)
* TQMa8QM: fix typo in machine file preventing mfgtool bootstream builds

### Removed

* Recipes for never used automatic build of mfgtool bootstreams. While removing
  the recipes remove also PREFFERRED\_PROVIDER vars for these recipes

## zeus.TQMa8.BSP.SW.0053

### Added

* linux: drivers for Vision Components GmbH MIPI CSI cameras with Sony IMX327
  and Omnivision OV9281
* linux: add device trees for TQMa8XDPS on MB-SMARC-2
* machines: add TQMa8XDPS on MB-SMARC-2

### Changed

* TQMa8MxML: prepare support for Vision Components GmbH MIPI CSI cameras
  - add device trees
  - add 'camera' MACHINE\_FEATURE
* linux: TQMa8Mx: apply NXP fix for PCIe Phy VPH @ 3.3V
* linux: TQMa8Mx / TQMa8MxML: disable PCIe L1SS
* linux: enable more RAW/Bayer formats for `mxc_mipi_csi` and `mxc-mipi-csi2_yav`
* TQMa8Mx: prepare support for Vision Components GmbH MIPI CSI cameras
  - add device trees
  - add 'camera' MACHINE_FEATURE
* TQMa8\[Mx,MxML,MxNL,MPxL,Xx,XxS,x\]: improve kernel config
  - remove ACPI
  - remove unused audio stuff
  - remove unused net devices
  - enable Vision Components GmbH MIPI CSI cameras
* u-boot: TQMa8MxML: DDR Timing improvement (REV.0153)
* u-boot: TQMa8MxML / TQMa8MxNL: use default prompt

### Fixed

* u-boot: TQMa8Mx: allow chip REV. 2.2
* u-boot: TQMa8Mx: apply fix for PCIe PHY VPH
  * disable VREG_BYPASS early to ensure using the CPU internal regulator for
    PCIe Phy when supplying with 3.3 V
* u-boot: TQMa8MPxL: pad config fixes and optimization in DT
* doc: README.md: board table
* imx-sc-firmware: update to version TQMa8.NXP-v1.6.0.B4894.0031
  from zeus.TQMa8.BSP.SW.0037 (RAM size IOCTL for TQMa8QM 8GB)

## zeus.TQMa8.BSP.SW.0052

### Fixed

* u-boot: TQMa8MxML: RAM Timing
* u-boot: TQMa8XDPS: board name handling

## zeus.TQMa8.BSP.SW.0051

### Added

* TQMa8MPxL: enable CortexM demo package
* linux: TQMa8MPxL: device tree for CortexM7 demos
* u-boot: TQMa8MPxL: env settings for CortexM7
* u-boot: DSR support for Micron SPI NOR
* linux: DSR support for Micron SPI NOR
* TQMa8MPxL: add more machine features (can, rtc, serial, ...)

### Changed

* machines / layer.conf: disable packages only relevant for NXP STK
  * nxp8987 WiFi
  * imx-m\[4,7\]-demos
* machines: TQMa8Mx / TQMa8MxML / TQMa8MxNL: update to new CortexM demo package
* recipes-bsp: rewrite recipe for CortexM demos
  * based on NXP SDK v2.9.0
  * rename and sanitize recipe
  * use versioned archive name
* linux: TQMa8MPxL: fix drive strength for SAI3
* linux: backport upstream PMIC driver for PCA9450
  * adjust device trees for TQMa8MPxL / TQMa8MxML / TQMa8MxNL
  * limit voltage rails for TQMa8MxML / TQMa8MxNL
* u-boot: TQMa8MxNL / TQMa8MxML: optimize PMIC config in SPL
  * lower voltage for LDO2 / V\_SNVS\_0V8
  * improved configs for BUCK1/3 (recommendations from hardware development guide
    and app note for using same board for i.MX8MN / i.MX8MM)
* u-boot: TQMa8MPxL: make SPL more silent, do not print PMIC registers
* linux: TQMa8MPxL / TQMa8MxML / TQMa8MxNL: label sys EEPROM as read only
* linux: TQMa8MPxL: use verified pad config for e-MMC
* linux: TQMa8MxML / TQMa8MxNL: use verified pad config
* u-boot: TQMa8MPxL: use verified pad config for e-MMC
* u-boot: TQMa8MxML / TQMa8MxNL: use verified pad config
* u-boot: TQMa8MxML / TQMa8MxNL: optimize RAM-config
* u-boot: TQMa8MPxL fix RAM-config to support 2000 mHz / 4000 MZ

### Fixed

* linux: TQMa8MxNL: fix SAI interface sychronous play / record
* u-boot: backport lmb fixes from upstream, needed for newer DTB with lots of
  reserved mem regions
* u-boot: fsl_fspi: fix flow for EVCR access
* alsa-state: optimize codec preconfig. PCM input and LineOut amp factors were
  configured suboptimal.
* linux: TQMa8MPxL: use DTE for UART 1-3

## zeus.TQMa8.BSP.SW.0050

### Added

* TQMa8MPxL: add support for new machine (see doc for initial supported features)
* u-boot: TQMa8Mx\[M,N\]L: support SD UHS modes and optimized pad config
* linux: TQMa8Mx\[M,N\]L: support SD UHS modes and optimized pad config
* Linux: I2C recovery gpio for
  * TQMa8Mx
  * TQMa8Mx\[M,N\]L
  * TQMa8Xx
  * TQMa8XxS
* u-boot: I2C recovery gpio for
  * TQMa8Mx
  * TQMa8Mx\[M,N\]L
  * TQMa8Xx
  * TQMa8XxS
* all: support for NXP BSP rel\_imx\_5.4.70\_2.3.1
  * u-boot: migrate all imx8 machines with U-Boot v2020.04 support to NXP BSP version
  * linux: migrate all machines to NXP BSP version
  * imx-atf: add fork based on NXP BSP version
* linux:  TQMA8:  enable wifi support in kernelconfig
* machines: enable wifi for all TQMA8 mainboards

### Changed

* u-boot: TQMa8Mx: backport QSPI driver changes to support more flash types out
  of the box
* TQMa8Mx: port to U-Boot version from used NXP BSP
* TQMa8Mx\[M,N\]L: port to U-Boot version from used NXP BSP
* all: support for NXP BSP rel\_imx\_5.4.70\_2.3.2
  * imx-boot: use meta-freescale upstream as template for own recipe version 1.0
  * machines: adapt to NXP BSP
  * recipes:  adapt to NXP BSP, merge branches for imx-atf, uboot-imx and linux-imx

### Fixed

* linux: ASoc: port fixes for Codec on STK to prevent race condition that could
  cause driver load failure
* linux: fix DT for mPCIe USB cards
  * TQMa8X\[D,Q\]P\[4\] on MBa8Xx
  * TQMa8Mx\[M,N\]L on MBa8Mx
* linux: TQMa8MPxL: fix USB phy driver
  * port power handling for dual role without Type-C using regulator
  * add optional over current low active signal handling
* linux: TQMa8MPxL: MDIO ad phy reset handling for dwmac / stmac driver
* u-boot: TQMa8MPxL: fix phy init for dwc\_eth\_qos mdio bus
* u-boot: TQMa8Mx\[M,N\]L: use mmc rescan in env scripts to prevent errors when
  exchanging SD Card
* u-boot: TQMa8Mx\[M,N\]L: prevent nasty voltage switch error warning for e-MMC

## Removed

* doc: TQMa8MxML / TQMa8MxNL: remove remaining docs for incompatible MBa8Mx REV.020x
* u-boot: drop support for v2019.04, since no user left
* firmware-imx: remove local recipe

## zeus.TQMa8.BSP.SW.0036

* u-boot: v2020.04 fix FEC Ethernet PHY init
  - fix phy_config using devicetree
  - forward error from phy_config
* config/machines: sanitize variable assignments to improve reusability
  - replace `IMAGE_INSTALL_append` with more approbiate
    `MACHINE_EXTRA_RRECOMMENDS_append`
  - remove all dirty magic for `DISTRO_FEATURES_remove`
  - replace hard assignments to `IMAGE_BOOT_FILES` with `?=`

## zeus.TQMa8.BSP.SW.0035

### Added

* machines: support for tqma8xdp\[4\]-mba8xx
* linux:  support for tqma8xdp\[4\]-mba8xx

### Fixed

* u-boot: 2019.04 / TQMa8Mx\[M,N\]L: fixes for REV.0200
* u-boot: 2020.04 / TQMa8QM: fix env script loadhdp
* u-boot: 2020.04 / TQMa8Xx: fix board name for TQMa8XDP\[4\]

## zeus.TQMa8.BSP.SW.0034

### Changed

* u-boot: update v2019.04 to support HW REV.0200 of TQMa8Mx\[M,N\]L
  * updated RAM-Timing for TQMa8MxML with 2GB
  * e-MMC is now on USDHC3
  * some GPIO signals changed
* conf/machines: rename tqma8mxml-1gb-mba8mx -> tqma8mxml-2gb-mba8mx since
  HW REV.0200 has per default 2 GB RAM
* linux: TQMa8Mx\[M,N\]L: fix device trees for TQMa8Mx\[M,N\]L TQMa8Mx\[M,N\]L
  * e-MMC is now on USDHC3
  * some GPIO signals changed

### Fixed

* u-boot: TQMa8Mx\[M,N\]L: remove muxing and usage of USER\_LED\_3 signal as
  already done in kernel dtb
* linux: TQMa8x / TQMa8Xx / TQMa8XxS: backport patch for SCU thermal sensor to
  prevent shutdown on negative sensor values
* linux: TQma8x: fix device trees to enable support without SCU PMIC thermal
  sensor. Whith this patch thermal sensors are working again

### Added

* linux: TQMa8Mx\[M,N\]L: add recovery GPIO for I2C1
* linux: TQma8Xx / TQMa8XxS: device tree examples for LVDS displays on
  ldb2 / LVDS1
* linux: TQma8x: add support for SCU PMIC thermal sensor

### Removed

* machines: TQMa8Mx\[M,N\]L: support for HW REV.0100

## zeus.TQMa8.BSP.SW.0033

This is the initial production release for TQMa8XQPS.

### Changed

* linux: ASoc: backport improvements for TI TLV320AIC32x4 codec
* linux: TQMa8QM: enable SD regulator on MBa8x REV.0200 and SD UHS modes
* u-boot: TQMa8QM: enable SD regulator on MBa8x REV.0200 and SD UHS modes
* doc: TQMa8Xx / TQMa8XxS / TQMa8x: update fatures + issues
* doc: TQMa8XxS / TQMa8x: updates for new bootstream versions
* linux: TQMa8XQP / TQMa8XQPS: cleanup LVDS support device trees
* u-boot: TQMa8x: use v2020.04 together with improve drive strenth settings
* u-boot: TQMa8XxS: use v2020.04 together with improve drive strenth settings
* linux: TQMa8Xx: lower drive strength for some pins
* linux: TQMa8QM: lower drive strength for some pins
* linux: TQMa8Xx: QSPI NOR DSR

### Fixed

* linux: TQMa8XxS: add missing I2C GPIO recovery
* linux: TQMa8QM / TQMa8Xx\[S,4\]: adjust thermal trip points
* u-boot: TQMa8XxS: disable SD3 modes for HW REV.0200, enable for newer REV
* u-boot: TQMa8x / TQMa8Xx / TQMa8XxS: fix not working update scripts when
  exchanging an SD card just before start updating
* linux: TQMa8Xx: reenable audio in DT (deadlock fixed for I2C host)
* linux: i2c-imx-lpi2c: fix deadlock (TQMa8Xx/TQMa8x)
* linux: i2c-imx: fix deadlock (TQMa8Mx, TQMa8MxML, TQMa8MxNL)
* linux: sn65dsi83: add fix from Maxim Paymushkin for probe panel errors
* linux: TQMa8XQPS: fix earlycon bootarg
* linux: TQMa8MNxL: fix spi gpio CS
* linux: TQMa8Xx: fix PMIC / CPU thermal sensor support
* linux: TQMa8QM: fix DP audio support
* linux: TQMa8QM: make PCIe voltage alway on, fixes USB port usablity at PCIe slot

### Added

* linux: TQMa8XxS:
  * SMARC-2 GPIO
  * SMARC-2 DSI0 I2C
  * SMARC-2 LVDS1 with example
  * support for second UART
* u-boot: prepare TQMa8XDP / TQMa8XDPS support
* TQMa8x: board specific asound.state
* linux: TQMa8x: enable audio
* linux: TQMa8XQPS: add support for LVDS AUO G133HAN.01 full HD panel
* linux: allow DSR for Macronix SPI NOR on all supported TQ-Systems SOM
* u-boot: allow DSR for Macronix SPI NOR on all supported TQ-Systems SOM
* TQMa8QXPS: prepare REV.0300 Support, support for REV.0200 moved to separate
  config (tqma8xqps-mb-smarc-2-r0200)
* linux: TQMa8QXPS: enable VPU support
* linux: TQMa8QXP[4]: enable VPU support
* lm-sensors: TQMa8QM: add ADC support
* lm-sensors: allow machine specific configs
* linux: TQMa8QM: enable ADC support
* linux: spi-nor: add DSR support for Macronix flash
* linux: TQMa8QM: enable VPU support
* linux: TQMa8QM: DT example for MicroBus (clock module RTC5)
* linux: TQMa8Xx: QSPIA_SS1 is usable as GPIO

### Removed

* imx-sc-firmware: drop support for v1.3.1
* imx-seco: drop support for v2.5.6

## zeus.TQMa8.BSP.SW.0032

This is the initial production release for TQMa8XQP / TQMa8XQP4.

### Changed

* doc: updates for TQMa8Xx

### Added

* imx-atf: TQMa8Xx: backport recipe for 2.2, verified for production release
* firmware-imx: TQMa8Xx: add version based on porting kit 1.6.0,
  verified for production release
* imx-seco: TQMa8Xx: backport recipe for 3.7.1, for production release
* u-boot: TQMa8Xx: add recipe for v2020.04, verified for production release

### Fixed

* treewide: spelling of TQ-Systems
* doc: DIP switch doku for TQMa8Mx / TQMa8MxML / TQMa8MxNL

## zeus.TQMa8.BSP.SW.0031

### Added

* linux: TQMa8x: initial support for current kernel with MBa8x REV.020x
* u-boot: TQMa8x: initial support for current kernel with MBa8x REV.020x
* meta-tq: add TQMa8 on MBa8x with 4GB / 8 GB
* doc: document an LPDDR4 issue for TQMa8Xx4 / TQMa8XxS

### Fixed

* doc: fixes for TQMa8Mx\[ML,NL\] / MBa8Mx REV.030x
* doc: cleanup wrong entries for TQMa8Xx feature list

## zeus.TQMa8.BSP.SW.0030

### Added

* linux: TQMa8Mx: compatibility to MBa8Mx REV.0300
* u-boot: TQMa8Mx: compatibility to MBa8Mx REV.0300
* machines: integrate CortexM examples for TQMa8Mx / TQMa8Mx\[M,N\]L
* recipes-bsp: first version of CortexM examples for TQMa8Mx / TQMa8Mx\[M,N\]L
* linux: TQMa8Mx: add dt examle for HDMI
* machines: TQMa8Mx / TQMa8Mx\[M,N\]L: include RPMSG examle device tree
* machines: add configuration for TQMa8Mx with 4 GiB RAM
* machines: TQMa8MxNL: build FlexSPI bootstream by default
* linux: TQMa8Mx: add dt examle for M4 / RPMSG
* u-boot: TQMa8Mx: support for variant with 4 GiB RAM
* u-boot: TQMa8Mx\[M,N\]L add flexspi boot support
* u-boot: TQMa8Mx\[M,N\]L add env support for bootstream update on flexspi
* u-boot: TQMa8Mx\[M,N\]L add env support for CortexM core boot and update
* linux: TQMa8Mx\[M,N\]L add devicetree for RPMSG support
* linux: TQMa8XxS: add SPI support in DT

### Changed

* u-boot: TQMa8Mx: restrict SPL size. Use only configured DDR timing to prevent
  SRAM overflow
* machines: TQMa8Mx: rename an include (mba8mx.inc -> tqma8mx-mba8mx.inc)
* linux: TQMa8Xx[S]: fix compatible names in devicetree
* u-boot: TQMa8Mx: use devicetree to configure MMC env settings
* u-boot: TQMa8Mx: increase env size to 32 kiB (equal to TQMa8Mx\[M,N\]L)
* u-boot: TQMa8Mx: cleanup and cosmetic in config header
* u-boot: tqc/common: tqc-rtc improvements (offset, clkout)
* u-boot: TQMa8Xx[S]: fix compatible names in devicetree
* u-boot: TQMa8Mx\[M,N\]L fix compatible and model names in devicetree
* linux: TQMa8Mx\[M,N\]L fix compatible and model names in devicetree
* linux: TQMa8Mx\[M,N\]L fix devicetree bindings for module

### Fixed

* linux: fix imx-spi for GPIO chip select (deferred probe failures on TQMa8Mx)
* u-boot: TQMa8Mx: fix USB Support. DM_USB and DWC3 for i.MX8M are not 100%
  compatible when using with non Type-C dual role setup
* linux: TQMa8Xx: fix dt bindings
* linux: TQMa8Mx\[M,N\]L: remove USER\_LED\_3 (not usable for these SOM)
* linux: i.MX8MQ: backport device tree fixes from NXP upstream
  * MLK-24483-2: ecspi compatible to imx51-ecspi
  * MLK-24383: Change the noc clock setting
* u-boot: TQMa8Mx: fix compiler warning in spl
* u-boot: TQMa8Mx\[M,N\]L: fix spelling errors in multiple places
* linux: i.MX8MM: backport fix for voltage @ 1.6GHz operating point
* linux: TQMa8Mx\[M,N\]L switch to gpio CS for SPI (device tree change,
  should fix spi communication issues)
* linux: TQMa8Mx switch to gpio CS for SPI (device tree change,
  should fix spi communication issues)
* linux: i.MX8QXP: increase clock for LPSPI1, use same PER base clocks as other
  LPSPI instance. Fixes clock mismatch for spidev on TQMa8XxS

### Removed

* linux: devicetree: remove `local-mac-address` prop from fec for TQ SOM
  `mac-address` has the highest priority per binding doc and should contain
  the last used address

## zeus.TQMa8.BSP.SW.0029

### Added

* u-boot-fw-utils-imx-tq: add default config for TQMa8Xx\[S,4\]
* linux: TQMa8XQP\[4\]: audio support
* systemd-machine-units: rewrite bbappend to conform to new systemd version.
  CAN does not need separate units, but a simple network definition. Tested with
  TQMa8XQP\[S,4\]
* machines: Add Support for TQMa8XQP4. The new machine needs a different bootstream
  hence a differen SCU firmware. The rest is equal to the existing TQMa8XQP.
* u-boot: Support for TQMa8XQP4 as variant of TQMa8Xx (defconfigs, memory defines)

### Changed

* linux: TQMa8: dt: use aliases for RTC. This guarantees that the I2C RTC backuped
  by coin cell is always rtc0 which is important for some userland tools.
* alsa-state: Use same asound.state for all TQMa8 boards. Depends on linux support
* linux: TQMa8Mx / TQMa8MxM\[M,N\]L: dt: change sound card model name. The codec
  integration is the same. So no need to use different model names. As a plus, we
  can use a single asound.state for multiple boards

### Fixed

* u-boot-fw-utils-imx-tq: fix missing dependency on bison-native
* imx-boot: use weak assignments for IMXBOOT_TARGETS to allow overrides and
  to prevent generating invalid bootstreams in imx-boot-mfgtool_
* u-boot: TQMa8Xx\[S\]: fixed missing defines for default mtest addresses
* u-boot: TQMa8XxS: dt: fix wrong setting for pin muxing
* linux: TQMa8Xx\[S\]: dt: fix wrong setting for pin muxing

### Removed

* linux: TQMa8XxS: drop support for HW REV.010x
* u-boot: TQMa8XxS: drop support for HW REV.010x

## zeus.TQMa8.BSP.SW.0028

### Added

* TQMa8Xx\[S\]: support bootstream for mfgtool config
* port / append mfgtool dependend recipes
* imx-boot: support reusage of recipe
* TQMa8Xx\[S\]: support bootstream for FlexSPI
* u-boot: TQMa8Xx\[S\]: display CPU info
* u-boot: TQMa8Xx\[S\]: add mfgtool config
* u-boot: TQMa8Xx\[S\]: single config for SD / e-MMC / FlexSPI Boot device
* port TQMa8XxS to new u-boot / kernel (rev.0200/lpddr4)
* linux: TQMa8XxS: support LVDS
* linux: TQMa8XxS: support CAN
* linux: TQMa8XxS: support PCIe
* linux: TQMa8XxS: support USB
* u-boot: TQMa8XxS: support USB
* linux: TQMa8Xx: support PCIe

### Changed

* TQMa8MxML: enable LVDS device tree
* imx-sc-firmware: update to TQMa8.NXP-v1.3.1.B4124.0029
* imx-boot: support reusage of recipe
* imx-boot: support i.MX8M bootstream with multiple DTB

### Fixed

* imx-sc-firmware: fix bitbake warning

## zeus.TQMa8.BSP.SW.0027

### Changed

* linux: TQMa8MxML: optimized CMA size for 1 GiB RAM
* machines: rename some machines to unify naming and reflect real mem size
* u-boot: TQMa8Mx\[M,N\]L: rename defconfigs to unify naming
* linux: TQMa8MxML: optimized pad settings for SD-Card
* linux: TQMa8Mx\[M,N\]L: simplify USB OTG (use ID from USB)
* u-boot: TQMa8MxML: optimized pad settings for SD-Card
* u-boot: TQMa8Mx\[M,N\]L: simplify USB OTG (use ID from USB)

### Added

* u-boot: TQMa8Mx\[M,N\]L: add support for RTC load capacity configuration
* u-boot: TQMa8Mx\[M,N\]L: QSPI Support (1-1-1, 4B)
* linux: TQMa8MQ: sound support
* linux: TQMa8Mx\[M,N\]L: sound support
* linux: TQMa8Mx\[M,N\]L: USB host support (Hub on MBa8Mx)
* linux: TQMa8Mx\[M,N\]L: enable QSPI flash
* linux: TQMa8Mx\[M,N\]L: enable SD104 for SD-Card
* u-boot: TQMa8Mx\[M,N\]L: USB host support (Hub on MBa8Mx)

### Fixed

* u-boot: TQMa8MxNL: fix RAM Timing for 1GiB
* u-boot: TQMa8MxML: fix RAM size (default 1GiB instead of 512 MiB)
* u-boot: TQMa8Mx\[M,N\]L: fix Kconfig for default device tree
* linux: TQMa8MxML: remove pull config from pad settings
* u-boot: TQMa8MxNL: fix not working USB download in SPL
* u-boot: TQMa8Mx\[M,N\]L: USB power domain handling

## zeus.TQMa8.BSP.SW.0026

### Changed

* linux: backport fixes from 5.4.24_2.1.0 for DSI and USB
* linux: improve driver for SN65DSI83
* linux: simple-panel fixes for i.MX8 and LVDS via DSI
* imx-boot: use version from NXP BSP 5.4.3_2.0.0
* imx-boot: do not longer use dirty hack for U-Boot DTB of i.MX8M CPU

### Added

* tqma8mq: support LVDS/PCIe with new kernel
* local version gereration or u-boot and kernel
* initial support for TQMa8MxML
* initial support for TQMa8MxNL
* initial port of TQMa8Mx to new U-Boot / Kernel (not feature complete)

### Fixed

* fix e-MMC supplies for tqma8mq, tqma8mm, tqma8mn
* fix network problems (caused by wrong regulator settings)

## zeus.TQMa8.BSP.SW.0025

### Changed

* tqma8xx: support LVDS
* tqma8xx: support USB
* linux-imx-tq_5.4: port simple-panel display timings
* linux-imx-tq_5.4: backport LVDS bugfixes from 5.4.3\_2.0.0 beta
* linux-imx-tq_5.4: port LVDS DSI bridge
* linux-imx-tq_5.4: port audio fixes for TQ-Systems starter kits
* mesa: restrict bbappend to mx8
* tqma8: disable *.bz2 image types
* update to zeus

### Added

* port TQMa8Xx to new u-boot / kernel
* add recipes for kernel / u-boot based on NXP 5.4.y_1.0.0 reference BSP
* add machine files for TQMa8 modules (based on old local kernel and u-boot,
  only basic boot support))
* update to 1.3.1 bed SCFW firmware
* add bbappends for recipes from meta-imx
* add recipes from sumo-tqma8x branch

## warrior.TQMLS10xx.BSP.SW.0101

### Changed

* tqmls10xxa: use EDAC_LAYERSCAPE error detection

### Fixed

* tqmls1043a: U-Boot: use correct SF_DEFAULT_BUS
* tqmls10xxa: Linux: fix error message on reboot

### Added

* tqmls1043a: add configs for 2GB variant

## warrior.TQMa57xx.BSP.SW.0009

### Changed

* tqma57xx: update default kernel to TI 2019.06 (based on 4.19+rt)
* tqma57xx: add alternative kernel mainline 4.19+rt
* tqma57xx: update u-boot to unify tqma57xx variant configurations
* tqma57xx: rename MACHINE tqma574x-ecc-mba57xx to tqma57xx-ecc-mba57xx.conf
  * Makes the MACHINE name match the u-boot defconfig name
  * The renaming does not imply any functional changes, only TQMa5748 has
    ECC memory
* tqma\[6,6ul,6ull,7\]: update u-boot to enable qspi boot

## warrior.TQMa6x.BSP.SW.0115

### Changed

* tqma6\[q,s,dl,qp\]: use "?=" to assign UBOOT_CONFIG
* tqma6x\[q,s,dl,qp\]: add spinor u-boot config to default value of UBOOT_CONFIG
* tqma6qp-mba6x: enable SPI u-boot
* tqma7x: fixes for booting mainline kernel
* tqma6x: u-boot: increase default kernel space on SPI NOR to 10 MiB
* tqma6ul\[l\]x\[l\]: u-boot: emphasize cpu arch mismatch warning

## warrior.TQMa6ULx.BSP.SW.0110

### Added

* tqma6ul\[l\]x: add configs for 512 MB variant

### Changed

* tqma\[6,6ul,6ull,7\]: add SDMA firmware to MACHINE\_EXTRA\_RRECOMMENDS
* tqma6ul\[l\]x\[l\]: simplify machine configs
* u-boot-tq_2016.03: update to support 512 MB variant of tqma6ul\[l\]x

### Fixed

* tqma\[6,6ul,6ull,7\]: assignment of providers for bootloader / kernel should use "?="
* tqma6ul: fix duplicate inclusion of imx-base when using with meta-freescale

## warrior.TQMa57xx.BSP.SW.0008

### Changed

* tqma57xx: rename kernelimage in firmware partition (sync with PTXdist BSP)
* tqma57xx: update kernelconfig to BSP REV.0008
* tqma57xx: update linux-ti to branch TQMa57xx-TI-linux-v4.9 (BSP REV.0008)
* tqma57xx: simplify machine definitions, need only 2 different
* tqma57xx: update u-boot version to 2019.04
* prueth-fw: simplify recipe and allow usage for different mainboards
* TQMa6ULLx\[L\]: finally qualified RAM-Timing for (no functional change)
* linux-tq: improved kernel defconfig for TQMa6x

### Added

* linux-tq: HDMI support for TQMa6x
* tqma6x: enable hdmi support dtb

### Fixed

* linux-tq: DT style fixes for TQMa6x / TQMa6UL\[L\]x\[L\] / TQMa7x
* doc fixes for machine with i.MX6ULL

## warrior.TQMLS10xx.BSP.SW.0100

### Added

* support for TQMLS1088
* support for TQMLS1046 with 8G RAM

### Changed

* update U-Boot for TQMLS1043a and TQMLS1046a
* update Linux for TQMLS1043a and TQMLS1046a
* common defconfig file for TQMLS1043a and TQMLS1046a

### Fixed

* README.md: formatting

## warrior.MBa6ULxL.BSP.0101

### Added

* add CHANGELOG
* experimental support for TQMT10\[22,40,42\]

### Changed

* convert README to markdown
* update Linux for TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6x / TQMa7x
* update U-Boot for TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6x / TQMa7x

### Fixed

* Ethernet resume issue on MBa6ULxL
* ALSA Mono per asound.state for TQMa6ULx / TQMa6ULLx / TQMa6ULxL
* ALSA Mono per asound.state for TQMa6x
* ALSA Mono per asound.state for TQMa7x
* u-boot MMU / Cache fix for Cortex A7 when booting via serial downloader

## warrior.TQMLS102xA.BSP.SW.0112

### Changed

* Linux defconfigs for TQMa6ULx / TQMa6ULLx / TQMa6ULxL from PTXdist BSP TQMLS102xA.BSP.SW.0112
* Linux defconfigs for TQMa7x from PTXdist BSP TQMLS102xA.BSP.SW.0112
* Linux defconfigs for TQMa6x from PTXdist BSP TQMLS102xA.BSP.SW.0112
* Linux defconfigs for TQMLS102xA from PTXdist BSP TQMLS102xA.BSP.SW.0112
  * Systemd compatibility improved
  * disable parallel NOR / CFI
  * Network bridging enable, enable bufferbloat prevention options
  * kernel mode neeon support
  * lockup detector

### Added

* Linux / TQMLS102xA: RS485 Support via DT

## warrior.TQMLS102xA.BSP.SW.0111

### Added

* TQMLS102x audio support from PTXdist BSP TQMLS102xA.BSP.SW.0111

### Fixed

* ptpd recipe warning for architecture when using with meta-freescale

## Older releases
