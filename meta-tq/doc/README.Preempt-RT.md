# Linux Preempt-RT on i.MX

This README contains some useful information for using Linux Preempt-RT

[[_TOC_]]

# Supported Platforms

| Board     | linux 6.6| linux 6.12 |
|-----------|----------|------------|
| TQMa6ULx  |    x     |            |
| TQMa6ULxL |    x     |            |
| TQMa6x    |    x     |            |
| TQMa7x    |    x     |            |
| TQMa8Mx   |    x     |            |
| TQMa8MxML |    x     |            |
| TQMa8MxNL |    x     |            |
| TQMa8MPxL |   (x)    |    x       |
| TQMa8MPxS |          |    x       |
| TQMa93xx  |          |    x       |
| TQMa335x  |          |    x       |
| TQMLS102xA|          |    x       |
| TQMLS10xxA|          |    x       |
| TQMLX2160A|          |    x       |

Notes:

* **(x):** Version is not tested.

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
```

The output `PREEMPT_RT` indicates that Preempt-RT is enabled.
