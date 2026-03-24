SUMMARY = "i.MX System Manager Firmware for TQ modules"
DESCRIPTION = "\
The System Manager (SM) is a firmware that runs on a Cortex-M processor on \
many NXP i.MX processors. The Cortex-M is the boot core, runs the boot ROM \
which loads the SM (and other boot code), and then branches to the SM. The \
SM then configures some aspects of the hardware such as isolation mechanisms \
and then starts other cores in the system. After starting these cores, it \
enters a service mode where it provides access to clocking, power, sensor, \
and pin control via a client RPC API based on ARM's System Control and \
Management Interface (SCMI). This recipe provides the implementation for \
TQ-Systems SOM"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=f2a70813bc08547f509361c08b718861"

SRC_URI = "${IMX_SYSTEM_MANAGER_SRC};branch=${SRCBRANCH}"
IMX_SYSTEM_MANAGER_SRC = "${TQ_GIT_BASEURL}/tq-imx-sm.git;protocol=${TQ_GIT_PROTOCOL}"

SRCBRANCH = "TQM-lf-6.18.2"
SRCREV = "276eb5537c558e5fbbb28c800f50ca86e1074cce"

require dynamic-layers/arm-toolchain/recipes-bsp/imx-system-manager/imx-system-manager.inc

# for production releases monitor and console needs to be disabled
# Pass "c0 m0" in this case
SYSTEM_MANAGER_DEBUG ?= "0"

# using 'c1 m0' can be used for informal debug output without monitor while
# 'c0 m0' is intended for release builds
PACKAGECONFIG[c0] = ",,,,,c1 m1 m2"
PACKAGECONFIG[c1] = "C=1,,,,,c0"

PACKAGECONFIG_FOR_RELEASE = "m0 c0"
PACKAGECONFIG_FOR_DEBUG = "c1 m1"

PACKAGECONFIG ?= "${@oe.utils.ifelse(int(d.getVar('SYSTEM_MANAGER_DEBUG')), '${PACKAGECONFIG_FOR_DEBUG}', '${PACKAGECONFIG_FOR_RELEASE}')}"

# should be the output file name w/o extension
SYSTEM_MANAGER_FIRMWARE_BASENAME ?= "m33_image"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
