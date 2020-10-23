# Copyright (C) 2014 O.S. Systems Software LTDA.
# Copyright (C) 2014-2016 Freescale Semiconductor
# Copyright 2017-2019 NXP
# Copyright 2020 TQ Systems GmbH

require recipes-bsp/u-boot/u-boot-imx-tq_${PV}.bb
require recipes-bsp/u-boot/u-boot-mfgtool.inc

SPL_IMAGE = "${SPL_BINARYNAME}-${MACHINE}-mfgtool-${PV}-${PR}"
SPL_SYMLINK = "${SPL_BINARYNAME}-mfgtool-${MACHINE}"

COMPATIBLE_MACHINE_append = "tqma8mx"
COMPATIBLE_MACHINE_append = "|tqma8mxml"
COMPATIBLE_MACHINE_append = "|tqma8mxnl"

