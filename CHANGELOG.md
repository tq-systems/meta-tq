# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## Changed

* linux: TQMa8MxML: optimized pad settings for SD-Card
* linux: TQMa8Mx\[M,N\]L: simplify USB OTG (use ID from USB)
* u-boot: TQMa8MxML: optimized pad settings for SD-Card
* u-boot: TQMa8Mx\[M,N\]L: simplify USB OTG (use ID from USB)

## Added

* u-boot: TQMa8Mx\[M,N\]L: QSPI Support (1-1-1, 4B)
* linux: TQMa8MQ: sound support
* linux: TQMa8Mx\[M,N\]L: sound support
* linux: TQMa8Mx\[M,N\]L: USB host support (Hub on MBa8Mx)
* linux: TQMa8Mx\[M,N\]L: enable QSPI flash
* linux: TQMa8Mx\[M,N\]L: enable SD104 for SD-Card
* u-boot: TQMa8Mx\[M,N\]L: USB host support (Hub on MBa8Mx)

## Fixed

* linux: TQMa8MxML: remove pull config from pad settings
* u-boot: TQMa8MxNL: fix not working USB download in SPL
* u-boot: TQMa8Mx\[M,N\]L: USB power domain handling

## zeus.TQMa8.BSP.SW.0026

## Changed

* linux: backport fixes from 5.4.24_2.1.0 for DSI and USB
* linux: improve driver for SN65DSI83
* linux: simple-panel fixes for i.MX8 and LVDS via DSI
* imx-boot: use version from NXP BSP 5.4.3_2.0.0
* imx-boot: do not longer use dirty hack for U-Boot DTB of i.MX8M CPU

## Added

* tqma8mq: support LVDS/PCIe with new kernel
* local version gereration or u-boot and kernel
* initial support for TQMa8MxML
* initial support for TQMa8MxNL
* initial port of TQMa8Mx to new U-Boot / Kernel (not feature complete)

## Fixed

* fix e-MMC supplies for tqma8mq, tqma8mm, tqma8mn
* fix network problems (caused by wrong regulator settings)

## zeus.TQMa8.BSP.SW.0025

## Changed

* tqma8xx: support LVDS
* tqma8xx: support USB
* linux-imx-tq_5.4: port simple-panel display timings
* linux-imx-tq_5.4: backport LVDS bugfixes from 5.4.3\_2.0.0 beta
* linux-imx-tq_5.4: port LVDS DSI bridge
* linux-imx-tq_5.4: port audio fixes for TQ Systems starter kits
* mesa: restrict bbappend to mx8
* tqma8: disable *.bz2 image types
* update to zeus

## Added

* port TQMa8Xx to new u-boot / kernel
* add recipes for kernel / u-boot based on NXP 5.4.y_1.0.0 reference BSP
* add machine files for TQMa8 modules (based on old local kernel and u-boot,
  only basic boot support))
* update to 1.3.1 bed SCFW firmware
* add bbappends for recipes from meta-imx
* add recipes from sumo-tqma8x branch

## warrior.TQMLS10xx.BSP.SW.0101

## Changed

* tqmls10xxa: use EDAC_LAYERSCAPE error detection

## Fixed

* tqmls1043a: U-Boot: use correct SF_DEFAULT_BUS
* tqmls10xxa: Linux: fix error message on reboot

## Added

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

## Changed

* tqma6\[q,s,dl,qp\]: use "?=" to assign UBOOT_CONFIG
* tqma6x\[q,s,dl,qp\]: add spinor u-boot config to default value of UBOOT_CONFIG
* tqma6qp-mba6x: enable SPI u-boot
* tqma7x: fixes for booting mainline kernel
* tqma6x: u-boot: increase default kernel space on SPI NOR to 10 MiB
* tqma6ul\[l\]x\[l\]: u-boot: emphasize cpu arch mismatch warning

## warrior.TQMa6ULx.BSP.SW.0110

## Added

* tqma6ul\[l\]x: add configs for 512 MB variant

## Changed

* tqma\[6,6ul,6ull,7\]: add SDMA firmware to MACHINE\_EXTRA\_RRECOMMENDS
* tqma6ul\[l\]x\[l\]: simplify machine configs
* u-boot-tq_2016.03: update to support 512 MB variant of tqma6ul\[l\]x

## Fixed

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

## Changed

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

## Added

* TQMLS102x audio support from PTXdist BSP TQMLS102xA.BSP.SW.0111

## Fixed

* ptpd recipe warning for architecture when using with meta-freescale

## Older releases
