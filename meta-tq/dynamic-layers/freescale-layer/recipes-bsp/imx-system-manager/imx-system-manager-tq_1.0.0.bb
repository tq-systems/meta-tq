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
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=b66f32a90f9577a5a3255c21d79bc619"

SRC_URI = "${IMX_SYSTEM_MANAGER_SRC};branch=${SRCBRANCH}"
IMX_SYSTEM_MANAGER_SRC = "${TQ_GIT_BASEURL}/tq-imx-sm.git;protocol=${TQ_GIT_PROTOCOL}"
SRCBRANCH = "TQM-lf-6.6.52"
SRCREV = "71f528b1ac59bd53351a439e31f969ad1aed4128"

S = "${WORKDIR}/git"

require dynamic-layers/arm-toolchain/recipes-bsp/imx-system-manager/imx-system-manager.inc

# needs to be removed for production releases
PACKAGECONFIG ?= "m1"

# should be the output file name w/o extension
SYSTEM_MANAGER_FIRMWARE_BASENAME ?= "m33_image"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:tqma95xx = "tqma95xx"
