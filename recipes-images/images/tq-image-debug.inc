# For modifying /etc/issue during rootfs postbuild steps
DEPENDS += "figlet-native"

# plenty of tools for test / development
IMAGE_INSTALL_append = "\
    packagegroup-testutils \
"

# enable root login w/o password and passwordless ssh login
IMAGE_FEATURES_append = " debug-tweaks"
# poky defined collection of debug/test tools
IMAGE_FEATURES_append = " tools-debug"
IMAGE_FEATURES_append = " tools-testapps"

#
# Add a postprocess command to add a note and a message in the issue banner files
# if debug setting can be found in IMAGE_FEATURES
#
ISSUE_WARN = "WARNING: your rootfs has debug settings enabled and is not secure."

do_issue_warning () {
    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "development" | \
            sed 's,\\,\\\\,g'  >> "${IMAGE_ROOTFS}/${sysconfdir}/issue"
    echo "${ISSUE_WARN}" >> "${IMAGE_ROOTFS}/${sysconfdir}/issue"

    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "development" >> \
            "${IMAGE_ROOTFS}/${sysconfdir}/issue.net"
    echo "${ISSUE_WARN}" >> "${IMAGE_ROOTFS}/${sysconfdir}/issue.net"
}

ROOTFS_POSTPROCESS_COMMAND += '${@bb.utils.contains_any("IMAGE_FEATURES", \
    [ 'debug-tweaks', 'empty-root-password', 'allow-root-login' ], \
    "do_issue_warning; ", "",d)}'
