inherit radiodanapp

RDEPENDS_${PN} += "nodejs nodejs-npm"

do_install_append() {
    cp -R ${S}/* ${D}${DIR}

    # Stage the package.json file, so we can later use this to install
    # dependencies
    install -d ${D}${datadir}/radiodan/packages/${PN}
    cp ${S}/package.json ${D}${datadir}/radiodan/packages/${PN}
}
