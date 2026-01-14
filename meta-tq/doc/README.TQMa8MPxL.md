# TQMa8MPxL

This README contains some useful information for TQMa8MPxL on MBa8MPxL and MBa8MP-RAS314

[[_TOC_]]

## Variants

* TQMa8MPQL REV.020x on MBa8MPxL REV.020x
* TQMa8MPQL REV.020x on MBa8MP-RAS314 REV.010x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

## Supported Features

### U-Boot

| Feature                                          |   REV.020x   |
| :----------------------------------------------- | :----------: |
| RAM configs                                      |  1,2,4,8 GiB |
| CPU variants                                     |     i.MX8MPQ |
| Fuses / OCRAM                                    |       x      |
| speed grade / temperature grade detection        |       x      |
| UART (console on UART4)                          |       x      |
| **GPIO**                                         |              |
| LED                                              |       x      |
| Button                                           |       x      |
| 24V IO                                           |       x      |
| **I2C**                                          |              |
| system EEPROM parsing                            |       x      |
| PMIC                                             |       x      |
| **eMMC / SD**                                    |              |
| Read                                             |       x      |
| Write                                            |       x      |
| **Ethernet**                                     |              |
| GigE / FEC via Phy on MBa8MPxL                   |       x      |
| GigE / EQOS via Phy on MBa8MPxL                  |       x      |
| **Bootdevices**                                  |              |
| SD-Card on USDHC2                                |       x      |
| eMMC on USDHC3                                   |       x      |
| QSPI-NOR on FlexSPI                              |       x      |
| Serial Downloader                                |       x      |
| **USB**                                          |              |
| USB 3.0 Host / Hub                               |       x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)             |              |
| **QSPI NOR**                                     |              |
| Read with 1-1-1 SDR                              |       x      |
| PP / Erase with 1-1-1 SDR                        |       x      |
| **Cortex M7**                                    |              |
| env settings for starting from TCM               |       x      |
| examples with UART3 as debug console             |       x      |

**TODO or not tested / supported**

* CPU variants i.MX8MPD/S and Lite

### Linux

**Note:** For Linux 6.1 based on NXP / vendor branch prefer using `kirkstone` branch.

#### MBa8MPxL

| Feature                                                      |   fslc-6.1  |   fslc-6.6  |    6.12.y   |
|:-------------------------------------------------------------|:-----------:|:-----------:| :---------: |
| RAM configs                                                  | 1,2,4,8 GiB | 1,2,4,8 GiB | 1,2,4,8 GiB |
| CPU variants                                                 |  i.MX8MPQ   |  i.MX8MPQ   |  i.MX8MPQ   |
| Fuses / OCRAM                                                |      x      |      x      |      x      |
| speed grade / temperature grade detection                    |      x      |      x      |      x      |
| **UART**                                                     |             |             |             |
| console on UART4 (via USB / UART converter) (X28 USB If 3)   |      x      |      x      |      x      |
| UART3 via USB UART converter (X28 USB If 2)                  |      x      |      x      |      x      |
| UART1 / UART2 via pin header                                 |             |             |      x      |
| **GPIO**                                                     |             |             |             |
| LED                                                          |      x      |      x      |      x      |
| Button                                                       |      x      |      x      |      x      |
| 24V IO                                                       |             |             |      x      |
| **I2C**                                                      |             |             |      x      |
| EEPROMs                                                      |      x      |      x      |      x      |
| PMIC                                                         |      x      |      x      |             |
| RTC                                                          |      x      |      x      |      x      |
| Temperature Sensors                                          |      x      |      x      |             |
| **ENET**                                                     |             |             |      x      |
| GigE / FEC via Phy on MBa8MPxL                               |      x      |      x      |      x      |
| GigE / EQOS via Phy on MBa8MPxL                              |      x      |      x      |      x      |
| **USB**                                                      |             |             |             |
| USB 3.0 Host / Hub                                           |      x      |      x      |      x      |
| USB DRD (USB 3.0 Cable Detect, VBUS)                         |      x      |      x      |      x      |
| **QSPI NOR**                                                 |             |             |             |
| Read with 1-1-4 SDR                                          |      x      |      x      |      x      |
| PP / Erase with 1-1-1 SDR                                    |      x      |      x      |      x      |
| **Graphic / Multimedia**                                     |             |             |             |
| GPU                                                          |      x      |      x      |      x      |
| VPU                                                          |      x      |      x      |             |
| **Display**                                                  |             |             |             |
| LVDS                                                         |      x      |      x      |             |
| Dual-Channel LVDS                                            |      x      |      x      |             |
| HDMI                                                         |      x      |      x      |      x      |
| MIPI-DSI (DP Bridge)                                         |             |             |      x      |
| **Audio**                                                    |             |             |             |
| HDMI                                                         |      x      |      x      |             |
| Codec (Line IN / Line OUT)                                   |      x      |      x      |      x      |
| **PCIe**                                                     |             |             |             |
| wireless card at M.2                                         |      x      |      x      |             |
| **CAN-FD**                                                   |             |             |             |
| CAN-FD                                                       |      x      |      x      |      x      |
| **SPI**                                                      |             |             |             |
| spidev at all CS                                             |      x      |      x      |             |
| ADC                                                          |      x      |      x      |      x      |
| **Cortex M7**                                                |             |             |             |
| examples running from TCM                                    |      x      |      x      |             |
| use UART3 as debug console (see issues)                      |      x      |      x      |             |
| **MIPI CSI (see Issues section)**                            |             |             |             |
| Gray with Vision Components GmbH camera (Sensor OV9281)      |      x      |      x      |             |
| Raw Bayer with Vision Components GmbH camera (Sensor IMX327) |      x      |      x      |             |
| **NPU**                                                      |             |             |             |
| NPU                                                          |             |      x      |             |

#### MBa8MP-RAS314

| Feature                                                      |    6.12.y   |
|:-------------------------------------------------------------| :---------: |
| RAM configs                                                  | 1,2,4,8 GiB |
| CPU variants                                                 |  i.MX8MPQ   |
| Fuses / OCRAM                                                |      x      |
| speed grade / temperature grade detection                    |      x      |
| **UART**                                                     |             |
| console on UART4 (via USB / UART converter) (X4 USB If 2)    |      x      |
| UART3 via USB UART converter (X4 USB If 1)                   |      x      |
| **GPIO**                                                     |             |
| LED                                                          |      x      |
| **I2C**                                                      |      x      |
| EEPROMs                                                      |      x      |
| PMIC                                                         |             |
| RTC                                                          |      x      |
| Temperature Sensors                                          |      x      |
| **ENET**                                                     |      x      |
| GigE / FEC via Phy on MBa8MPxL                               |      x      |
| GigE / EQOS via Phy on MBa8MPxL                              |      x      |
| **USB**                                                      |             |
| USB 3.0 Host / Hub (X12/X13)                                 |      x      |
| USB Device (X14)                                             |      x      |
| **QSPI NOR**                                                 |             |
| Read with 1-1-4 SDR                                          |      x      |
| PP / Erase with 1-1-1 SDR                                    |      x      |
| **Graphic / Multimedia**                                     |             |
| GPU                                                          |      x      |
| VPU                                                          |      x      |
| **Display**                                                  |             |
| LVDS                                                         |             |
| HDMI                                                         |      x      |
| **Audio**                                                    |             |
| HDMI                                                         |             |
| Codec (Headphone / Mic In)                                   |      x      |
| **PCIe**                                                     |             |
| wireless & BT card on-board                                  |      x      |
| **MIPI CSI**                                                 |             |
| Raspberry Pi Camera Module 2                                 |             |
| **NPU**                                                      |             |
| NPU                                                          |             |

## TODO / Untested

### MBa8MPxL

* UART1/UART2 via pin header
* SPI via pin header
* I²C interface of PCIe Clock generator not tested

### MBa8MP-RAS314

* HDMI Audio

## Known Issues / Limitations

* REV.020x SoM without variant data in EEPROM (prototypes)
  * With the default U-Boot configuration the boot flow is interrupted in
    SPL and waits for input of the assembled RAM size (1, 2, 4, 8).
  * Use fixed 2GB U-Boot configuration. This is built by default `UBOOT_CONFIG`
    entries `sd-2gb` and `uuu-2gb`
    **Note:** the generated wic-File uses the U-Boot multi RAM config
* MIPI CSI
  * driver stack is not completely v4l2-compliance test proof. The IOCTLS for format / resolution
    enumeration and query can return invalid / wrong values depending of the internal state
    of the driver stack. Please follow given examples for a working setup.
  * IMX327: bayer support with 12 Bit does not work at the moment, only 10 Bit with
    1280x720 is tested with gstreamer
  * IMX327: when configuring to SRGGB12 reboot may be needed to get a working
    capture again
  * OV9281: gstreamer does not support Y10 format
* Ethernet
  * Possible communication error to PHY attached to FEC, reboot required to fix
  * ETH1 looses manual assigned IP after suspend/resume. Default systemd network
    configuration uses DHCP with fallback. Has to be adjusted if needed.
* A reset using button S7 will not reset the SD card properly. Especially if the SD
  card is operating on 1.8V it will be non-functional after reset. Use button S8 instead.
* PCIe
  * Some WiFi modules might not be supported
  * Suspend to RAM not supported. Disable PCIe RC node in device tree
  ```
  &pcie {
    status = "disabled";
  };
  ```
* Audio
  * Suspend to RAM does not work reliably. Disable `sound` node in device tree
  ```
  sound {
    compatible = "fsl,imx-audio-tlv320aic32x4";
    [...]
    status = "disabled";
  };
  ```
* USB Host
  * USB Superspeed U3 powersave mode is broken
  * U-Boot: some USB stick types are not working correctly in U-Boot.
    After `usb reset` they may fail to enumerate correctly or causing
    a system reset during enumeration with errors like:
    ```
    WARN halted endpoint, queueing URB anyway.
    Unexpected XHCI event TRB, skipping... (fbf120f0 00000000 13000000 03008401)
    BUG at drivers/usb/host/xhci-ring.c:496/abort_td()!
    BUG!
    �esetting ...
    ```
* USB Bluetooth:
  * Some adapters cause the following error during bootup  
    `Bluetooth: hci0: unexpected event for opcode 0xfc2f`  
    According to https://lkml.org/lkml/2019/6/6/868 this can be ignored
* UBI / UBIFS images are enabled by default when using `DISTRO=spaetzle[-nxp]`.
  The generated rootfs size must not exceed the size defined by `UBI_LEB_SIZE` and
  `UBI_MAX_LEB_COUNT` on machine level.
* Kernel based on linux-imx / linux-fslc: Review indicated that, by default, device tree
  reserves memory for NPU in an area which is only available on 4GiB DDR RAM config.
  Upstream kernel is not affected.
* Kernel based on linux-tq / linux-rt-tq
  * Suspend & resume not supported (yet)
* MBa8MP-RAS314
  * no support for vendor kernel and BSP
* When using HDMI the default audio device changes to HDMI output.
  For using I2S audio codec `aplay` requires the parameter `-Dsysdefault:CARD=tqmtlv320aic32`
* The HDMI audio device has to be selected explicitely by passing `-Dsysdefault:CARD=audiohdmi` to `aplay` & friends
* U-Boot: watchdog will reset the system after using `wdt start [timeout]`.  
  Watchdog is enabled but not configured for automatic servicing.
  If needed, `CONFIG_WATCHDOG` can be activated in defconfig.
* U-Boot: not booting when building with secure boot enabled. With updating to NXP base v2024.04
  the size of SPL is larger than with older versions. It is recommended to build with only one
  RAM configuration enabled. For example, use `TQMA8MPXL_RAM_SINGLE_2GB` instead of `TQMA8MPXL_RAM_MULTI`
  in U-Boot defconfig. Other config settings can easily be added.

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

### MBa8MPxL specific

* \*.dtb: device tree blobs
  * imx8mp-tqma8mpql-mba8mpxl.dtb
  * imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb (LVDS support with TIANMA TM070JVHG33)
  * imx8mp-tqma8mpql-mba8mpxl-imx327.dtb (Vision Components CSI camera with Sony IMX327)
  * imx8mp-tqma8mpql-mba8mpxl-ov9281.dtb (Vision Components CSI camera with OmniVision OV9281)
  * imx8mp-tqma8mpql-mba8mpxl-rpmsg.dtb (CortexM / RPMSG Support)

### MBa8MP-RAS314 specific

* \*.dtb: device tree blobs
  * imx8mp-tqma8mpql-mba8mp-ras314.dtb

### All boards

* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_spl\_uboot: boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_evk\_flexspi: boot stream for FlexSPI
* imx-boot-${MACHINE}-uuu.bin-flash\_spl\_uboot:  boot stream for UUU
* imx-boot-${MACHINE}-ecc.bin-flash\_spl\_uboot: boot stream with inline ECC for SD / eMMC
* imx-boot-${MACHINE}-ecc.bin-flash\_evk\_flexspi: boot stream with inline ECC for FlexSPI
* hello\_world.bin (Cortex M7 demo, UART3, TCM)
* rpmsg\_lite\_pingpong\_rtos\_linux\_remote.bin (Cortex M7 demo, UART3, TCM)

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1 (MBa8MPxL) or S4 (MBa8MP-RAS314).

### Serial Downloader

*BOOT\_MODE: 0001*

| DIP S1/S4  | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         | x |   |   |   |
| Off        |   | x | x | x |

### eMMC (USDHC3)

BOOT\_MODE: 0010

| DIP S1/S4  | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         |   | x |   |   |
| Off        | x |   | x | x |

### SD Card (USDHC2)

BOOT\_MODE: 0011

| DIP S1/S4  | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         | x | x |   |   |
| Off        |   |   | x | x |

### FlexSPI / 3B Read

BOOT\_MODE: 0110

| DIP S1/S4  | 1 | 2 | 3 | 4 |
| ---------- | - | - | - | - |
| On         |   | x | x |   |
| Off        | x |   |   | x |

## Boot device initialisation and update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### OS boot

See the [Distroboot README](README.Distroboot.md).

__Note:__ Default u-boot environment variable `bootcmd` has to be set to `run distro_bootcmd`

### OS updates

See [RAUC](RAUC.md).

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

### MIPI-CSI

#### Vision Components GmbH cameras

*Note*: see known issue section above.

##### Gray with Omnivision OV9281

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-ov9281.dtb`

gstreamer examples:

```
# configure
yavta --format Y8 --size 1280x800 --capture=1 /dev/video0

# grab to file
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
	videorate ! video/x-raw,format=GRAY8,framerate=1/1 ! jpegenc ! multifilesink location=test%d.jpg

# show live video
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-raw,format=GRAY8,width=1280,height=800 ! \
  videoconvert ! waylandsink sync=false
```

##### Raw Bayer with Sony IMX327

* Devicetree: `imx8mp-tqma8mpql-mba8mpxl-imx327.dtb`
* gstreamer example:

```
# show live video
gst-launch-1.0 v4l2src device=/dev/video0 ! video/x-bayer,format=rggb10le,bpp=10,width=1280,height=720 ! \
  bayer2rgb ! waylandsink sync=false
```

### Display Support

HDMI support is enabled by default. Additionally LVDS display can be enabled by using the
corresponding device tree. To allow reusage, the support for each display is separated
in a dtsi fragment.

#### MBa8MPxL

| Interface       | Device tree                                    | Type               |
|-----------------|------------------------------------------------|--------------------|
| HDMI            | imx8mp-tqma8mpql-mba8mpxl.dtb                  | compatible monitor |
| LVDS            | imx8mp-tqma8mpql-mba8mpxl-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| LVDS, dual      | imx8mp-tqma8mpql-mba8mpxl-lvds-g133han01.dtb   | AUO G133HAN.01     |

#### MBa8MP-RAS314

| Interface       | Device tree                                    | Type               |
|-----------------|------------------------------------------------|--------------------|
| HDMI / DP       | imx8mp-tqma8mpql-mba8mp-ras314.dtb             | compatible monitor |

*Note*: `weston` by default uses the DRI device with highest number. This is usually Display Port.
To explicitely select a DRI device, please refer to `--drm-device` argument during startup.

### CAN (MBa8MPxL only)

#### Troubleshooting

In case of problems first check the bus termination:

| Interface | Connector | DIP |
| --------- | --------- | --- |
| CAN0      | X18       | S12 |
| CAN1      | X19       | S11 |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

### Cortex M7

Demos are compiled to use UART3 with 115200 8N1.
For demos available in the BSP and the device tree to be used see [artifacts section](#build-artifacts).

Detailed documentation for CortexM support can be found [here](./README.CortexM-on-IMX8M.md).

### NPU

```
cd /usr/bin/tensorflow-lite-2.15.0/examples
./label_image -m mobilenet_v1_1.0_224_quant.tflite --external_delegate_path=/usr/lib/libvx_delegate.so
```
__Note:__ There is some notable warmup time

### High Assurance Boot (Secure Boot)

See [i.MX High Assurance Boot](README.Verified-Boot.md).

### Inline ECC

The i.MX8MP DDR controller supports inline ECC, i.e. using part of RAM for
ECC data without additional sideband RAM. To use this feature, a special boot
stream is needed. The U-Boot in this boot stream  will add a reserved memory
node to the kernel device tree. The reserved region covers 1/8 of total RAM
size and is located at the top of RAM. This region is used for storing ECC
parity bits.

To build the ECC boot stream, add the `ecc` configuration to `UBOOT_CONFIG`
(added by default) and rebuild the boot stream:
```
bitbake imx-boot
```

Replace the current boot stream with the ECC boot stream on SD card or directly
in the wic image:
```
dd if=imx-boot-${MACHINE}-ecc.bin-flash_spl_uboot of=/dev/<SD card device> bs=1K seek=32 conv=fsync
# OR
dd if=imx-boot-${MACHINE}-ecc.bin-flash_spl_uboot of=<path/to/wic/image> bs=1K seek=32 conv=notrunc
```

To test the ECC functionality, the following procedure can be used:

Requirements:

- Boot stream with ECC support: `UBOOT_CONFIG` contains `ecc` (enabled by default)
- Synopsys EDAC support on Linux: `CONFIG_EDAC_SYNOPSYS=(y|m)`
- user space access to all of `/dev/mem` for Linux: `CONFIG_STRICT_DEVMEM=n`
- `devmem` executable in image

Addresses:

- base address of DDRC memory map: `0x3d400000`
- ECC test addresses

RAM size | `ECC_PARITY_REGION_CORRUPTION_ADDR` | `DATA_REGION_CORRUPTION_ADDR`
-------- | ----------------------------------- | -----------------------------
1GB      | `0x79000000`                        | `0x77000000`
2GB      | `0xB2000000`                        | `0xAE000000`
4GB      | `0x124000000`                       | `0x11C000000`
8GB      | `0x208000000`                       | `0x1F8000000`

**Attention:** Corrupting ECC parity data can affect processes using the
memory at `${DATA_REGION_CORRUPTION_ADDR}`. Use this only for development
purposes.

Steps:
1. Enable software write to DDRC registers (set SWCTL (offset `0x320`)
   register to `0`)
```
devmem 0x3d400320 32 0x0
```
2. Disable ECC parity region lock (set ECC_REGION_PARITY_LOCK (Bit 4) to `0` in
   ECCCFG1 (offset `0x74`) register)
```
devmem 0x3d400074 32
0x790
devmem 0x3d400074 32 0x780
```
3. Corrupt ECC parity bit
   - Single bit error (correctable): Change one bit at
     `ECC_PARITY_REGION_CORRUPTION_ADDR`
```
# Example
devmem ${ECC_PARITY_REGION_CORRUPTION_ADDR} 8
0x03
devmem ${ECC_PARITY_REGION_CORRUPTION_ADDR} 8 0x01
```
   - Double bit error (uncorrectable): Change two bits at
     `ECC_PARITY_REGION_CORRUPTION_ADDR`
```
# Example
devmem ${ECC_PARITY_REGION_CORRUPTION_ADDR} 8
0x03
devmem ${ECC_PARITY_REGION_CORRUPTION_ADDR} 8 0x00
```
4. Read corrupted data area
```
devmem ${DATA_REGION_CORRUPTION_ADDR}
```
5. Show error counters
```
cat /sys/devices/system/edac/mc/mc0/ce_count
cat /sys/devices/system/edac/mc/mc0/ue_count
```

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

### PREEMPT-RT / Realtime support

For Preempt-RT see [Linux Preempt-RT on i.MX](./README.Preempt-RT.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa8MPxL](https://support.tq-group.com/en/arm/tqma8mpxl)
