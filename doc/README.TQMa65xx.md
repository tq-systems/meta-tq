# TQMa65xx on MBa65xx carrier board

## Overview

### Supported Hardware:

* TQMa6548: Module revisions REV.010x (2GB & 4GB RAM variants)
* MBa65xx: Board revisions REV.020x

### Versions

_Bootloader:_

* uboot-ti-tq-2020.01 (based on ti-u-boot 2020.01)

_Kernel:_

* linux-ti-tq-5.4.109 (based on ti-rt-linux-5.4.y)

### Known issues

* USB3 is not working on OTG port.
  - In host mode, USB3 devices connected using an USB3 OTG cable are not
    detected. Use a USB2 OTG cable.
  - In device mode (gadget driver), USB2 is used even when both sides and the
    cable should support USB3.
* The USB OTG port does not work in host mode in U-Boot
  - Use one of the hub ports to connect USB media in U-Boot. The OTG port works
    in host mode after booting Linux.
* The 6 PRU Ethernet ports only work in PHY master mode. This makes it
  impossible to connect these ports to other devices that enforce master mode
  (like the PRU ports of other MBa65xx boards).
* SPI Linux boot from UBIFS is currently unsupported.
* The linux-ti-tq-5.4.109 recipe is not based on linux-yocto, so the kernel
  defconfig cannot be extended using config fragments.
* Macronix Octal SPI-NOR flash is currently unsupported. TQMa65xx variants with
  an OSPI flash run in single SPI mode.

## Support Wiki

See [TQ Support Wiki for TQMa65xx](https://support.tq-group.com/en/arm/tqma65xx)
