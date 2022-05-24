# TQMLS1012AL on MBLS1012AL carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMLS1012AL: module
* MBLS1012AL:  board

### Known issues

- none

## Version information for software components

## U-Boot:

* based on uboot-imx (https://source.codeaurora.org/external/qoriq/qoriq-components/u-boot/)
* branched from lf-5.15.5-1.0.0

## ATF:

* based on imx-atf (https://source.codeaurora.org/external/qoriq/qoriq-components/atf/)
* branched from lf-5.15.5-1.0.0

## Linux:

* based on linux-imx-fslc (https://source.codeaurora.org/external/qoriq/qoriq-components/linux/)
* branched from lf-5.15.5-1.0.0

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`
* `atf/`
  * 512MiB
    * `bl2_qspi.pbl` Primary Boot Loader with RCW
    * `fip_uboot.bin` U-Boot
  * 1GiB
    * `bl2_qspi_tqmls1012al_1gb.pbl` Primary Boot Loader with RCW
    * `fip_uboot_tqmls1012al_1gb.bin` U-Boot
* `engine-pfe-bin/pfe_fw_sbl.itb` PFE engine firmware file
* `rcw/`: different rcw configurations to use with atf-recipe
* `fsl-ls1012a-mbls1012al-tqmls1012al-mbls1012al.dtb`: device tree blob
* `Image.gz`: Linux kernel image
* `u-boot-tfa-2021.04-r0.bin` U-Boot binary
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

## HowTo:

### MBLS1012AL DIP Switch settings for Boot

```
DIP S1  ON                              OFF
1       Hard-coded RCW enabled          Hard-coded RCW disabled
2       Short RESET_REQ# and RESET#     Isolate RESET_REQ# from RESET#
3       Debug-UART on USB / pin header  Debug-UART on OpenSDA
4       CPU-JTAG disabled               CPU-JTAG enabled
```

### U-Boot mtest

A lot of RAM is reserved for the BL31 or is used by U-Boot itself,  
so we can only test the lower 368 MiB at 512 MiB of RAM  
or the lower 880 MiB at 1GiB RAM.

U-Boot mtest is configured for 512 MiB RAM.

For 1 GB RAM start mtest with parameters

`mtest 0x80000000 0xB7000000`

### RCW

RCW is for SerDes Protocol 0x3508  
3G configuration with 1 SGMII + 1 RGMII + 1 PCIe + 1 SATA

The default core frequency is 1 GHz.

To use RCW with a core frequency of 800 MHz, set in local.conf:
`RCWQSPI = "default/rcw_800_sd"`

## Support Wiki

See [TQ Embedded Wiki for TQMLS1012AL](https://support.tq-group.com/en/layerscape/tqmls1012al)
