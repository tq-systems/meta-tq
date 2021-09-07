
EXTRA_IMAGEDEPENDS += "\
	${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti', ' prueth-fw', '', d)} \
"
