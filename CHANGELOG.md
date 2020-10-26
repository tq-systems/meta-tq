# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

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
* linux-imx-tq_5.4: port audio fixes for TQ Systems starter kits
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
