#!/bin/sh

# Parameter Check
if [ $# -ne 3 -a $# -ne 4 ]; then
	echo "error: invalid number of arguments to script."
	exit 1
fi

for f in ${1} ${2} ${3} ;
do
	if [ ! -e ${f} ]; then
		echo "error: not found "${f}
		exit 1
	fi
done

# Input/output file definition
SIG_KEY=$1
ENC_KEY=$2
IN_FILE=$3
if [ $# -eq 4 ]; then VMA_ADR=$4; fi

IN_NAME=$(basename ${IN_FILE})
OUT_NAME=${IN_NAME%.*}
if [ -z ${OUT_NAME} ]; then OUT_NAME=${IN_NAME}; fi
OUT_FILE="$(dirname ${IN_FILE})/${OUT_NAME}_enc"

TMP_FILE="tmp.bak"
if [ -e ${TMP_FILE} ]; then rm ${TMP_FILE}; fi

FILE_EXT=${IN_NAME##*.}
if [ "srec" = ${FILE_EXT} ]; then
	objcopy -I srec -O binary ${IN_FILE} ${TMP_FILE}
elif [ "elf" = ${FILE_EXT} ]; then
	objcopy -O binary ${IN_FILE} ${TMP_FILE}
else
	cp ${IN_FILE} ${TMP_FILE}
fi

FSIZE=`wc -c < ${TMP_FILE}`

if [ 0 -eq ${FSIZE} ]; then
	echo "error: file size is zero."
	exit 1
fi

x=`expr ${FSIZE} % 16`
if [ 0 -ne ${x} ] ; then
	i=0
	j=`expr 16 - ${x}`
	while [ ${i} -lt ${j} ]; do
		printf '\0'
		i=$((i+1))
	done >> ${TMP_FILE}
fi

openssl dgst -sha256 -sign ${SIG_KEY} ${TMP_FILE} >> ${TMP_FILE}

ENC_KEY=`od -An -tx1 < ${ENC_KEY} | tr -d ' ' | tr '\n' ' '`

KEY_VAR=`echo ${ENC_KEY} | cut -d' ' -f1`
IV_VAR=`echo ${ENC_KEY} | cut -d' ' -f2`

openssl aes-128-cbc -e -in ${TMP_FILE} -out ${OUT_FILE}.bin -K ${KEY_VAR} -iv ${IV_VAR} -nopad

if [ -n "${VMA_ADR}" ]; then
	objcopy -I binary -O srec --adjust-vma=${VMA_ADR} --srec-forceS3 ${OUT_FILE}.bin ${OUT_FILE}.srec
fi

rm ${TMP_FILE}

exit 0
