# Version information for software components

[[_TOC_]]

## TQMa6x / TQMa6UL[L]x[L] / TQMa7x

### U-Boot:

#### U-Boot based on v2023.04 (forked from mainline U-Boot)

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2023.04 (Branch master)

**Attention**: This U-Boot version must not be used with Linux kernel versions
before v5.9. for SOM hardware revisions before 040x. Due to changes in device
tree node naming DSR settings can not be applied to eMMC. This may lead to
lifetime degradation.

### Linux:

#### Kernel based on 6.6-rt (linux-stable-rt)

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* branched from `v6.6-rt` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq-rt_6.6.bb` for exact release)

#### Kernel based on 6.6 (linux-stable)

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git)
* branched from `linux-6.6.y` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq_6.6.bb` for exact release)

## TQMLS1012AL

### U-Boot

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf-5.15.5-1.0.0

### ATF

* based on imx-atf (https://github.com/nxp-qoriq/atf/)
* branched from lf-5.15.5-1.0.0

### Kernel based on 6.6 (linux-imx-fslc)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  contains commits up to 237948c6e22c90b64818531871f1f28611eb363e and
  stable tags up to 6.6.84

## TQMLS1028A

### U-Boot

* based on qoriq-u-boot (https://github.com/nxp-qoriq/u-boot/)
* branched from LSDK-20.04-update-290520

### ATF

* based on qoriq-atf (https://github.com/nxp-qoriq/atf/)
* branched from lf-5.15.5-1.0.0

### Kernel based on 6.6 (linux-imx-fslc)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  contains commits up to 237948c6e22c90b64818531871f1f28611eb363e and
  stable tags up to 6.6.84

## TQMLS102xA

### Kernel based on 5.4 (linux-stable)

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `5.4.y` / tag commit is merged into (see `LINUX_VERSION` in linux-tq-5.4.bb for exact release)

### U-Boot based on v2027.11

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2017.11 (Branch master)

## TQMa8 / TQMa9 series

### U-Boot (TQMa8 series except TQMa8MPxL)

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from imx-5.4.70-2.3.0 + changes from imx-5.4.70-2.3.2

### U-Boot (TQMa8MPxL and TQMa93/TQMa91 series)

* U-Boot based on v2024.04 and NXP vendor BSP
  [`uboot-imx`](https://github.com/nxp-imx/uboot-imx)
* branched from lf_v2024.04  / integrates all commits up to
  6c4545203d123c246c5d7995f2893959506d28e0 / tag lf-6.6.52-2.2.0)

### ATF / TF-A

* TF-A based on v2.10 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.10 based branch lf_v2.10 / integrates all commits up to
  1b27ee3edbb40ef9432c69ccaa744d1ac5d54c5d / tag lf-6.6.52-2.2.0

### SCU / SCFW and SECO

__Note__: only for TQMa8X* and TQMa8QM

* SCU firmware based on NXP SCFW porting kit v1.15.0
* TQ-Systems version TQMa8.NXP-v1.15.0.B5624.0037 (source code on request via
  Support@tq-group.com)
* SECO firmware from NXP binary package 5.9.0 (imx-seco-5.9.0.bin)

### Linux

#### Kernel based on 6.6 (linux-imx-fslc)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  contains commits up to 237948c6e22c90b64818531871f1f28611eb363e and
  stable tags up to 6.6.84

#### Kernel 6.12 stable

__Note__: supports TQMa8MPxL / TQMa93xx

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* device tree adjustments for TQ-Systems starter kits
* Supports Preempt-RT

#### Kernel 6.6 stable

__Note__: supports TQMa8MQ/MxML/MxNL

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation

#### Kernel 6.6 stable-rt

__Note__: supports only TQMa8MQ/MxML/MxNL/

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
