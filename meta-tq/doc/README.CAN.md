# CAN-FD Mode Enabled

CAN interface (`can0` / `can1`) should be enabled (with CAN-FD mode)
and configured by default when using meta-tq / systemd and platform
supports CAN-FD.

To enable CAN-FD the following command can be used, if using a carrier board with
CAN-FD capable transceiver:

```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 sample-point 0.75 dbitrate 4000000 dsample-point 0.8 fd on
```

# CAN-FD Mode Disabled

CAN interface (`can0` / `can1`) should be enabled (with CAN-FD mode)
and configured by default when using meta-tq / systemd and platform
supports CAN-FD.

Configure CAN1/2 per commandline without CAN-FD:
```
CANIF="can[0,1]"
ip link set ${CANIF} up type can bitrate 500000 fd off
```

To (permanently) configure CAN1/2 in systemd network file, set in files
* `/lib/systemd/network/20-can0.network`
* `/lib/systemd/network/20-can1.network`

`FDMode=no` and remove/comment `DataBitRate` option to disable CAN-FD.
