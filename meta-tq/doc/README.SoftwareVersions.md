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

## TQMLS1012AL / TQMLS102xA / TQMLS10xxA / TQMLX2160A

### TF-A (ATF)

* Aarch64 platforms
* TF-A based on v2.10 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.10 based branch lf_v2.10 / integrates all commits up to
  7e374c5f57328949a2b141a567175b6a2939e964 / tag lf-6.6.52-2.2.1

### U-Boot

#### TQMLS1012AL

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf-5.15.5-1.0.0

#### TQMLS102xA

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2017.11 (Branch master)

#### TQMLS1028A

* based on qoriq-u-boot (https://github.com/nxp-qoriq/u-boot/)
* branched from LSDK-20.04-update-290520

#### TQMLS10xxA

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf_v2022.04

### Linux

#### TQMLS1012AL / TQMLS1028A

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)

#### TQMLS102xA / TQMLS10xxA / TQMLX2160A

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `6.12.y`
  (see `LINUX_VERSION` in `linux-tq_6.6.bb` for exact release)
* Supports Preempt-RT for all but TQMLS102xA

## TQMa8 / TQMa9 series

### SCU / SCFW and SECO

__Note__: only for TQMa8X* and TQMa8QM

* SCU firmware based on NXP SCFW porting kit v1.15.0
* TQ-Systems version TQMa8.NXP-v1.15.0.B5624.0037 (source code on request via
  Support@tq-group.com)
* SECO firmware from NXP binary package 5.9.4 (imx-seco-5.9.4.bin)

### ATF / TF-A

* TF-A based on v2.10 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.10 based branch lf_v2.10 / integrates all commits up to
  7e374c5f57328949a2b141a567175b6a2939e964 / tag lf-6.6.52-2.2.1

### U-Boot

#### TQMa8 series except TQMa8MPxL

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from imx-5.4.70-2.3.0 + changes from imx-5.4.70-2.3.2

#### TQMa8MPxL / TQMa8MPxS / TQMa93 / TQMa91 series

* U-Boot based on v2024.04 and NXP vendor BSP
  [`uboot-imx`](https://github.com/nxp-imx/uboot-imx)
* branched from lf_v2024.04  / integrates all commits up to
  6c4545203d123c246c5d7995f2893959506d28e0 / tag lf-6.6.52-2.2.0)

### Linux

#### Kernel based on 6.6 (linux-imx-fslc)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)

#### Kernel 6.12 stable

__Note__: supports TQMa8MPxL / TQMa8MPxS / TQMa93xx

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* device tree adjustments for TQ-Systems starter kits
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)
* Supports Preempt-RT

#### Kernel 6.6 stable

__Note__: supports TQMa8MQ/MxML/MxNL

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)

#### Kernel 6.6 stable-rt

__Note__: supports only TQMa8MQ/MxML/MxNL/

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)
