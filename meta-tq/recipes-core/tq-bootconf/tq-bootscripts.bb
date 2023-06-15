SUMMARY = "boot.scr for U-Boot Standard Boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "dtc-native u-boot-mkimage-native"

inherit deploy

SRC_URI = "\
    file://boot.its \
    file://boot-blockdev.cmd \
"
VARIANTS = "boot-blockdev"

build_scr () {
    local input="$1" output="$2"

    ln -sf "${input}" boot.cmd
    mkimage -f boot.its "${output}"
}

do_compile() {
    # Add symlink to build directory - /incbin/ requires the ITS and the
    # referenced boot.cmd in the same directory
    ln -sf ${WORKDIR}/boot.its .

    for variant in ${VARIANTS}; do
        build_scr "${WORKDIR}/${variant}.cmd" "${variant}.scr"
    done
}

do_deploy() {
    for variant in ${VARIANTS}; do
        install -m 644 "${variant}.scr" "${DEPLOYDIR}/${variant}.scr"
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
