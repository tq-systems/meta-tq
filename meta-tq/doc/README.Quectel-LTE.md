# LTE with Quectel wwan Card

This README contains some useful information for using Quectel LTE card.

[[_TOC_]]

# Supported hardware

## hardware platform
Currently with Linux 5.15 the supported platforms are
* TQMa9xxx

## Quectel modules
* Quectel EC21
  * USB VID: 0x2c7c
  * USB PID: 0x0121

# Usage

`pppd call quectel-ppp`

A `ppp0` device should be available when executing the command `ifconfig ppp0`.

## Building & configuration

### Kernel configuration

```
CONFIG_PPP=m
CONFIG_PPP_DEFLATE=m
CONFIG_PPP_ASYNC=m
CONFIG_PPP_SYNC_TTY=m
CONFIG_SLHC=m
CONFIG_USB_NET_QMI_WWAN=m
CONFIG_USB_WDM=m
```

### Kernel patches

The kernel patches from the Quectel documentation are not necessary for the
Quectel EC21 module.

### PPP scripts

The current ppp scripts only support SIM cards without PIN.

The recipe for the ppp script is in
`meta-dumpling/recipes-connectivity/quectel-ppp/quectel-ppp_0.1.bb`.

### Miscellaneous

There is an `lte` machine feature and a `packagegroup-lte` to add LTE support
for a target.

In order to select the Quectel support the following Access Point Name
(APN) has to be added to your `conf/local.conf`:
```
QUECTEL_PPP_APN = "my-apn"
```

The following variable sets the USB UART for AT commands of the wwan card.
The default UART is `/dev/ttyUSB2`. It should be set in the `conf/machine/tqma*.conf`
by path (see below).
```
QUECTEL_PPP_TTY = "/dev/serial/by-path/platform-ci_hdrc\.0-usb-0\:1\.2\:1\.2-port0"
```
