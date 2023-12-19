# TQMLS1012AL on MBLS1012AL carrier board

This README contains some useful information for TQMLS1012AL on MBLS1012AL carrier board

[[_TOC_]]

## Variants

* TQMLS1012AL SOM REV.020x 512 MiB / 1024 MiB RAM 
* MBLS1012AL carrier Board

## Version information for software components

### U-Boot

* based on uboot-imx (https://github.com/nxp-qoriq/u-boot/)
* branched from lf-5.15.5-1.0.0

### ATF

* based on imx-atf (https://github.com/nxp-qoriq/atf/)
* branched from lf-5.15.5-1.0.0

### Linux

* based on linux-imx-fslc (https://github.com/nxp-qoriq/linux/)
* branched from lf-5.15.5-1.0.0

## Supported machine configurations

See top level README.md for configurations usable as MACHINE.

## Important notes

*Attention*: CPU supports only booting from QSPI NOR. When deleting bootloader
recovery via JTAG is needed.

## Known Issues

* U-Boot: USB HUB(X3): sometimes lock after second 'usb reset` when using USB stick
  * seems to be hardware dependend
  * only with multiple connected USB mass storage devices
* Linux: currently no out of the box support for RootFS on SPI
* Linux: Wake Up support not working (RTC / GPIO button)
* Linux: tftp does not work on swp0@eth1 (X13 next to X6)

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
* \*.wic: SD / e-MMC system image (without boot loader)
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)

## Functional DIP Switches

```
DIP S1  ON                              OFF
1       Hard-coded RCW enabled          Hard-coded RCW disabled
2       Short RESET_REQ# and RESET#     Isolate RESET_REQ# from RESET#
3       Debug-UART on USB / pin header  Debug-UART on OpenSDA
4       CPU-JTAG disabled               CPU-JTAG enabled
```

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
setenv uboot_spi_file <filename>	# U-boot file name
run update_uboot			# update U-boot
```

### Update with dynamic IP

```
setenv ipmode dynamic			# obtain IP configuration via DHCP
setenv pbl_spi_file <filename>		# RCW/PBL/BL2 file name
run update_pbl				# update PCW/PBL/BL2
setenv uboot_spi_file <filename>	# U-boot file name
run update_uboot			# update U-boot
```

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
