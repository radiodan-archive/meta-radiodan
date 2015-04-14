
do_install_append() {
    # Somewhere in the radiodan-buttons stack the gpio program is called with
    # a hardcoded path to /usr/local/bin, so make symlink
    install -d ${D}${prefix}/local/bin
    ln -sf /usr/bin/gpio ${D}${prefix}/local/bin/
}

FILES_${PN} += "${prefix}/local/bin"