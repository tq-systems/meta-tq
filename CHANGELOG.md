# Changelog

All notable changes to this project will be documented in this file.
Releases are named with thefollowing scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## Unreleased versions

### Changed

* u-boot: new RAM timing for TQMa8Mx
* scfw: update to version 1.2.7
* rework imx-sc-firmware recipe (use git tag in package name = needs tq_imx-scfw-v1.2.7-b3357 or later)

### Added

* add support for MBa8Mx Rev.020x
* tqma8mx: u-boot: QSPI support
* tqma8mx: u-boot: USB support for MBa8x
* tqma8mx: u-boot: manufacturer EEPROM

### Removed

* drop support for MBa8Mx Rev.010x

## sumo.TQMa8.BSP.SW.0013

### Added

* README for more modules

### Removed

* recipes: remove backported versions of imx-atf, use now imx\_4.14.98\_2.2.0

### Changed

* CHANGELOG converted to markdown
* u-boot: update to imx\_4.14.98\_2.2.0 version
* README: formatting and other improvements

### Fixed

* recipes: alsa-state installs now the correct file for tqma8mx codec
* TQMa8Mx: working sound output on codec with aplay
* TQMa8Mx: working sound output on codec and HDMI with gstreamer / pulsesink

## sumo.TQMa8.BSP.SW.0012

### Added

* linux: experimental support for TQMA8QM
* u-boot: experimental support for TQMA8QM
* machines: experimental support for TQMA8QM

### Changed

* recipes: change recipe name for TQMa8 linux (linux-tq -> linux-tq-imx)
* machines: cleanup assignments in machine files
* linux: update to rel_imx\_4.14.98\_2.2.0
* recipes: prepare using NXP release rel\_imx\_4.14.98_2.2.0
* recipes: temporary use older versions of imx-atf for TQMa8

## sumo.TQMa8.BSP.SW.0010

### Added

* linux: support HW REV.020x of TQMa8Mx
* u-boot: support HW REV.020x of TQMa8Mx

## sumo.TQMa8.BSP.SW.0009

### Added

* machines: additional LVDS display for MBa8Xx
* linux: additional LVDS display for MBa8Xx
* linux: experimental sound support for MBa8Mx

## sumo.TQMa8.BSP.SW.0008

### Added

* machines: additional LVDS display for MBa8Mx
* linux: additional LVDS display for MBa8Mx
* linux: enable audio codec in kernel config

### Fixed

* recipes: fix can support for MBPa8Xx in systemd-machine-units
* recipes: fix dependencies in imx-boot
* linux: backport flexcan fixes

## sumo.TQMa8.BSP.SW.0007

### Added

* machines: experimental support for ARMv7A machines
* linux: integrate patches for MBPa8xx
* u-boot: integrate patches for MBPa8xx

### Fixed

* linux: backport audio codec features / fixes
* recipes: drop hard systemd dependency on systemd-machine-units
* linux: fix ethernet LEDs for MBa8Mx / MBa8Xx
* u-boot: fix ethernet LEDs for MBa8Mx / MBa8Xx

