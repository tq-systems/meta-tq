# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## sumo.TQMa8.BSP.SW.0018

### Added

* tqma8mx: linux: fix dt for LVDS over DSI @ eLCDIF
* tqma8mx: linux: increase CMA size to 640 MB
* linux: fix simple-panel settings for tianma LVDS display
* tqma8qm: linux: add support for PWM
* tqma8qm: linux: add support for mikroBUS module RTC5

### Changed

* tqma8mx: machine config: change console UART1 -> UART3
* tqma8mx: linux: change console UART1 -> UART3
* tqma8mx: u-boot: change console UART1 -> UART3
* tqma8mx: linux: QSPI optimized pad settings
* linux: backport some ASoC patches
* tqma8mx: unified wic config
* tqma8x: unified wic config
* tqma8xx: unified wic config
* tqma8xxs: unified wic config
* tqma8mx: u-boot: QSPI optimized pad settings
* tqma8mx: u-boot: \[cosmetic\] board header cleanup

### Fixed

* tqma8x: linux: fix only one device on PCIe causes kernel hang
* tqma8x: u-boot: fix ENET randomly not communicating
* tqma8x: linux: fix ENET randomly not communicating
* linux: ressource leak fixes for imx-tlv320aic32x4
* tqma8mx: u-boot: QSPI driver 4 byte addressing
* tqma8mx: u-boot: WDOG_B pin as hog pin
* tqma8x: u-boot: fix earlycon in kernel command line
* tqma8xx: u-boot: fix earlycon in kernel command line
* tqma8xxs: u-boot: fix earlycon in kernel command line

## sumo.TQMa8.BSP.SW.0017

### Changed

* tqma8qm: update SCU firmware to patch 1.2.10
* tqma8xx: update SCU firmware to patch 1.2.10
* tqma8xxs: update SCU firmware to patch 1.2.10
* tqma8mq: linux: increase QSPI NOR frequency

### Added

* tqma8qm: SCU: add support for PMIC info
* tqma8xx: SCU: add support for PMIC info
* tqma8xxs: SCU: add support for PMIC info
* tqma8xx: linux: add LPUART3 support
* tqma8xx: linux: enable wakeup from GPIO button
* tqma8xx: linux: add SPI support
* tqma8xx: u-boot: add support for SCU PMIC info
* tqma8xxs: linux: add initial USB support
* tqma8xxs: u-boot: add support for SCU PMIC info
* tqma8xxs: u-boot: add initial USB support
* tqma8xxs: u-boot: add support for module EEPROM readout
* tqma8qm: linux: add displayport audio support
* tqma8qm: linux: add audio support
* tqma8qm: linux: add FTM PWM support
* tqma8qm: linux: add ADC
* tqma8qm: linux: add SATA
* tqma8qm: linux: add GPIO button support incl. wakeup capabilities
* tqma8qm: linux: add LED support
* tqma8qm: linux: Display Port support
* tqma8qm: linux: enable wakeup from GPIO button
* tqma8qm: linux: GPIO LED and button
* tqma8qm: linux: LVDS support on both channels
* tqma8qm: u-boot: load display port firmware during boot
* tqma8qm: u-boot: add support for SCU PMIC info
* tqma8mq: linux: add UARTS 2,3,4

### Fixed

* remove unused patches for u-boot and linux from TQMa8QM first bringup
* linux: fix QSPI NOR access for tqma8xx, tqma8xxs and tqma8qm
* linux: fix audio for tqma8xx
 
## sumo.TQMa8.BSP.SW.0016

### Changed

tqma8qm: rename tqma8mq-mba8mx -> tqma8mq-4gb-mba8mx
tqma8qm: update SCU firmware to support 8GB variant

### Added

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

