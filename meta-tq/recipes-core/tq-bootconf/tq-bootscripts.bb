SUMMARY = "boot.scr for U-Boot Standard Boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "dtc-native u-boot-mkimage-native"

require conf/image-fitimage.conf

EXTRA_INHERIT = ""
# UBOOT_SIGN_KEYDIR depends on ti-secdev.bbclass for K3 machines
EXTRA_INHERIT:k3 = "ti-secdev"

inherit uboot-config deploy ${EXTRA_INHERIT}

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
    local variant="$1"
    local input="$2"
    local output="$3"

    local csum="${FIT_HASH_ALG}"
    local sign_algo="${FIT_SIGN_ALG}"
    local sign_keyname
    if [ "${UBOOT_SIGN_ENABLE}" = "1" ]; then
        sign_keyname="${UBOOT_SIGN_KEYNAME}"
    fi

    cat >boot.its <<EOF
/dts-v1/;

/ {
        description = "U-Boot boot script";

        configurations {
                default = "conf-1";

                conf-1 {
                        description = "Boot configuration '$variant'";
                        script = "script-1";

                        hash-1 {
                                algo = "$csum";
                        };
EOF

    if [ -n "$sign_keyname" ]; then
        cat >>boot.its << EOF
                        signature-1 {
                                algo = "$csum,$sign_algo";
                                key-name-hint = "$sign_keyname";
                                sign-images = "script";
                        };
EOF
    fi

    cat >>boot.its <<EOF
                };
        };

        images {

                script-1 {
                        compression = "none";
                        data = /incbin/("$input");
                        type = "script";

                        hash-1 {
                                algo = "$csum";
                        };
                };
        };
};
EOF

    ${UBOOT_MKIMAGE} \
        ${@'-D "${UBOOT_MKIMAGE_DTCOPTS}"' if len('${UBOOT_MKIMAGE_DTCOPTS}') else ''} \
        -f boot.its "${output}"

    if [ "${UBOOT_SIGN_ENABLE}" = '1' ] ; then
        ${UBOOT_MKIMAGE_SIGN} \
            ${@'-D "${UBOOT_MKIMAGE_DTCOPTS}"' if len('${UBOOT_MKIMAGE_DTCOPTS}') else ''} \
            -F -k "${UBOOT_SIGN_KEYDIR}" \
            -r "${output}" \
            ${UBOOT_MKIMAGE_SIGN_ARGS}
    fi
}

do_compile() {
    for variant in ${VARIANTS}; do
        build_scr "${variant}" "${WORKDIR}/${variant}.cmd" "${variant}.scr"
    done
}

do_deploy() {
    for variant in ${VARIANTS}; do
        install -m 644 "${variant}.scr" "${DEPLOYDIR}/${variant}.scr"
    done
}
addtask deploy before do_build after do_compile

PACKAGE_ARCH = "${MACHINE_ARCH}"
