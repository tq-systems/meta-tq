# TQMa91xxCA and TQMa91xxLA

This README contains some useful information for TQMa91xxCA and TQMa91xxLA

[[_TOC_]]

## Variants

* TQMa91xxCA / TQMa91xxLA (1 GiB RAM) REV.010x on MBa91xxCA REV.010x
* TQMa91xxCA / TQMa91xxLA (1 GiB RAM) REV.010x on MBa93xxCA REV.020x

## Version information for software components

See [here](./README.SoftwareVersions.md) for the software base versions.

## Supported machine configurations

See top level [README](../README.md) for configurations usable as MACHINE.

* tqma91xx-mba91xxca
* tqma91xx-mba93xxca

## Supported Features

### U-Boot

Support matrix for `MBa91xxCA` REV.010x / `MBa93xxCA` REV.020x

|                    Feature                      | u-boot-imx-tq_2024.04 |
| :---------------------------------------------: | :-------------------: |
|                  RAM configs                    |      0.5 / 1 GiB      |
|                  inline ECC                     |          x            |
|                 CPU variants                    |        i.MX91         |
|                 Fuses / OCRAM                   |          x            |
|   speed grade / temperature grade detection     |          x            |
|            UART (console on UART1)              |          x            |
|                   **GPIO**                      |                       |
|                      LED                        |          x            |
|                    Button                       |          x            |
|                    **I2C**                      |                       |
|             system EEPROM parsing               |          x            |
|                     PMIC                        |          x            |
|                **eMMC / SD**                    |                       |
|                     Read                        |          x            |
|                     Write                       |          x            |
|                 **Ethernet**                    |                       |
|        GigE / FEC via Phy on MBa91xxCA          |          x            |
|       GigE / EQOS via Phy on MBa91xxCA          |          x            |
|                **Bootdevices**                  |                       |
|               SD-Card on USDHC2                 |          x            |
|                eMMC on USDHC1                   |          x            |
|              QSPI-NOR on FlexSPI                |          x            |
|               Serial Downloader                 |          x            |
|                    **USB**                      |                       |
|              USB 2.0 Host / Hub                 |          x            |
|     USB DRD (USB 2.0 Cable Detect, VBUS)        |          x            |
| (configured as device to be usable with UUU )   |                       |
|                 **QSPI NOR**                    |                       |
|              Read with 1-1-4 SDR                |          x            |
|           PP / Erase with 1-1-4 SDR             |          x            |

### Linux

Support matrix for `MBa91xxCA` REV.010x / `MBa93xxCA` REV.020x


|                   Feature                     | linux-imx-tq_6.6   |
| :-------------------------------------------: | :----------------: |
|                 RAM configs                   |      1 GiB         |
|                CPU variants                   |      i.MX91        |
|                Fuses / OCRAM                  |        x           |
|  speed grade / temperature grade detection    |                    |
|                  **UART**                     |                    |
| console on UART1 (via USB / UART converter)   |        x           |
|                  UART2                        |        x           |
|                  **GPIO**                     |                    |
|                     LED                       |        x           |
|                   Button                      |        x           |
|                   **I2C**                     |                    |
|                   EEPROMs                     |        x           |
|                    PMIC                       |        x           |
|                     RTC                       |        x           |
|             Temperature Sensors               |        x           |
|               IMU / Gyroscope                 |        x           |
|                Port expander                  |        x           |
|                  **ENET**                     |                    |
|       GigE / FEC via Phy on MBa9[1,3]xxCA     |        x           |
|      GigE / EQOS via Phy on MBa9[1,3]xxCA     |        x           |
|                   **USB**                     |                    |
|             USB 2.0 Host / Hub                |        x           |
|    USB DRD (USB 2.0 Cable Detect, VBUS)       |        x           |
|                **QSPI NOR**                   |                    |
|             Read with 1-1-4 SDR               |                    |
|             Read with 1-4-4 SDR               |                    |
|          PP / Erase with 1-1-4 SDR            |                    |
|          PP / Erase with 1-4-4 SDR            |                    |
|          **Display (MBa91xxCA only)**         |                    |
|               LVDS via Bridge                 |        x           |
|                  DPI / RGB                    |        x           |
|                 **CAN-FD**                    |                    |
|                   CAN-FD                      |        x           |
|                   **SPI**                     |                    |
|              spidev at all CS                 |        x           |
|              **internal ADC**                 |                    |
|                     ADC                       |        x           |

## TODO

* WiFi (driver and firmware loading OK, needs additional testing)
* Bluetooth firmware on MBa91xxLA does not initialize
* optee support

## Important Notes

* U-Boot: USB Type-C port (X17) is usable as device-only under U-Boot
* DVFS is not supported using cpu-freq framework. See [here](#frequency-scaling)
  on how to use frequency scaling
* Ethernet device order is defined by DT aliases. Linux and bootloader DT need to match
* The SPI UBI rootfs Volume has been renamed from `rootfs` to `root`
  to conform with distroboot settings (scarthgap.TQ.ARM.BSP.0007, u-boot 2024.04)

## Known Issues

* 512 MiB: booting Linux with default environment not possible

  The addresses used in default environment and in BSP FIT image generation
  expect variants with 1 GiB or more RAM. Can be fixed manually.
* NFS boot: The interface to be used for NFS boot (`netdev`) has inverted order, compared
  to u-boot and Linux. Device renaming in Linux happens after mounting rootfs.
* U-Boot:
  * Not all USB sticks are detected properly.
  * Using `usb reset` in U-Boot will give a warning from Type-C port controller.  
    The USB controller is configured as device only via device tree to support
    serial download use case.
  * boot from USB using `uuu` config displays misleading pinctrl / iomux warning.  
    The UDC gadget driver warns not only for failed pinmux but also when no pinmux group
    is assigned in device tree.

## Build Artifacts

Artifacs can be found at the usual locations for bitbake:
`${DEPLOY_DIR_IMAGE}` (default: `${DEPLOY_DIR}/images/${MACHINE}`)

* \*.dtb: device tree blobs
  * imx91-tqma9131-mba91xxca*.dtb
* Image: Linux kernel image
* \*.wic[.<compress>]: SD / eMMC system image
* \*.rootfs.tar.gz: RootFS archive (NFS root etc.)
* \*.rootfs.ubifs: UBIFS rootfs (incl. kernel and device trees)
* \*.rootfs.ubi: UBI image containing UBIFS rootfs for SPI-NOR
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot: CortexA boot stream for SD / eMMC
* imx-boot-${MACHINE}-sd.bin-flash\_singleboot\_flexspi: CortexA boot stream for FlexSPI
* imx-boot-${MACHINE}-ecc.bin-flash\_spl\_uboot: boot stream with inline ECC for SD / eMMC
* imx-boot-${MACHINE}-ecc.bin-flash\_evk\_flexspi: boot stream with inline ECC for FlexSPI
* imx-boot-${MACHINE}-uuu.bin-flash\_singleboot: boot stream for UUU

## Boot DIP Switches

BOOT\_MODE can be configured using DIP switch S1 on mainboard.

| Bootmode | Description           | S1-4 | S1-3 | S1-2 | S1-1 |
| :------: | :-------------------: | :--: | :--: | :--: | :--: |
| 0000     | Boot from fuses       | OFF  | OFF  | OFF  | OFF  |
| 0001     | Serial Downloader     | OFF  | OFF  | OFF  | ON   |
| 0010     | eMMC (USDHC1)         | OFF  | OFF  | ON   | OFF  |
| 0011     | SD Card (USDHC2)      | OFF  | OFF  | ON   | ON   |
| 0100     | QSPI (FlexSPI NOR)    | OFF  | ON   | OFF  | OFF  |

## Boot device initialisation and update

See [here](./README.imx.BootMedia.md) for detailed information how to write a
bootstream image and bootloader support for updating the bootstream.

**Note:** For SPI boot it is required to update the script partition once using the following command sequence:

```
tftp boot-ubi.scr
sf probe
sf update ${loadaddr} script ${filesize}
```

## Use UUU Tool

See [here](./README.imx.UUU.md) for details about using Serial Download mode and UUU.

## Howto

### Frequency scaling

See [here](./README.TQMa9-non-scmi-dvfs.md) for details about frequency scaling.

### OS boot

See the [Distroboot README](README.Distroboot.md).

### OS updates

See [RAUC](RAUC.md).

### Using RTC for wakeup

See [here](./README.Wakeup.md) for details about sleep modes and wakeup using RTC.

**Note**: On this platform `rtc0` is the I2C RTC on SoM and `rtc1` is the RTC in CPU BBNS domain

### Display Support (MBa91xxCA)

Each Display can be used on its own by using the corresponding device tree.
To allow reusage, the support for each display is separated in a dtsi fragment.


| Interface | Device tree                                   | Type        ----   |
| --------- | --------------------------------------------- | ------------------ |
| LVDS      | imx91-tqma9131-mba91xxca-lvds-tm070jvhg33.dtb | Tianma TM070JVHG33 |
| DPI/RGB   | imx91-tqma9131-mba91xxca-rgb-cdtech-dc44.dtb  | CDTECH DC44 (DMB)  |

### CAN

#### Troubleshooting

In case of problems first check the bus termination

| Interface | Connector | DIP                             |
| --------- | --------- | ------------------------------- |
| CAN1      | X8        | S4.1 (CAN1\_H) / S4.2 (CAN1\_L) |

See [here](./README.CAN.md) for details about configurating of CAN interfaces.

__Note:__ Values for bitrate, sample-point, dbitrate and dsample-point depend
on your hardware setup.

### RS485

<!-- TODO -->

### High Assurance Boot (Secure Boot)

<!-- TODO -->

### Inline ECC

The i.MX91 DDR controller supports inline ECC, i.e. using part of RAM for
ECC data without additional sideband RAM. To use this feature, a special boot
stream is needed. The U-Boot in this boot stream will add a reserved memory
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

To test the ECC functionality, the procedure below can be used. For further reading
please consult the application note AN14438 "ECC on i.MX 93 and i.MX 91".

Production Requirements:

- Boot stream with ECC support: enabled by default if `UBOOT_CONFIG` contains `ecc`
- Layerscape EDAC support on Linux: `CONFIG_EDAC_LAYERSCAPE=(y|m)`
  - Note: EDAC not available in mainline kernel yet (only included since version 6.13)

Test Requirements:

- user space access to all of `/dev/mem` for Linux: `CONFIG_STRICT_DEVMEM=n`
- `devmem` executable in image
- Optional: `CONFIG_EDAC_DEBUG` for error-injection via debugfs files

Addresses:

- ECC test addresses

RAM size | `DATA_REGION_CORRUPTION_ADDR`
-------- | -----------------------------
512MB    | `0x9E000000`
1GB      | `0xB7000000`
1.5GB    | `0xDC000000`
2GB      | `0xEE000000`

**Attention:** Corrupting ECC parity data can affect processes using the
memory at `${DATA_REGION_CORRUPTION_ADDR}`. Use this only for development
purposes.

Steps:
1. Clear ERR_DETECT flags to keep test honest for 
   next corrupted ECC test
```
devmem 0x4e301140 32 0x8000000D
```
1. set SBET (Bit 16-19) to 4 to allow trigger of SBE error
```
devmem 0x4e301158 32
0x790
devmem 0x4e301158 32 0x00040000
```
1. Define the address that you want to as a trigger 
   when injecting ECC errors
```
devmem 0x4e30110C 32 $DATA_REGION_CORRUPTION_ADDR
```
1. Configure `ERR_INJECT`:
- [31] `ADDR_TEN = 1` to enable address triggering for error injection
- [22-21] `ECC_INJ_SRC = 11b` to enable use of address error injection
- [8] `EIEN = 1` to enable error injection
```
devmem 0x4e301108 32 0x80600100
```
1. Corrupt `DATA_REGION_CORRUPTION_ADDR`
```
# Example
devmem ${DATA_REGION_CORRUPTION_ADDR} 8
0x00
devmem ${DATA_REGION_CORRUPTION_ADDR} 8 0x03
```
1. Show error flags and counters
```
echo `devmem 0x4e301158 32` ERR_SBE
echo `devmem 0x4e301128 32` CAPTURE_ECC
echo `devmem 0x4e301140 32` ERR_DETECT

cat /sys/devices/system/edac/mc/mc0/ce_count
cat /sys/devices/system/edac/mc/mc0/ue_count
```

### Access U-Boot environment from Linux

See [U-Boot environment tools](README.libubootenv.md).

## Support Wiki

See [TQ Embedded Wiki for TQMa91xxCA](https://support.tq-group.com/en/arm/tqma91xxca).
