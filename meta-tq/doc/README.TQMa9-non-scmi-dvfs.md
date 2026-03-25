# Dynamic Frequency Scaling for i.MX91 and i.MX93

For i.MX91 and i.MX93 a SOC specific driver (`imx93-lpm`) is used.
Depending on SOC following modes can be supported

| Mode                         | sysfs | i.MX93 | i.MX91 | Note                                             |
|------------------------------|-------|--------|--------|--------------------------------------------------|
| OD (over drive)              |   0   |  x     |   -    | can be disabled via devicetree                   |
| ND (normal drive)            |   1   |  x     |   x    |                                                  |
| LD (low drive)               |   2   |  x     |   x    | must be enabled via device tree                  |
| SWFFC (low drive with SWFFC) |   3   |  x     |   x    | with enabled ND and >2 set points in DRAM config |

*SWFFC*: software fast frequency change

To change SOC frequency mode the mode can be switched via sysfs.

```
echo <sysfs> > /sys/devices/platform/imx93-lpm/mode
```

The mode can be read from the same file:

```
cat /sys/devices/platform/imx93-lpm/mode
```

Additionally the DRAM hardware fast frequency change (HWFFC) can be controlled with
`/sys/devices/platform/imx93-lpm/auto_clk_gating` when running with multiple DRAM set points.
HWFFC switches dynamically between the two fastes DRAM setpoints in a hardware controlled manner.
A value of zero disables HWFFC, non zero values enable the HWFFC.
