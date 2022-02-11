# CortexM Support for TQ Systems SOM with i.MX8X / i.MX8

[[_TOC_]]

## Preface

M4 binaries can only be started as part of the bootstream or per debugger.
For debugger support see the SDK documentation. The default demo image to be
built into the bootstream can be defined per bitbake variables. For the naming
see the SOM specific sections.

The default images in the BSP are linked to run from the TCM of the M4
Cores. Devicetrees for M4 support are adjusted to respect the hardware used by
the demos in the BSP. Also the SCU firmware adjusts the hardware partitions for
the demos in the BSP.

When using other M4 firmware keep in mind to adjust

* SCU firmware (contact TQ-Support <Support@tq-group.com> for sources)
  * resource assignment
  * yocto recipe and recipe date for the SCU firmware
* Device tree
  * disable all hardware used by M4 firmware
  * adjust shared memory if needed
* Bootloader (if needed)
* yocto recipes

*Note* The SCU firmare in this BSP implements handling for M4 enabled images with
alternate config flag supplied from `imx-mkimage` when building `flash_linux_m4`.

## Building

To compile the bootstreams with M4 images at least the `imx-boot` target has to
be built. This depends on

- U-Boot
- ATF
- SCU firmware
- M4 firmware
- seco firmware

The following SOM specific variables have to be adjusted for the machine to build:

### TQMa8Xx / TQMa8XxS

* `M4_DEFAULT_IMAGE`: defaults to `rpmsg_lite_pingpong_rtos_linux_remote.bin`

To build a bootstream with M4 binary in SD / e-MMC the bitbake variable 
`IMXBOOT_TARGETS` must contain the target `flash_linux_m4`

### TQMa8x

`M4_DEFAULT_IMAGE`: defaults to `rpmsg_lite_pingpong_rtos_linux_remote_m40.bin`
`M4_1_DEFAULT_IMAGE`: defaults to `rpmsg_lite_pingpong_rtos_linux_remote_m41.bin`

To build a bootstream with M4 binaries in SD / e-MMC the bitbake variable
`IMXBOOT_TARGETS` must contain the target `flash_linux_m4`

### Preparing the system

Per default SD / e-MMC wic images are built using a bootstream without M4
firmware. Bootstreams with M4 firmware follow the naming convention
`imx-boot-${MACHINE}-sd.bin-flash\_linux\_m4`. See SOM specific documentation
how to change the bootstream on SD / e-MMC. To boot with new bootstream a
power cycle of the board is needed after updating the bootstream.

Before running Linux the devicetree in U-Boot environment has to be changed to
the CortexM aware tree. See SOM specific documentation for available device tree
and how to change them.

### Starting

To demonstrate `rpmsg` and `freertos` start the prepared SD / e-MMC. The CortexM4
firmware uses the CortexM UART(s) connecte to the FTDI USB / UART converter on
the starter kits. The M4 firmware is started by the bootstream. You should see
a banner message on the UART for the M4 when the demo is started.

When using the ping pong demo you can connect from running Linux using:

```
modprobe imx_rpmsg_pingpong
```

## Development hints

*Note:* This demo runs for a fixed number of cycles and terminates the RPMSG system.
The kernel driver stack may generate a warning after the demo on Cortex M terminates.

*Note*: For real products the SCU firmware needs to be adjusted to implement
further hardware partitioning. Bootloader and Linux must take in account the
partitioning and must not use any hardware reserved for M4.
