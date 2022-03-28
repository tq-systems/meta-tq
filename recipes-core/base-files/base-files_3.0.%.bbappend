#
# this bbappend is intended to be used with example distros defined in this
# layer. It shows, how to customize the banner files /etc/issue[.net]
#
DEPENDS += "figlet-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI_append = "\
    file://handle_issue.sh \
"

dirs755_append = "\
    ${sysconfdir}/profile.d \
"

# this usually comes from our distro
VENDOR_LONG_NAME ??= "TQ-Systems"
DISTRO_SHORT_NAME ??= "dumpling"

do_install_append() {
    install -m 0755 ${WORKDIR}/handle_issue.sh ${D}${sysconfdir}/profile.d/handle_issue.sh
}

do_install_basefilesissue_append() {
    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "${VENDOR_LONG_NAME}" | \
        sed 's,\\,\\\\,g'  >> ${D}${sysconfdir}/issue
    echo "\n" >> ${D}${sysconfdir}/issue
    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "${DISTRO_SHORT_NAME}" | \
        sed 's,\\,\\\\,g'  >> ${D}${sysconfdir}/issue
    echo "\n" >> ${D}${sysconfdir}/issue

    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "${VENDOR_LONG_NAME}" >> \
        ${D}${sysconfdir}/issue.net
    echo "\n" >> ${D}${sysconfdir}/issue.net
    figlet -c -d ${RECIPE_SYSROOT_NATIVE}/usr/share/figlet -- "${DISTRO_SHORT_NAME}" >> \
        ${D}${sysconfdir}/issue.net
    echo "\n" >> ${D}${sysconfdir}/issue.net
}
