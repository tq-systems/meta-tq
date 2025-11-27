# TQMLS102xA on MBLS102xA carrier board

This README contains some useful information for TQMLS102xA on MBLS102xA

[[_TOC_]]

### Variants

* TQMLS102xA: module
* MBLS102xA: board

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions of
bootloader and Linux kernel.

## Important Notes

* CPLD mux must match RCW configuration
* CPLD mux mode #1 EC1_HDMI matches the default RCW configuration of the TQMLS102xA BSP.
* If DIP S9 is not set to one of the CPLD mux modes #1 - #12, the CPLD firmware selects mux mode #1.

## Known issues

- The power supply (VBUS) of the USB-OTG (X40) port can't be disabled on the
  MBLS102xA. The port must not be used in device mode.
- Enumeration of USB devices connected to the USB-OTG port (X40) via a USB-OTG
  cable fails
- After Power-On or restart the display might be blanked
  Use `echo 0 > /sys/class/backlight/backlight/bl_power` to unblank the display
- HDMI's DDC channel is shared with on-board I2C. Not all displays might be supported
  due to address conflicts with DDC device IDs.
- USB (X40) does not work
- u-boot
  - Default MTD partition layout and boot script don't load files from UBIFS
  - No USB support

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * ls1021a-tqmls1021a-mbls1021a.dtb
  * ls1021a-tqmls1021a-mbls1021a-hdmi.dtb (HDMI support)
  * ls1021a-tqmls1021a-mbls1021a-lvds-tm070jvhg33.dtb (support for LVDS display Tianma TM070JVHG33)
  * ls1021a-tqmls1021a-mbls1021a-rgb-cdtech-dc44.dtb (support for RGB display DC44)
  * ls1021a-tqmls1021a-mbls1021a-rgb-cdtech-fc21.dtb (support for RGB display FC21)
* zImage: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* u-boot.bin-\*:
  * sd: boot stream for SD / eMMC
    * _can: including CAN support
    * _ecc: including ECC support
      * _can: including ECC and CAN support
    * _sai: including SAI support
  * qspi: boot stream for QSPI
    * _can: including CAN support
    * _ecc: including ECC support
      * _can: including ECC and CAN support
    * _sai: including SAI support
  * ae_qspi_ecc: boot stream for AE module for QSPI including ECC support
    * _can: including CAN support

## Boot DIP Switches

_Note:_

* S3: Boot device
* S9: CPLD

* X means position of DIP, - means don't care

### BOOT\_MODE

#### SD Card

Only for TQMLS102xA without eMMC.

| DIP S3   | 1 | 2 | 3 | 4 |
| -------  | - | - | - | - |
| ON       | x | x | - | - |
| OFF      |   |   | - | - |

#### eMMC

eMMC is a placement option on TQMLS102xA

| DIP S3   | 1 | 2 | 3 | 4 |
| -------  | - | - | - | - |
| ON       | x |   | - | - |
| OFF      |   | x | - | - |

#### QSPI NOR

| DIP S3   | 1 | 2 | 3 | 4 |
| -------  | - | - | - | - |
| ON       |   | - | - | - |
| OFF      | x | - | - | - |

* 2:
  * ON: TQMLS102xA without eMMC
  * OFF: TQMLS102xA with eMMC

### CPLD signal routing

| DIP S9      | 1   | 2   | 3 | 4 |
| ----------- | --- | --- | - | - |
| HDMI        | on  | on  | - | - |
| LVDS        | off | on  | - | - |
| UCC         | on  | off | - | - |
| LVDS_RGBINV | off | off | - | - |


| DIP S9      | 1 | 2 | 3   | 4   |
| ----------- | - | - | --- | --- |
| EC1         | - | - | on  | on  |
| CAN1 / CAN2 | - | - | off | on  |
| SAI1 / SAI2 | - | - | on  | off |
| Reserved    | - | - | off | off |

## Boot Media

See [Layerscape Boot Media](./README.ls.BootMedia.md) for details.

## Howto

### HDMI

When using HDMI output the default CMA size (16MiB) might be too small, `weston` will not be able to start. In this case add the parameter `cma=32M` to the kernel command line.

Using an HDMI monitor might require color space conversion. Without a GPU this causes a high CPU usage.

### NFS boot

When booting from NFS the `netdev` variable needs to be set correspondent to the used Ethernet interface,
specified in variable `ethact`.

| ethact | netdev |
|--------|--------|
| eTSEC1 | eth0   |
| eTSEC2 | eth1   |
| eTSEC3 | eth2   |

## Support Wiki

See [TQ Embedded Wiki for TQMLS102xA](https://support.tq-group.com/en/layerscape/tqmls102xa)
