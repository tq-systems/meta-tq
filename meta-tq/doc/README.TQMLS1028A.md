# TQMLS1017A / TQMLS1028A

[[_TOC_]]

## Variants
* TQMLS1028A / TQMLS1017A SOM REV.020x/030x with 1GiB / 4GiB RAM
* MBLS1028A / MBLS1028A-IND carrier board

## Version information for software components

### U-Boot

* based on qoriq-u-boot (https://source.codeaurora.org/external/qoriq/qoriq-components/u-boot/)
* branched from LSDK-20.04-update-290520

### ATF

* based on qoriq-atf (https://source.codeaurora.org/external/qoriq/qoriq-components/atf/)
* branched from lf-5.15.5-1.0.0

### Linux

* based on linux-qoriq (https://source.codeaurora.org/external/qoriq/qoriq-components/linux/)
* branched from lf-5.15.5-1.0.0

## Kernel variants

By default, the kernel recipe linux-imx-tq (based on qoriq kernel
5.15.5) is built. An alternative kernel based on LSDK-20.12-V5.4-RT
can be selected for PREEMPT_RT support by adding the following to
local.conf or a custom DISTRO config:

    PREFERRED_PROVIDER_virtual/kernel = "linux-rt-lsdk-tq"

Please note that the default linux-imx-tq kernel has received more thorough
testing and it is therefore recommended for most usecases.

## Supported Features

### U-Boot

| Feature                                          |              |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |   1,4 GiB    |
| CPU variants                                     |LS1017/LS1028A|
| GPIO                                             |      x       |
| I2C                                              |      x       |
| **QSPI**                                         |              |
| Read                                             |      x       |
| Write                                            |      x       |
| Boot                                             |      x       |
| **e-MMC / SD-Card**                              |              |
| Read                                             |      x       |
| Write                                            |      x       |
| Boot                                             |      x       |
| **USB**                                          |              |
| USB 3.0                                          |      x       |
| **ENET**                                         |              |
| ENET 0                                           |      x       |
| ENET 1                                           |      x       |
| ENET Switch                                      |      x       |
| **Bootstreams**                                  |              |
| FlexSPI                                          |      x       |
| SD / e-MMC                                       |      x       |

### Linux

| Feature                                          |              |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |    1,4 GiB   |
| CPU variants                                     |LS1017/LS1028A|
| speed grade / temperature grade detection        |       x      |
| **UART**                                         |              |
| console on UART1 (X19)                           |       x      |
| additional UART2 on pin heads (X38)              |       x      |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| **I2C**                                          |              |
| Temperature Sensors                              |       x      |
| RTC                                              |       x      |
| EEPROMS                                          |       x      |
| **ENET**                                         |              |
| ENET 0                                           |       x      |
| ENET 1                                           |       x      |
| ENET Switch                                      |       x      |
| **USB**                                          |              |
| USB 3.0 Hub                                      |       x      |
| USB 2.0 Dual Role (X5)                           |       x      |
| **QSPI NOR**                                     |              |
| Read                                             |       x      |
| Write                                            |       x      |
| **Graphic**                                      |              |
| GPU                                              |       x      |
| **Display**                                      |              |
| DisplayPort                                      |       x      |
| **PCIe**                                         |              |
| mini-PCIe on MBLs1028a                           |       x      |
| **SATA**                                         |              |
| M.2 SATA                                         |       x      |
| **CAN**                                          |              |
| CAN 1                                            |       x      |
| CAN 2                                            |       x      |
| **SPI**                                          |              |
| SPI user space device                            |       x      |

## Notes

Differing from the LS1028A defaults, the TQMLS1028A device trees use an
external sensor IC (connected to a measurement diode inside the SoC)
instead of the builtin TMU as their primary data source for CPU core
temperature. The external IC features a greater measurement range and
higher precision.

The sensor is used for automatic core clock reduction and shutdown in the
case of overheating.

## Known Issues

DisplayPort only works with some monitors and only in 1920x1080.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`
* `atf/`
  * 1GiB
    * `bl2_flexspi_nor.pbl` Primary Boot Loader with RCW
    * `bl2_auto.pbl` Primary Boot Loader with RCW for SD/e-MMC boot
    * `fip_uboot.bin` U-Boot
  * 4GiB
    * `bl2_flexspi_nor_tqmls1028a_4gb.pbl` Primary Boot Loader with RCW
    * `bl2_auto_tqmls1028a_4gb.pbl` Primary Boot Loader with RCW for SD/e-MMC boot
    * `fip_uboot_tqmls1028a_4gb.bin` U-Boot
* `atf/variants/`: different Primary Boot Loader variants built with RCW binaries form `rcw/`
* `rcw/`: different RCW configuration binaries
* `fsl-ls1028a-mbls1028a-tqmls1028a-mbls1028a.dtb`: device tree blob for mbls1028a board
* `fsl-ls1028a-mbls1028a-tqmls1028a-mbls1028a-ind.dtb`: device tree blob for mbls1028a-ind board
* `Image.gz`: Linux kernel image
* `u-boot-tfa-201010-r0.bin` U-Boot binary
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

## Build-Time Configuration

* BL2_IMAGE: ATF BL2 file used for WIC image creation
* BL3_IMAGE: ATF BL3 file used for WIC image creation
* RCWAUTO: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SD/e-MMC Boot
* RCWXSPI: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SPI-NOR Flash
* ATF_RCW_VARIANTS: List of RCW binaries used to build variants of the Primary Boot Loader

Set BL2_IMAGE to `bl2_auto${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.pbl` and BL3_IMAGE to
`fip_uboot${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.bin` to create an SD/e-MMC image for the 4GiB variant.

### Secure Boot

Secure Boot is enabled by adding "secure" to `DISTRO_FEATURES`. With this setting, signed variants
of all ATF components are generated and built into the SD/e-MMC system image.

By default, a newly generated keypair will be used for signing, which may be lost when certain
packages are rebuilt. To enable the build of secured images with a pregenerated keypair, the
following settings can be added to a distro configuration or `local.conf`:

```
DISTRO_FEATURES:append = " secure"

SRK_PATH = "/path/to/my/srk/keypair"
SRC_URI:append:pn-qoriq-cst-native = " file://${SRK_PATH}/srk.pri file://${SRK_PATH}/srk.pub"
SECURE_PRI_KEY:pn-qoriq-cst-native = "${SRK_PATH}/srk.pri"
SECURE_PUB_KEY:pn-qoriq-cst-native = "${SRK_PATH}/srk.pub
```

To boot a secured image, a LS1028AE CPU (with cryptography support) with programmed OTPMK fuses is
required. Please refer to the
[LSDK User Guide](https://www.nxp.com/design/software/embedded-software/linux-software-and-development-tools/layerscape-linux-distribution-poc:LAYERSCAPE-SDK#documentation)
for more information on the Secure Boot process and the required hardware preparation.

For now, only the early boot stages (up to U-Boot) are signed and verified.
Additional configuration of U-Boot is required to verify subsequent boot images
like the Linux kernel.

## Boot DIP Switches

### SD Card

| DIP S9  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   |   |   |
| OFF     | x | x | x | x |

### e-MMC

| DIP S9  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      | x |   |   |   |
| OFF     |   | x | x | x |

### FLEXSPI

| DIP S9  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   | x |   |   |
| OFF     | x |   | x | x |

## Signal Switch Select

DIP S10 allows switching of signals:
 * S10-1: 0: IIC5 to Mikro-Module 1: IIC5 to Baseboard I2C
 * S10-2: 0: UART2 to Mikro-Module 1: UART2 to Baseboard X38 Pin 15 and Pin 17
 * S10-3: 0: SPI3 to Mikro-Module 1: SPI3 to Baseboard X25 Pin 38/40/42/44
 * S10-4: 0: Fan on X37 active 1: Fan on X37 not active

## Support Wiki

See [TQ Embedded Wiki for TQMLS1028A](https://support.tq-group.com/en/layerscape/tqmls1028a)
