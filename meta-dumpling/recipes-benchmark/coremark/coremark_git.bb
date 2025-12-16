# SPDX-License-Identifier: MIT
# based on recipe from https://git.yoctoproject.org/meta-amd
# commit 46869ba4fa8b ("Added recipe for coremark benchmark tool")

DESCRIPTION = "The CoreMark benchmark allows performance measurements for multicore CPU"

SUMMARY = "CoreMark is a benchmark that measures the performance of central processing units (CPU) \
used in embedded systems. It was intended to replace the Dhrystone benchmark. The code is \
witten in C and contains implementations of the following algorithms: \
list processing (find and sort), matrix manipulation (common matrix operations), \
state machine (determine if an input stream contains valid numbers), and CRC. \
The code is under the Apache License 2.0 and is free of cost to use, but ownership is retained \
by the Embedded Microprocessor Benchmark Consortium and publication of modified versions under \
the CoreMark name is prohibited. \
"

HOMEPAGE = "https://www.eembc.org/coremark"

SECTION = "benchmark/tests"

# note restrictions and trademark limitations for publishing modified versions
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=0a18b17ae63deaa8a595035f668aebe1"

SRC_URI = "git://github.com/eembc/coremark.git;branch=main;protocol=https"
SRCREV = "1f483d5b8316753a742cbf5590caf5bd0a4e4777"

S = "${WORKDIR}/git"
TARGET_CC_ARCH += "${LDFLAGS}"

CORE_VARIANTS ?= "1 2 4 8 16"
do_compile() {
	for cores in ${CORE_VARIANTS}; do
		oe_runmake XCFLAGS="-DMULTITHREAD=${cores} -DUSE_FORK=1" OPATH=./cores_${cores}/ compile
	done
}

do_install() {
	install -d ${D}${bindir}
	for cores in ${CORE_VARIANTS}; do
		install -m 0755 ./cores_${cores}/coremark.exe ${D}${bindir}/coremark${cores}
	done
}

COMPATIBLE_HOST = '(i.86|x86_64|arm|aarch64).*-linux'
