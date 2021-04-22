
SRCBRANCH = "TQMa8-imx_5.4.70_2.3.0"
SRC_URI = " \
    ${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
"

SRCREV = "cfccd003a1c08655e25bc8d8fdfa1938b0dbda36"

PLATFORM        ?= "INVALID"

# UART3
ATF_UART_BASE_tqma8mx ?= "0x30880000"
ATF_UART_BASE_tqma8mxml ?= "0x30880000"
ATF_UART_BASE_tqma8mxnl ?= "0x30880000"
# UART4
ATF_UART_BASE_tqma8mpxl ?= "0x30a60000"

# debug uart index (lpuart<n>)
ATF_IMX_DEBUG_UART ?= "0"
ATF_IMX_DEBUG_UART_tqma8x ?= "0"
ATF_IMX_DEBUG_UART_tqma8xx ?= "0"
ATF_IMX_DEBUG_UART_tqma8xxs ?= "0"

# enable / disable debug console
ATF_DEBUG_CONSOLE ?= "0"

EXTRA_OEMAKE_append_mx8m = " \
    IMX_BOOT_UART_BASE=${ATF_UART_BASE} \
"

EXTRA_OEMAKE_append_mx8qm += " \
	IMX_DEBUG_UART=${ATF_IMX_DEBUG_UART} \
	DEBUG_CONSOLE=${ATF_DEBUG_CONSOLE} \
"

EXTRA_OEMAKE_append_mx8x += " \
	IMX_DEBUG_UART=${ATF_IMX_DEBUG_UART} \
	DEBUG_CONSOLE=${ATF_DEBUG_CONSOLE} \
"

# needed, since we add machine specific settings
PACKAGE_ARCH = "${MACHINE_ARCH}"
