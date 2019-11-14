# Changelog

All notable changes to this project will be documented in this file.
Releases are named with thefollowing scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## warrior.MBa6ULxL.BSP.SW.0101

### Added

* add a Changelog

### Changed

* doc: README as Markdown
* doc: improve README

### Removed

* perf: remove bbappend needed for linux 4.1.x

## warrior.TQMa6ULx.BSP.SW.0107 / warrior.TQMa7x.BSP.SW.0108 /
   warrior.TQMLS102xA.BSP.SW.0111 / arrior.TQMLS102xA.BSP.SW.0112

### Fixed

* tq-image-generic: add missing packagegroup-base
* tq-image-generic: append IMAGE_LINGUAS instead of hard setting
* bbappend fsl-image-multimedia-full from meta-freesacale-distro

### Changed

*  tq-image-generic: use new packagegroup-systemd

### Added

* add packagegroup for systemd relevant packages

## warrior.TQMLS1046A.BSP.SW.0003

### Changed

* layer.conf: mark compatibility for warrior
* packagegroup-hwutils: add spitools package
* tq-image-generic: force openssh instead of dropbear
* packagegroup-hwutil: let pciutils depend on MACHINE_FEATURE pci
* packagegroup-hwutils: add can-utils if openembedded-layer is present
* images: use new packagegroup-audio
* images: add new can packagegroup if can is in machine features

### Added

* layers.conf: prepare dynamic layers
* add new packagegroup for packages needed for can support
* add new packagegroup for basic audio support

### Fixed

* busybox: add missing dd 3rd status line config
* packagegroup-fsl-gstreamer1.0: fix upstream sumo gstreamer change

## Older releases
