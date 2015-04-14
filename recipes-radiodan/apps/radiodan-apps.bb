SUMMARY = "Radiodan applications meta package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "radiodan"

ALLOW_EMPTY_${PN} = "1"

#inherit allarch

NODEJS_APPS = "radiodan-server magic-button radiodan-buttons"
GO_APPS = "radiodan-debug radiodan-cease"

DEPENDS = "nodejs-native ${NODEJS_APPS}"
RDEPENDS_${PN} = "${NODEJS_APPS} ${GO_APPS}"

# Borrowed from the nodejs recipes
def map_nodejs_arch(a, d):
    import re

    if   re.match('p(pc|owerpc)(|64)', a): return 'ppc'
    elif re.match('i.86$', a): return 'ia32'
    elif re.match('x86_64$', a): return 'x64'
    elif re.match('arm64$', a): return 'arm'
    return a

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

# Install all npm modules these apps require
do_install() {
   set -e
   pfx="${D}${libdir}/node_modules"
   install -d ${pfx}
   for app in ${NODEJS_APPS}; do
     echo "Doing npm install in ${STAGING_DATADIR}/radiodan/packages/${app}"
     cd ${STAGING_DATADIR}/radiodan/packages/${app}
     npm install --arch=${@map_nodejs_arch(d.getVar('TARGET_ARCH', True), d)}

     cp -R node_modules/* ${pfx}
   done
}

FILES_${PN} = "${libdir}/node_modules"
