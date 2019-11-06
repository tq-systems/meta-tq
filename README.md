# OpenEmbedded/Yocto hardware support layer for TQ Systems ARM SOM

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

## Overview

### Dependencies

This layer in the checked out branch depends on:

  URI: git://git.yoctoproject.org/poky
  branch: sumo
  revision: HEAD
  layers: meta, meta-poky

  URI: git://git.yoctoproject.org/meta-freescale
  branch: sumo
  revision: HEAD
  layers: meta-freescale

  URI: https://source.codeaurora.org/external/imx/meta-fsl-bsp-release
  branch: sumo-4.14.98-2.2.0
  revision: HEAD
  layers: meta-bsp

### Patches

Please submit patches against the meta-tq layer to the
maintainer:

Maintainer: Markus Niebel <Markus.Niebel@tq-group.com>

Additionally you can use github's collaboration features.

## Table of Contents

1. Adding the meta-tq layer to your build  
2. Supported machines  
3. Support for wic  
4. Support custom mainboards  

### 1. Adding the meta-tq layer to your build

In order to use this layer, you need to make the build system aware of
it.

Assuming the tq layer exists in subdir sources at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the tq layer to bblayers.conf, along with any
other layers needed. e.g.:

`
  BBLAYERS ?= " \
    /<path to build tree>/sources/poky/meta \
    /<path to build tree>/sources/poky/meta-poky \
    /<path to build tree>/sources/meta-freescale \
    /<path to build tree>/sources/meta-tq \
    /<path to build tree>/sources/meta-fsl-bsp-release/imx/meta-bsp \
    ...
  "
`

### 2. Supported machines

Support for the following machines is contained in this version:

	SOC		SOM		Base board	MACHINE
[y]	i.MX8M[D,Q]	TQMA8M[D,Q]	MBa8Mx		tqm8mx-1gm-mba8mx (TQMa8M[D,Q] with 1 GiB RAM, HW REV.010x/020x)
[y]	i.MX8MQL	TQMA8MQL	MBa8Mx		tqm8mx-2gm-mba8mx (TQMa8MQL with 2 GiB RAM, HW REV.010x/020x)
[b]	i.MX8QM		TQMA8QM		MBa8x		tqm8qm-mba8x (TQMa8QM with 2 GiB RAM, HW REV.010x)
[b]	i.MX8DX		TQMA8XD		MBa8Xx		tqm8xd-mba8xx (TQMa8XD with 512 MiB RAM)
[b]	i.MX8QXP	TQMA8XQP	MBa8Xx		tqm8xqp-mba8xx (TQMa8XQP with 1 GiB RAM)
[b]	i.MX8QXP	TQMA8XQP	MBpa8Xx		tqm8xqp-mbpa8xx (TQMa8XQP with 1 GiB RAM)
[b]	i.MX8DX		TQMA8XDS	MB-SMARC-2	tqm8xds-mb-smarc-2 (TQMa8XDS with 512 MiB RAM)
[b]	i.MX8QXP	TQMA8XQPS	MB-SMARC-2	tqm8xqps-mb-smarc-2 (TQMa8XQPS with 1 GiB RAM)

[*]	i.MX6[QP,DP]	TQMa6QP/TQMa6DP	MBa6x		tqma6qp-mba6x (TQMa6[QP,DP] HW REV.040x / MBa6x HW REV.020x)
[*]	i.MX6[Q,D]	TQMa6D/TQMa6Q	MBa6x		tqma6q-mba6x (TQMa6[D,Q] HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6[Q,D]	TQMa6D/TQMa6Q	NAV		tqma6q-nav (TQMa6[D,Q] HW REV.030x ... 040x / NAV REV.020x)
[*]	i.MX6DL		TQMa6DL		MBa6x		tqma6dl-mba6x (TQMa6DL HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6S		TQMa6S		MBa6x		tqma6s-mba6x (TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6UL		TQMa6ULx	MBaULx		tqma6ulx-mba6ulx (TQMa6UL HW REV.030x / MBa6ULx HW REV.020x)
[*]	i.MX6UL		TQMa6ULxL	MBaULx		tqma6ulx-lga-mba6ulx (TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x)
[*]	i.MX6ULL	TQMa6ULLx	MBaULx		tqma6ullx-mba6ulx (TQMa6ULLx HW REV.030x / MBa6ULx HW REV.020x)
[*]	i.MX7[S,D]	TQMa7<S,D>	MBa7x		tqma7x-mba7 (TQMa7[S,D] HW REV.010x ... 0x020x / MBa7x HW REV.020x)
[*]	LS102[0,1]a	TQMLS102[0,1]a>	MBLS102xa	tqmls102xa-mbls102xa (TQMLS102[0,1]a HW REV.020x / MBLS102xa HW REV.020x)

[y]: supported
[b]: build tested only
[*]: not buildable in this version

Note: for TQMa6UL1 and baseboard using this module variant with i.MX6ULG1
      a dedicated device tree is supplied

### 3. Support for wic

To generate bootable images for SD / e-MMC wic is supported. Config files are
located in wic directory. Machine files are preconfigured to generate wic images.

### 4. Support custom mainboards

To allow reusing of defines and settings all machine configuration files are
splitted in SOM and mainboard specific parts. Most of the settings can easily
be overwritten due to the usage of `?=` assignments.

To support a custom mainboard for one of the supported SOM, the recommended way
is

* create your own layer
* define your own machine (you can start with a copy of the TQ starter kit for
the SOM)
* include SOM specific settings from meta-tq (`conf/machine/include`)
