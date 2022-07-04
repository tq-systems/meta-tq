# TQMLS1017A / TQMLS1028A

[[_TOC_]]

## Kernel variants

By default, the kernel recipe linux-imx-tq (based on FSLC kernel
5.4-1.0.0-imx) is built. An alternative kernel based on LSDK-20.12-V5.4-RT
can be selected for PREEMPT_RT support by adding the following to
local.conf or a custom DISTRO config:

    PREFERRED_PROVIDER_virtual/kernel = "linux-rt-lsdk-tq"

Please note that the default linux-imx-tq kernel has received more thorough
testing and it is therefore recommended for most usecases.

## Notes

Differing from the LS1028A defaults, the TQMLS1028A device trees use an
external sensor IC (connected to a measurement diode inside the SoC)
instead of the builtin TMU as their primary data source for CPU core
temperature. The external IC features a greater measurement range and
higher precision.

The sensor is used for automatic core clock reduction and shutdown in the
case of overheating.

## Artifacts

Artifacs can be found at the usual locations for bitbake:
`${TMPDIR}/deploy/images/${MACHINE}`
* `atf/`
  * 1GiB
    * `bl2_flexspi_nor.pbl` Primary Boot Loader with RCW
    * `bl2_sd.pbl` Primary Boot Loader with RCW for SD/e-MMC boot
    * `fip_uboot.bin` U-Boot
  * 4GiB
    * `bl2_flexspi_nor_tqmls1028a_4gb.pbl` Primary Boot Loader with RCW
    * `bl2_sd_tqmls1028a_4gb.pbl` Primary Boot Loader with RCW for SD/e-MMC boot
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

* BL2_IMAGE: ATF BL2 file used for WIC Image creation
* BL3_IMAGE: ATF BL3 file used for WIC Image creation
* RCWSD: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SD/e-MMC Boot
* RCWXSPI: default RCW binary file used by qoriq-atf recipe to build Primary Boot Loader for SPI-NOR Flash
* ATF_RCW_VARIANTS: List of RCW binaries used to build variants of the Primary Boot Loader

Set BL2_IMAGE to `bl2_sd_tqmls1028a_4gb.pbl` and BL3_IMAGE to `fip_uboot_tqmls1028a_4gb.bin` to create
SD/e-MMC image for 4GiB variant.

## Support Wiki

See [TQ Embedded Wiki for TQMLS1028A](https://support.tq-group.com/en/layerscape/tqmls1028a)
