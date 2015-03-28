#!/bin/sh
#
# load keymap, if existing

# Allow unversioned keymaps as well as gzipped keymaps
KERNEL_MAJMIN=`uname -r | cut -d '.' -f 1,2`
if [ -e /etc/keymap-$KERNEL_MAJMIN.map.gz ]; then
	loadkeys /etc/keymap-$KERNEL_MAJMIN.map.gz
elif [ -e /etc/keymap-$KERNEL_MAJMIN.map ]; then
	loadkeys /etc/keymap-$KERNEL_MAJMIN.map
elif [ -e /etc/keymap.map.gz ]; then
	loadkeys /etc/keymap.map.gz
elif [ -e /etc/keymap.map ]; then
	loadkeys /etc/keymap.map
fi

if ( ls "/etc" | grep -q "keymap-extension-${KERNEL_MAJMIN}" ); then
	for extension in `ls -1 /etc/keymap-extension-$KERNEL_MAJMIN*`
	do
		loadkeys "$extension"
	done
elif ( ls "/etc" | grep -q "keymap-extension" ); then
	for extension in `ls -1 /etc/keymap-extension*`
	do
		loadkeys "$extension"
	done
fi
