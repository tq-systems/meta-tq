FILESEXTRAPATHS:prepend := "${THISDIR}/lmsensors-config:"

#
# Use a list of files instead of machine specific overrides. This ensures the files are
# there for install regardless of machine renaming. We use wildcards for the same reason
# in appended install stage
#
SRC_URI:append = "\
    file://mba8x.conf \
    file://mba8mpxl.conf \
"

do_install:append() {
    case ${MACHINE} in
        tqma8qm*-mba8x) install -m 0644 ${WORKDIR}/mba8x.conf ${D}${sysconfdir}/sensors.d
                         ;;
        tqma8mp*-mba8mpxl) install -m 0644 ${WORKDIR}/mba8mpxl.conf ${D}${sysconfdir}/sensors.d
                         ;;
    esac
}

# libsensors configuration file
FILES:${PN}-libsensors += "${sysconfdir}/sensors.d/*"
