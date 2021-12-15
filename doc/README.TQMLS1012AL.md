# TQMLS1012AL on MBLS1012AL carrier board

## Overview

### Supported Hardware:

* TQMLS1012AL: module
* MBLS1012AL:  board

### Known issues

- none

## HowTo:

### MBLS1012AL DIP Switch settings for Boot

```
DIP S1  ON                              OFF
1       Hard-coded RCW enabled          Hard-coded RCW disabled
2       Short RESET_REQ# and RESET#     Isolate RESET_REQ# from RESET#
3       Debug-UART on USB / pin header  Debug-UART on OpenSDA
4       CPU-JTAG disabled               CPU-JTAG enabled
```

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
