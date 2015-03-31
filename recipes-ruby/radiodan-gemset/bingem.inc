DEPENDS = "ruby"

SRC_URI = "file://${BPN}-${PV}.tar.bz2"

S = "${WORKDIR}/${BPN}-${PV}"

do_install() {
    install -d ${D}
    cp -R ${S}/usr ${D}
}

PACKAGES = "${PN}-dbg ${PN} ${PN}-doc ${PN}-dev"

FILES_${PN}-dbg += " \
        ${libdir}/ruby/gems/*/gems/*/*/.debug \
        ${libdir}/ruby/gems/*/gems/*/*/*/.debug \
        ${libdir}/ruby/gems/*/gems/*/*/*/*/.debug \
        ${libdir}/ruby/gems/*/gems/*/*/*/*/*/.debug \
        "

FILES_${PN} += " \
        ${libdir}/ruby/gems/*/gems \
        ${libdir}/ruby/gems/*/cache \
        ${libdir}/ruby/gems/*/bin \
        ${libdir}/ruby/gems/*/specifications \
        "

FILES_${PN}-doc += " \
        ${libdir}/ruby/gems/*/doc \
        "
