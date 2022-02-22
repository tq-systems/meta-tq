# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

[[_TOC_]]

## Next Release

## hardknott.TQMa65xx.BSP.SW.0008

### Fixed

* documentation:
  * Updates and fixes for global README.md
  * Improve description of UBI/UBIFS images
  * Add missing infos for TQMa6x / TQMa6UL\[L\]x\[L\]
  * Update Issues for TQMa65xx
* TQMa6Q/DL: Fix accidentally changed sound card name in DTS
* TQMa65xx:
  - Better support for modules without SPI-NOR flash
  - Set correct EEPROM page size for faster writes
  - Add MTD and CAN utils to TQ images (meta-dumpling)
  - Build UBI images for SPI-NOR boot
  - Fix access to QSPI-NOR flash from Linux
  - Fix GPIO access from U-Boot command line

## hardknott.TQMa8.BSP.SW.0083

### Added

* u-boot-imx-tq_2020.04: TQMa8Xx/TQMa8XxS/TQMa8x: support booting UBI rootfs
  from SPI NOR
* TQMa6UL\[L\]x/[L\] : linux 5.15 support based on linux-stable 5.15.y
* linux-tq_5.4 / TQMLS102xA: support EDAC for ECC RAM
* mesa: add bbappend to support TQMa6x / TQMa7x / TQMa6U\[L\]x\[L\] with mainline
  kernel
* TQMa8 / TQMa6x: add new MACHINE_FEATURE `vpu` for all but TQMa8MxNL
* TQMa6x: linux 5.10 support based on linux-fslc 5.10 + imx
* TQMa6x: linux 5.15 support based on linux-stable 5.15.y
* TQMxx: support different list of device tree files, depending on the `virtual/kernel` provider
* TQMa65xx: Add new module family
  - New kernel recipe linux-ti-tq-5.4.109
  - New U-Boot recipe u-boot-ti-tq-2020.01

### Changed

* TQMa6UL\[L\]x/[L\] / TQMa6x: add firmware ath10k as `MACHINE_EXTRA_RRECOMEND`
* TQMLS\[all machines\]: remove rootfs size restrictions for wic images
* linux-tq_5.4: rewrite to use linux-yocto class
* TQMa8 / TQMa6x: force build vivante gpu driver for linux-tq-imx as out of tree module
  to ensure versions between module and userland match. Overrides default assignments
  from meta-freescale.
* linux-imx-tq: switch to use linux-yocto for kernel fragment handling
* u-boot: use local version as implemented in branch zeus-tqma8. This will make
  the U-boot version string consistent with newer and upcomming kernel version
  string and consists of `<upstream version>-tq+g<git short hash>`

### Fixed

* u-boot-imx-tq_2020.04: support for large micron SPI NOR on TQMa8Xx / TQMa8x
  flash was discovered but progam / erase silently fails. Backport upstream
  driver changes for FlexSPI IP and use this driver instead of the older one.
* TQMa6x: VPU firmware was not installed if building against linux mainline kernel
  without meta-freescale
* linux-tq_5.15: TQMa6x: kernel defconfig
  remove debug options to improve performance and cleanup
* treewide: fix assignment of `IMAGE_BOOT_FILES` in machine configs. Do not use `?=`
  to prevent unwanted defaults from vendor layers.
* u-boot-tq_2017.11 / TQMLS102xA: ensure backward compatibility to older SOM variants
  using module EEPROM on different I2C address
* machines: add `USE_VT = "0"` where missing to disable virtual console support.
* u-boot-imx-tq\_2020.04: tqma8\[all variants\] adjust `DEFAULT_FDT_FILE` to
  kernel 5.10 used in this BSP. This is also the naming scheme that will be
  found in linux-mainline.
* u-boot-imx-tq\_2016.03: tqma\[6,7,6ul,6ull\] adjust board specific dt_fixup
  to work also with newer kernel versions. In newer kernel node naming was
  changed according to device tree specification.

## Removed

* linux-imx-tq_5.4: remove support for i.MX8 based machines

## hardknott.TQMa8.BSP.SW.0082

### Changed

* tqma335x: linux-ti-tq: enable RTC TPS65910 in kernelconfig
* tqma335x: u-boot-tq: e-mmc pins slewrate set to slow

### Added

* TQMa8Xx/TQMa8Xx4/TQMa8XxS: linux 5.10 support based on
  linux-fslc 5.10 + imx
* tqma335x: u-boot-tq: mmc hwpartition allows size '-' for whole e-mmc
* machines: add machine config for TQMa8MxML with 1GiB RAM on MBa8Mx
* u-boot: tqma8mxml: add support for SOM variant with 1GiB RAM

### Fixed

* linux-rt-tq_4.19: fix missing defaults for SRCREV and SRCBRANCH
* ti-sgx-ddk-km: spurious build problems due to bbappend. Not tested with rootfs
* tqma335x: linux-ti-tq: fixes for RTC TPS65910 in dtb

## hardknott.TQMa8.BSP.SW.0081

## Changed

* doc: add missing links to TQ-Embedded Wiki
* doc: updates for TQMa8Mx / TQMa8MPxL / TQMa8MxNL

## hardknott.TQMa8.BSP.SW.0080

## Changed

* rework systemd-machine-units bbappend (add simplification from branch
  zeus-tqma8, adapt to newer systemd version with CAN FD support)
* adjust layerscape machine files to meta-freescale / hardknott
* adapt layerscape specific recipes / bbappend to meta-freescale / hardknott
* adapt TI specific recipes / bbappend to used meta-ti
* rename atf recipe for LS based boards to qoriq-atf (follows changes in meta-freescale)
* update sub modules to support hardknott

## Added

* TQMa8Mx/TQMa8MxML/TQMa8MxNL/TQMa8x/TQMa8MPxL: linux 5.10 support based on
  linux-fslc 5.10 + imx
  * experimental usb support for ath10k
  * adjusted kernel config settings
* port TQMa8 boards from branch zeus-tqma8 (equal to release
  zeus.TQMa8.BSP.SW.0056 of mentioned branch)
* ubi as MACHINE_FEATURE if \[Q\]SPI NOR is supported by machine
* gitpkgv-nonexact.bbclass

## Fixed

* weak assignments in machine files wherever possible to allow overwriting for
  custom mainboards or project specific tailoring
* package dependencies for board specific firmware files in imx-boot-tq

## Removed

* preview support for PowerPC machines

## zeus.TQMLS1012AL.BSP.SW.0010

### Added

* tqmls1012al: add RCW with 1 GHz core frequency

### Changed

* tqmls1012al: use 1 GHz core frequency as default

## zeus.TQMLS1012AL.BSP.SW.0009

### Added

* tqmls1012al: add README
* atf: add support for tqmls1012al with 1 GB RAM
* tqmls1012al: add module variant with 1 GB RAM

## zeus.TQMa35xx.BSP.SW.0120

### Added
* tqma335x: Support for TQMa335x\[L\] 256MB and 512MB

### Changed

* linux-rt-tq-5.4: tqma\[6,6ul,6ull,7\]: sync with PTXdist defconfig
* linux-rt-tq-5.4: update defconfigs
* linux-tq-5.4: tqma\[6,6ul,6ull,7\]: sync with PTXdist defconfig
* linux-tq-5.4: update defconfigs
* linux-rt-tq-5.4: update to 5.4.87-rt48
* linux-tq: update to v5.4.87

## zeus.TQMLX2160A.BSP.SW.0009

### Changed

* tqmlx2160a: linux-lsdk-tq: update to v5.4.47 and use same recipe as tqmls1088a
* tqmlx2160a: atf: change ddr4 parameter to allow reliable 2800MT/s
* tqmlx2160a: wic: generate sd-card image on default
* tqmlx2160a: change default rcw to 14_11_2

### Fixed
* tqmlx2160a: u-boot-lsdk-tq: bugfixes in pcie, sdcard
* tqmlx2160a: mc-utils: fixed dpl-files

## zeus.TQMa57xx.BSP.SW.0014

### Changed
* u-boot-tq_2019.04: fix crc offset for variant detection on TQMa57xx
* u-boot-tq_2019.04: fix written file names for update_uboot script on TQMa57xx

## zeus.TQMLS10xxA.BSP.SW.0103

### Changed
* tqmls1043a/tqmls1046a: linux-tq: update to v5.4.87
* tqmls1088a: linux-lsdk-tq: update to v5.4.47
* tqmls10xxa: u-boot-tq: add XFI Retimer and serdes initialization
* tqmls10xxa: u-boot-tq: add mmc subcommand to query max enhanced partition size

## zeus.TQMLS1028A.BSP.SW.0105

### Added
* tqma6: added machine for TQMa6\[Q,D\] 2GB variant

### Changed
* tqma6: U-Boot: update to support 2GB variant

### Fixed
* tqmls1028a: use correct manufacturer EEPROM address
* tqma6ul: do not enable QPSI boot for mmc configs

## zeus.TQMLS1028A.BSP.SW.0104

### Added
* tqmls1028a: new kernel linux-rt-lsdk-tq-5.4 with PREEMPT_RT support
  * See the [TQMLS1017A/TQMLS1028A README](doc/README.TQMLS1028A.md)
    for more information


## zeus.TQMLS1012AL.BSP.SW.0008

### Added
* tqmls1012al: added support for variants with mt25qu01g SPI-NOR flash

### Changed
* tqma7: disable broken PCIe on MBa7 with linux-tq and linux-rt-tq kernels
  * PCIe is working on the default linux-imx-tq kernel

### Fixed
* tqma6: fix polarity setting of LEDs on MBa6x with linux-tq and linux-rt-tq
  kernels
  * Already fixed for the default linux-imx-tq kernel with the previous release


## zeus.TQMLS1028A.BSP.SW.0103

### Added
* tqmlx2160a: initial support for tqmlx2160a

### Changed
* tqmls1028a: switch from TMU to external sensor IC for core-cluster
  thermal zone

### Fixed
* tqmls1012al: fix size check for update\_pbl script in U-Boot default
  environment
* tqmls1028a: fix update\_pbl\_spi and update\_pbl\_mmc scripts in U-Boot
  default environment. Before this fix, update\_pbl\_mmc was unusable.
* tqmls1028a: fix CPU node FDT fixups in U-Boot not to break the
  thermal-zones (making the thermal driver unusable). This adds
  support for the TQMLS1017A to the tqmls1028a images. Both U-Boot and the
  Linux kernel must be updated for proper operation on TQMLS1017A.
* tqma6: fix polarity setting of LEDs on MBa6x


## zeus.TQ.Yocto.BSP.SW.0001

This is a joint release for multiple TQMaxx and TQMLSxx module families. It can
also be found under the following tag names:

* zeus.TQMa6x.BSP.SW.0118
* zeus.TQMa7x.BSP.SW.0111
* zeus.TQMa6ULx.BSP.SW.0113
* zeus.TQMLS1012AL.BSP.SW.0007
* zeus.TQMLS102xA.BSP.SW.0115
* zeus.TQMLS1028A.BSP.SW.0102

### Added
* tqmls1012al: enabled U-Boot mtest command

### Changed
* tqma6: marked ERR006687 as fixed to avoid unnecessary software workaround
* tqmls1028a: modified U-Boot default environment for DisplayPort and
  SPI-NOR boot

### Fixed

* Fixed a resource allocation issue in the TLV320AIC32x4 audio codec
  driver that could lead to warnings and lockups during boot or shutdown
* Fixed incorrect clock setup in the TLV320AIC32x4 audio codec driver
  causing too slow or too fast playback and recording after a soft reboot
  * The two above issues affect all TQ starterkit mainboards with analog
    audio support
* tqma6: enabled default-on LED trigger in kernel defconfig
* tqma\[6ul,6ull,7\]: fixed a QSPI driver bug causing frequent corruptions
  of filesystems on SPI-NOR flash
* tqma6ul: fixed USB support with parallel LCD device tree on MBa6ULxL
* tqma7: enabled cpufreq driver in kernel defconfig
* tqmls1028a: set up CAN interfaces on boot

## zeus.TQMa57xx.BSP.SW.0013

### Changed

* tqma57xx: errata i863 application fixed
* tqma57xx: Linux: mba57xx dt cleanup
  * license header to SPDX
  * spidev: add tq,testdev
  * enet: move phy props from emac
  * add arch to top-level compatible strings
  * drm/panel: replace cdtech display with mainline patch
  * move touch node to display dts
  * fixed display dts naming
  * fixed display brightness

## zeus.TQMa6x.BSP.SW.0117 / zeus.TQMa7x.BSP.SW.0110 / zeus.TQMa6ULx.BSP.SW.0112 / zeus.TQMLS1012AL.BSP.SW.0006 / zeus.TQMLS102xA.BSP.SW.0114 / zeus.TQMLS1028A.BSP.SW.0101

### Added

* tqma\[6,6ul,6ull,7\], tqmls102xa: added kernel linux-tq 5.4
* tqma\[6,6ul,6ull,7\], tqmls102xa: added kernel linux-rt-tq 5.4
* tqma\[6,6ul,6ull,7\], tqmls1028a: added kernel linux-imx-tq 5.4
  * The corresponding userland packages (imx-gpu-*, libdrm, weston) for the
    Vivante graphics stack (TQMa6x) have been updated from meta-freescale
    to match the kernel version
* tqmls1028a: added u-boot-lsdk-tq 2019.10 (based on LSDK 20.04)
* tqmls1012al: added new embedded module and its starterkit baseboard MBLS1012AL

### Changed

* tqma\[6,6ul,6ull,7\]: A mainline-based kernel (linux-tq) is now chosen by
  default when use-mainline-bsp is added to MACHINEOVERRIDES
  (`MACHINEOVERRIDES .= ":use-mainline-bsp"`)
* tqma\[6ul,6ull,7\]: The kernel configuration was changed to use the better
  supported mainline graphics stack. The linux-imx mxcfb stack is only used
  on TQMa6x now.
* tqmls10xxa, tqmls1028a: the meta-freescale layer has been made mandatory for
  these machines
* tqmls10xxa: the variable BOOTMODE can be set to "sd" or "emmc" to choose
  between SD card and eMMC boot configuration
* tqmls1028a: SD card and eMMC boot configurations have been merged and are
  now supported by a single image
* tqmls1028a: changed to TF-A (Trusted Firmware) boot
  * The U-Boot environment for SPI-NOR boot moved to offset 5 MiB
  * The U-Boot environment for eMMC/SD card boot moved to offset 8 MiB
  * The PPA (Primary Protected Application) package was replaced with ATF
    (ARM Trusted Firmware)
  * For more information about the TF-A boot process please refer to the
    NXP Layerscape SDK User Guide

### Removed

* tqma\[6,7\], tqmls102xa: removed kernel linux-tq 4.14
* tqma\[6,7\], tqmls102xa: removed kernel linux-rt-tq 4.14
* tqma\[6,6ul,6ull,7\]: removed kernel linux-imx-tq 4.14
* tqma6q-nav: removed machine

## zeus.TQMa57xx.BSP.SW.0012

### Fixed

* tqma57xx: U-Boot: patched fatfs support

## zeus.TQMa57xx.BSP.SW.0011

### Changed

* tqma57xx: U-Boot: update u-boot on vfat partition

### Fixed

* tqma57xx: U-Boot: implement errata i863 workaround: mmc2 no pullups
* tqma57xx: Linux: implement errata i863 workaround: mmc2 no pullups
* tqma57xx: Linux: enable USB2 OTG mode on MBa57xx

## zeus.TQMa57xx.BSP.SW.0010

### Added

* tqma57xx: Linux: add TI Linux 5.4 (tagged 07.00.00.05-rt)

### Fixed

* tqma57xx: U-Boot: update to set pru mac from eeprom
* tqma57xx: added pci to MACHINE_FEATURES

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
