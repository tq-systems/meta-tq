#!/bin/sh

# SPDX-License-Identifier: MIT
#
# Copyright (c) 2024 TQ-Systems GmbH <oss@ew.tq-group.com>, D-82229 Seefeld, Germany.
# Author: Matthias Schiffer

set -e

RUNTIME_CONFIG=/run/tq/rauc/system.conf

get_root_dev () {
	findmnt --evaluate --first-only --noheadings --output SOURCE --mountpoint /
}

get_config_suffix () {
	case "$(get_root_dev)" in
	/dev/mmcblk0p*)
		echo mmc0
		;;
	/dev/mmcblk1p*)
		echo mmc1
		;;
	ubi0:*)
		echo spi
		;;
	esac
}

get_system_config () {
	echo "/etc/rauc/system.conf-$(get_config_suffix)"
}

main () {
	local config

	config=$(get_system_config)

	if ! [ -r "$config" ]; then
		config=/dev/null
	fi

	mkdir -p "$(dirname "$RUNTIME_CONFIG")"
	ln -sfn "$config" "$RUNTIME_CONFIG"
}

main "$@"
