# Version information for software components

[[_TOC_]]

## U-Boot

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from imx-5.4.70-2.3.0 + changes from imx-5.4.70-2.3.2

## ATF / TF-A

* based on imx-atf (https://github.com/nxp-imx/imx-atf)
* branched from v2.6 based branch lf_v2.6 / integrates all commits up to
  3c1583ba0 ("Merge remote-tracking branch 'origin/imx_v2.6' into lf_v2.6")

## SCU / SCFW and SECO

__Note__: not for TQMa8M*

* SCU firmware based on NXP SCFW porting kit v1.15.0
* TQ-Systems version TQMa8.NXP-v1.15.0.B5624.0037 (source code on request via
  Support@tq-group.com)
* SECO firmware from NXP binary package 5.9.0 (imx-seco-5.9.0.bin)

## Linux

### Kernel based on 5.15

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `5.15-2.0.x-imx` / contains commits up to d818413e4d7901cb8a00a631a389326e2c93ae41

### Kernel based on 6.1

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `6.1-2.0.x-imx` / contains commits up to b872b1170fc8843b55e9f8838dd373ff43bb7552

### Kernel 6.1 stable

__Note__: supports only TQMa8M*

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation

### Kernel 6.1 stable-rt

__Note__: supports only TQMa8M*

* based on linux-stable-rt (https://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git)
* defconfig changes for TQ-Systems starter kits and build time optimisation
* defconfig not ready for production use
