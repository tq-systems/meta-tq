# TQMLS1017A / TQMLS1028A

[[_TOC_]]

## Variants
* TQMLS1028A / TQMLS1017A SOM REV.020x/030x with 1/2/4/8 GiB RAM
* MBLS1028A / MBLS1028A-IND carrier board

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported Features

### U-Boot

| Feature                                   |                 |
|:------------------------------------------|:---------------:|
| RAM configs                               |   1,2,4,8 GiB   |
| CPU variants                              | LS1027A,LS1028A |
| CPU variants                              | LS1017A,LS1018A |
| GPIO                                      |        x        |
| I2C                                       |        x        |
| **QSPI**                                  |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| Boot                                      |        x        |
| **eMMC / SD-Card**                        |                 |
| Read                                      |        x        |
| Write                                     |        x        |
| Boot                                      |        x        |
| **USB**                                   |                 |
| USB 3.0                                   |        x        |
| **SATA**                                  |                 |
| M.2 card (SSD)                            |        x        |
| **PCIe**                                  |                 |
| mPCIe on mainboard                        |        x        |
| **ENET**                                  |                 |
| ENET 0                                    |        x        |
| ENET 1                                    |        x        |
| ENET Switch                               |        x        |
| **Bootstreams**                           |                 |
| FlexSPI                                   |        x        |
| SD / eMMC                                 |        x        |

### Linux

| Feature                                   |    fslc-6.6     |
|:------------------------------------------|:---------------:|
| RAM configs                               |   1,2,4,8 GiB   |
| CPU variants                              | LS1027A,LS1028A |
| CPU variants                              | LS1017A,LS1018A |
| speed grade / temperature grade detection |        x        |
| **UART**                                  |                 |
| console on UART1 (X19)                    |        x        |
| additional UART2 on pin heads (X38)       |        x        |
| **GPIO**                                  |                 |
| LED                                       |        x        |
| Button                                    |        x        |
| **I2C**                                   |                 |
| Temperature Sensors                       |        x        |
| RTC                                       |        x        |
| EEPROMS                                   |        x        |
| **ENET**                                  |                 |
| ENET 0  (X7)                              |        x        |
| ENET 1  (X6)                              |        x        |
| ENET Switch (X8, X9)                      |        x        |
| **USB**                                   |                 |
| USB 3.0 Hub (X10)                         |        x        |
| USB 2.0 Dual Role (X5)                    |        x        |
| **QSPI NOR**                              |                 |
| Read with 1-4-4 SDR                       |        x        |
| PP / Erase with 1-4-4 SDR                 |        x        |
| **Graphic**                               |                 |
| GPU                                       |        x        |
| **Display**                               |                 |
| DisplayPort                               |        x        |
| **PCIe**                                  |                 |
| mini-PCIe (X12)                           |        x        |
| **SATA**                                  |                 |
| M.2 SATA  (X35)                           |        x        |
| **CAN**                                   |                 |
| CAN-FD (X17, X29)                         |        x        |
| **SPI**                                   |                 |
| SPI user space device                     |        x        |

## Notes

Differing from the LS1028A defaults, the TQMLS1028A device trees use an
external sensor IC (connected to a measurement diode inside the SoC)
instead of the builtin TMU as their primary data source for CPU core
temperature. The external IC features a greater measurement range and
higher precision.

The sensor is used for automatic core clock reduction and shutdown in the
case of overheating.

## Known Issues

* DisplayPort only works with some monitors and only in 1920x1080.
* Buzzer does not work
* MBLS1028A: Missing Pull-up for `SPI3_PCS0`
* SPI: XSPI mode might get stuck
* CAN-FD: possible CAN (data) bitrates combinations are limited due to platform clock
* USB DR (X5) does not support SuperSpeed (CPU limitation for cable detection with ID-pin)
* Wake up using GPIO buttons does not work
* PCIe device causes timeout for L2 entry preventing suspend
* Linux: currently no out of the box support for Linux kernel on SPI-NOR
  * `tq-image-small-debug` image from `spaetzle` distribution is too large

## Artefacts

Artefacts can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)
* `atf/`
  * `bl2_flexspi_nor.pbl` Primary Boot Loader with RCW (1 GiB)
  * `bl2_auto.pbl` Primary Boot Loader with RCW for SD/eMMC boot (1 GiB)
  * `fip_uboot.bin` Firmware Image Package for BL3 (TF-A as BL31
     and U-Boot as BL33 non secure payload) (1 GiB)
  * `bl2_flexspi_nor_tqmls1028a_<size>gb.pbl` Primary Boot Loader with RCW (2/4/8 GiB)
  * `bl2_auto_tqmls1028a_<size>gb.pbl` Primary Boot Loader with RCW for SD/eMMC boot (2/4/8 GiB)
  * `fip_uboot_tqmls1028a_<size>gb.bin` Firmware Image Package for BL3 (TF-A as BL31
     and U-Boot as BL33 non secure payload) (2/4/8 GiB)
  * `atf/variants/`: different Primary Boot Loader variants built with RCW binaries form `rcw/`
* `rcw/`: different RCW configuration binaries
* `fsl-ls1028a-tqmls1028a-mbls1028a.dtb`: device tree blob for mbls1028a board
* `fsl-ls1028a-tqmls1028a-mbls1028a-ind.dtb`: device tree blob for mbls1028a-ind board
* `Image.gz`: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

Artefacts under `atf` can be used to manually update boot images on SOM or exchange them in WIC image.

## Build-Time Configuration (default boot images for SPI-NOR and WIC)

* BL2_IMAGE: ATF/TF-A BL2 file used for WIC image creation
* BL3_IMAGE: ATF/TF-A BL3 file used for WIC image creation
* RCWAUTO: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SD/eMMC Boot
* RCWXSPI: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SPI-NOR Flash
* ATF_RCW_VARIANTS: List of RCW binaries used to build variants of the Primary Boot Loader

By default, all images will be built and the 1GiB variant is set as default for WIC-creation.
Set `BL2_IMAGE` to `bl2_auto${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.pbl` and
`BL3_IMAGE` to `fip_uboot${ATF_SECURE_SUFFIX}_tqmls1028a_4gb.bin` to create an SD/eMMC image for the 4GiB
variant (or 2gb/8gb for the 2GiB/8GiB variants respectively).

### Secure Boot

Secure Boot is enabled by adding "secure" to `DISTRO_FEATURES`. With this setting, signed variants
of all ATF components are generated and built into the SD/eMMC system image.

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

## Boot Media

See [Layerscape Boot Media](./README.ls.BootMedia.md) for details.

## Boot DIP Switches

### SD Card

| DIP S9  | 1 | 2 | 3 | 4 |
| ------- | - | - | - | - |
| ON      |   |   |   |   |
| OFF     | x | x | x | x |

### eMMC

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
| DIP S10 | off                   | on                                    |
|---------|-----------------------|---------------------------------------|
| S10-1   | IIC5 to Mikro-Module  | IIC5 to Baseboard I2C                 |
| S10-2   | UART2 to Mikro-Module | UART2 to Baseboard X38 Pin 15/17      |
| S10-3   | SPI3 to Mikro-Module  | SPI3 to Baseboard X25 Pin 38/40/42/44 |
| S10-4   | Fan on X37 active     | Fan on X37 not active                 |

## Boot Media

### Update Scripts

In U-Boot the following update scripts are available to update the components:
* RCW/PBL (use `*.pbl` file ):
  * `update_pbl_mmc`
  * `update_pbl_spi`
* TF-A / U-Boot (use FIP Firmware Image Package):
  * `update_uboot_mmc`
  * `update_uboot_spi`

## Support Wiki

See [TQ Embedded Wiki for TQMLS1028A](https://support.tq-group.com/en/layerscape/tqmls1028a)
