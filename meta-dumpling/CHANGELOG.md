# Changelog

All notable changes to this project will be documented in this file.
Releases are named with the following scheme:

`<Yocto Project version name>.<TQ module family>.BSP.SW.<version number>`

[[_TOC_]]

## Next Release

## kirkstone.TQ.ARM.BSP.0007

### Module BSP Revisions

* kirkstone.TQMLX2160A.BSP.SW.0010

## kirkstone.TQ.ARM.BSP.0006

### Module BSP Revisions

* kirkstone.TQMa8.BSP.SW.0091

### Added

* dumpling-ls and spaetzle-ls distro for Layerscape modules.
* image_type_pblvariant to build bootonly image with alternative pbl for
  Layerscape modules.

## kirkstone.TQ.ARM.BSP.0005

### Module BSP Revisions

* kirkstone.TQMa8.BSP.SW.0090
* kirkstone.TQMa64xxL.BSP.SW.0004

## kirkstone.TQ.ARM.BSP.0004

### Module BSP Revisions

* kirkstone.TQMa64xxL.BSP.SW.0003
* kirkstone.TQMa8.BSP.SW.0089

### Added

* Added initial support for TQMT10xx machines

### Changed

* dynamic-layers/meta-ti:
  * Update for compatibility with latest meta-ti kirkstone (as of
    commit 38941472e1e3 "meta-ti-bsp: add BeagleBone AI-64 support"). Our
    ti-sgx-ddk-um initialization fix has been upstreamed and is removed from
    meta-dumpling.
* Check for `screen` instead of `display` in `MACHINE_FEATURES` since `screen`
  is used in Yocto Project / OpenEmbedded to test for display support of
  current `MACHINE`
* dynamic-layers/qt5-layer:
  * Split package groups for Qt5, so that examples etc. can be left out for
    release images.
  * Rewrite bbappend for qtbase. Use `PACKAGECONFIG_<abc>` variables provided by
    qtbase recipe from meta-qt5 to have some structure and provide a better set
    of defaults.
  * Add qtwayland and QPA plugin if `wayland` is in `DISTRO_FEATURES`
  * Add `eglfs` QPA plugin if `imxgpu3d` is in `MACHINEOVERRIDES`
* Use glibc in spaetzle distro for tqmt10xx machines

### Fixed

* dynamic-layers/qt5-layer: Move Qt5 bbappends to correct location. This
  allows bitbake to use them.

## kirkstone.TQ.ARM.BSP.0003

### Module BSP Revisions

* kirkstone.TQMa335x.BSP.SW.0125
* kirkstone.TQMa64xxL.BSP.SW.0002
* kirkstone.TQMa65xx.BSP.SW.0009
* kirkstone.TQMa8.BSP.SW.0088

### Removed

* gengetopt: remove recipe, we depend on openembedded-layer which provides this
  recipe

### Fixed

* Fix ti-sgx-ddk-um initialization on systemd-based distros. This allows
  Weston to work on TQMa65xx.

## kirkstone.TQMa335x.BSP.SW.0124
## kirkstone.TQMLS1012AL.BSP.SW.0012
## kirkstone.TQMLS102xA.BSP.SW.0116

## kirkstone.TQMa8.BSP.SW.0087
## kirkstone.TQMa6x.BSP.SW.0121
## kirkstone.TQMa7x.BSP.SW.0115
## kirkstone.TQMa6UL.BSP.SW.0117

### Changed

* distros: use busybox ping instead of inetutils version to
  have more complete commandline args for some use cases
* distros: disable dhcpcd systemd units by default
* distros: build small SD / e-MMC boot image for SOM with TI CPU
* treewide: syntax conversion for honister
* treewide: recipe changes for building with honister

### Added

* distros: dumpling: enable bluetooth as DISTRO_FEATURE by default
* doc: paragraph for SDK usage

### Fixed

* recipes-graphics/kmscube: Fix optional GLES3 support
  backport new upstream fixes and make sure kmscube will be installed
* Fix missing `WAYLAND_DISPLAY` environment variable. Allows access for
  members of `wayland` group
* gstreamer1.0-plugins-good: fix patch fuzz warning
* images: tq-image-weston-debug: bbappend for image did not work after
  splitting image recipes. IMX gstreamer packages were missing when using
  meta-freescale together with `use-nxp-bsp`

* distros: fix broken SDK build (due to a wrong named variable)

__Start of porting to kirkstone__
------------------------------------------------------------------------

### Changed

* distros
  * spaetzle: force using busybox utils
  * dumpling: prefer full featured util-linux utils
* packagegroup-testutils:
  * improve package handling for opengl related stuff
  * optimize utils selection if `MACHINE_FEATURES` contains `display`
* packagegroup-hwutils: improve handling of usb related packages. usb-modeswitch
  is now provided as new subpackage. This is not used by default because it is
  rarely needed but depends on tcl
* treewide: syntax conversion for honister
* treewide: recipe changes for building with honister

__Start of porting to honister__
------------------------------------------------------------------------

## hardknott.TQMLS1028A.BSP.SW.0107

### Changed

* images: reduce size of debug images. `IMAGE_FEATURES` with tools-testapps
  have complex dependencies causing large image sizes.

### Added

* image: recipe for PREEMPT RT use case

### Fixed

* base-files: issue.net handling
  * output only for interactive sessions
  * prevent escape codes in issue.net
* packagegroup-testutils: fix build for DISTRO_FEATURES without "opengl"
* distros: enable kernel provider override for PREEMPT RT use case

## hardknott.TQMa7x.BSP.SW.0114
## hardknott.TQMa6x.BSP.SW.0120
## hardknott.TQMa6UL.BSP.SW.0116

### Changed

* distro: unbundle dumpling / spatzle distros from using buildhistory and
  buildstats. This should be done in buildspace local.conf or auto.conf rather
  then in distro config.
* rng.tools: do not start rngd if HW RNG is available. Modern kernels already use
  the entropy from a HW RNG itself. Also starting rngd using libjitterentropy
  introduces a huge but useless CPU usage upon startup.

### Fixed

* rng.tools: remove outdated recipe that was needed for zeus and older to include
  libjitterentropy support. Switch to use current upstream from poky.
* lmsensors: default packageconfig installs sensord with complex dependencies.
  Fix it to prevent massive rootfs size increase and installment of otherwise
  not needed packages

### Added

* image recipes:
  * recipes are split, so that every image has a conjugated
    -debug enabled recipe. This enables to distinguish debug and release builds
    easily
  * add a postprocess command for -debug images that adds a hint regarding
    security to etc/issue\[.net\]
* packagegroup-testutils:
  * add alsabat if alsa is enabled
  * RRECOMMEND openssh-sftp-server (useful for QtCreator, eclipse etc.)
* alsa-utils: add bbappend to allow compilation of alsabat
* base-files: customize /etc/issue[.net] to have some branding

## hardknott.TQMa65xx.BSP.SW.0008
## hardknott.TQMa8.BSP.SW.0084

### Changed

* make sure hwclock from util-linux gets installed
* add coreutils to images not built for spaetzle

### Changed

* packagegroup-can: install also subpackage can-utils-cantest
* alsa packages: use COMBINED_FEATURES for optional install
* dependencies: meta-openembedded/meta-oe is now a dependency, remove from
  dynamic-layers and fold bbappends in normal directories and recipes.

## hardknott.TQMa8.BSP.SW.0083

### Fixed

* packagegroup-wifi: bbappend for hostapd in dynamic-layers to prevent build
  failure without meta-openembedded
* kmscube: missing dependency (already fixed upstream)
* recipes-image: fix installing kmscube and glmark2 for different setups
* distro spaetzle* / image tq-image-small: fix to use initscripts from within
  busybox. Otherwise no start / stop scripts are installed at all

### Changed

* rename packagegroup-camera to packagegroup-v4l2 and install also, if
  `MACHINE_FEATURES` contains `vpu`
* glmark2: enable install multiple flavours
* distro:
  * enable mesa libs as virtual providers for mainline based distros
  * refactor include hierarchy
* tq-image-small:
  * install kernel and dtbs into image for using the rootfs in UBI
    images on MTD devices like QSPI NOR
  * install packagroup-netutils for basic network support

### Removed

* perf: scripting scripting doesn't need to be disabled anymore, remove bbappend
  file

## hardknott.TQMa8.BSP.SW.0080

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
* packagegroup-hwutils: enable libgpiod-tools - libgpiod has been split into
  subpackages in newer versions
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
