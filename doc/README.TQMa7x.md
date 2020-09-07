# TQMa7x

## Known issues

- With kernel linux-imx-tq 5.4 (and possibly other variants based on 5.4),
  power management for the HSIC USB hub of the MBa7x is too aggressive and
  will power off the switch completely when no device is connected. A
  software access to the switch (for example by running `lsusb -v`) is
  necessary to wake up the switch, so a newly connected device is detected.
