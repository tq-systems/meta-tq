# Version Information for Software Components

[[_TOC_]]

## SOM with i.MX ARMv7-A CPU

### Supported SOM Families

* TQMa6x
* TQMa6ULx
* TQMa6ULxL
* TQMa6ULLx
* TQMa6ULLxL
* TQMa7x

### U-Boot

#### u-boot-tq 2023.04

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2023.04 (Branch master)

**Attention**: This U-Boot version must not be used with Linux kernel versions
before v5.9. on TQMa6x SOM hardware revisions before 040x. Due to changes in device
tree node naming DSR settings can not be applied to eMMC. This may lead to
lifetime degradation.

### Linux

#### Kernel 6.6-rt (linux-stable-rt)

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes and patches for TQ-Systems starter kits and build time optimisation
* branched from `v6.6-rt` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq-rt_6.6.bb` for exact release)

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

#### Kernel 6.6 (linux-stable)

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/next/linux-next.git)
* defconfig changes and patches for TQ-Systems starter kits and build time optimisation
* branched from `linux-6.6.y` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq_6.6.bb` for exact release)

## SOM with Layerscape CPU

### Supported SOM Families

* TQMLS1012AL
* TQMLS102xA
* TQMLS1028A (TQMLS1017A, TQMLS1018A, TQMLS1027A)
* TQMLS1043A
* TQMLS1046A
* TQMLS1088A
* TQMLX2160A

### TF-A (ATF)

Supported SOM families:

* TQMLS1012AL
* TQMLS1028A
* TQMLS1043A
* TQMLS1046A
* TQMLS1088A
* TQMLX2160A

Version information:

* TF-A based on v2.10 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.10 based branch lf_v2.10 / integrates all commits up to
  7e374c5f57328949a2b141a567175b6a2939e964 / tag lf-6.6.52-2.2.1

### U-Boot

#### u-boot-lsdk-tq 2021.04

Supported SOM families:

* TQMLS1012AL

Version information:

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf-5.15.5-1.0.0

#### u-boot-tq 2017.11

Supported SOM families:

* TQMLS102xA

Version information:

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2017.11 (Branch master)

#### u-boot-lsdk-tq 2019.10

Supported SOM families:

* TQMLS1028A

Version information:

* based on qoriq-u-boot (https://github.com/nxp-qoriq/u-boot/)
* branched from LSDK-20.04-update-290520

#### u-boot-lsdk-tq 2022.04

Supported SOM families:

* TQMLS1043A
* TQMLS1046A
* TQMLS1088A

Version information:

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf_v2022.04

#### u-boot-lsdk-tq 2019.04

Supported SOM families:

* TQMLX2160A

Version information:

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from tag lx2160a-early-access-bsp0.7

### Linux

#### Kernel 6.6 (linux-imx-fslc)

Supported SOM families:

* TQMLS1012AL
* TQMLS1028A

Version information:

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* defconfig changes and patches for TQ-Systems starter kits and build time optimisation
* branched from `6.6-2.2.x-imx`
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)

#### Kernel 6.12 (stable)

Supported SOM families:

* TQMLS102xA
* TQMLS1043A
* TQMLS1046A
* TQMLS1088A
* TQMLX2160A

Version information:

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes and patches for TQ-Systems starter kits and build time optimisation
* branched from `6.12.y`
  (see `LINUX_VERSION` in `linux-tq_6.12.bb` for exact release)

#### Kernel 6.12 (stable-rt)

Supported SOM families:

* TQMLS102xA
* TQMLS1043A
* TQMLS1046A
* TQMLS1088A
* TQMLX2160A

Version information:

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes and patches for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
  (see `LINUX_VERSION` in `linux-rt-tq_6.12.bb` for exact release)

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

## SOM with i.MX ARMv8-A CPU

### Supported SOM Families

* TQMa8Mx
* TQMa8MxML
* TQMa8MxNL
* TQMa8MPxL / TQMa8MPxS
* TQMa8QM
* TQMa8Xx / TQMa8XxS
* TQMa91xxCA / TQMa91xxLA
* TQMa93xxCA / TQMa93xxLA

### SCU / SCFW and SECO

Supported SOM families:

* TQMa8QM
* TQMa8Xx / TQMa8XxS

Version information:

* SCU firmware based on NXP SCFW porting kit v1.15.0
* TQ-Systems version TQMa8.NXP-v1.15.0.B5624.0037 (source code on request via
  Support@tq-group.com)
* SECO firmware from NXP binary package 5.9.4 (imx-seco-5.9.4.bin)

### ATF (TF-A)

Supported SOM families

* TQMa8Mx
* TQMa8MxML
* TQMa8MxNL
* TQMa8MPxL / TQMa8MPxS
* TQMa8QM
* TQMa8Xx / TQMa8XxS
* TQMa91xxCA / TQMa91xxLA
* TQMa93xxCA / TQMa93xxLA

Version information:

* TF-A based on v2.10 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.10 based branch lf_v2.10 / integrates all commits up to
  7e374c5f57328949a2b141a567175b6a2939e964 / tag lf-6.6.52-2.2.1

### U-Boot

#### u-boot-imx-tq 2020.04

Supported SOM families

* TQMa8Mx
* TQMa8MxML
* TQMa8MxNL
* TQMa8QM
* TQMa8Xx / TQMa8XxS

Version information:

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from imx-5.4.70-2.3.0 + changes from imx-5.4.70-2.3.2

#### u-boot-imx-tq 2024.04

Supported SOM families

* TQMa8MPxL / TQMa8MPxS
* TQMa91xxCA / TQMa91xxLA
* TQMa93xxCA / TQMa93xxLA

Version information:

* U-Boot based on v2024.04 and NXP vendor BSP
  [`uboot-imx`](https://github.com/nxp-imx/uboot-imx)
* branched from lf_v2024.04  / integrates all commits up to
  6c4545203d123c246c5d7995f2893959506d28e0 / tag lf-6.6.52-2.2.0)

#### u-boot-imx-tq 2025.04

Supported SOM families

* TQMa95xxLA
* TQMa95xxSA

Version information:

* U-Boot based on v2025.04 and NXP vendor BSP
  [`uboot-imx`](https://github.com/nxp-imx/uboot-imx)
* branched from lf_v2025.04

### Linux

#### Kernel 6.6 (linux-imx-fslc)

Supported SOM families

* TQMa8Mx
* TQMa8MxML
* TQMa8MxNL
* TQMa8MPxL / TQMa8MPxS
* TQMa8QM
* TQMa8Xx / TQMa8XxS
* TQMa91xxCA / TQMa91xxLA
* TQMa93xxCA / TQMa93xxLA

Version information:

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.6-2.2.x-imx`
  (see `LINUX_VERSION` in `linux-imx-tq_6.6.bb` for exact release)

#### Kernel 6.12 (linux-imx-fslc)

Supported SOM families

* TQMa95xxLA
* TQMa95xxSA

Version information:

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.12-2.0.x-imx`
  (see `LINUX_VERSION` in `linux-imx-tq_6.12.bb` for exact release)

#### Kernel 6.12 stable

Supported SOM families

* TQMa8MPxL
* TQMa8MPxS
* TQMa93xxCA / TQMa93xxLA

Version information:

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* device tree adjustments for TQ-Systems starter kits
  (see `LINUX_VERSION` in `linux-tq_6.12.bb` for exact release)
* Supports Preempt-RT

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

#### Kernel 6.12 (stable-rt)

Supported SOM families

* TQMa8MPxL
* TQMa8MPxS
* TQMa93xxCA / TQMa93xxLA

Version information:

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
  (see `LINUX_VERSION` in `linux-rt-tq_6.12.bb` for exact release)

#### Kernel 6.6 (stable)

Supported SOM families

* TQMa8MQ
* TQMa8MxML
* TQMa8MxNL

Version information:

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
  (see `LINUX_VERSION` in `linux-tq_6.6.bb` for exact release)

#### Kernel 6.6 (stable-rt)

Supported SOM families

* TQMa8MQ
* TQMa8MxML
* TQMa8MxNL

Version information:

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
  (see `LINUX_VERSION` in `linux-rt-tq_6.6.bb` for exact release)
