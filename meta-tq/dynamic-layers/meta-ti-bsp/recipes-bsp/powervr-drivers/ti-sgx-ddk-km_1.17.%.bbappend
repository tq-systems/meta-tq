# If the machine does not set TI_SGX_DDK_KM_KERNVER to 5.4 or 5.10, the
# upstream recipe from meta-ti is used
TI_SGX_DDK_KM_KERNVER ??= ""

# Overridden by our recipe for legacy kernels to disarm the SkipRecipe from
# this .bbappend
TI_SGX_DDK_KM_LEGACY_RECIPE ??= "0"

# If TI_SGX_DDK_KM_KERNVER is 5.4 or 5.10, skip the recipe from meta-ti-bsp
# and use ours instead.
python () {
    if not oe.types.boolean(d.getVar('TI_SGX_DDK_KM_LEGACY_RECIPE')):
        if d.getVar('TI_SGX_DDK_KM_KERNVER') == '5.4':
            raise bb.parse.SkipRecipe('TI_SGX_DDK_KM_KERNVER is 5.4')
        if d.getVar('TI_SGX_DDK_KM_KERNVER') == '5.10':
            raise bb.parse.SkipRecipe('TI_SGX_DDK_KM_KERNVER is 5.10')
}
