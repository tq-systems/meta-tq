# Version information for software components

[[_TOC_]]

## TQMa6x / TQMa6UL[L]x[L] / TQMa7x

### U-Boot:

* based on uboot-imx (https://github.com/nxp-imx/uboot-imx)
* branched from rel_imx_4.1.15_2.0.0_ga (Branch imx_v2016.03_4.1.15_2.0.0_ga)

### Linux:

#### Kernel based on 5.15 (linux-stable)

* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `linux-5.15.y` / tag commit is merged into (see `LINUX_VERSION` in `linux-tq-5.15.bb` for exact release)

#### Kernel based on 5.15 (linux-imx-fslc)

_Note:_ Not supported for:

- TQMa6ULx / TQMa6ULxL: use [5.15 linux-stable](#kernel-based-on-515-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [5.15 linux-stable](#kernel-based-on-515-linux-stable)
- TQMa7x: use [5.15 linux-stable](#kernel-based-on-515-linux-stable)

##### i.MX SOM

* based on [5.15-2.0.x-imx](https://github.com/Freescale/linux-fslc/tree/5.15-2.0.x-imx)
* branched from `TQMa8-fslc-5.15-2.0.x-imx` / contains commits up to d818413e4d7901cb8a00a631a389326e2c93ae41

##### TQMLS1012AL and TQMLS1028A

* based on [lf-5.15.y](https://github.com/nxp-imx/linux-imx/tree/lf-5.15.y)
* branched from lf-5.15.5-1.0.0 / contains commits up to c1084c2773fc1005ed140db625399d5334d94a28

#### Kernel based on 5.10 (linux-imx-fslc)

_Note:_ Not supported for:

- TQMa6ULx / TQMa6ULxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa7x: use [5.15](#kernel-based-on-515-linux-stable)

* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `TQMa8-fslc-5.10-2.1.x-imx` / contains commits up to 3485dced8f9c272e44b70c2df74ed58ae8bacac7

#### Kernel based on 5.4 (linux-imx-fslc)

_Note:_ Not recommended for usage with

- TQMa6x: use [5.10](#kernel-based-on-510-linux-imx-fslc)
- TQMa6ULx / TQMa6ULxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa7x: use [5.15](#kernel-based-on-515-linux-stable)
- TQMLS1012AL: use [5.15](#kernel-based-on-515-linux-imx-fslc)

* not selected by default
* based on linux-imx-fslc (https://github.com/Freescale/linux-fslc.git)
* branched from `5.4-1.0.0-imx` / contains commits up to 067b7d3c4d293e901aac09f0f769744be73a7fb2

#### Kernel based on 5.4 (linux-stable)

_Note:_ Not supported for:

- TQMa6x: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa6ULx / TQMa6ULxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa6ULLx / TQMa6ULLxL: use [5.15](#kernel-based-on-515-linux-stable)
- TQMa7x: use [5.15](#kernel-based-on-515-linux-stable)

* only selected by default for TQMLS102xA
* based on linux-stable (https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git)
* branched from `5.4.y` / tag commit is merged into (see `LINUX_VERSION` in linux-tq-5.4.bb for exact release)

## TQMa8 series

See [here](./README.TQMa8.SoftwareVersions.md) for the software base versions.
