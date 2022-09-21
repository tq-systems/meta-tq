FLASH_WRITER_URL = "${TQ_GIT_BASEURL}/rzg2_flash_writer.git"
BRANCH = "TQMaRZG2x"

SRC_URI = "${FLASH_WRITER_URL};branch=${BRANCH};protocol=${TQ_GIT_PROTOCOL}"
SRCREV = "4613ceb93374ef8c453523cab45ca029feb6ef86"

do_compile() {
	case "${MACHINE}" in
	"hihope-rzg2n" | "hihope-rzg2m" | "hihope-rzg2h" ) 
		BOARD="HIHOPE"
		;;
	"ek874")
		BOARD="EK874"
		;;
	"tqmarzg2n-mbarzg2x" | "tqmarzg2m-mbarzg2x" | "tqmarzg2h-mbarzg2x")
		BOARD="TQMARZG2X"
		;;
	esac
        cd ${S}

       oe_runmake BOARD=${BOARD}
}
