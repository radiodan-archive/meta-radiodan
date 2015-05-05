APPBASEDIR="/opt/radiodan/apps/${PN}"
APPDIR="${APPBASEDIR}/releases/0"

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
