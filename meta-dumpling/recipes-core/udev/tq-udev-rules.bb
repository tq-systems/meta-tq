SUMMARY = "Additinal udev rules for TQ distros"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch

SRC_URI = "\
    file://persistent-storage-mtd.rules \
"

do_install() {
    install -Dm 644 ${WORKDIR}/persistent-storage-mtd.rules \
        ${D}${nonarch_base_libdir}/udev/rules.d/60-persistent-storage-mtd.rules
}
