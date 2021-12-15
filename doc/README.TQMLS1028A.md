# TQMLS1017A / TQMLS1028A

## Kernel variants

By default, the kernel recipe linux-imx-tq (based on FSLC kernel
5.4-1.0.0-imx) is built. An alternative kernel based on LSDK-20.12-V5.4-RT
can be selected for PREEMPT_RT support by adding the following to
local.conf or a custom DISTRO config:

    PREFERRED_PROVIDER_virtual/kernel_tqmls1028a = "linux-rt-lsdk-tq"

Please note that the default linux-imx-tq kernel has received more thorough
testing and it is therefore recommended for most usecases.

## Notes

Differing from the LS1028A defaults, the TQMLS1028A device trees use an
external sensor IC (connected to a measurement diode inside the SoC)
instead of the builtin TMU as their primary data source for CPU core
temperature. The external IC features a greater measurement range and
higher precision.

The sensor is used for automatic core clock reduction and shutdown in the
case of overheating.

## Support Wiki

See [TQ Embedded Wiki for TQMLS1028A](https://support.tq-group.com/en/layerscape/tqmls1028a)
