APPBASEDIR="/opt/radiodan/apps/${PN}"
APPDIR="${APPBASEDIR}/releases/0"

radiodanapp_do_configure() {
    echo "nop"
}

radiodanapp_do_install() {
    install -d ${D}${APPDIR}
    echo -e "{\"name\":\"radiodan/${PN}\",\"ref\":\"${SRCBRANCH}\",\"commit\":\"\"}" > ${D}${APPDIR}/.deploy

    cd ${D}/opt/radiodan/apps/${PN}
    ln -sf releases/0 current
}

pkg_postrm_${PN} () {
    LINK="/opt/radiodan/apps/${PN}/current"
    rm -f ${D}${LINK}
}

EXPORT_FUNCTIONS do_configure do_install

FILES_${PN} = "/opt/radiodan/apps/${PN}"
FILES_${PN}-dev += "${datadir}/radiodan"
