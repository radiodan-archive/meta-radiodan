inherit radiodanapp

DEPENDS += "nodejs-native"
RDEPENDS_${PN} += "nodejs nodejs-npm"

# Borrowed from the nodejs recipes
def map_nodejs_arch(a, d):
    import re

    if   re.match('p(pc|owerpc)(|64)', a): return 'ppc'
    elif re.match('i.86$', a): return 'ia32'
    elif re.match('x86_64$', a): return 'x64'
    elif re.match('arm64$', a): return 'arm'
    return a

do_install_append() {
    set -e

    # npm creates a cache in $HOME/.npm, so point $HOME at the WORKDIR
    export HOME="${WORKDIR}"

    cd ${S}
    npm install --arch=${@map_nodejs_arch(d.getVar('TARGET_ARCH', True), d)}

    cp -R ${S}/* ${D}${APPDIR}
}

FILES_${PN} = "${APPDIR} ${APPBASEDIR}/current"
FILES_${PN}-dbg += "${APPDIR}/node_modules/*/*/*/.debug \
                    ${APPDIR}/node_modules/*/*/*/*/.debug \
                    ${APPDIR}/node_modules/*/*/*/*/*/.debug \
                    ${APPDIR}/node_modules/*/*/*/*/*/*/.debug \
                    ${APPDIR}/node_modules/*/*/*/*/*/*/*/.debug \
                    ${APPDIR}/node_modules/*/*/*/*/*/*/*/*/.debug \
                   "
FILES_${PN}-staticdev += "${APPDIR}/node_modules/*/*/*.a"