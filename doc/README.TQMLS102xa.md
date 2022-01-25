# TQMLS102xA on MBLS102xA carrier board

This README contains some useful information for TQMLS102xA on MBLS102xA

[[_TOC_]]

## Overview

### Supported Hardware

* TQMLS102xA: module
* MBLS102xA:  board

### Version information for software components

#### U-Boot

* based on u-boot v2017.11
* board support for TQMLS102xA and TQMa6x

#### Linux

* based on linux-5.4.y
* contains upstream changes up to v5.4.87
* board support for TQMLS102xA and TQMa6x

### Known issues

- The power supply (VBUS) of the USB-OTG port can't be disabled on the
  MBLS102xA. The port must not be used in device mode.
- Enumeration of USB devices connected to the USB-OTG port via a USB-OTG
  cable fails

## HowTo

### MBLS102xA DIP Switch settings for Boot

[DIP Switch settings for TQMLS102xA](https://support.tq-group.com/en/layerscape/tqmls102xa/mbls102xa/dip_switches)

## Support Wiki

See [TQ Embedded Wiki for TQMLS102xA](https://support.tq-group.com/en/layerscape/tqmls102xa)
