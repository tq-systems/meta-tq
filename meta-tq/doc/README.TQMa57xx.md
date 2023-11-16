# TQMa57xx on MBa57xx carrier board

[[_TOC_]]

This README page is currently a Work in Progress.

## Overview

### Supported Hardware:

* TQMa57xx: Module revisions REV.010x
* MBa57xx: Board revisions REV.020x

### Versions

_Bootloader:_

* uboot-tq-2019.04

_Kernel:_

* linux-ti-tq-5.10 (default, based on ti-rt-linux-5.10.y)
* linux-ti-tq-5.4 (based on ti-rt-linux-5.4.y)

### Known issues

* The current default kernel version 5.10 does not include display support. Add
  the following settings to local.conf to select the older kernel 5.4, where
  display support was available:

    PREFERRED_VERSION_linux-ti-tq = "5.4%"
    TI_SGX_DDK_KM_KERNVER = "5.4"


## Support Wiki

See [TQ Embedded Wiki for TQMa57xx](https://support.tq-group.com/en/arm/tqma57xx)

## Artifacts

Artifacts can be found at:
`deploy-ti/images/${MACHINE}`

* \*.dtb: Device Tree blobs
* zImage: Compressed Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* MLO: first-stage bootloader (SPL)
* u-boot.img: full bootloader
