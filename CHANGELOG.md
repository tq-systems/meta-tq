# Changelog

All notable changes to this project will be documented in this file.
Releases are named with thefollowing scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## [Unreleased]

### Added

* add CHANGELOG
* experimental support for TQMT10\[22,40,42\]

### Changed

* convert README to markdown
* update Linux for TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6x / TQMa7x
  remove version pinning for warrior.TQMa7x.BSP.SW.0108 / warrior.TQMa6ULx.BSP.SW.0107

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
