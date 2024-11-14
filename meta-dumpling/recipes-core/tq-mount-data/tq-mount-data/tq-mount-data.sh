#!/bin/sh

# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer

set -e

TQ_DATA_MOUNT_TARGET='@TQ_DATA_MOUNT_TARGET@'

get_root_dev () {
	findmnt --evaluate --first-only --noheadings --output SOURCE --mountpoint /
}

get_data_dev () {
	local root_dev
	root_dev=$(get_root_dev)
	case "${root_dev}" in
	# 2 and 3 are possible root partitions in our WKS files for RAUC
	/dev/*2|/dev/*3)
		# Data is partition 4
		echo "${root_dev}" | sed -e 's/.$/4/'
		;;
	esac
}

main () {
	local data_dev

	data_dev=$(get_data_dev)

	if [ -z "${data_dev}" ]; then
		return 0
	fi

	mount "${data_dev}" "${TQ_DATA_MOUNT_TARGET}"
}

main "$@"
