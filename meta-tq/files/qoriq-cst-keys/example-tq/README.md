Endianess of both OTPMK and the SRK hash found in `srk_hash.txt` must be swapped
when programming from U-Boot.

Program minimal OTPMK fuses using the following commands:
```
mw.l 0x1e80234 0x0f000000
md 0x1e80234 8
# On LS1028A, LS1088A, LS2088A, LX2160A, LX2162A:
mw 0x1e80020 0x2
# On LS1021A, LS1012A, LS1043A, LS1046A:
mw 0x1e80020 0x02000000
```

**WARNING**: Only for development purposes! A board-specific OTPMK can be
generated using `gen_otpmk_drbg -b 2` from qoriq-cst.

Program SRK hash fuses using the following commands:
```
mw.l 0x1e80254 0x30421fd7
mw.l 0x1e80258 0x9457cd2f
mw.l 0x1e8025c 0x4d6a5f72
mw.l 0x1e80260 0x3f88994a
mw.l 0x1e80264 0x1878aad6
mw.l 0x1e80268 0x0308ced4
mw.l 0x1e8026c 0xce032752
mw.l 0x1e80270 0x220c68d9
md 0x1e80254 8
# On LS1028A, LS1088A, LS2088A, LX2160A, LX2162A:
mw 0x1e80020 0x2
# On LS1021A, LS1012A, LS1043A, LS1046A:
mw 0x1e80020 0x02000000
```

**WARNING**: Only for development purposes! The key cannot be modified after
it has been programmed. A project-specific key must be generated for production
using the tool `gen_keys` from qoriq-cst.

See also:
- [Byte swap for reading and writing SRKH/OTPMK](https://docs.nxp.com/bundle/GUID-487B2E69-BB19-42CB-AC38-7EF18C0FE3AE/page/GUID-036CF49E-4211-48B8-803A-870C57777C8A.html)
- [Program OTPMK](https://docs.nxp.com/bundle/GUID-487B2E69-BB19-42CB-AC38-7EF18C0FE3AE/page/GUID-F780D1D5-F1B2-478B-86AE-267D74F9C790.html)
- [Program SRKH mirror registers in U-Boot environment](https://docs.nxp.com/bundle/GUID-487B2E69-BB19-42CB-AC38-7EF18C0FE3AE/page/GUID-2CF1D60F-C79F-4A35-800B-2BFE504EBAC5.html)
- [Write SFP_INGR register](https://docs.nxp.com/bundle/GUID-487B2E69-BB19-42CB-AC38-7EF18C0FE3AE/page/GUID-EFF8FF41-C8C0-4A3B-AF95-E801D585B7C6.html)
