# allow issue.net to be used as banner page when logon via ssh
do_install_append () {
    sed -i -e 's:#Banner none:Banner /etc/issue.net:' ${D}${sysconfdir}/ssh/sshd_config
}
