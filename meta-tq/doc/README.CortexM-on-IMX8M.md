# CortexM Support for TQ-Systems SOM with i.MX8M

To start a demo stored on SD / e-MMC from U-Boot:

```
setenv fdt_file <rpmsg enabled device tree>
setenv cm_image <demo>
run boot_cm_mmc
```

To demonstrate `rpmsg` and `freertos` a simple demo is included. When the demo
is started you can connect from running Linux to the rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```

*Note:* This demo runs for a fixed number of cycles and terminates the RPMSG system.
The kernel driver stack may generate a warning after the demo on Cortex M terminates.
