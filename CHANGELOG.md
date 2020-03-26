# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## Next version

### Added

* tqmls1012al: linux: gpio key and gpio button
* tqmls1012al: systemd support for mbls1012al-config
* recipes: u-boot-fw-utils support
* recipes: systemd-machine-units support

### Changed

* tqmls1012al: u-boot: remove / lower delays for booting
* doc: convert to MarkDown
* tqmls1012al: linux: enable missing drivers
* tqmls1012al: linux: improve kernel config fragments
* tqmls1012al: add module specific MACHINEOVERRIDE

### Fixed

* tqmls1012al: linux: gpio epander IRQ support
* tqmls1012al: fix SERIAL_CONSOLES list
* wic scripts: fixes for systemd support
* u-boot: fix PROVIDES in recipe
* linux: fix PROVIDES in recipe

## sumo.TQMLS1012AL.BSP.SW.0005

### Fixed

* tqmls1012al: u-boot: avoid boot hang

## sumo.TQMLS1012AL.BSP.SW.0004

### Added

* tqmls1012al: u-boot: Support for e-MMC variant

## Older releases
