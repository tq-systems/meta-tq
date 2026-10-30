# Changelog

All notable changes to this project will be documented in this file.
Releases since `wrynose` use names with the following scheme:

`<Yocto Project major version number>.<Yocto Project minor version number>.<TQ major version number>.<TQ patch version number>`

## Semi-automatic Releases

There are semi-automatic releases with a `-ci.<CI release version number>`
suffix and contains the current development and are
released **without any testing**. They always target a release with
`TQ patch version number` set to `0`.

## Release Candidates (RC)

Release candidates use a `-rc.<RC release version number>` suffix. These are
used for release preparation. Once the first RC has been.
released a semi-automatic release increases the `<TQ major version number>`.

**NOTE:** For details to the changes in a release see the CHANGELOG.md
files in git the submodule for meta-tq (meta layers meta-tq and meta-dumpling).

## Older Releases

Releases before `wrynose` are documented in [CHANGELOG-5.0.0-scarthgap.md](CHANGELOG-5.0.0-scarthgap.md)

[[_TOC_]]

## Next Release

### Added

* meta-tq: Support building packages with EXTERNALSRC with invalid/unknown SRCREV values.
* u-boot-imx-tq-2025.05: TQMa95xxLA / TQMa95xxSA
  * USB host support for MBa95xxCA when not in serial download mode

    Implement runtime device tree fixup for U-Boot proper to decide at runtime if USB host is
    available.
  * DISTROBOOT for QSPI-NOR

    MTD partitioning needs an additional partition for the extlinux.conf script. The partition uses
    reserved space just before the UBI rootfs. Since partitions are referenced by name in environment
    and kernel command line no reformatting of SPI-NOR is needed. Update script for script partition
    is now part of default environment.
  * prepare new hardware:
    * Microchip USB5734 USB hub
    * NXP PCAL9538 I2C portexpander

### Changed

* linux-ti-tq-6.18:
  * update to ti-linux-kernel 12.01.00.06, including linux-stable updates up to 6.18.47
  * TQMa62xx: enable spidev for NAFE13388 analog frontend
* u-boot-imx-tq-2025.05: TQMa95xxLA / TQMa95xxSA
  * long reset SD card pulse to allow SD3 / UHS104 cards on MB-SMARC-2
  * pin mux / pad config optimisation
  * defconfig optimisation: remove unused drivers
  * environment improvements: kernel_addr_r is no longer identical to loadaddr

    Allows loading larger fitImages to kernel_addr_r without overwriting parts of the extracted
    system components before booting.
  * allow using relative paths from generic filesystem functions
* u-boot-imx-tq-2025.05: TQMa95xxLA / TQMa95xxSA
  * DISTROBOOT for QSPI-NOR
* linux-tq-6.18: update to linux-stable 6.18.55

### Fixed

* u-boot-imx-tq-2025.05: TQMa95xxLA / TQMa95xxSA: boot from eMMC stuck in SPL

  A combination of SPL malloc limits caused failure to initialize the eMMC and to lod next
  image container from eMMC.

## 6.0.1.0 (not released yet)

### Added

* TQMa93xxLA/TQMa93xxCA: enable devicetree overlay and bootmenu for Cortex-M33 and
  mainline kernel.
* linux-tq-6.18: TQMa93xxLA/TQMa93xxCA: Cortex-M33 support
  * port example drivers from NXP downstream linux-imx
  * add devicetree overlay with reserved hardware and memory for Cortex-M33

### Changed

* linux-tq-6.18: update to linux-stable 6.18.44
* linux-ti-tq-6.18: update to ti-linux-kernel 12.01.00.05, including linux-stable updates
  up to v6.18.35
* u-boot-tq-2023.04
  * tqma6: increase max U-Boot size for SPI flash
* imx-oei-tq: updates for project internal CI settings, no functional change
* imx-system-manager-tq:
  * updates for project internal CI settings
  * remove unused configurations for TQ-Systems boards

    After BSP switched to new configuration names with technical identical settings the old configs
    were removed from.
* optee-os: Use loglevel 'info' throughout all TI modules.

### Fixed

* linux-ti-tq-6.18
  * Fix LVDS Overlays not being applicable because of renamed node names
  * TQMa\[62,64,67\]xx(L): add default UART properties to fix console initialization.
* u-boot-tq-2026.01
  * TQMa\[62,64,67\]xx(L): fix RAM size detection by U-Boot

    An unused function in R5 SPL caused U-Boot proper to ignore RAM detection and
    ECC reservation resulting in the default RAM configuration always being loaded.
* u-boot-tq-2023.04
  * tqma6,6ul,7: default environment for `fdtoverlay_addr_r`

    Booting with FDT overlay does not work with the default. Do not rely on
    preprocessor arithmetic for env initialisation.
* tq-extlinux: add missing variable dependency

  Rebuild was not triggered when UBOOT_EXTLINUX_KERNEL_ARGS_EXTRA was changed

## 6.0.1.0-ci1

### Added

* u-boot-tq-2026.04
  * new recipe based on upstream U-Boot
  * optional support for TQMa8MPxL boards (preferred version still based on NXP downstream fork)

* linux-ti-tq-6.18
  * Support for TQMa62xx\[L\]
  * Support for TQMa64xxL
  * Support for TQMa67xx\[L\]

* linux-imx-tq-6.18
  * OS08A20 camera Support for TQMa95xxLA
  * Support for TQMa91
  * Support for TQMa93
* CVE: add CVE_PRODUCT mappings for TQ-maintained kernel, bootloader and ATF recipes
* BOM: add CYCLONEDX_EXTRA_RUNTIME_RECIPES for firmware components deployed via EXTRA_IMAGEDEPENDS

### Changed

* u-boot-tq-2023.04
  * tqma6ulx[l] / tqma6ullx[l]: cleanup defconfigs for environment settings

    Images for QSPI boot and SD/eMMC boot have different linker settings. Starter kit BSP supports
    environment on boot device only. Not needed settings are removed for clarity.
* u-boot-lsdk-tq-2019.10
  * tqmls1028a-mbls1028a: fix DT aliases for eMMC and SD

    Use `0` for eMMC and `1` for SD-Card, to conform to other TQ-Systems starter kits
* tqma8mx / mba8mx: add hdmi firmware to MACHINE_FIRMWARE
* tq-extlinux: add labels to extlinux.conf menu
* u-boot-tq-2017.11: TQMLS102xA:  support alternative temperature sensor

  Add detection logic and device tree fixup to support SoM variants with different sensor types.
* u-boot-lsdk-tq-2019.10: TQMLS1028A: support alternative temperature sensor

  Add detection logic and device tree fixup to support SoM variants with different sensor types.
* TQMa95*: use new imx-system-manager-tq configuration names
* imx-system-manager-tq: support unified configuration name scheme for TQ-Systems SoM
* TQMa8M*: improve assignment for IMXBOOT_TARGETS / IMXBOOT_FSPI_TARGET / IMXBOOT_SDMMC_TARGET

  Use default assignment for all variables, move this to the SoM specific part and use IMXBOOT_FSPI_TARGET
  IMXBOOT_SDMMC_TARGET when the relevant target name is used e.g. for MACHINE_WKS_BOOTSTREAM  etc.
* imx-boot-tq: improve dependencies
* u-boot-2026.01:
  * TQMa6\[62,64,67\]xx(L): enable "hash" command
  * TQMa6\[62,64,67\]xx(L): enable "ddr ecc_err" error injection command
  * Add TQMa62xx and TQMa64xxL 1GiB Nanya RAM variants (experimental)

    Alternative RAM vendor needs a different configuration. RAM type
    detection and initialisation is handled in boot firmware.
    Include a _temporary_ workaround for TQMa64xxL for Nanya RAM initialisation.
* u-boot-imx-tq-2025.04
  * integrate NXP Fixes up to BSP release tag lf-6.18.2_1.0.0
* linux-tq-6.18
  * integrate fixes up to stable tag v6.18.36
* linux-imx-tq-6.18
  * integrate FSLC fixes up to stable tag v6.18.32

### Removed

* linux-ti-tq-6.12: Remove recipe for TQMa62xx\[L\], TQMa64xxL and TQMa67xx\[L\]

### Fixed

* tqma335x: adjust `KERNEL_DTBDEST` to allow loading from rootfs with U-Boot env boot script.

  Otherwise the default environment needs adjustments to boot BSP image.
* layer.conf: Remove stale LICENSE_PATH variable

  pointing to nonexistent directory breaks `devtool add`
* udev-rules-imx: the recipe in `meta-freescale` has a version now, correct file name of bbappend
* imx-cst-keys: fix wrong default for `S`

  Use `UNPACKDIR` since there are only local files.
* u-boot-imx-tq-2023.04: TQMa6UL[L]x[L]: Fix redundant environment offset

  Set the offset to a correct value to prevent overwriting data in QSPI NOR.
  Otherwise saving the environment on QSPI NOR can lead to a non booting system.

* TQMa335x/62xx\[L\]/64xxL/67xx\[L\]: Fix generation of `wic.bootonly` images

  The partition table in these images was broken. This is a regression in
  BSP release 6.0.0.0.
* systemd-machine-units: adjust settings for TQMa95xxLA on MBa95xxCA

  Configure CAN with CAN-FD by default and allow additional network interface.

## 6.0.0.0

### Added

* linux-imx-tq-6.18 (based on NXP lf-6.18.2_1.0.0 release + FSLC stable updates)
  * Support for TQMa95xxLA and TQMa95xxSA
* linux-tq-6.18
  * Support for TQMa8MPxL and TQMa8MPxS
  * Support fot TQMa93xxCA an TQMa93xxLA

### Changed

* imx-boot-tq: use NXP lf-6.18.2_1.0.0 release
* qoriq-atf / imx-atf: update to v2.12 based NXP branch
* adjust to wrynose release
  * U-Boot configuration variables
  * Sanity fixes
  * Variable usage fixes
  * FIT image generation
* linux-tq-6.12: update to v6.12.80
* linux-tq-6.6: update to v6.6.133
* linux-rt-tq-6.6: update to v6.6.132-rt72

### Fixed

* tqma91 / tqma93: fix bootmenu generation
* u-boot-imx-tq-2024.04:
  * TQMa93xxCA/LA: set thermal trips from fuses

    Using hard settings for consumer grade CPU can lead to boot issues for
    industrial graded CPU when the temperature is outside of consumer grade
    temperature range.

### Removed

* machines:
  * remove tqma8mpxs REV.010x
  * remove tqma6ulx-lga-mba6ulxl
* linux-[rt-]tq-6.12: remove recipe, alls boards using the recipe are updated to v6.18 LTS
* u-boot-tqmt-2015.07: TQMT10xx variants using this recipe are not supported under scarthgap.
* linux-tqmt-5.15: TQMT10xx variants using this recipe are not supported under scarthgap.
* machines: Remove TQMT10xx variants since they are not supported under scarthgap.
