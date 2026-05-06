# Inline ECC

The i.MX9X DDR controller supports inline ECC, i.e. using part of RAM for
ECC data without additional sideband RAM. Enabling this feature requires
a modified DDR controller initialization and a dedicated boot stream, which
adds a reserved-memory node to the kernel device tree. This region located at
the top of RAM is then used for storing ECC parity bits.
In this example, inline ECC is enabled for the entire memory, resulting in 1/8
of the total RAM being reserved for ECC data.

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
please consult the NXP application note AN14438 "ECC on i.MX 93 and i.MX 91".

Production Requirements:

- Boot stream with ECC support: enabled by default if `UBOOT_CONFIG` contains `ecc`
- Layerscape EDAC support on Linux: `CONFIG_EDAC_LAYERSCAPE=(y|m)` (enabled by default in BSP)

Test Requirements:

**Attention:** Don't enable these options in production builds! Corrupting ECC
parity data can affect processes using the ECC memory.

- enable error-injection via debugfs files on Linux: `CONFIG_EDAC_DEBUG=y`
1. Configure `ERR_INJECT`:
   - [31] `ADDR_TEN = 1` to enable address triggering for error injection
   - [22-21] `ECC_INJ_SRC = 00b` use programmed SDRAM injection addresses
   - [8] `EIEN = 1` to enable error injection
   ```
   cd /sys/bus/edac/devices/mc/mc0/
   echo 0x80000100 > inject_ctrl
   ```
2. Trigger Single-Bit Error (SBE)
   ```
   echo 0x01 > inject_data_lo # SBE
   # Note: triggers kernel log (see dmesg -w)
   ```
3. Show error counters
   ```
   grep . *_count
   ```
4. Trigger 2-Bit Error (2BE)
   ```
   echo 0x03 > inject_data_lo # 2BE
   # Note: causes kernel panic
   ```
