# OpenEmbedded/Yocto hardware support layer for TQ Systems ARM SOM

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

## Overview

### Dependencies

This layer in the checked out branch depends on:

URI: https://git.yoctoproject.org/poky  
branch: zeus  
revision: HEAD  
layers: meta, meta-poky  

Optionally the layer can make use of features from meta-freescale /
meta-freeescale-distro if using i.MX6, i.MX6UL, i.MX7 based machines
(tqma6[s,dl,q,qp]-mba6x, tqma6ul[l]x-mba6ulx, tqma6ul[l]x-lga-mba6ulx etc.)
or Layerscape/QorIQ.

URI: https://git.yoctoproject.org/git/meta-freescale  
branch: zeus  
revision: HEAD  
layers: meta-freeescale  

URI: https://github.com/Freescale/meta-freescale-distro.git  
branch: zeus  
revision: HEAD  
layers: meta-freeescale-distro  

**Attention:** meta-freescale is mandatory for the following machines:

- tqmls1012al-mbls1012al
- tqmls1028a-mbls1028a
- tqmls1028a-mbls1028a-ind
- tqmls1088a-mbls10xxa
- tqmlx2160a-mblx2160a

**Attention:** The following distros defined in meta-freescale-distro are not comatible
with machines in meta-tq:

- fsl-framebuffer
- fsl-wayland
- fsl-x11
- fsl-xwayland

Optionally the layer can make use of features from meta-ti if using AM57xx
based machines (tqma57[1,2,4]x-mba57xx etc.)

URI: https://git.yoctoproject.org/git/meta-ti  
branch: zeus  
revision: HEAD  
layers: meta-ti  

**Attention:** meta-ti depends on meta-arm and meta-arm-toolchain. The correct
branch of meta-arm to be used depends on revision. Yocto zeus support started
in branch master and is currently maintained in branch dunfell

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

Assuming the all layers exist in a subdir sources at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the tq layer to bblayers.conf, along with any
other layers needed. e.g.:

```
  BBLAYERS ?= " \
    ${BSPDIR}/sources/poky/meta \
    ${BSPDIR}/sources/poky/meta-poky \
    ${BSPDIR}/sources/meta-tq \
    ...
  "
```

The layer can be used together with the Freescale / NXP layers if needed and
if boards with i.MX CPU are used. Therefore the `MACHINEOVERRIDE` variable is
prepared inside the <modulename>.conf files. See following example:

```
BBLAYERS = " \
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  \
  ${BSPDIR}/sources/meta-freescale \
  ${BSPDIR}/sources/meta-freescale-distro \
  \
  ${BSPDIR}/sources/meta-tq \
"
```

The layer can be used together with the meta-ti layer if needed and am57xx based
boards are used. Therefore the `MACHINEOVERRIDE` variable is prepared inside the
<modulename>.conf files. See following example:

```
BBLAYERS = " \
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  \
  ${BSPDIR}/sources/meta-arm \
  ${BSPDIR}/sources/meta-arm-toolchain \
  \
  ${BSPDIR}/sources/meta-ti \
  \
  ${BSPDIR}/sources/meta-tq \
"
```

### 2. Supported machines

Support for the following machines is contained in this version:

```
	SOC			SOM			Base board	MACHINE
[b]	i.MX6[QP,DP]		TQMa6QP/TQMa6DP		MBa6x		tqma6qp-mba6x (TQMa6[QP,DP] HW REV.040x / MBa6x HW REV.020x)
[y]	i.MX6[Q,D]		TQMa6D/TQMa6Q		MBa6x		tqma6q-mba6x (TQMa6[D,Q] HW REV.010x ... 040x / MBa6x HW REV.020x)
[b]	i.MX6DL			TQMa6DL			MBa6x		tqma6dl-mba6x (TQMa6DL HW REV.010x ... 040x / MBa6x HW REV.020x)
[b]	i.MX6S			TQMa6S			MBa6x		tqma6s-mba6x (TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x)
[y]	i.MX6UL			TQMa6ULx		MBaULx		tqma6ulx-mba6ulx (TQMa6UL HW REV.030x / MBa6ULx HW REV.020x)
[y]	i.MX6UL			TQMa6ULx		MBaULx		tqma6ulx-512mb-mba6ulx (TQMa6ULx HW REV.030x 512 MiB RAM / MBa6ULx HW REV.020x)
[y]	i.MX6UL			TQMa6ULxL		MBaULx		tqma6ulx-lga-mba6ulx (TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x)
[y]	i.MX6UL			TQMa6ULxL		MBaULxL		tqma6ulx-lga-mba6ulxl (TQMa6ULxL HW REV.020x / MBa6ULxL HW REV.020x)
[y]	i.MX6ULL		TQMa6ULLx		MBaULx		tqma6ullx-mba6ulx (TQMa6ULLx HW REV.030x / MBa6ULx HW REV.020x)
[y]	i.MX6ULL		TQMa6ULLx		MBaULx		tqma6ullx-512mb-mba6ulx (TQMa6ULLx HW REV.030x 512 MiB RAM / MBa6ULx HW REV.020x)
[y]	i.MX6ULL		TQMa6ULLxL		MBaULx		tqma6ullx-lga-mba6ulx (TQMa6ULLxL HW REV.020x / MBa6ULx HW REV.020x)
[y]	i.MX7[S,D]		TQMa7[S,D]		MBa7x		tqma7x-512mb-mba7 (TQMa7[S,D] HW REV.010x ... 0x020x with 512 MiB RAM / MBa7x HW REV.020x)
[y]	i.MX7[S,D]		TQMa7[S,D]		MBa7x		tqma7x-1gb-mba7 (TQMa7[S,D] HW REV.010x ... 0x020x with 1 GiB RAM / MBa7x HW REV.020x)
[y]	i.MX7[S,D]		TQMa7[S,D]		MBa7x		tqma7x-2gb-mba7 (TQMa7[S,D] HW REV.010x ... 0x020x with 2 GiB RAM / MBa7x HW REV.020x)
[y]	LS1012A			TQMLS1012AL		MBLS1012AL	tqmls1012al-mbls1012al
[y]	LS1028A			TQMLS1028A		MBLS1028A	tqmls1028a-mbls1028a
[b]	LS1028A			TQMLS1028A		MBLS1028A-IND	tqmls1028a-mbls1028a-ind
[b]	LS1043A			TQMLS1043A		MBLS10xxA	tqmls1043a-mbls10xxa (TQMLS1043a / MBLS10xxa)
[b]	LS1043A			TQMLS1043A		MBLS10xxA	tqmls1043a_2g-mbls10xxa (TQMLS1043a with 2 GiB RAM / MBLS10xxa)
[b]	LS1046A			TQMLS1046A		MBLS10xxA	tqmls1046a-mbls10xxa (TQMLS1046a with 2 GiB RAM / MBLS10xxa)
[b]	LS1046A			TQMLS1046A		MBLS10xxA	tqmls1046a_8g-mbls10xxa (TQMLS1046a with 8 GiB RAM / MBLS10xxa)
[b]	LS1088A			TQMLS1088A		MBLS10xxA	tqmls1088a-mbls10xxa (TQMLS1088a / MBLS10xxa)
[y]	LS102[0,1]A		TQMLS102[0,1]A		MBLS102xA	tqmls102xa-mbls102xa (TQMLS102[0,1]A HW REV.020x / MBLS102xA HW REV.020x)
[y]	LX2160A			TQMLX2160A		MBLX2160A	tqmlx2160a-mblx2160a
[y]	AM57[1,2,4]8		TQMa57[1,2,4]8		MBa57xx		tqma57xx-mba57xx (TQMa57[1,2,4]8 / MBa57xx)
[y]	AM5748			TQMa5748 ECC		MBa57xx		tqma57xx-ecc-mba57xx (TQMa5748 with ECC / MBa57xx)
[p]	T1022			TQMT1022		STK104x		tqmt1022-64bit-stkt104x
[p]	T1040			TQMT1040		STK104x		tqmt1040-64bit-stkt104x
[p]	T1042			TQMT1042		STK104x		tqmt1042-64bit-stkt104x
```

\[y\]: supported  
\[b\]: build tested only  
\[p\]: prerelease  
\[\*\]: not buildable in this version  

See also the machine-specific README file:

- [TQMA6\[Q,D\]](doc/README.TQMa6x.md)
- [TQMA7\[S,D\]](doc/README.TQMa7x.md)
- [TQMLS102\[0,1\]A](doc/README.TQMLS102xa.md)

**Note:** for TQMa6UL1 and baseboards using this module variant with i.MX6ULG1
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

### 5. SOM specific documentation

Under the given links SOM specific information can be found:

