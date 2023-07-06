BRANCH = "TQMaRZG2x-v2.7"

SRC_URI_remove = " \
	git://github.com/renesas-rz/rzg_trusted-firmware-a.git;branch=${BRANCH};protocol=https \
"

SRC_URI_append = " \
	${TQ_GIT_BASEURL}/atf.git;branch=${BRANCH};protocol=${TQ_GIT_PROTOCOL} \
"

SRCREV = "4d7d8e4778fa54b5b3e997646b177bc00fa91196"
COMPATIBLE_MACHINE_rzg2h .= "|tqmarzg2x"

# TQMaRZG2M has only one DRAM chip places, so only one channel is used
ATFW_OPT_remove_tqmarzg2m = "RCAR_DRAM_SPLIT=2"

ATFW_OPT_prepend_tqmarzg2x = "RZG_TQMARZG2X=1 RCAR_SA6_TYPE=1 "
