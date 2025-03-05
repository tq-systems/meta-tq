
require recipes-security/optee-imx/optee-os_4.2.0.imx.bb

# upgrade to 4.4.0
SRCBRANCH = "lf-6.6.52_2.2.0"
SRCREV = "60beb308810f9561a67fdb435388a64c85eb6dcb"

COMPATIBLE_MACHINE:tqma91xx = "tqma91xx"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"

# select based on core/arch/arm/plat-imx/conf.mk
PLATFORM_FLAVOR:tqma91xx = "mx91evk"
PLATFORM_FLAVOR:tqma93xx = "mx93evk"
