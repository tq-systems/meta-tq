# OpenEmbedded/Yocto hardware support layer for TQ Systems ARM SOM

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

## Overview

### Dependencies

This layer in the checked out branch depends on:

URI: https://git.yoctoproject.org/poky  
branch: sumo  
revision: HEAD  
layers: meta, meta-poky  

URI: https://git.yoctoproject.org/meta-freescale  
branch: sumo  
revision: HEAD  
layers: meta-freescale  

**Note:** Full Support for TQMLS1012al / MBLS1012AL needs additional NXP supplied layers
at the moment:

- meta-qoriq-demos
- meta-freescale

These layers have additional dependencies.

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
5. SOM specific documentation

### 1. Adding the meta-tq layer to your build

In order to use this layer, you need to make the build system aware of
it.

Assuming all layers existing in a subdir sources at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the tq layer to bblayers.conf, along with any
other layers needed. e.g.:

```
BSPDIR := "${@os.path.abspath(os.path.dirname(d.getVar('FILE', True)) + '/../..')}"

  BBLAYERS ?= " \
    ${BSPDIR}/sources/poky/meta \
    ${BSPDIR}/sources/poky/meta-poky \
    ${BSPDIR}/sources/meta-freescale \
    ${BSPDIR}/sources/meta-tq \
    ...
  "
```

### 2. Supported machines

Support for the following machines is contained in this version:

[*]	i.MX6[QP,DP]	TQMa6QP/TQMa6DP	MBa6x		tqma6qp-mba6x (TQMa6[QP,DP] HW REV.040x / MBa6x HW REV.020x)
[*]	i.MX6[Q,D]	TQMa6D/TQMa6Q	MBa6x		tqma6q-mba6x (TQMa6[D,Q] HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6[Q,D]	TQMa6D/TQMa6Q	NAV		tqma6q-nav (TQMa6[D,Q] HW REV.030x ... 040x / NAV REV.020x)
[*]	i.MX6DL		TQMa6DL		MBa6x		tqma6dl-mba6x (TQMa6DL HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6S		TQMa6S		MBa6x		tqma6s-mba6x (TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x)
[*]	i.MX6UL		TQMa6ULx	MBaULx		tqma6ulx-mba6ulx (TQMa6UL HW REV.030x / MBa6ULx HW REV.020x)
[*]	i.MX6UL		TQMa6ULxL	MBaULx		tqma6ulx-lga-mba6ulx (TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x)
[*]	i.MX6ULL	TQMa6ULLx	MBaULx		tqma6ullx-mba6ulx (TQMa6ULLx HW REV.030x / MBa6ULx HW REV.020x)
[*]	i.MX7[S,D]	TQMa7[S,D]	MBa7x		tqma7x-mba7 (TQMa7[S,D] HW REV.010x ... 0x020x / MBa7x HW REV.020x)
[*]	LS102[0,1]a	TQMLS102[0,1]a>	MBLS102xa	tqmls102xa-mbls102xa (TQMLS102[0,1]a HW REV.020x / MBLS102xa HW REV.020x)
[y]	LS1012a		TQMLS1012AL	MBLS1012AL	tqmls1012al-mbls1012al (TQMLS1012AL HW REV. 100/ MBLS1012AL HW REV.101)
```

\[y\]: supported  
\[b\]: build tested only  
\[\*\]: not buildable in this version  


### 3. Support for wic

To generate images for SD / e-MMC wic is supported. Config files are
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

### 5. SOM specific documentation

Under the given links SOM specific information can be found:

[TQMLS1012AL](doc/README.TQMLS1012AL.md)  
