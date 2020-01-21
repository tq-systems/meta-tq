# Changelog

All notable changes to this project will be documented in this file.
Releases are named with thefollowing scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## sumo.TQMa8.BSP.SW.0016

### Changed

tqma8qm: linux: support both LVDS outputs
tqma8qm: rename tqma8mq-mba8mx -> tqma8mq-4gb-mba8mx
tqma8qm: update SCU firmware to support 8GB variant

### Added
* tqma8qm: linux: enable wakeup from GPIO button
* tqma8qm: linux: GPIO LED and button
* tqma8qm: linux: LVDS support
* systemd-machine-units: add tqma8qm support
* systemd-machine-units: reduce default can bitrate
* tqma8qm: linux: add CAN support
* tqma8qm: linux: add PCIE support
* tqma8qm: linux: add USB support
* tqma8qm: linux: add SPI support
* tqma8qm: linux: add USB support
* tqma8qm: u-boot: PCIe GPIO sideband pins
* tqma8qm: u-boot: GPIO LED and button
* tqma8qm: tqma8xx: u-boot: fix for flexspi write SR
* tqma8qm: SCU: PCIe external clock
* tqma8qm: u-boot: add USB support
* tqma8qm: linux: add spi support
* tqma8qm: linux: QSPI support (not completely functional)
* tqma8qm: u-boot: enable QSPI Boot
* tqma8qm: add support for 8GB variant
* tqma8xxs: linux: LVDS support

## sumo.TQMa8.BSP.SW.0015

### Changed

* conf: use device tree name logic for kernel and image also for non TQMa8Mx boards
* tqma8mx: linux: update audio settings in dt for new kernel version
* u-boot: new RAM timing for TQMa8Mx
* scfw: update to version 1.2.7
* rework imx-sc-firmware recipe (use git tag in package name = needs tq_imx-scfw-v1.2.7-b3357 or later)

### Added

* tqma8xx: u-boot: enable QSPI Boot
* tqma8mx: add support for MBa8Mx Rev.020x
* tqma8mx: linux: QSPI support
* tqma8mx: linux: SD3 / UHS support
* tqma8mx: linux: rtc / rtc alarm
* tqma8mx: linux: USB OTG (USB 2.0 only)
* tqma8mx: linux: HDMI audio
* tqma8mx: linux: PCIe Slot
* tqma8mx: linux: GPIO buttons
* tqma8mx: linux: GPIO LED
* tqma8mx: linux: LVDS via DSI bridge on MBa8Mx REV.0x20x
* tqma8mx: u-boot: QSPI support
* tqma8mx: u-boot: USB HUB support for MBa8Mx Rev.020x
* tqma8mx: u-boot: USB OTG / DualRole (USB 2.0 / Cable Detect)
* tqma8mx: u-boot: manufacturer EEPROM
* tqma8mx: u-boot: lot of GPIO are requested and preconfigured
* tqma8xxs: CAN support
* tqma8xxs: u-boot: enable QSPI Boot

### Fixed

* tqma8xx: u-boot: fix QSPI probe write a byte to flash (MACRONIX)

### Removed

* drop support for MBa8Mx Rev.010x
* drop support for TMa8Mx Rev.010x

## sumo.TQMa8.BSP.SW.0014

### Changed

* tqma8mx: u-boot: new RAM timing

### Fixed

* tqma8mx: system stall after long up time
* tqma8mx: u-boot: correct board name handling

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

