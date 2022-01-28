# TQMa6ULx / TQMa6ULLx / TQMa6ULxL / TQMa6ULLxL

## Overview

### Supported Hardware

* TQMa6ULx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULx)
* TQMa6ULxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULxL)
* TQMa6ULLx REV.030x on MBa6ULx REV.020x carrier board (aka STKa6ULLx)
* TQMa6ULLxL REV.020x on MBa6ULx REV.020x carrier board (aka STKa6ULLxL)
* TQMa6ULxL REV.020x on MBa6ULxL REV.020x carrier board

### Known issues

- The CPU frequency cannot be output.

## HowTo

### MBa6ULx DIP Switch settings for Boot

_Note:_

* S12: BOOT_CFG1\[0 .. 7\]
* S11: BOOT_CFG2\[0 .. 7\]
* S13: BOOT_CFG4\[0 .. 7\]
* S5: BOOT\_MODE\[0 .. 1\]
* X means position of DIP, - means don't care

#### SD Card

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |  x  |     |  x  |  x  |  x  |  x  |  x  |   |  x  |  x  |     |  x  |  x  |  x  |     |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |     |  x  |     |     |     |     |     |   |     |     |  x  |     |     |     |  x  |     |    |     |     |     |     |     |     |     |     |    |  x  |     |

#### e-MMC

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |     |  x  |  x  |     |  x  |  x  |  x  |   |  x  |  x  |  x  |  x  |  x  |     |     |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |  x  |     |     |  x  |     |     |     |   |     |     |     |     |     |  x  |  x  |     |    |     |     |     |     |     |     |     |     |    |  x  |     |


#### QSPI

|         | S11 |     |     |     |     |     |     |     |   | S12 |     |     |     |     |     |     |     |    | S13 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |   |  x  |  x  |  x  |  x  |     |  x  |  x  |  x  |    |  x  |  x  |  x  |  x  |  x  |  x  |  x  |  x  |    |     |  x  |
| OFF     |     |     |     |     |     |     |     |     |   |     |     |     |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |


### MBa6ULxL DIP Switch settings for Boot

_Note:_

* S16 are for BOOT_CFG 0..07.
* S13 is for Boot Mode.

#### SD Card

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |  x  |     |  x  |  x  |     |     |  x  |   |     |  x  |
| OFF     |  -  |     |  x  |     |     |  x  |  x  |     |   |  x  |     |

#### e-MMC

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |     |  x  |     |     |     |  x  |  x  |   |     |  x  |
| OFF     |  -  |  x  |     |  x  |  x  |  x  |     |     |   |  x  |     |

#### QSPI

|         | S16 |     |     |     |     |     |     |     |   | S13 |     |
| ------- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | - | :-: | :-: |
| DIP     |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |   |  1  |  2  |
| ON      |  -  |  -  |  -  |  -  |  x  |  x  |  x  |     |   |     |  x  |
| OFF     |  -  |  -  |  -  |  -  |     |     |     |  x  |   |  x  |     |


## Support Wiki

See [TQ Embedded Wiki for TQMa6ULx and TQMa6ULx](https://support.tq-group.com/en/arm/tqma6ulx)  
See [TQ Embedded Wiki for TQMa6ULx and TQMa6ULxL](https://support.tq-group.com/en/arm/tqma6ulxl)
