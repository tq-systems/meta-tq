#

# Copyright (C) 2017-2023 NXP
# Copyright (C) 2022-2024 TQ-Systems GmbH

DESCRIPTION = "i.MX ARM Trusted Firmware"
SECTION = "BSP"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

PROVIDES += "imx-atf"

PV .= "+git"

ATF_BRANCH = "TQM-lf_v2.10"
ATF_SRC = "${TQ_GIT_BASEURL}/atf.git;protocol=${TQ_GIT_PROTOCOL}"

SRC_URI = "${ATF_SRC};branch=${ATF_BRANCH}"
SRCREV = "a1946103fbb4718e9e41b947542b270c9530818b"

S = "${WORKDIR}/git"

inherit deploy

ATF_PLATFORM ??= "INVALID"

# We return INVALID here since this is highly machine dependend.
# Currently only i.MX8[M,MMini,MNano,MPlus] and i.MX93 have support to override
# the debug / boot UART base address.
ATF_IMX_BOOT_UART_BASE ??= "INVALID"

# We return INVALID here since this is highly machine dependend.
# Currently only i.MX8QM / i.MX8[D,Q]XP have support to override the UART index.
ATF_IMX_DEBUG_UART ??= "INVALID"

# Debug console enable for i.MX8QXP / i.MX8QM
ATF_IMX_DEBUG_CONSOLE ??= "0"

EXTRA_OEMAKE += " \
    CROSS_COMPILE=${TARGET_PREFIX} \
    PLAT=${ATF_PLATFORM} \
"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

# Baremetal, just need a compiler
INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "virtual/${HOST_PREFIX}gcc"

# Bring in clang compiler if using clang as default
DEPENDS:append:toolchain-clang = " clang-cross-${TARGET_ARCH}"

BUILD_OPTEE = "${@bb.utils.contains('MACHINE_FEATURES', 'optee', 'true', 'false', d)}"

# CC and LD introduce arguments which conflict with those otherwise provided by
# this recipe. The heads of these variables excluding those arguments
# are therefore used instead.
def remove_options_tail (in_string):
    from itertools import takewhile
    return ' '.join(takewhile(lambda x: not x.startswith('-'), in_string.split(' ')))

# LD can have linker suffix in its name e.g. aarch64-yoe-linux-ld.lld so we need to
# drop .lld as well along with options from LD
EXTRA_OEMAKE += 'LD="${HOST_PREFIX}ld.bfd"'

EXTRA_OEMAKE += 'CC="${@remove_options_tail(d.getVar('CC'))}"'

# Set the UART to use during the boot for i.MX8M.
IMX_EXTRA_OEMAKE_UART:mx8m-generic-bsp += "\
    IMX_BOOT_UART_BASE=${ATF_IMX_BOOT_UART_BASE} \
"

# Set the UART to use during the boot for i.MX93 and upcoming.
IMX_EXTRA_OEMAKE_UART:mx9-generic-bsp += "\
    IMX_LPUART_BASE=${ATF_IMX_BOOT_UART_BASE} \
"

IMX8_EXTRA_OEMAKE_UART = "\
    IMX_DEBUG_UART=${ATF_IMX_DEBUG_UART} \
    DEBUG_CONSOLE=${ATF_IMX_DEBUG_CONSOLE} \
"

# Set the UART to use during the boot for i.MX8QM.
IMX_EXTRA_OEMAKE_UART:mx8qm-generic-bsp = "${IMX8_EXTRA_OEMAKE_UART}"

# Set the UART to use during the boot for i.MX8[D,Q]XQP.
IMX_EXTRA_OEMAKE_UART:mx8x-generic-bsp += "${IMX8_EXTRA_OEMAKE_UART}"

EXTRA_OEMAKE += "${IMX_EXTRA_OEMAKE_UART}"

do_configure[noexec] = "1"

do_compile() {
    # Clear LDFLAGS to avoid the option -Wl recognize issue
    oe_runmake bl31
    if ${BUILD_OPTEE}; then
        oe_runmake clean BUILD_BASE=build-optee
        oe_runmake BUILD_BASE=build-optee SPD=opteed bl31
    fi
}

do_install[noexec] = "1"

addtask deploy after do_compile
do_deploy() {
    install -Dm 0644 ${S}/build/${ATF_PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${ATF_PLATFORM}.bin
    if ${BUILD_OPTEE}; then
        install -m 0644 ${S}/build-optee/${ATF_PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${ATF_PLATFORM}.bin-optee
    fi
}

# needed, since we add machine specific settings
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma8mpxl = "tqma8mpxl"
COMPATIBLE_MACHINE:tqma8mpxs = "tqma8mpxs"
COMPATIBLE_MACHINE:tqma8mq = "tqma8mq"
COMPATIBLE_MACHINE:tqma8mxml = "tqma8mxml"
COMPATIBLE_MACHINE:tqma8mxnl = "tqma8mxnl"
COMPATIBLE_MACHINE:tqma8x = "tqma8x"
COMPATIBLE_MACHINE:tqma8xx = "tqma8xx"
COMPATIBLE_MACHINE:tqma8xxs = "tqma8xxs"
COMPATIBLE_MACHINE:tqma91xx = "tqma91xx"
COMPATIBLE_MACHINE:tqma93xx = "tqma93xx"
