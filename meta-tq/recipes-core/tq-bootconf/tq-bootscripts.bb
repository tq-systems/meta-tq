SUMMARY = "boot.scr for U-Boot Standard Boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "dtc-native u-boot-mkimage-native"

inherit deploy

SRC_URI = "\
    file://boot-blockdev.cmd \
    file://boot-blockdev-rauc.cmd \
    file://boot-ubi.cmd \
"
VARIANTS = "\
    boot-blockdev \
    boot-blockdev-rauc \
    boot-ubi \
"

build_scr () {
    local input="$1" output="$2"

    cat >boot.its <<END
/dts-v1/;

/ {
        description = "U-Boot boot script";

        images {
                default = "script-1";

                script-1 {
                        compression = "none";
                        data = /incbin/("$input");
                        type = "script";

                        hash-1 {
                                algo = "crc32";
                        };
                };
        };
};
END

    mkimage -f boot.its "${output}"
}

do_compile() {
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
