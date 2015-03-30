SUMMARY = "Eventmachine gem"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://GNU;md5=904005457611884b150f6bda894a505e"

DEPENDS = "ruby openssl"

SRC_URI = "file://eventmachine-${PV}.tar.bz2"

S = "${WORKDIR}/${BPN}-${PV}"

do_configure() {
    cd ${S}
    L=$(find usr -name GNU)
    echo "found license at $L"
    ln -sf $L GNU
}

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
