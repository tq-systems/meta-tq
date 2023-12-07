# Distroboot

The term "Distroboot" describes a standardized interface between the U-Boot
bootloader and the operating system. The operating system can provide its own
boot script as a `boot.scr` image (which can optionally be signed for Secure
Boot) and/or a `extlinux.conf`, which define the loaded kernel, Device Trees,
command line or other details of the OS boot, rather than hardcoding these
in the bootloader.

The TQ Yocto BSP currently uses Distroboot by default for the following modules:

- TQMa62xx
- TQMa64xxL

By default, U-Boot will boot the OS from the same medium that it was started
from (eMMC, SD card or SPI-NOR). The boot source can be modified by setting
the `boot_targets` variable to `mmc0` (eMMC), `mmc1` (SD card), `sf0` (SPI-NOR),
`usb0` (USB mass storage) or `pxe` (netboot). Multiple boot sources separated
with spaces will be tried in the order they are specified.

For MMC and USB devices, U-Boot will load a script image `boot.scr` from the
boot partition of the selected medium. The script provided in this BSP's
default configuration then selects the kernel and its configuration based on
`/boot/extlinux/extlinux.conf`.

For netboot, first an IP address is obtained via DHCP; static IP addresses are
not supported. A configuration file in the `extlinux.conf` format is then loaded
via TFTP, trying a number of different paths based on the MAC address,
IP address and finally hardware type. The path
`/pxelinux.cfg/default-${arch}-${soc}-${board_name}` can always be used,
where `arch`, `soc` and `board_name` are variables found in the U-Boot
environment.

A commented [example configuration](examples/pxelinux.cfg) is provided as a
starting point for the PXE boot config file, which expects to find the root
filesystem mountable via NFS.
