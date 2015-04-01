
RDEPENDS_${PN} += "nodejs nodejs-npm"

def rda_metadata(d):
    src_uri = "http://deploy.radiodan.net/"
    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
    except bb.fetch2.BBFetchException as e:
        raise bb.build.FuncFailed(e)

def rda_appdata(d):
    import json

    rda_metadata(d)
    dldir = d.getVar('DL_DIR', True)
    branch = d.getVar('SRCBRANCH', True)
    pn = d.getVar('PN', True)
    metadata = json.loads(os.path.join(dldir,'radiodan-metadata'))
    appdata = metadata['radiodan/' + pn]
    return appdata[branch]

def radiodanapp_srcrev(d):
    appdata = rda_appdata(d)
    return appdata['commit']

radiodanapp_do_configure() {
    echo "nop"
}

radiodanapp_do_install() {
    DIR="/opt/radiodan/apps/${PN}/releases/0"
    install -d ${D}${DIR}
    echo -e "{\"name\":\"radiodan/${PN}\",\"ref\":\"${SRCBRANCH}\",\"commit\":\"\"}" > ${D}${DIR}/.deploy
    cp -R ${S}/* ${D}${DIR}

    cd ${D}/opt/radiodan/apps/${PN}
    ln -sf releases/0 current

    # Stage the package.json file, so we can later use this to install
    # dependencies
    install -d ${D}${datadir}/radiodan/packages/${PN}
    cp ${S}/package.json ${D}${datadir}/radiodan/packages/${PN}
}

pkg_postrm_${PN} () {
    LINK="/opt/radiodan/apps/${PN}/current"
    rm -f ${D}${LINK}
}

EXPORT_FUNCTIONS do_configure do_install

FILES_${PN} = "/opt/radiodan/apps/${PN}"
FILES_${PN}-dev += "${datadir}/radiodan"
