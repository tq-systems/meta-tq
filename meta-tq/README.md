# OpenEmbedded/Yocto hardware support layer for TQ-Systems ARM SOM

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ-Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

[[_TOC_]]

## Overview

### Dependencies

This layer in the checked out branch depends on:

URI: https://git.yoctoproject.org/poky  
branch: kirkstone  
revision: HEAD  
layers: meta, meta-poky  

#### Notes for machines with i.MX CPU

Optionally the layer can make use of features from meta-freescale /
meta-freeescale-distro if using CPU from following NXP families - see
[table](#supported-machines)

* i.MX6
* i.MX6UL / i.MX6ULL
* i.MX7

For machines based on following CPU families from NXP the use of meta-freescale
is _mandatory_ - see [table](#supported-machines).

* i.MX8
* i.MX8X
* i.MX8MQ
* i.MX8MM
* i.MX8MN
* i.MX8MP
* i.MX93

URI: https://git.yoctoproject.org/git/meta-freescale  
branch: kirkstone  
revision: tested with HEAD, at least commit 8bd13d69 ("firmware-imx: Upgrade 8.17 -> 8.18")
layers: meta-freeescale  

**Attention:** The following distros defined in meta-freescale-distro are not
tested with machines in meta-tq:

- fsl-framebuffer
- fsl-wayland
- fsl-x11
- fsl-xwayland

#### Notes for machines with Layerscape CPU

For machines based on following CPU families from NXP usage of meta-freescale
is _mandatory_ - see [table](#supported-machines).

* LS1012A
* LS1028A / LS1017A
* LS1043A / LS1046A / LS1088A
* LX2160A

#### Notes for machines with TI CPU

Optionally the layer can make use of features from meta-ti if using machines
with the following CPU families from TI  - see [table](#supported-machines).

* AM355x
* AM57xx

For machines based on following CPU families from TI usage of meta-ti
is _mandatory_ - see [table](#supported-machines)

* AM64xx
* AM65xx


URI: https://git.yoctoproject.org/git/meta-ti  
branch: kirkstone  
revision: HEAD  
layers: meta-ti-bsp  

If using meta-ti, meta-arm is also needed due to dependencies of meta-ti.

### Coding style

It is recommended to use the [Format_Guidelines](https://www.openembedded.org/wiki/Styleguide#Format_Guidelines)
from openembedded.

### Patches

Please submit patches against the meta-tq layer to the
maintainer:

Maintainer: Markus Niebel <Markus.Niebel@tq-group.com>

Additionally you can use github's collaboration features.

## Usage

### Adding the meta-tq layer to your build

In order to use this layer, you need to make the build system aware of
it.

Assuming that all layers exist in a subdir `sources` at the top-level of your
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

If the layer has to be used together with the Freescale / NXP layers for
machines with i.MX CPU, `MACHINEOVERRIDES` and other configuration
settings are prepared inside the <machine>.conf and their include files.
See following example:

```
BBLAYERS = "\
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-python \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  \
  ${BSPDIR}/sources/meta-freescale \
  \
  ${BSPDIR}/sources/meta-tq \
"
```

If the layer has to be used together with the meta-ti layer for machines with
TI CPU, `MACHINEOVERRIDES` and other configuration settings are prepared inside
the <machine>.conf and their include files.
See following example:

```
BBLAYERS = "\
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-python \
  \
  ${BSPDIR}/sources/meta-arm/meta-arm \
  ${BSPDIR}/sources/meta-arm/meta-arm-toolchain \
  \
  ${BSPDIR}/sources/meta-ti \
  \
  ${BSPDIR}/sources/meta-tq \
"
```

### Supported machines

Support for the following machines is contained in this version:

| Status | SOC / CPU        | SOM                      | Base board     | yocto / OpenEmbedded machine | hardware revision                                                     |
| ------ | ---------------- | -------------------------| ---------------| ---------------------------- | --------------------------------------------------------------------- |
| [y]    | i.MX6\[QP,DP\]   | TQMa6\[QP,DP\]           | MBa6x          | tqma6qp-mba6x                | TQMa6\[QP,DP\] HW REV.040x / MBa6x HW REV.020x                        |
| [y]    | i.MX6\[Q,D\]     | TQMa6\[Q,D\]             | MBa6x          | tqma6q-mba6x                 | TQMa6\[D,Q\] HW REV.010x ... 040x / MBa6x HW REV.020x                 |
| [y]    | i.MX6\[Q,D\]     | TQMa6D/TQMa6Q            | MBa6x          | tqma6q-2gb-mba6x             | TQMa6\[D,Q\] with 2 GiB RAM HW REV.040x / MBa6x HW REV.020x           |
| [y]    | i.MX6DL          | TQMa6DL                  | MBa6x          | tqma6dl-mba6x                | TQMa6DL HW REV.010x ... 040x / MBa6x HW REV.020x                      |
| [y]    | i.MX6S           | TQMa6S                   | MBa6x          | tqma6s-mba6x                 | TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x                       |
| [y]    | i.MX6UL          | TQMa6ULx                 | MBaULx         | tqma6ulx-mba6ulx             | TQMa6UL HW REV.030x / MBa6ULx HW REV.020x                             |
| [y]    | i.MX6UL          | TQMa6ULx                 | MBaULx         | tqma6ulx-512mb-mba6ulx       | TQMa6ULx HW REV.030x 512 MiB RAM / MBa6ULx HW REV.020x                |
| [y]    | i.MX6UL          | TQMa6ULxL                | MBaULx         | tqma6ulx-lga-mba6ulx         | TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x                           |
| [y]    | i.MX6UL          | TQMa6ULxL                | MBaULx         | tqma6ulx-512mb-lga-mba6ulx   | TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x                           |
| [y]    | i.MX6UL          | TQMa6ULxL                | MBaULxL        | tqma6ulx-lga-mba6ulxl        | TQMa6ULxL HW REV.020x / MBa6ULxL HW REV.020x |
| [y]    | i.MX6ULL         | TQMa6ULLx                | MBaULx         | tqma6ullx-mba6ulx            | TQMa6ULLx HW REV.030x / MBa6ULx HW REV.020x |
| [y]    | i.MX6ULL         | TQMa6ULLx                | MBaULx         | tqma6ullx-512mb-mba6ulx      | TQMa6ULLx HW REV.030x 512 MiB RAM / MBa6ULx HW REV.020x |
| [y]    | i.MX6ULL         | TQMa6ULLxL               | MBaULx         | tqma6ullx-lga-mba6ulx        | TQMa6ULLxL HW REV.020x / MBa6ULx HW REV.020x |
| [y]    | i.MX6ULL         | TQMa6ULLxL               | MBaULx         | tqma6ullx-512mb-lga-mba6ulx  | TQMa6ULLxL HW REV.020x 512 MiB RAM/ MBa6ULx HW REV.020x               |
| [y]    | i.MX7\[S,D\]     | TQMa7\[S,D\]             | MBa7x          | tqma7x-512mb-mba7            | TQMa7\[S,D\] HW REV.010x ... 0x020x / 512 MiB RAM / MBa7x HW REV.020x |
| [y]    | i.MX7\[S,D\]     | TQMa7\[S,D\]             | MBa7x          | tqma7x-1gb-mba7              | TQMa7\[S,D\] HW REV.010x ... 0x020x / 1 GiB RAM / MBa7x HW REV.020x |
| [y]    | i.MX7\[S,D\]     | TQMa7\[S,D\]             | MBa7x          | tqma7x-2gb-mba7              | TQMa7\[S,D\] HW REV.010x ... 0x020x / 2 GiB RAM / MBa7x HW REV.020x |
| [y]    | i.MX8MP          | TQMa8MPxL                | MBa8MPxL       | tqma8mpxl-mba8mpxl           | TQMa8MPQL with 1/2/4/8 GiB RAM, HW REV.020x, MBa8Mx HW REV.020x |
| [y]    | i.MX8M\[D,Q\]    | TQMa8M\[D,Q\]            | MBa8Mx         | tqma8mx-1gb-mba8mx           | TQMa8M\[D,Q\] with 1 GiB RAM, HW REV.020x, MBa8Mx HW REV.020x / 030x |
| [y]    | i.MX8MQL         | TQMa8MQL                 | MBa8Mx         | tqma8mx-2gb-mba8mx           | TQMa8MQL / 2 GiB RAM, HW REV.020x, MBa8Mx HW REV.020x / 030x |
| [y]    | i.MX8MQ          | TQMa8MQ                  | MBa8Mx         | tqma8mx-4gb-mba8mx           | TQMa8MQ / 4 GiB RAM, HW REV.020x, MBa8Mx HW REV.020x / 030x |
| [y]    | i.MX8MM\[D,Q\]   | TQMa8M\[D,Q\]ML          | MBa8Mx         | tqma8mxml-1gb-mba8mx         | TQMa8M\[D,Q\]ML / 1 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x |
| [y]    | i.MX8MMQ         | TQMa8MQML                | MBa8Mx         | tqma8mxml-2gb-mba8mx         | TQMa8MQML / 2 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x |
| [y]    | i.MX8MNQ         | TQMa8MQNL                | MBa8Mx         | tqma8mxnl-1gb-mba8mx         | TQMa8MQNL / 1 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x |
| [y]    | i.MX8MNDL        | TQMa8MDLNL               | MBa8Mx         | tqma8mxnl-1gb-mba8mx         | TQMa8MDLNL / 1 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x |
| [y]    | i.MX8QM          | TQMa8QM                  | MBa8x          | tqma8qm-4gb-mba8x            | TQMa8QM / 4 GiB RAM, HW REV.0102 and newer / 020x, MBa8x HW REV.020x |
| [y]    | i.MX8QM          | TQMa8QM                  | MBa8x          | tqma8qm-8gb-mba8x            | TQMa8QM / 8 GiB RAM, HW REV.0102 and newer / 020x, MBa8x HW REV.020x |
| [y]    | i.MX8DXP         | TQMa8XDP                 | MBa8Xx         | tqma8xdp-1gb-mba8xx          | TQMa8XDP / 1 GiB RAM ECC, HW REV.020x / 0x30x |
| [y]    | i.MX8DXP         | TQMa8XDP4                | MBa8Xx         | tqma8xdp4-mba8xx             | TQMa8XDP / 2 GiB LPDDR4 RAM, HW REV.010x |
| [y]    | i.MX8QXP         | TQMa8XQP                 | MBa8Xx         | tqma8xqp-1gb-mba8xx          | TQMa8XQP / 1 GiB RAM ECC, HW REV.020x / 0x30x |
| [y]    | i.MX8QXP         | TQMa8XQP                 | MBa8Xx         | tqma8xqp-2gb-mba8xx          | TQMa8XQP / 2 GiB RAM ECC, HW REV.020x / 0x30x |
| [y]    | i.MX8QXP         | TQMa8XQP4                | MBa8Xx         | tqma8xqp4-mba8xx             | TQMa8XQP / 2 GiB LPDDR4 RAM, HW REV.010x |
| [y]    | i.MX8DXP         | TQMa8XDPS                | MB-SMARC-2     | tqma8xdps-mb-smarc-2         | TQMa8XDPS / 2 GiB RAM, HW REV.030x |
| [y]    | i.MX8QXP         | TQMa8XQPS                | MB-SMARC-2     | tqma8xqps-mb-smarc-2         | TQMa8XQPS / 2 GiB RAM, HW REV.030x |
| [p]    | i.MX93 11x11     | TQMa93\[3,5\]\[1,2\]LA   | MBa9xxxCA      | tqma93xxla-mba9xxxca         | TQMa93\[3,5\]\[1,2\]LA / 1 GiB RAM, HW REV.010x (Prototype) / MBa9xxxCA |
| [y]    | LS1012A          | TQMLS1012AL              | MBLS1012AL     | tqmls1012al-mbls1012al       | TQMLS1012AL HW REV.010x ... 020x / 512 MiB / 1GiB RAM / MBLS1012AL HW REV.020x |
| [p]    | LS10\[17,28\]A   | TQMLS10\[17,28\]A        | MBLS1028A      | tqmls1028a-mbls1028a         | TQMLS1017A / TQMLS1028A HW REV.010x ... 030x, 1 GiB / 4 GiB RAM, MBLS1028A HW REV.010x |
| [p]    | LS10\[17,28\]A   | TQMLS10\[17,28\]A        | MBLS1028A-IND  | tqmls1028a-mbls1028a-ind     | TQMLS1017A / TQMLS1028A HW REV.010x ... 030x, 1 GiB / 4 GiB RAM, MBLS1028A-IND HW REV.010x |
| [p]    | LS1043A          | TQMLS1043A               | MBLS10xxA      | tqmls1043a-mbls10xxa         | TQMLS1043a / 1 GiB RAM, HW REV.020x / MBLS10xxa, HW REV.020x |
| [p]    | LS1043A          | TQMLS1043A               | MBLS10xxA      | tqmls1043a_2g-mbls10xxa      | TQMLS1043a / 2 GiB RAM, HW REV.020x / MBLS10xxa, HW REV.020x |
| [p]    | LS1046A          | TQMLS1046A               | MBLS10xxA      | tqmls1046a-mbls10xxa         | TQMLS1046a / 2 GiB RAM, HW REV.020x / MBLS10xxa, HW REV.020x |
| [p]    | LS1046A          | TQMLS1046A               | MBLS10xxA      | tqmls1046a_8g-mbls10xxa      | TQMLS1046a / 8 GiB RAM, HW REV.020x / MBLS10xxa, HW REV.020x |
| [p]    | LS1088A          | TQMLS1088A               | MBLS10xxA      | tqmls1088a-mbls10xxa         | TQMLS1088a / MBLS10xxa |
| [y]    | LS102\[0,1\]A    | TQMLS102\[0,1\]A         | MBLS102xA      | tqmls102xa-mbls102xa         | TQMLS102\[0,1\]A HW REV.020x / MBLS102xA HW REV.020x |
| [p]    | LX2160A          | TQMLX2160A               | MBLX2160A      | tqmlx2160a-mblx2160a         | TQMLX2160A HW REV.010x / MBLX2160A HW REV.010x and REV.020x |
| [y]    | AM355x           | TQMa335x\[L\]            | MBa335x        | tqma355x-mba335x             | TQMa335x[L] HW REV.020x with 256/512 MiB RAM / MBa335x HW REV.020x |
| [b]    | AM57\[1,2,4\]8   | TQMa57\[1,2,4\]8         | MBa57xx        | tqma57xx-mba57xx             | TQMa57[1,2,4]8 / MBa57xx |
| [b]    | AM5748           | TQMa5748 ECC             | MBa57xx        | tqma57xx-ecc-mba57xx         | TQMa5748 with ECC / MBa57xx |
| [y]    | AM6442           | TQMa6442L                | MBaX4XxL       | tqma64xxl-mbax4xxl           | TQMa6442L / MBaX4XxL HW REV.020x |
| [y]    | AM6548           | TQMa654x                 | MBa65xx        | tqma65xx-2gb-mba65xx         | TQMa6548 HW REV.010x with 2GiB RAM |
| [y]    | AM6548           | TQMa654x                 | MBa65xx        | tqma65xx-4gb-mba65xx         | TQMa6548 HW REV.010x with 4GiB RAM |


\[y\]: fully supported  
\[b\]: build tested only  
\[p\]: prerelease  
\[\*\]: not buildable in this version  

**Note:** for TQMa6UL1 and baseboards using this module variant with i.MX6ULG1
          a dedicated device tree is supplied

### Support for wic

To generate bootable images for SD / e-MMC wic is supported. Config files are
located in wic directory. Machine files are preconfigured to generate wic images.

### Support custom mainboards

To allow reusing of defines and settings all machine configuration files are
splitted in SOM and mainboard specific parts. Most of the settings can easily
be overwritten due to the usage of `?=` assignments.

To support a custom mainboard for one of the supported SOM, the recommended way
is

* create your own layer
* define your own machine (you can start with a copy of the TQ starter kit for
  the SOM)
* include SOM specific settings from meta-tq (`conf/machine/include`)

## Additional information

### SOM specific documentation

Under the given links SOM specific information can be found:

* [TQMA6\[Q,D\]](doc/README.TQMa6x.md)
* [TQMA6UL\[L\]](doc/README.TQMa6ULx.md)
* [TQMA7\[S,D\]](doc/README.TQMa7x.md)
* [TQMa8Mx](doc/README.TQMa8Mx.md)
* [TQMa8MxML](doc/README.TQMa8MxML.md)
* [TQMa8MxNL](doc/README.TQMa8MxNL.md)
* [TQMa8MPxL](doc/README.TQMa8MPxL.md)
* [TQMa8XDP](doc/README.TQMa8Xx.md)
* [TQMa8XDP4](doc/README.TQMa8Xx.md)
* [TQMa8XQP](doc/README.TQMa8Xx.md)
* [TQMa8XQP4](doc/README.TQMa8Xx.md)
* [TQMa8XDPS](doc/README.TQMa8XxS.md)
* [TQMa8XQPS](doc/README.TQMa8XxS.md)
* [TQMa8QM](doc/README.TQMa8x.md)
* [TQMa93\[3,5\]\[1,2\]LA](doc/README.TQMa93xx.md)
* [TQMA335x\[L\]](doc/README.TQMa335x.md)
* [TQMA57xx](doc/README.TQMa57xx.md)
* [TQMA64xxL](doc/README.TQMa64xxL.md)
* [TQMA65xx](doc/README.TQMa65xx.md)
* [TQMLS1012AL](doc/README.TQMLS1012AL.md)
* [TQMLS102\[0,1\]A](doc/README.TQMLS102xa.md)
* [TQMLS1017A/TQMLS1028A](doc/README.TQMLS1028A.md)
* [TQMLX2160A](doc/README.tqmlx2160a-mblx2160a.md)

### Known Issues

- `u-boot-fw-utils` is not buildable currently because it uses the U-Boot Mainline
repository. To make it buildable a bbappend which changes SRC_URI would be necessary.
