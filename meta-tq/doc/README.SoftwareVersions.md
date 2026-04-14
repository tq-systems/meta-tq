# Version information for software components

[[_TOC_]]

## TQMa6x / TQMa6UL[L]x[L] / TQMa7x

### U-Boot:

#### U-Boot based on v2016.03 (uboot-imx, not TQMa6x)

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from rel_imx_4.1.15_2.0.0_ga (Branch imx_v2016.03_4.1.15_2.0.0_ga)

#### U-Boot based on v2023.04 (forked from mainline U-Boot, TQMa6x only)

* based on U-Boot (https://github.com/u-boot/u-boot)
* branched from v2023.04 (Branch master)

**Attention**: This U-Boot version must not be used with Linux kernel versions
before v5.9. for SOM hardware revisions before 040x. Due to changes in device
tree node naming DSR settings can not be applied to eMMC. This may lead to
lifetime degradation.

### Linux:

#### Kernel based on 6.1 (linux-stable)

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `linux-6.1.y` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq_6.1.bb` for exact release)

#### Kernel based on 6.1-rt (linux-stable-rt)

For usage see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md)

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* branched from `v6.1-rt` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq-rt_6.1.bb` for exact release)

#### Kernel based on 5.15 (linux-imx-fslc)

_Note:_ Not supported for:

- TQMa6ULx / TQMa6ULxL: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)
- TQMa7x: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)

##### i.MX SOM

* based on [5.15-2.0.x-imx](https://github.com/Freescale/linux-fslc/tree/5.15-2.0.x-imx)
* branched from `5.15-2.0.x-imx` / contains commits up to d818413e4d7901cb8a00a631a389326e2c93ae41

##### TQMLS1012AL and TQMLS1028A

* based on [lf-5.15.y](https://github.com/nxp-imx/linux-imx/tree/lf-5.15.y)
* branched from lf-5.15.5-1.0.0 / contains commits up to c1084c2773fc1005ed140db625399d5334d94a28

#### Kernel based on 5.4 (linux-stable)

_Note:_ Not supported for:

- TQMa6x: use use [6.1 linux-stable](#kernel-based-on-61-linux-stable)
- TQMa6ULx / TQMa6ULxL: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)
- TQMa7x: use [6.1 linux-stable](#kernel-based-on-61-linux-stable)

* only selected by default for TQMLS102xA
* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `5.4.y` / tag commit is merged into (see `LINUX_VERSION` in linux-tq-5.4.bb for exact release)

## TQMa8 / TQMa93 series

### U-Boot (TQMa8 series)

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from imx-5.4.70-2.3.0 + changes from imx-5.4.70-2.3.2

### U-Boot (TQMa93 series)

* U-Boot based on v2023.04 and NXP vendor BSP
  [`uboot-imx`](https://github.com/nxp-imx/uboot-imx)
* branched from lf_v2023.04 (changes up to lf-6.6.3-1.0.0)

## ATF / TF-A

* TF-A based on v2.8 and NXP vendor BSP
  (https://github.com/nxp-imx/imx-atf.git),
* branched from v2.8 based branch lf_v2.8 / integrates all commits up to
  8dbe28631802a51b3ec8179b2c5635b00393ad97 / tag lf-6.6.3-1.0.0

## SCU / SCFW and SECO

__Note__: only for TQMa8X* and TQMa8QM

* SCU firmware based on NXP SCFW porting kit v1.15.0
* TQ-Systems version TQMa8.NXP-v1.15.0.B5624.0037 (source code on request via
  Support@tq-group.com)
* SECO firmware from NXP binary package 5.9.0 (imx-seco-5.9.0.bin)

## Linux

### Kernel based on 6.1 (linux-imx-fslc)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.1-2.0.x-imx` and merged in changes from branch `6.1-2.2.x-imx` /
  contains commits up to 2bfda7392e6621dd9060f87d7f9d601bb1906dbf

### Kernel 6.1 stable

__Note__: supports only TQMa8M*

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation

### Kernel 6.1 stable-rt

__Note__: supports only TQMa8M*

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use

### Kernel based on 5.15 (linux-imx-fslc)

* not recommended, use kernel based on 6.1 (linux-imx-fslc)
* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `5.15-2.0.x-imx` / contains commits up to d818413e4d7901cb8a00a631a389326e2c93ae41
