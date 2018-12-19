
IMAGE_INSTALL_append = "\
	${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', ' prueth-fw', '', d)} \
"
