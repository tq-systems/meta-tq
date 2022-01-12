# OpenEmbedded/Yocto hardware support layer for TQ Systems ARM SOM

**Attention:** This branch should only be used for TQMaRZG2x machines. For all
other machines use current yocto versions.

This README file contains information on the contents of the meta-tq layer.
This layer provides support for TQ Systems CPU modules and Starterkits with
ARM CPU.

Please see the corresponding sections below for details.

[[_TOC_]]

## Overview

### Dependencies

This layer in the checked out branch depends on:

  URI: git://git.yoctoproject.org/poky
  branch: rocko
  layers: meta
  revision: HEAD

optionally the layer can make use of features from meta-freescale and meta-rzg2

  URI: https://github.com/Freescale/meta-freescale.git
  layers: meta-freeescale
  branch: rocko

  URI: https://github.com/renesas-rz/meta-rzg2.git
  layers: meta-rzg2
  branch: rocko

**Attention:** meta-rzg2 is mandatory for the following machines:

- tqmarzg2h_c-mbarzg2x
- tqmarzg2m_aa-mbarzg2x
- tqmarzg2m_e-mbarzg2x
- tqmarzg2n_b-mbarzg2x

### Coding style

It is recommended to use the [Format_Guidelines](https://www.openembedded.org/wiki/Styleguide#Format_Guidelines)
from openembedded.

### Patches

Please submit patches against the meta-tq layer to the
maintainer:

Maintainer: Markus Niebel <Markus.Niebel@tq-group.com>

Additionally you can use github's collaboration features.

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

The layer can be used together with the Freescale / NXP layers if needed and
if boards with i.MX CPU are used. Therefore the `MACHINEOVERRIDE` variable is
prepared inside the <modulename>.conf files. See following example:

```
BBLAYERS = "\
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
BBLAYERS = "\
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  \
  ${BSPDIR}/sources/meta-ti \
  \
  ${BSPDIR}/sources/meta-tq \
"
```

The layer can be used together with the Renesas meta-rzg2 layer if needed and
rzg2x based boards are used. Therefore the `MACHINEOVERRIDE` variable is
prepared inside the <modulename>.conf files. See following example:

```
QT_CHECK = "${@os.path.isdir("${BSPDIR}/sources/meta-qt5")}"
VIRTUALIZATION_CHECK = "${@os.path.isdir("${BSPDIR}/sources/meta-virtualization")}"

BBFILES ?= ""
BBLAYERS = " \
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  ${BSPDIR}/sources/meta-tq \
  ${BSPDIR}/sources/meta-dumpling \
  ${BSPDIR}/sources/meta-gplv2 \
  ${BSPDIR}/sources/poky/meta-yocto-bsp \
  ${BSPDIR}/sources/meta-rzg2 \
  ${BSPDIR}/sources/meta-linaro/meta-optee \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  ${BSPDIR}/sources/meta-openembedded/meta-python \
  ${@'${BSPDIR}/sources/meta-qt5' if '${QT_CHECK}' == 'True' else ''} \
  ${@'${BSPDIR}/sources/meta-openembedded/meta-filesystems' if '${VIRTUALIZATION_CHECK}' == 'True' else ''} \
  ${@'${BSPDIR}/sources/meta-openembedded/meta-networking' if '${VIRTUALIZATION_CHECK}' == 'True' else ''} \
  ${@'${BSPDIR}/sources/meta-openembedded/meta-python' if '${VIRTUALIZATION_CHECK}' == 'True' else ''} \
  ${@'${BSPDIR}/sources/meta-virtualization' if '${VIRTUALIZATION_CHECK}' == 'True' else ''} \
"
```

### Supported machines

Support for the following machines is contained in this version:


| Status | SOC / CPU    | SOM               | Base board | yocto / OpenEmbedded machine | hardware revision                                       |
| ------ | ------------ | ----------------- | ---------- | ---------------------------- | ------------------------------------------------------- |
| [b]    | i.MX6[QP,DP] | TQMa6QP/TQMa6DP   | MBa6x      | tqma6qp-mba6x                | (TQMa6[QP,DP] HW REV.040x / MBa6x HW REV.020x)          |
| [b]    | i.MX6[Q,D]   | TQMa6D/TQMa6Q     | MBa6x      | tqma6q-mba6x                 | (TQMa6[D,Q] HW REV.010x ... 040x / MBa6x HW REV.020x)   |
| [b]    | i.MX6[Q,D]   | TQMa6D/TQMa6Q     | NAV        | tqma6q-nav                   | (TQMa6[D,Q] HW REV.030x ... 040x / NAV REV.020x)        |
| [b]    | i.MX6DL      | TQMa6DL           | MBa6x      | tqma6dl-mba6x                | (TQMa6DL HW REV.010x ... 040x / MBa6x HW REV.020x)      |
| [b]    | i.MX6S       | TQMa6S            | MBa6x      | tqma6s-mba6x                 | (TQMa6S HW REV.010x ... 040x / MBa6x HW REV.020x)       |
| [b]    | i.MX6UL      | TQMa6ULx          | MBaULx     | tqma6ulx-mba6ulx             | (TQMa6UL HW REV.030x / MBa6ULx HW REV.020x)             |
| [b]    | i.MX6UL      | TQMa6ULxL         | MBaULx     | tqma6ulx-lga-mba6ulx         | (TQMa6ULxL HW REV.020x / MBa6ULx HW REV.020x)           |
| [b]    | i.MX6UL      | TQMa6ULxL         | MBaULxL    | tqma6ulx-lga-mba6ulxl        | (TQMa6ULxL HW REV.020x / MBa6ULxL HW REV.020x)          |
| [b]    | i.MX6ULL     | TQMa6ULLx         | MBaULx     | tqma6ullx-mba6ulx            | (TQMa6ULLx HW REV.030x / MBa6ULx HW REV.020x)           |
| [b]    | i.MX7[S,D]   | TQMa7<S,D>        | MBa7x      | tqma7x-mba7                  | (TQMa7[S,D] HW REV.010x ... 0x020x / MBa7x HW REV.020x) |
| [b]    | LS102[0,1]a  | TQMLS102[0,1]a>   | MBLS102xa  | tqmls102xa-mbls102xa         | (TQMLS102[0,1]a HW REV.020x / MBLS102xa HW REV.020x)    |
| [b]    | LS10[46]a    | TQMLS102[46]a>    | MBLS10xxa  | tqmls1046a-mbls1046a         | (TQMLS1046a HW REV.020x / MBLS102xa HW REV.020x)        |
| [b]    | AM571[6,8]   | TQMa5718/TQMa5716 | MBa57xx    | tqma571x-mba57xx             |                                                         |
| [b]    | AM572[6,8]   | TQMa5728/TQMa5726 | MBa57xx    | tqma572x-mba57xx             |                                                         |
| [b]    | AM574[6,8]   | TQMa5748/TQMa5746 | MBa57xx    | tqma574x-mba57xx             |                                                         |
| [b]    | RZG/2H       | TQMaRZG2H         | MBaRZG2x   | tqmarzg2h_c-mbarzg2x         | (TQMaRZG2H-PROTO1 HW REV.02xx with 4 GiB RAM)           |
| [b]    | RZG/2M       | TQMaRZG2M         | MBaRZG2x   | tqmarzg2m_e-mbarzg2x         | (TQMaRZG2M-PROTO1 HW REV.02xx with 8 GiB RAM)           |
| [y]    | RZG/2M       | TQMaRZG2M         | MBaRZG2x   | tqmarzg2m_aa-mbarzg2x        | (TQMaRZG2M-AA HW REV.02xx with 2GiB RAM)                |
| [b]    | RZG/2N       | TQMaRZG2N         | MBaRZG2x   | tqmarzg2n_b-mbarzg2x         | (TQMaRZG2N-PROTO1 HW REV.02xx with 2GiB RAM)            |


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

[TQMaRZG2\[H,M,N\]](doc/README.TQMaRZG2x.md)  
