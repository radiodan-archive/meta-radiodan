
# Default to UK keyboard
pkg_postinst_${PN}-keymaps () {
    if [ "x$D" = "x" ]; then
        KERNEL_MAJMIN=`uname -r | cut -d '.' -f 1,2`

	# Setup uk keymap as default, so it can be loaded with loadkeys -d
        # this is for convenience only
        ln -sf /usr/share/keymaps/i386/qwerty/uk.map.gz /usr/share/keymaps/defkeymap.map.gz

        # Since the Yocto keymap.sh script does not handle .map.gz maps in etc,
        # but will happilly load .gz extensions, prettend the map is an
        # extension
        ln -sf /usr/share/keymaps/i386/qwerty/uk.map.gz /etc/keymap-extension-$KERNEL_MAJMIN-0-defkeymap.map.gz
    fi
}
