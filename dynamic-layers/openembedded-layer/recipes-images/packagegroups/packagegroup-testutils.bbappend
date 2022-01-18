RDEPENDS_${PN}_append = "\
    htop \
    atop \
    dstat \
    iotop \
    memtester \
    evtest \
    stress-ng \
    nano \
    fb-test \
    stressapptest \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', ' glmark2', '', d)} \
"
