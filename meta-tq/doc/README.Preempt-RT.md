# Linux Preempt-RT on i.MX

This README contains some useful information for using Linux Preempt-RT

[[_TOC_]]

# Supported platforms

Currently with Linux 5.15 Support the supported platforms are
* TQMa6x
* TQMx6ULx
* TQMa6ULLx
* TQMa7x

# Usage

## Configuration

In order to select the the Preempt-RT patched kernel the following line has to
be added to your `conf/local.conf`:
```
PREFERRED_PROVIDER_virtual/kernel = "linux-rt-tq"
```

This uses a patched kernel and also enables the necessary kernel
configurations for Preempt-RT.

## Building

When building together with `meta-dumpling` one of the two example images can be used:
* `tq-image-generic-rt`
* `tq-image-generic-rt-debug`

These recipes ensure that an RT patched kernel is used as well as include tools for
testing / meassuring RT features. These are based on the regular `tq-image-generic[-debug]`.

## Verification

Once booted to check that an RT patched kernel is used a simple test is
```
$ uname -a
Linux tqma7x-512mb-mba7 5.15.27-tq+ga6a8aed2ddcc-rt35 #1 SMP PREEMPT_RT Fri Mar 11 08:42:34 UTC 2022 armv7l armv7l armv7l GNU/Linux
```

The output `PREEMPT_RT` indicates that Preempt-RT is enabled.
