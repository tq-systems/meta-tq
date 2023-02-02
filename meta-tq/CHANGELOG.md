# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

[[_TOC_]]

## Next Release

### Added

* TQMLS1046A: add machine config for RAM without ECC
* TQMLS1046A: add u-boot-tq-2018.07 configs for Modules without ECC
* TQMa93xxLA: add wifi and bluetooth to `MACHINE_FEATURES` and enable firmware for
  WLAN cards with 88W8997 chipset
* TQMa8X[xS]: Known issue regarding hardware controlled chip-select

### Fixed

* MBa8Xx[S]: linux-imx-tq 5.15:
  * Pull-up configured for SPI chip-selects in kernel devicetree
  * use gpio chip select for SPI where possible to prevent toggling chip select
    due to SPI IP and DMA limitation

### Changed

* all imx based SOM: port changes from meta-imx kirkstone-5.15.71-2.2.0
  in libdrm, weston, wayland-protocols as bbappend-files under dynamic layers
  to get the latest changes needed for imx93
* TQMa93xxLA: port and configure more graphic support from meta-imx
* TQMa93xxLA: rename starter kit mainboard to MBa93xxCA, therefore machine name is
  now tqma93xxla-mba93xxca
* linux-imx-tq 2022.04 / TQMa93xxLA
  * improve VARD support, fixup devictree
  * improve QSPI NOR support
  * speed grade / temperature grade handling
* linux-imx-tq 5.15:
  * TQMa93xxLA: LVDS support, QSPI support, USB at mPCIe slot,
  * enable enable marvel / nxp wifi driver in wifi support fragment
  * enable PWM driver needed for i.MX93
* TQMa93xxLA: update firmware-imx-9 to 8.18. Needs at least meta-freescale
  commit 8bd13d69 ("firmware-imx: Upgrade 8.17 -> 8.18")

## kirkstone.TQ.ARM.BSP.0003

### Module BSP Revisions

* kirkstone.TQMa335x.BSP.SW.0125
* kirkstone.TQMa64xxL.BSP.SW.0002
* kirkstone.TQMa65xx.BSP.SW.0009
* kirkstone.TQMa8.BSP.SW.0088

### Added

* u-boot-imx-tq: TQMa8MPxL: VARD based device tree patching

  Devices may be assembled optionally on SOM. Use variant and revision data to
  enable / disable devices in device tree based on VARD. This prevents the OS
  from probing / accessing not assembled devices
* u-boot-imx-tq: TQMa8[x,Xx,Mx,MxML,MxNL,MPxL]: add nfs boot script to boot from
  network without TFTP server
* recipes-kernel: add support for linux-6.1.y as stable kernel for
  * TQMa8Mx
  * TQMa8MxML
  * TQMa8MxNL
  * TQMa8MPxL
* TQMa8: prepare to have different `KERNEL_DEVICETREE` lists for different
  kernel flavours. Reuse the logic already in use for TQMa6x.

### Changed

* linux-imx-tq: TQMa8[Mx,MxML,MxNL,MPxL]: hog IRQ gpio as input in devicetree to
  prevent configuration as output
* TQMLX2160A-MBLX2160A: follow yocto migration guide to prevent install
  kernel image into rootfs
* TQMa8: allow kernel image to be installed in rootfs per default
* TQMa8 / TQMa9: rewrite logic for `KERNEL_DEVICETREE` and device trees in
  `IMAGE_BOOT_FILES`. This removes the intermidiate variable `BOARD_DEVICETREE_FILES`
* u-boot-imx-tq: TQMa8[x,Xx,Mx,MxML,MxNL,MPxL]: cleanup default environment
* TQMa93xxAL: update imx-tf-tq_2.6 to NXP BSP lf-5.15.71-2.2.0
* TQMa93xxAL: update security controller firmware to version 0.8 from NXP
* TQMa64xxL: Update to include latest ti-u-boot-2021.01 changes. This fixes
  a license issue in U-Boot (inclusion of GPL-incompatible code).
* TQMa64xxL/TQMa65xx: updated for compatiblity with latest meta-ti
  (commit 2a5a0339d5bd)
* TQMa64xxL: updated build to generate tiboot3.bin for all 3 SYSFW variants
  (GP, HS-FS, HS-SE). Only boot on GP variants has been actually tested, as no
  TQMa64xxL with a HS CPU variant exists at this point.
* TQMLS1028A:
  * Removed CONFIG_NET_RANDOM_ETHADDR in U-Boot
  * Renamed U-Boot environment variables for bootscripts
  * Added Support for GPIO Controllers in U-Boot
  * Updated README.TQMLS1028A.md

### Fixed

* TQMa65xx: Fix RX hang of PRU Ethernet ports

  The TQMa65xx PRU Ethernet ports frequently got stuck in a state where all
  received traffic was dropped. Revert the PRU Ethernet firmware to 02.02.09.03
  (as was used in our Hardknott-based BSPs) to fix this issue.
* TQMa57xx: Fix setup of DSP coprocessors on AM572x/AM574x SoC variants
* TQMa64xxL: Fix periodic rescan for SD cards with warning message when no SD
  card is inserted
* TQMa64xxL: Fix QSPI data corruption in U-Boot when accessing UBIFS volumes
* u-boot-imx-tq: TQMa8MPxL:
  * long delay when accessing network via Eqos / TSN port (dwc_eth_qos)
  * unregister MDIO bus when probe failed for Eqos / TSN port (dwc_eth_qos)
    and FEC port (fec-mxc)
* machines: add missing dependency for `KERNEL_DEVICETREE`
  when using the python helper `kernel_provider_dtbs` `KERNEL_DEVICETREE`
  has a hidden dependency on `KERNEL_DEVICETREE_${PREFERRED_PROVIDER_virtual/kernel}`
* doc: fixes for kernel versions in use for SOM with NXP CPU
* TQMa6x/TQMa7x/TQMa6UL[L]: fix comatibility of overrides with
  meta-freescale
* TQMa65xx: correctly set SERIAL_CONSOLES
* TQMLS1028A:
  * Fix SD-Card WP pin issue in Linux and U-Boot
  * Fixed automatic setting of bootcmd
  * Linux: Fixed wrong dcdc_reset pin
* TQMa335x: Fix RTC alarm IRQ

## kirkstone.TQMa335x.BSP.SW.0124
## kirkstone.TQMLS1012AL.BSP.SW.0012
## kirkstone.TQMLS102xA.BSP.SW.0116

### Changed

* recipes-kernel: simplify structure for TQMa8 kernel config fragments
  and reuse for TQMa93xxLA

### Added

* recipes-bsp: add recipes for i.MX93 support
  * firmware-sentinel
  * firmware-imx-9
  * imx-atf-tq nased on imx-atf 2.6
  * u-boot-imx-tq based on v2022.04
* recipes-kernel: prepare i.MX93 support for linux-imx-tq 5.15
* conf:
  * prepare optional include for imx-base for i.MX93 `MACHINEOVERRIDES`
  * Add machine `tqma93xxla-mba9xxxca`

## kirkstone.TQMa8.BSP.SW.0087
## kirkstone.TQMa6x.BSP.SW.0121
## kirkstone.TQMa7x.BSP.SW.0115
## kirkstone.TQMa6UL.BSP.SW.0117

### Removed

* linux-rt-tq-4.19: Remove recipe
* linux-ti-tq-4.19: Remove recipe
* qoriq-atf_1.5: Removed recipe
* linux-imx-tq_5.4: Drop support for tqmls1028a
* TQMa8MP: 2GB machine config variant, support for 2GB fallback bootstream is
  included in new tqma8mpx-mba8mpxl machine configuration

### Fixed

* TQMa64xx:
  * linux-ti-tq_5.10: make OSPI working
* TQMa57xx:
  * Update U-Boot for Kernel/DTB in rootfs
  * Fix MBa57xx USB hub initialization
  * Fix inclusion of PRU Ethernet firmwares
* linux-tq / linux-rt-tq 5.4: tqmls102xa - fix rtc quartz capacity
  handling. Even if the correct value is set by bootloader the value
  falls back to its default if not configured in dtb
* machines:
  * fix handling of provider specific `KERNEL_DEVICETREE` lists. The initial
    implementation could not handle non assigned lists.
  * Fix handling of bootstream image to use for wic image generation.
    Do not use a link that is generated with the hidden dependency that
    the first bootstream generated is the one that is the correct for
    SD / eMMC
  * TQMa8[*]: change assignment for `WKS_FILE` to ensure our wks
    template is used and not the one from meta-freescale
* u-boot-tq_2019.04:
  * TQMa335x[L]: fixed incorrect MAC addresses in environment
  * SPDX license header for TQMa335x[L] sources and DT
  * backport fixes for environment for multiple environment
    location support
* tqmls1028a / tqmlx2160a: add qoriq-atf to EXTRA_IMAGEDEPENDS since it
  needs to be built although not installed in rootfs

### Changed

* TQMa64xxL:
  * Update U-Boot and kernel for hardware revision 0200. The obsolete
    revision 0100 is not supported anymore.
* linux-ti-tq-5.10: update to to latest ti-linux-kernel upstream, based
  on 5.10.152-rt75
* u-boot-ti-tq-2021.01: update to latest ti-u-boot upstream
  * The latest kirkstone version of the meta-ti layer is required for
    compatiblity of U-Boot and the SCI firmware
* imx-boot-tq: Remove ${DEPLOYDIR}/${BOOT_TOOLS} usage. This makes
  recipes providing artifacts for boot stream generation cleaner and
  simplify imx-boot-tq deploy itself.
* linux-rt-tq_5.15: update to 5.15.55-rt48
* linux-tq-5.15: update to 5.15.55
* all machines with i.MX8[*] CPU: improve variables for image and wic
  generation
  * WKS_FILE_DEPENDS is used for host tools only
  * EXTRA_IMAGEDEPENDS for packages that are needed to generate images
    although not installed for rootfs
  * remove unused variables from machine config
* all machines with layerscape CPU: improve variables for image and wic
  generation
  * WKS_FILE_DEPENDS is used for host tools
  * use EXTRA_IMAGEDEPENDS to allow kernel and firmware installment also
    outside of rootfs
  * use MACHINE_EXTRA_RRECOMMENDS for tools to install into rootfs
* all machines with [Q]SPI-NOR: improve readablility for UBI / UBIFS
  parameters
  * use long opts for MKUBIFS_ARGS / UBINIZE_ARGS
  * Use variables for parameters
* TQMa8QM/TQMa8Xx[S]:
  * update imx-seco
  * update imx seco-libs
  * update imx-sc-firmware
* TQMa64xxL: doc: update Known Issues
* TQMLS1028A: update documentation
* TQMa335x[L]: documentation
  * add Issues
  * add infos for boot sources and update
* u-boot-tq_2019.04:
  * TQMa335x[L]: cleanup update scripts and variables
* u-boot-lsdk-tq_2019.10: tqmls1028a: add support for second DRAM bank
* qoriq-atf_v2.4:
  * add support for tqmls1028a
  * add support for tqmls1028a 4GiB variant
* u-boot-imx-tq_2020.04: TQMa8Mx/TQMa8MxML/TQMa8MxNL/TQMa8MP
  * backport MMU table fixxes from upstream
  * backport U-Boot relocation and RAM size fixes from upstream
* TQMa335x/TQMa57xx/TQMa65xx: allow configuration of boot VFAT size. This enables
  smaller sizes if the kernel image will be installed into /boot which is the
  default now.
* TQMa335x: do not install kernel and dtb into boot VFAT image. The images are
  already found and used from /boot
* TQMa6x / TQMa335x: add ubi to `MACHINE_FEATURES`
  since we have an optionally assembled SPI-NOR, `ubi` should be added to have
  the ability to install MTD utils in rootfs based on `MACHINE_FEATURES`
* u-boot-imx-tq_2020.04: TQMa8MPxL: update RAM Timings

### Added

* TQMa57xx:
  * Added support for the linux-ti-tq-5.10 kernel. This kernel is the default
    now. See the TQMa57xx README for more information.
* u-boot-imx-tq_2016.03: update
  * tqma6ul: remove obsolete warning for 512 MB variants
* TQMa8MPxL: update u-boot-imx-tq
  * support 4GiB variant
  * optional support for LVDS splash (clock, ldb, pwm, lcdif2)
* linux-imx-tq_5.15:
  * Support for TQMa6x
  * Support for TQMa8Mx
  * Support for TQMa8MxML
  * Support for TQMa8MxNL
  * Support for TQMa8MPxL
  * Support for TQMa8x
  * Support for TQMa8Xx
  * Support for TQMa8XxS
* MBa7: add wifi, bluetooth and firmware
* MBa6ULxL: add wifi, bluetooth and firmware
* MBa6ULx: add bluetooth and firmware
* MBa6: add bluetooth and firmware
* linux-tq-5.15: add optional bluetooth config support
* linux-tq_5.15: add ath10k usb support for QCA9377
* MBa8: add bluetooth and firmware
* MB-SMARC-2: add bluetooth and firmware
* linux-imx-tq-5.10: add optional bluetooth config support
* qoriq-atf_v2.4: add support for tqmlx2160a
* TQMa8MPxL: add `bluetooth` to `MACHINE_FEATURES` of MBa8MPxL and add missing
  firmware to `MACHINE_EXTRA_RRECOMMENDS`
* linux-imx-tq_5.15: Add support for tqmls1028a as default kernel
* Add preliminary TQMa64xxl/MBaX4XxL support (tqma64xxl-mbax4xxl)
  * New kernel linux-ti-tq-5.10
  * New U-Boot u-boot-ti-tq-2021.01
* u-boot-tq_2019.04:
  * TQMa335x[L]: installation of MLO for SPI NOR Flash in yocto recipe
  * TQMa335x[L]: SPI NOR boot support
* tqmls1028a: use BL2_IMAGE, BL3_IMAGE variable for wic file creation
* classes: add `image_type_bootonly` to support generation of minimal bootable
  images
* TQMa8MP: new machine config variant (single image for multiple RAM sizes)
  with support for 2GB RAM fallback variant
* u-boot-imx-tq_2020.04: TQMa8MP
  * variant and revision data support
  * single image for multiple RAM size variants
* rcw: tqmls1028a:
  - Added 1000MHz variants for eMMC/SD and SPI-NOR boot
  - Removed 800MHz variants for SPI-NOR boot due to erratum A050958
* machines: add `display` to `MACHINE_FEATURES`. This enables rootfs customization.

__Start of porting to kirkstone__
------------------------------------------------------------------------

### Added

* tqmls1012al: Support for Winbond RAM
* rcw: tqmls1028a:
  - Added 1000MHz variants for eMMC/SD and SPI-NOR boot
  - Removed 800MHz variants for SPI-NOR boot due to erratum A050958
* machines: add `display` to `MACHINE_FEATURES`. This enables rootfs customization.

### Fixed

* u-boot-imx-tq_2020.04:
  * TQMa8MPxL: fix mmc env offset for redundant env
  * TQMa8QM/ TQMa8Xx[S]: fix argument check for scu_rm command
* linux-tq_5.15: TQMa6x: non working DDC for HDMI
* u-boot-tq_2019.04: RAM timings for TQMa335x

### Changed

* TQMa335x/TQMa57xx/TQMa65xx: allow configuration of boot VFAT size. This enables
  smaller sizes if the kernel image will be installed into /boot which is the
  default now.
* TQMa335x: do not install kernel and dtb into boot VFAT image. The images are
  already found and used from /boot
* TQMa6x / TQMa335x: add ubi to `MACHINE_FEATURES`
  since we have an optionally assembled SPI-NOR, `ubi` should be added to have
  the ability to install MTD utils in rootfs based on `MACHINE_FEATURES`
* u-boot-imx-tq_2020.04: TQMa8MPxL: update RAM Timings
* linux-ti-tq_5.4.43: TQMa335x
  - change compression to speed up booting
  - disable debug options to speed up booting
  - rewrite recipe to support config fragments
  - port some config fragments from TQMa6
  - use same e-MMC pad configs in DT as in U-Boot
* TQMa335x: merge machines to a single config
* systemd-machine-units: TQMa8MPxL: enable CAN-FD support
* u-boot-tq_2019.04: rework for TQMa335x
  - update RAM-Timings to be compliant with more RAM types
  - Restore delay during RAM calibration to make Winbond RAM usable
  - Use a single config for both RAM variants
  - Improve CPU frequency specific initialisation
  - enable more SPI NOR vendors
  - backport some fixes from upstream to lower unaligned access warnings
  - remove unused stuff from defconfig
  - handle optional SPI-NOR if not assembled
  - improve module EEPROM and MAC handling
* tqmls\[xyz\]: fix WKS file handling (compatibility to upstream meta-freescale
  hardknott)
* tqmls104\[3,6\]a: merge WKS files (use wks.in and variables, no defference in
  layout for the two boards)
* tqmls1012al: add wifi to MACHINE_FEATURES and rrecommend firmware-ath10k
* tqmls1012al: hardknott release using updated versions:
  * `qoriq-atf_2.4`
  * `u-boot-lsdk-tq-2021.04`
  * `linux-imx-tq_5.15`

### Removed

* Dedicated machine `tqmls1012al-1gb-mbls1012al` was removed.
  Use specific ATF files for 1GiB module (see [TQMLS1012AL README](doc/README.TQMLS1012AL.md)) as replacement

__Start of porting to honister__
------------------------------------------------------------------------

## hardknott.TQMLS1028A.BSP.SW.0107

### Changed

* qoriq-atf\_1.5: tqmls1028a build bl2 for all rcw variants
* machines:
  * imx: optimize MACHINEOVERRIDES and other variable assignments in
  * simplify machine configs for starterkits using MBLS10xxA
  * adjustments for latest meta-freescale for Layerscape based SOM
  preparation of better linux PREEMPT RT support
* u-boot-imx-tq\_2020.04: TQMa8MPxL: use nxp\_fspi instead of old fsl\_fspi driver
* linux-imx-tq\_5.10: merge all mainline stable fixes up to v5.10.109. Contains 
  further fixing for spectre related problems and fixes for CVE-2022-1016
* linux-tq\_5.15: merge all mainline stable fixes up to v5.15.32. Contains fixes
  for CVE-2022-1015 and CVE-2022-1016
* u-boot-lsdk-tq-2019.10: backport e-MMC pSLC conversion from upstream. This
  enables converting the complete user area without further parameters

### Fixed

* u-boot-imx-tq\_2020.04: TQMa8Xx: workaround for a SD-Card boot issue. When the
  OTG port on MBa8Xx is enabled in U-Boot this can lead to a non detected SD card
  in linux. This can cause system stall when rootfs is on SD-Card.
* linux-imx-tq\_5.10: backport deadlock workaround from NXP linux-imx lf-5.15 branch.
  i.MX8MP clock system and power domain exposes an potential A-B/B-A deadlock during
  driver loading.

### Added

* rcw: tqmls1028a: Added 800MHz variants for sd boot and spi boot
* Experimental support for Basler daA3840-30mc on TQMa8MPxL + MBa8MPxL

## hardknott.TQMa6UL.BSP.SW.0116
## hardknott.TQMa6x.BSP.SW.0120
## hardknott.TQMa7x.BSP.SW.0114

* tqma7x-<nn>-mba7x machines: linux 5.15 support based on linux-stable 5.15.y
* tqma6ulxl / tqma6ullxl: add 512 MB variants
* u-boot-lsdk-tq-2019.10: backport e-MMC full size pSLC conversion

### Changed

* linux-imx-tq_5.10: rename and optimize kernel config fragments:
  * remove `tqma8x` prefix for generic fragments
  * use `tqma8` instead of `tqma8x` for others

### Fixed

* TQMa57xx: wks file generates non booting wic images with dosfstools 4.2, mkfs.vfat
  change /boot partition config to use --ofset and --fixed-size and increase size
* linux-imx-tq_5.10:
  * TQMa8: optimize kernel config for USB gadget:
    gadgets should be modules to not block loading the UDC driver if gadget cannot
    be loaded. Tested with TQMa8MPxL
  * TQMa6x: fix for smsc95xx suspend / resume
  * TQMa8MPxL: enable missing ADC driver
* linux-imx-tq_5.10: merge all mainline stable fixes up to v5.10.103
  (CVE-2022-0847 "Dirty Pipe Vulnerability")
* linux-tq_5.15: merge all mainline stable fixes up to v5.15.27
  (CVE-2022-0847 "Dirty Pipe Vulnerability")

## hardknott.TQMa65xx.BSP.SW.0008

### Added

* tqma6ulx-lga-mba6ulxl machine: linux 5.15 support based on linux-stable 5.15.y

### Changed

* linux-tq-5.4 is deprecated for all TQMa6UL[L]x modules (LGA variants as well)
* Workaround for unset MAC address using linux-imx-tq-5.10 on TQMa6x

### Fixed
* TQMa65xx:
  - Fix unintended difference to TI AM65x-EVM memory layout in U-Boot SPL
* TQMa6x/TQMa6UL:
  - GPIO wakeup
  - Load USB ethernet MAC address from EEPROM on MBa6x as fallback
  - Audio configuration on TQMa6UL[L]x

## hardknott.TQMa8.BSP.SW.0084

### Added

* TQMa8MPxL: support REV.020x
  * u-boot-imx-tq: add new configs and adjust DT to the one supplied by kernel
  * u-boot-imx-tq: support booting rootfs from UBI (use same settings as for TQMa8Xx)
  * linux-imx-tq: add new devicetrees

### Changed

* TQMa8MPxL: REV.0100 machine was renamed to tqma8mpxl-2gb-mba8mpxl-r0100
* linux-imx-tq 5.10: integrate upstream fixes from linux-fslc (5.10.98)
* TQMa8MPxL: rework EEPROM acces code in u-boot
* u-boot-imx-tq 2020.04: backport upstream logic for mmc SPL conversion

### Fixed

* documentation:
  * wrong link for UUU section for TQMa8Mx/TQMa8MPxL/TQMa8MxML/TQMa8MxNL
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
