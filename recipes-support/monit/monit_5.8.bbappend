FILESEXTRAPATHS_prepend := "${THISDIR}/radiodan:"

SRC_URI += "file://general \
            file://radiodan-system \
            file://radiodan-type-example \
            file://radiodan-type-magic   \
            file://radiodan-device-type  \
           "

do_install_append() {
    install -d ${D}${bindir}
    install -m 0750 ${WORKDIR}/radiodan-device-type ${D}${bindir}

    install -d ${D}${sysconfdir}/monit.d
    install ${WORKDIR}/general ${D}${sysconfdir}/monit.d
    install ${WORKDIR}/radiodan-system ${D}${sysconfdir}/monit.d

    install -d ${D}${sysconfdir}/monit.d.available
    install ${WORKDIR}/radiodan-type-example ${D}${sysconfdir}/monit.d.available
    install ${WORKDIR}/radiodan-type-magic ${D}${sysconfdir}/monit.d.available

    ln -sf ${sysconfdir}/monit.d.available/radiodan-type-magic ${D}${sysconfdir}/monit.d
}