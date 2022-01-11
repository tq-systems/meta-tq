# TQMa6\[QP,DP,Q,D,DL,SL\] up to Rev.040x on MBa6x REV.020x carrier board

[[_TOC_]]

## Overview

### Supported Hardware:

* TQMa6x: module revisions REV.010x ... REV.040x
* MBa6x:  board revisions REV.020x

### Supported Features

|                              | linux-imx-tq-5.10 |
| ---------------------------- | :---------------: |
| Fuses                        |       x           |
| UART (console on UART3, X15) |       x           |
| GPIO                         |       x           |
| Button (S6, S7, S8)          |       x           |
| I2C                          |       x           |
| GPIO expander                |       x           |
| EEPROM                       |       x           |
| RTC                          |       x           |
| SPI NOR                      |       x           |
| Buzzer                       |       x           |
| GPU                          |       x           |
| VPU H.264                    |       x           |
| VPU VP8                      |       x           |
| USB Host (X6/X7)             |       x           |
| USB Dual Role (X8)           |       x           |
| eMMC/SD (on-board/X9)        |       x           |
| Ethernet GigE (X11/X12)      |       x           |
| CAN (X13/X14)                |       x           |
| RS-485 (X16)                 |       x           |
| HDMI (X17)                   |       x           |
| LVDS (X18, X19)              |       x           |
| Audio Line In (X20)          |       x           |
| Audio Line Out (x22)         |       x           |
| PCIe (X23)                   |       x           |

### ToDo
* SIM card
* Mic In (X21)

### Known issues

- PCIe requires a power cycle to work reliably. Asserting a POR using S9 or S10 is not sufficient.

## HowTo:

### MBa6x DIP Switch settings for Boot

_Note:_

* S1/2/4 are for BOOT_CFG.
* S5 is for Boot Mode.
* X means position of DIP, - means don't care

#### SD Card

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |      |  x   |      |      |      |      |    |     |     |  x  |     |  x  |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |  x   |      |  x   |  x   |  x   |  x   |    |  x  |  x  |     |  x  |     |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

#### e-MMC

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |  x  |  x   |  x   |      |      |      |      |    |     |  x  |     |  x  |     |     |     |     |    |     |     |     |     |     |     |     |     |    |  x  |     |
| OFF     |  x   |     |      |      |  x   |  x   |  x   |  x   |    |  x  |     |  x  |     |  x  |  x  |  x  |  x  |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |     |  x  |

#### SPI

|         |  S1  |     |      |      |      |      |      |      |    |  S2 |     |     |     |     |     |     |     |    |  S4 |     |     |     |     |     |     |     |    |  S5 |     |
| ------- | :--: | :-: | :--: | :--: | :--: | :--: | :--: | :--: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | -- | :-: | :-: |
| DIP     |  1   |  2  |  3   |  4   |  5   |  6   |  7   |  8   |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |    |  1  |  2  |
| ON      |      |     |  x   |  x   |      |      |      |      |    |     |     |     |     |     |     |     |     |    |     |     |     |  x  |  x  |     |     |     |    |  x  |     |
| OFF     |  x   |  x  |      |      |  x   |  x   |  x   |  x   |    |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |    |  x  |  x  |  x  |     |     |  x  |  x  |  x  |    |     |  x  |

### Dual LVDS usage

The 2nd framebuffer / display is blanked by default. In order to use the display on `LVDS1` it need to be unblanked: `echo 0 > /sys/devices/platform/fb@3/graphics/fb2/blank`

## Support Wiki

See [TQ Embedded Wiki for TQMa6x](https://support.tq-group.com/en/arm/tqma6x)
