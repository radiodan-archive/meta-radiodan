
SRC_URI += "file://init"

inherit radiodanapp update-rc.d

PACKAGES += "${PN}-init"

RDEPENDS_${PN}-init = "${PN} start-stop-daemon"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}

FILES_${PN}-init = "${sysconfdir}/init.d"

INITSCRIPT_PACKAGES = "${PN}-init"
INITSCRIPT_NAME_${PN}-init = "${PN}"
INITSCRIPT_PARAMS_${PN}-init = "defaults 99 01"
