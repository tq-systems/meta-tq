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

## 6.0.1.0 (not released yet)

### Removed

* packagegroup-sysutils: Remove cpufrequtils dependency

  cpufrequtils is not maintained anymore and causes conflicts when building the sdk.

## 6.0.1.0-ci1

### Changed

* linux-tq: integrate patches from linux-stable up to v6.18.34
* packagegroup-npu:
  * reenable tensorflow packages for i.MX8MP with NXP BSP
  * reenable tensorflow packages for i.MX93 with NXP BSP
* tensorflow-lite-neutron-delegate: update to NXP downstream lf-6.18.2_1.0.0
* tensorflow-lite-vx-delegate: update to NXP downstream lf-6.18.2_1.0.0
* tensorflow-lite-ethosu-delegate: update to NXP downstream lf-6.18.2_1.0.0
* tensorflow-lite: update to NXP downstream lf-6.18.2_1.0.0
* distro: dumpling: conditionally add opencl to DISTRO_FEATURES if MACHINE_FEATURES signals NPU support.

  opencl is needed by tensorflow-lite dependencies. Since opencl itself pulls in a lot of packages
  this is not configured by default to save compilation time for machines without support.
* tq-image-base.inc: include packagegroup-camera if MACHINE_FEATURES contains camera
* packagegroup-camera: add packegroup for libcamera and ISP support for i.MX95 with NXP BSP
* packagegroup-npu: add tensorflow packages for i.MX95 with NXP BSP
* neutron: port recipe from meta-freescale-ml and meta-imx NXP downstream lf-6.18.2_1.0.0

### Fixed

* qtbase: remove invalid PACKAGECONFIG, fix yocto QA check

## 6.0.0.0

### Changed

* gstreamer: remove superfluous patch
* systemd: remove superfluous patch
* alsa-utils: remove superfluous patch
* adjust to wrynose release
  * Sanity fixes
  * Variable usage fixes

### Removed

* distro: Remove `tqmt10xx' overrides. The SoM that are using the override are not supported in scarthgap.
