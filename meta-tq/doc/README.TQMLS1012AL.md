# TQMLS1012AL

This README contains some useful information for TQMLS1012AL on MBLS1012AL carrier board

[[_TOC_]]

## Variants

* TQMLS1012AL SOM REV.020x 512 MiB / 1024 MiB RAM 
* MBLS1012AL carrier Board

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                   |    REV.020x    |
|:--------------------------|:--------------:|
| RAM configs               | 512 MiB, 1 GiB |
| UART (console on DUART0)  |       x        |
| **GPIO**                  |                |
| Button                    |       x        |
| **I2C**                   |                |
| system EEPROM parsing     |       x        |
| **eMMC / SD**             |                |
| Read                      |       x        |
| Write                     |       x        |
| **Ethernet**              |                |
| GigE SGMII (X23)          |       x        |
| GigE Switch (X12, X13)    |       x        |
| **USB**                   |                |
| USB 3.0 Host / Hub        |       x        |
| **QSPI NOR**              |                |
| Read with 1-1-1 SDR       |       x        |
| PP / Erase with 1-1-1 SDR |       x        |

### Linux

**Note:** For Linux 5.15 based on NXP / vendor branch prefer using `kirkstone` branch.

| Feature                                           |   fslc-6.6    |
|:--------------------------------------------------|:-------------:|
| RAM configs                                       | 512MiB, 1 GiB |
| Fuses / OCRAM                                     |       x       |
| speed grade / temperature grade detection         |       x       |
| **UART**                                          |               |
| console on UART1 (via USB / UART converter) (X25) |       x       |
| **GPIO**                                          |               |
| LED                                               |       x       |
| Button                                            |       x       |
| **I2C**                                           |               |
| EEPROMs                                           |       x       |
| RTC                                               |       x       |
| Temperature Sensors                               |       x       |
| **ENET**                                          |               |
| GigE / SGMII (X23)                                |       x       |
| GigE / RGMII on Ethernetswitch (X12/X13)          |       x       |
| **USB**                                           |               |
| USB 3.0 Host / Hub (X6)                           |       x       |
| wireless card at Mini-PCIe (X3)                   |       x       |
| **QSPI NOR**                                      |               |
| Read with 1-1-4 SDR                               |       x       |
| PP / Erase with 1-1-1 SDR                         |       x       |
| **PCIe**                                          |               |
| Ethernet card at Mini-PCIe (X4)                   |       x       |
| **SATA**                                          |               |
| SATA M.2 (X10)                                    |       x       |

## Important notes

*Attention*: CPU supports only booting from QSPI NOR. When deleting bootloader
recovery via JTAG is needed.

## Known Issues

* U-Boot: USB HUB(X3): sometimes lock after second `usb reset` when using USB stick
  * seems to be hardware dependend
* Linux: currently no out of the box support for RootFS on SPI
  * `tq-image-small-debug` image from `spaetzle` distribution is too large
* Linux: Wake up support using GPIO button not working

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)
* `atf/`
  * 512MiB
    * `bl2_qspi.pbl` Primary Boot Loader with RCW
    * `fip_uboot.bin` U-Boot / TF-A Firmware Image Package
  * 1GiB
    * `bl2_qspi_tqmls1012al_1gb.pbl` Primary Boot Loader with RCW
    * `fip_uboot_tqmls1012al_1gb.bin` U-Boot / TF-A Firmware Image Package
* `engine-pfe-bin/pfe_fw_sbl.itb` PFE engine firmware file
* `rcw/`: different rcw configurations to use with atf-recipe
* `fsl-ls1012a-tqmls1012al-mbls1012al.dtb`: device tree blob
* `Image.gz`: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image (without boot loader)
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

## Functional DIP Switches

| DIP S1 | On                             | Off                            |
|:------:|:-------------------------------|:-------------------------------|
|   1    | Hard-coded RCW enabled         | Hard-coded RCW disabled        |
|   2    | Short RESET_REQ# and RESET#    | Isolate RESET_REQ# from RESET# |
|   3    | Debug-UART on USB / pin header | Debug-UART on OpenSDA          |
|   4    | CPU-JTAG disabled              | CPU-JTAG enabled               |

## Boot device initialisation and update

Make sure the power supply is on during update, see [important notes](#important-notes)
for recovery.

U-Boot envinronment provides scripts to help updating components needed to boot
the board. For filenames see [artifacts section](#artifacts).

### Update with static IP

```
setenv ipaddr <ipaddr>
setenv serverip <serverip>
setenv pbl_spi_file <filename>		# RCW/PBL/BL2 file name
run update_pbl				# update PCW/PBL/BL2
setenv uboot_spi_file <filename>	# U-Boot FIP file name
run update_uboot			# update U-Boot FIP
```

### Update with dynamic IP

```
setenv ipmode dynamic			# obtain IP configuration via DHCP
setenv pbl_spi_file <filename>		# RCW/PBL/BL2 file name
run update_pbl				# update PCW/PBL/BL2
setenv uboot_spi_file <filename>	# U-Boot FIP file name
run update_uboot			# update U-Boot FIP
```

### SD / eMMC images

See [Layerscape Boot Media](./README.ls.BootMedia.md) for details.

## Howto

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
