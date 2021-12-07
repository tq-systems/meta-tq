# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

## Next Release

### Added

* busybox: defconfig: enable extendend features / parameters for enabled applets
* packagegroup-wifi: add hostapd
* port from zeus-tqma8
  * camera package group
  * camera support recipes
  * wifi package group
  * psplash branding and systemd support
  * add recipe for small sized image capable for UBI
  * add recipe for qt5 demo image
* add distro support
  * small / normal and wayland based
  * support for building with vendor layers

### Fixed

* distros: fix spelling of TQ-Systems
* packagegroup-hwutils: enable libgpiod-tools - it is splitted package in newer
  versions
* busybox: defconfig: restore extendend parameters for `dd`
* allow kmscube to run in background
* packagegroup-fsutils: fix ubi support
* build fixes for musl and UTF8 / unicode

### Changed

* switch to hardknott

## zeus.TQMLS1028A.BSP.SW.0105

### Fixed

* Correct the spelling of TQ-Systems

## zeus.TQMLS1028A.BSP.SW.0103

### Added

* packagegroup-testutils: add various additional utilities from
  meta-openembedded

### Changed

* gstreamer1.0-plugins-bad: build without OpenCV by default to reduce
  build time

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

* tq-image-weston: added new image based on tq-image-generic, incorporating
  Wayland/Weston and Gstreamer support
* packagegroup-fsutils: added UBI, JFFS2 and misc mtd-utils


## zeus.TQMa6x.BSP.SW.0117 / zeus.TQMa7x.BSP.SW.0110 / zeus.TQMa6ULx.BSP.SW.0112 / zeus.TQMLS1012AL.BSP.SW.0006 / zeus.TQMLS102xA.BSP.SW.0114 / zeus.TQMLS1028A.BSP.SW.0101

### Added

* packagegroup-hwutils: added minicom, screen and libgpiod
* packagegroup-netutils: added iproute2
* packagegroup-systemd: added systemd-serialgetty

### Changed

* rng-tools: updated to 6.9 and enabled libjitterentropy on devices without
  dedicated hardware RNG
* busybox: removed telnet and ftp applets, enable full TFTP features
* All packagegroups were restructed to make better use of dynamic-layers


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
