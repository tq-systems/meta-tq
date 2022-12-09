# TQMLS102xA on MBLS102xA carrier board

This README contains some useful information for TQMLS102xA on MBLS102xA

[[_TOC_]]

### Variants

* TQMLS102xA: module
* MBLS102xA: board

## Version information for software components

### U-Boot

* based on u-boot v2017.11
* board support for TQMLS102xA and TQMa6x

### Linux

* based on linux-5.4.y
* contains upstream changes up to v5.4.87
* board support for TQMLS102xA and TQMa6x

## Important Notes

* CPLD mux must match RCW configuration
* CPLD mux mode #1 EC1_HDMI matches the default RCW configuration of the TQMLS102xA BSP.
* If DIP S9 is not set to one of the CPLD mux modes #1 - #12, the CPLD firmware selects mux mode #1.

## Known issues

- The power supply (VBUS) of the USB-OTG (X40) port can't be disabled on the
  MBLS102xA. The port must not be used in device mode.
- Enumeration of USB devices connected to the USB-OTG port (X40) via a USB-OTG
  cable fails

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
<`${TMPDIR}/deploy/images/${MACHINE}`>

* \*.dtb: device tree blobs
  * ls1021a-mbls1021a.dtb
  * ls1021a-mbls1021a-hdmi.dtb (HDMI support)
  * ls1021a-mbls1021a-dmb-ct44.dtb (LVDS support for display CT44)
  * ls1021a-mbls1021a-dmb-ct21.dtb (LVDS support for display CT21)
  * ls1021a-mbls1021a-glyn-etm0700g0edh6.dtb (LVDS support for display GLYN ETM0700G0EDH6)
* zImage: Linux kernel image
* \*.wic: SD / e-MMC system image
* \*.rootfs.ext4: RootFS image
* \*.rootfs.tar.gz: RootFS archive
* u-boot.bin-\*:
  * sd: boot stream for SD / e-MMC
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

_<Note:>_

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

## Support Wiki

See [TQ Embedded Wiki for TQMLS102xA](https://support.tq-group.com/en/layerscape/tqmls102xa)
