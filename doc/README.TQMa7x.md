# TQMa7x on MBa7x carrier board (aka STKa7x)

## Overview

### Supported Hardware:

* TQMa7x: module revisions REV.020x
* MBa7x:  board revisions REV.020x

### Known issues

- With kernel linux-imx-tq 5.4 (and possibly other variants based on 5.4),
power management for the HSIC USB hub of the MBa7x is too aggressive and
will power off the hub completely when no device is connected. A
software access to the hub (for example by running `lsusb -v`) is
necessary to wake up the hub, so a newly connected device is detected.
- PCI on MBa7x is not supported by the Linux mainline kernel
- Loading the imx-cpufreq-dt fails with the TQMa7s (Solo). This means
that the CPU frequency cannot be output. TQMa7s use only a single frequency.

## HowTo:

### MBa7x DIP Switch settings for Boot

_Note:_

* S2/3/4 are for BOOT_CFG 0..20.
* S1 is for Boot Mode.

#### SD Card

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |  x  |     |     |  x  |     |     |     |    |      |     |     |     |  x  |     |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x  |  x  |     |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

#### e-MMC

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |  x  |     |     |    |      |     |     |  x  |     |  x  |     |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |     |  x  |  x  |    |  x   |  x  |  x  |     |  x  |     |  x  |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

#### QSPI

|         |  S2  |     |     |     |     |     |     |     |    |  S3  |     |     |     |     |     |     |     |    |  S4 |     |     |     |    |  S1 |     |
| ------- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :--: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1   |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |    |  1  |  2  |
| ON      |      |     |     |     |     |     |     |     |    |      |     |     |     |     |     |  x  |     |    |     |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |  x   |  x  |  x  |  x  |  x  |  x  |     |  x  |    |  -  |  -  |  -  |  -  |    |     |  x  |

## Support Wiki

See [TQ Embedded Wiki for TQMa7x](https://support.tq-group.com/en/arm/tqma7x)
