
do_install_append(){
    install -d ${D}${sysconfdir}
    ln -sf /usr/share/keymaps/i386/qwerty/uk.map.gz ${D}${sysconfdir}/keymap.map.gz
}
