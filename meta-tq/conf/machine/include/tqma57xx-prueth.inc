MACHINE_EXTRA_RRECOMMENDS += "\
    ${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-ti-bsp', 'prueth-fw', '', d)} \
"
