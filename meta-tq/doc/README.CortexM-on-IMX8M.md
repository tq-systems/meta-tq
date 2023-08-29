# CortexM Support for TQ-Systems SOM with i.MX8M

To start a demo stored on SD / e-MMC from U-Boot:

```
setenv fdt_file <rpmsg enabled device tree>
setenv cm_image <demo>
run boot_cm_mmc
```

Starting from Linux kernel v5.19 a linux kernel command line has to be added.
In order to keep the clocks enabled, which are needed for Cortex-M usage,
a module specific parameter has to be added:

| Module    | command line argument       |
|-----------|-----------------------------|
| TQMa8Mx   | `clk_imx8mq.mcore_booted=1` |
| TQMa8MxML | `clk_imx8mm.mcore_booted=1` |
| TQMa8MxNL | `clk_imx8mn.mcore_booted=1` |
| TQMa8MPxL | `clk_imx8mp.mcore_booted=1` |

To demonstrate `rpmsg` and `freertos` a simple demo is included. When the demo
is started you can connect from running Linux to the rpmsg ping pong demo:

```
modprobe imx_rpmsg_pingpong
```

*Note:* This demo runs for a fixed number of cycles and terminates the RPMSG system.
The kernel driver stack may generate a warning after the demo on Cortex M terminates.
