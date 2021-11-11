FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://0002-Add-some-debug-output-1st-attempt-DDR-initialization.patch \
	file://0003-replace-hihope-boards-with-TQMaRZGx-modules.patch \
	file://0004-Add-support-for-new-SPI-NOR-Flash-type.patch \
	file://0005-Add-DRAM-initialization-code-for-TQMaRZG2N-H-M.patch \
	file://0006-TQMaRZG2x-build-module-specific-binaries.patch \
"
