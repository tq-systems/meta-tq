inherit bundle

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

require example-files.inc

RAUC_KEY_URI ??= "file://${RAUC_KEY_FILE}"
RAUC_CERT_URI ??= "file://${RAUC_CERT_FILE}"
RAUC_KEYRING_URI ??= "file://${RAUC_KEYRING_FILE}"

SRC_URI = " \
    ${RAUC_KEY_URI} \
    ${RAUC_CERT_URI} \
    ${RAUC_KEYRING_URI} \
"

RAUC_BUNDLE_FORMAT = "verity"
RAUC_BUNDLE_SLOTS = "rootfs"

# Smaller bundles can be achieved using a more modern compression algorithm like XZ or ZSTD
RAUC_SLOT_rootfs ?= "tq-image-generic-debug"
RAUC_SLOT_rootfs[fstype] ?= "tar.gz"

do_bundle:prepend () {
    (
        cd "${UNPACKDIR}" && for file in "${RAUC_KEY_FILE}" "${RAUC_CERT_FILE}" "${RAUC_KEYRING_FILE}"; do
            mkdir -p "$(dirname "${B}/${file}")"
            cp "${file}" "${B}/${file}"
        done
    )
}
