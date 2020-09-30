
IMAGE_INSTALL_append = "\
	${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', ' prueth-fw-am65x', '', d)} \
	${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', ' pru-icss', '', d)} \
	${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', ' pru-pwm-fw', '', d)} \
"
