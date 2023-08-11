# U-Boot environment support in RootFS

[[_TOC_]]

## Preface

Sometimes access to U-Boot environment from Linux user land shall be possible.
An Example is the interaction between bootloader and updater systems. To provide
a show case for multiple boot devices and different environment locations
the `libubootenv` implementation of `fw_prinenv` / `fw_setenv` has a new config
file format allowing to define multiple configurations.

For details see https://github.com/sbabic/libubootenv

## Implementation details

The new configuration file format uses YAML. For every boot device supported by
a machine the environment settings are placed as a `configuration`. The first /
top level configuration is treated as default if none is given on the command
line

| configuration name | description                                       |
| :----------------: | :-----------------------------------------------: |
| uboot              | default, on most boards this is the eMMC          |
| uboot-sd           | SD-Card                                           |
| uboot-qspi         | QSPI NOR                                          |

Please note:

* Depending on board variant not all possible boot devices may be assembled.
* When no valid environment is found for the requested configuration, the
  corresponding default environment from `/env` has to be used instead.
  The matching file can be supplied on command line.
* **Note**: Take care to not destroy your boot loader environment.
