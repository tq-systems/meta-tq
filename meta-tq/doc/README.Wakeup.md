# Wakeup

This README contains some useful information for configuring sleep modes and using wakeup.

## Sleep Modes

Available sleep modes can be read from `/sys/power/state`:

```
cat /sys/power/state
```

To enable a sleep mode write the name into the same file:

```
echo mem > /sys/power/state
```

## Wakeup Using RTC

On supported platforms the RTC can be used to wakeup the system.
For this an alarm has to be programmed which causes the RTC to raise
an interrupt which in turn wakes the system.

Use `rtc0` or `rtc1` to wakeup after 20 seconds:

```
RTC=rtc[0,1]
echo enabled > /sys/class/rtc/${RTC}/device/power/wakeup
echo 0 > /sys/class/rtc/${RTC}/wakealarm
echo +20 > /sys/class/rtc/${RTC}/wakealarm
echo mem > /sys/power/state
```
