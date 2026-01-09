
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI += "\
    file://0001-bat-Add-readcapture-option-to-support-analyzing-exte.patch \
    file://0002-alsabat-improve-error-handling-in-bat_init.patch \
    file://0003-bat-fix-the-verbose-compilation-warnings-for-latest-.patch \
    file://0004-reshuffle-included-files-to-include-config.h-as-firs.patch \
    file://0005-bat-use-ATTRIBUTE_UNUSED-instead-remove-argument-nam.patch \
    file://0006-bat-really-skip-analysis-of-the-first-period-and-upd.patch \
    file://0007-bat-Fix-buffer-time-configuration.patch \
"

PACKAGECONFIG:append = " bat "
