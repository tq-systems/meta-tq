# CortexM Support for TQ Systems SOM with i.MX8X

M4 binaries can only be started as part of the bootstream or per debugger.
For debugger support see the SDK documentation. The default demo image to be
built into the bootstream can be defined per bitbake variable `M4_DEFAULT_IMAGE`
which defaults to `rpmsg_lite_pingpong_rtos_linux_remote.bin`. To build a
bootstream with M4 binary in SD / e-MMC the bitbake variable `IMXBOOT_TARGETS`
must contain the target `flash_linux_m4`

The SCU firmare in this BSP implements handling for M4 enabled images with
alternate config flag supplied from `imx-mkimage` when building `flash_linux_m4`.

To demonstrate `rpmsg` and `freertos` a simple demo is included. When the demo
is started you can connect from running linux to the rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```

*Note:* This demo runs for a fixed number of cycles and terminates the RPMSG system.
The kernel driver stack may generate a warning after the demo on Cortex M terminates.

*Note*: For real products the SCU firmware needs to be adjusted to implement
further hardware partitioning. Bootloader and Linux must take in account the
partitioning and must not use any hardware reserved for M4.
