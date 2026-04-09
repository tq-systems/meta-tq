<!---
SPDX-License-Identifier: CC-BY-4.0

Copyright (c) 2016-2024 TQ-Systems GmbH <oss@ew.tq-group.com>,
D-82229 Seefeld, Germany.
--->

# OpenEmbedded/Yocto hardware support layer for TQ-Systems ARM SOM

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ-Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

[[_TOC_]]

## Overview

### Dependencies

This layer in the checked out branch depends on [bitbake](https://git.openembedded.org/bitbake) and
the following layers:

| URI                                             | branch     | layer              | remark                            |
| :---------------------------------------------- | :--------: | :----------------: | :-------------------------------: |
| https://git.openembedded.org/openembedded-core  | scarthgap  | meta               |                                   |
| https://git.yoctoproject.org/git/meta-freescale | scarthgap  | meta-freescale     | mandatory for Layerscape and i.MX |
| https://git.yoctoproject.org/git/meta-ti        | scarthgap  | meta-ti-bsp        | mandatory for TI SOC              |
| https://git.yoctoproject.org/git/meta-arm       | scarthgap  | meta-arm           |                                   |
| https://git.yoctoproject.org/git/meta-arm       | scarthgap  | meta-arm-toolchain |                                   |

For tested revisions of the referenced layers see BSP setup and release notes.

#### Notes for machines with i.MX or Layerscape CPU

For machines based on following CPU families from NXP the use of `meta-freescale` layer
is _mandatory_ - see [table](#supported-machines).

* i.MX6
* i.MX6UL / i.MX6ULL
* i.MX7
* i.MX8
* i.MX8X
* i.MX8MQ
* i.MX8MM
* i.MX8MN
* i.MX8MP
* i.MX91
* i.MX93
* i.MX95
* LS1012A
* LS102xA
* LS1028A (incl. LS1017A / LS1018A / LS1027A)
* LS1043A / LS1046A / LS1088A
* LX2160A

**Note:** For i.MX95 the `meta-arm` and `meta-arm-toolchain` layers
are needed too, since boot firmware recipe depends on them.

**Attention:** The distros defined in `meta-freescale-distro` layer are not
tested with machines in `meta-tq`:

- fsl-framebuffer
- fsl-wayland
- fsl-x11
- fsl-xwayland

#### Notes for machines with TI CPU

For machines based on following CPU families from TI usage of `meta-ti-bsp` layer
is _mandatory_ - see [table](#supported-machines):

* AM355x
* AM62xx
* AM64xx
* AM67xx

**Note:** If using the `meta-ti-bsp` layer from `meta-ti`, the `meta-arm` and `meta-arm-toolchain` layers
are needed too, since `meta-ti-bsp` depends on them.

### Coding style

It is recommended to use the [Format_Guidelines](https://www.openembedded.org/wiki/Styleguide#Format_Guidelines)
from openembedded.

### Patches

Please submit patches against the meta-tq layer via github's collaboration
features.

Additionally, you can send patches to the maintenance team by email:
<oss@ew.tq-group.com>

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

| Status | SOC / CPU               | SOM                     | Base board     | yocto / OpenEmbedded machine     | hardware revision                                                                                                                                            |
|--------|-------------------------|-------------------------|--------------- |----------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [y]    | i.MX6\[S,DL,D,Q,QP,DP\] | TQMa6\[S,DL,D,Q,QP,DP\] | MBa6x          | tqma6qdl-multi-mba6x             | TQMa6\[QP,DP\] HW REV.040x, TQMa6\[D,Q\] 1/2 GiB HW REV.010x ... 040x, TQMa6DL 1/2 GiB HW REV.010x ... 040x, TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x |
| [y]    | i.MX6UL                 | TQMa6ULx[L]             | MBaULx         | tqma6ul-multi-mba6ulx            | TQMa6ULx HW REV.030x / TQMa6ULxL HW REV.020x 256/512 MB RAM, MBa6ULx HW REV.020x                                                                             |
| [y]    | i.MX6ULL                | TQMa6ULLx               | MBaULx         | tqma6ull-multi-mba6ulx           | TQMa6ULLx HW REV.030x / TQMa6ULLxL HW REV.020x  256/512 MB RAM, MBa6ULx HW REV.020x                                                                          |
| [y]    | i.MX7D                  | TQMa7D                  | MBa7x          | tqma7x-multi-mba7                | TQMa7D HW REV.010x ... 020x / 1 GiB / 2 GiB /512 MiB RAM / MBa7x HW REV.020x                                                                                 |
| [y]    | i.MX8MP                 | TQMa8MPxL               | MBa8MPxL       | tqma8mpxl-mba8mpxl               | TQMa8MPQL with 1/2/4/8 GiB RAM, HW REV.020x, MBa8MPxL HW REV.020x                                                                                            |
| [y]    | i.MX8MP                 | TQMa8MPxS               | MMB-SMARC-2    | tqma8mpxs-mb-smarc-2             | TQMa8MPQS with 1/2/4/8 GiB RAM, HW REV.010x and HW REV.030x                                                                                                  |
| [y]    | i.MX8MP                 | TQMa8MPxL               | MBa8MP-RAS314  | tqma8mpxl-mba8mp-ras314          | TQMa8MPQL with 1/2/4/8 GiB RAM, HW REV.020x, MBa8MP-RAS314 HW REV.010x                                                                                       |
| [y]    | i.MX8M\[D,Q,QL\]        | TQMa8M\[D,Q,QL\]        | MBa8Mx         | tqma8mx-multi-mba8mx             | TQMa8M\[D,Q,QL\] with 1/2/4 GiB RAM, HW REV.020x, MBa8Mx HW REV.020x / 030x                                                                                  |
| [y]    | i.MX8MM\[D,Q\]          | TQMa8M\[D,Q\]ML         | MBa8Mx         | tqma8mxml-multi-mba8mx           | TQMa8M\[D,Q\]ML / 1/2/4 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x                                                                                             |
| [y]    | i.MX8MN\[Q,DL\]         | TQMa8M\[Q,DL\]NL        | MBa8Mx         | tqma8mxnl-1gb-mba8mx             | TQMa8M\[Q,DL\]NL / 1 GiB RAM, HW REV.020x, MBa8Mx HW REV.030x                                                                                                |
| [y]    | i.MX8QM                 | TQMa8QM                 | MBa8x          | tqma8qm-4gb-mba8x                | TQMa8QM / 4 GiB RAM, HW REV.0102 and newer / 020x, MBa8x HW REV.020x                                                                                         |
| [y]    | i.MX8QM                 | TQMa8QM                 | MBa8x          | tqma8qm-8gb-mba8x                | TQMa8QM / 8 GiB RAM, HW REV.0102 and newer / 020x, MBa8x HW REV.020x                                                                                         |
| [y]    | i.MX8DXP                | TQMa8XDP                | MBa8Xx         | tqma8xdp-1gb-mba8xx              | TQMa8XDP / 1 GiB RAM ECC, HW REV.020x / 030x                                                                                                                 |
| [y]    | i.MX8DXP                | TQMa8XDP4               | MBa8Xx         | tqma8xdp4-mba8xx                 | TQMa8XDP / 2 GiB LPDDR4 RAM, HW REV.010x                                                                                                                     |
| [y]    | i.MX8QXP                | TQMa8XQP                | MBa8Xx         | tqma8xqp-1gb-mba8xx              | TQMa8XQP / 1 GiB RAM ECC, HW REV.020x / 030x                                                                                                                 |
| [y]    | i.MX8QXP                | TQMa8XQP                | MBa8Xx         | tqma8xqp-2gb-mba8xx              | TQMa8XQP / 2 GiB RAM ECC, HW REV.020x / 030x                                                                                                                 |
| [y]    | i.MX8QXP                | TQMa8XQP4               | MBa8Xx         | tqma8xqp4-mba8xx                 | TQMa8XQP / 2 GiB LPDDR4 RAM, HW REV.010x                                                                                                                     |
| [y]    | i.MX8DXP                | TQMa8XDPS               | MB-SMARC-2     | tqma8xdps-mb-smarc-2             | TQMa8XDPS / 2 GiB RAM, HW REV.030x                                                                                                                           |
| [y]    | i.MX8QXP                | TQMa8XQPS               | MB-SMARC-2     | tqma8xqps-mb-smarc-2             | TQMa8XQPS / 2 GiB RAM, HW REV.030x                                                                                                                           |
| [y]    | i.MX91 11x11            | TQMa91\[3,2\]1CA        | MBa91xxCA      | tqma91xx-mba91xxca               | TQMa91\[3,2\]1CA, 0.5 / 1 GiB RAM, HW REV.010x / MBa91xxCA REV.010x                                                                                          |
| [y]    | i.MX91 11x11            | TQMa91\[3,2\]1LA        | MBa91xxCA      | tqma91xx-mba91xxca               | TQMa91\[3,2\]1LA, 0.5 / 1 GiB RAM, HW REV.010x / MBa91xxCA REV.010x                                                                                          |
| [y]    | i.MX91 11x11            | TQMa91\[3,2\]1CA        | MBa93xxCA      | tqma91xx-mba93xxca               | TQMa91\[3,2\]1CA, 0.5 / 1 GiB RAM, HW REV.010x / MBa93xxCA REV.020x                                                                                          |
| [y]    | i.MX91 11x11            | TQMa91\[3,2\]1LA        | MBa93xxCA      | tqma91xx-mba93xxca               | TQMa91\[3,2\]1LA, 0.5 / 1 GiB RAM, HW REV.010x / MBa93xxCA REV.020x                                                                                          |
| [y]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]CA  | MBa91xxCA      | tqma93xx-mba91xxca               | TQMa93\[3,5\]\[1,2\]CA, 1 / 2 GiB RAM, HW REV.010x / MBa91xxCA REV.010x                                                                                      |
| [y]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]LA  | MBa91xxCA      | tqma93xx-mba91xxca               | TQMa93\[3,5\]\[1,2\]LA on adaptor, 1 / 1.5 / 2 GiB RAM, HW REV.010x / MBa91xxCA REV.010x                                                                     |
| [y]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]CA  | MBa93xxCA      | tqma93xx-mba93xxca               | TQMa93\[3,5\]\[1,2\]CA, 1 / 2 GiB RAM, HW REV.010x / MBa93xxCA REV.020x                                                                                      |
| [y]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]LA  | MBa93xxCA      | tqma93xx-mba93xxca               | TQMa93\[3,5\]\[1,2\]LA on adaptor, 1 / 1.5 / 2 GiB RAM, HW REV.010x / MBa93xxCA REV.020x                                                                     |
| [y]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]LA  | MBa93xxLA      | tqma93xxla-mba93xxla             | TQMa93\[3,5\]\[1,2\]LA, 1 / 1.5 / 2 GiB RAM, HW REV.010x / MBa93xxLA REV.020x                                                                                |
| [p]    | i.MX93 11x11            | TQMa93\[3,5\]\[1,2\]LA  | MBa93xxLA-MINI | tqma93xxla-mba93xxla-mini        | TQMa93\[3,5\]\[1,2\]LA, 1 / 1.5 / 2 GiB RAM, HW REV.010x / MBa93xxLA-MINI REV.020x                                                                           |
| [p]    | i.MX95 19x19            | TQMa95xxLA              | MBa95xxCA      | tqma95xxla-4gb-mba95xxca         | TQMa95xxLA, 4 GiB RAM, HW REV.010x                                                                                                                           |
| [p]    | i.MX95 19x19            | TQMa95xxSA              | MB-SMARC-2     | tqma95xxsa-2gb-mb-smarc-2        | TQMa95xxSA, 2 GiB RAM, HW REV.010x                                                                                                                           |
| [p]    | i.MX95 19x19            | TQMa95xxSA              | MB-SMARC-2     | tqma95xxsa-4gb-mb-smarc-2        | TQMa95xxSA, 4 GiB RAM, HW REV.010x                                                                                                                           |
| [y]    | LS1012A                 | TQMLS1012AL             | MBLS1012AL     | tqmls1012al-mbls1012al           | TQMLS1012AL HW REV.010x ... 020x / 512 MiB / 1GiB RAM / MBLS1012AL HW REV.020x                                                                               |
| [y]    | LS10\[17,28\]A          | TQMLS10\[17,28\]A       | MBLS1028A      | tqmls1028a-mbls1028a             | TQMLS1017A / TQMLS1028A HW REV.010x ... 030x, 1 GiB / 4 GiB RAM, MBLS1028A HW REV.010x                                                                       |
| [y]    | LS10\[17,28\]A          | TQMLS10\[17,28\]A       | MBLS1028A-IND  | tqmls1028a-mbls1028a-ind         | TQMLS1017A / TQMLS1028A HW REV.010x ... 030x, 1 GiB / 4 GiB RAM, MBLS1028A-IND HW REV.010x                                                                   |
| [y]    | LS1043A                 | TQMLS1043A              | MBLS10xxA      | tqmls1043a-1gb-mbls10xxa         | TQMLS1043A, 1 GiB, HW REV.020x / MBLS10xxA, HW REV.020x                                                                                                      |
| [y]    | LS1043A                 | TQMLS1043A              | MBLS10xxA      | tqmls1043a-2gb-mbls10xxa         | TQMLS1043A, 2 GiB, HW REV.020x ... 030x / MBLS10xxA, HW REV.020x                                                                                             |
| [y]    | LS1043A                 | TQMLS1043A              | MBLS10xxA      | tqmls1043a-2gb-rev0300-mbls10xxa | TQMLS1043A, 2 GiB, HW REV.020x ... 030x / MBLS10xxA, HW REV.020x                                                                                             |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-2gb-mbls10xxa         | TQMLS1046A, 2 GiB, HW REV.020x / MBLS10xxA, HW REV.020x                                                                                                      |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-4gb-mbls10xxa         | TQMLS1046A, 4 GiB, HW REV.030x / MBLS10xxA, HW REV.020x                                                                                                      |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-8gb-mbls10xxa         | TQMLS1046A, 8 GiB, HW REV.020x / MBLS10xxA, HW REV.020x                                                                                                      |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-2gb-noecc-mbls10xxa   | TQMLS1046A, 2 GiB no ECC, HW REV.020x / MBLS10xxA, HW REV.020x                                                                                               |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-4gb-noecc-mbls10xxa   | TQMLS1046A, 4 GiB no ECC, HW REV.030x / MBLS10xxA, HW REV.020x                                                                                               |
| [y]    | LS1046A                 | TQMLS1046A              | MBLS10xxA      | tqmls1046a-8gb-noecc-mbls10xxa   | TQMLS1046A, 8 GiB no ECC, HW REV.020x / MBLS10xxA, HW REV.020x                                                                                               |
| [y]    | LS1088A                 | TQMLS1088A              | MBLS10xxA      | tqmls1088a-2gb-mbls10xxa         | TQMLS1088A, 2 GiB, HW REV.020x ... REV.030x / MBLS10xxA                                                                                                      |
| [y]    | LS1088A                 | TQMLS1088A              | MBLS10xxA      | tqmls1088a-4gb-mbls10xxa         | TQMLS1088A, 4 GiB, HW REV.020x ... REV.030x / MBLS10xxA                                                                                                      |
| [y]    | LS102\[0,1\]A           | TQMLS102\[0,1\]A        | MBLS102xA      | tqmls102xa-mbls102xa             | TQMLS102\[0,1\]A HW REV.020x / MBLS102xA HW REV.020x                                                                                                         |
| [y]    | LX2\[16,08\]0A          | TQMLX2\[16,08\]0A       | MBLX2160A      | tqmlx2160a-mblx2160a             | TQMLX2\[16,08\]0A HW REV.010x with 32 GiB / 16 Gib RAM / MBLX2160A HW REV.010x and REV.020x                                                                  |
| [y]    | AM335x                  | TQMa335x\[L\]           | MBa335x        | tqma355x-mba335x                 | TQMa335x[L] HW REV.020x with 256/512 MiB RAM / MBa335x HW REV.020x                                                                                           |
| [b]    | AM6234                  | TQMa6234\[L\]           | MBa62xx        | tqma62xx-mba62xx                 | TQMa6234\[L\] HW REV.010x ... 020x, MBa62xx HW REV.010x ... 020x                                                                                             |
| [b]    | AM6254                  | TQMa6254\[L\]           | MBa62xx        | tqma62xx-mba62xx                 | TQMa6254\[L\] HW REV.010x ... 020x, MBa62xx HW REV.010x ... 020x                                                                                             |
| [b]    | AM6411                  | TQMa6411L               | MBaX4XxL       | tqma64xxl-mbax4xxl               | TQMa6411L HW REV.020x, MBaX4XxL HW REV.020x                                                                                                                  |
| [b]    | AM6442                  | TQMa6442L               | MBaX4XxL       | tqma64xxl-mbax4xxl               | TQMa6442L HW REV.020x, MBaX4XxL HW REV.020x                                                                                                                  |
| [p]    | AM67A94                 | TQMa67A94\[L\]          | MBa67xx        | tqma67xx-mba67xx                 | TQMa67A94\[L\] HW REV.020x, MBa67xx HW REV.020x                                                                                                              |

Definition of support status:

| Mark| Meaning                          |
|-----|----------------------------------|
|\[y\]| fully supported                  |
|\[p\]| prerelease                       |
|\[b\]| builds but not completely tested |

**Note:** for TQMa6UL1 and baseboards using this module variant with i.MX6ULG1
          a dedicated device tree is supplied

### Obsolete machines

* TQMa57: Use `kirkstone` branch instead, last release is kirkstone.TQMa57xx.BSP.SW.0015
* TQMa65xx: Use `hardknott` branch or `kirkstone.TQMa65xx.BSP.SW.0009` release instead
* TQMA7S: Use `kirkstone` / `kirkstone-next` branches instead, contact support for updates.
* TQMT10xx: Use `kirkstone` / `kirkstone-next` branches instead, contact support for updates.
* MBaULxL (SBC with TQMa6ULxL): Use `kirkstone` / `kirkstone-next` branches instead, contact support for updates.

### Support for wic

All machines in meta-tq are preconfigured to generate bootable SD/eMMC images
using wic. Config files are located in wic directory.

To generate images in different formats or disable wic image generation,
`IMAGE_FSTYPES` can be set in a custom machine configuration, distro, or in
`local.conf` (in `local.conf`, an override like `IMAGE_FSTYPES:forcevariable`
is needed for the setting to take precedence).

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
* [TQMA7D](doc/README.TQMa7x.md)
* [TQMa8Mx](doc/README.TQMa8Mx.md)
* [TQMa8MxML](doc/README.TQMa8MxML.md)
* [TQMa8MxNL](doc/README.TQMa8MxNL.md)
* [TQMa8MPxL](doc/README.TQMa8MPxL.md)
* [TQMa8MPxS](doc/README.TQMa8MPxS.md)
* [TQMa8XDP](doc/README.TQMa8Xx.md)
* [TQMa8XDP4](doc/README.TQMa8Xx.md)
* [TQMa8XQP](doc/README.TQMa8Xx.md)
* [TQMa8XQP4](doc/README.TQMa8Xx.md)
* [TQMa8XDPS](doc/README.TQMa8XxS.md)
* [TQMa8XQPS](doc/README.TQMa8XxS.md)
* [TQMa8QM](doc/README.TQMa8x.md)
* [TQMa91\[3,2\]1CA](doc/README.TQMa91xx.md)
* [TQMa91\[3,2\]1LA](doc/README.TQMa91xx.md)
* [TQMa93\[3,5\]\[1,2\]CA](doc/README.TQMa93xx.md)
* [TQMa93\[3,5\]\[1,2\]LA](doc/README.TQMa93xx.md)
* [TQMa95xxSA](doc/README.TQMa95xxSA.md)
* [TQMA335x\[L\]](doc/README.TQMa335x.md)
* [TQMA57xx](doc/README.TQMa57xx.md)
* [TQMA62xx](doc/README.TQMa62xx.md)
* [TQMA64xxL](doc/README.TQMa64xxL.md)
* [TQMA67xx](doc/README.TQMa67xx.md)
* [TQMLS1012AL](doc/README.TQMLS1012AL.md)
* [TQMLS102\[0,1\]A](doc/README.TQMLS102xA.md)
* [TQMLS1017A/TQMLS1028A](doc/README.TQMLS1028A.md)
* [TQMLS1043/TQMLS1046A/TQMLS1088A](doc/README.TQMLS10xxA.md)
* [TQMLX2160A](doc/README.TQMLX2160A.md)
