SUMMARY = "Radiodan applications meta package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "radiodan"

ALLOW_EMPTY_${PN} = "1"

inherit allarch

THE_APPS = "radiodan-server magic-button"

DEPENDS = "nodejs-native ${THE_APPS}"
RDEPENDS_${PN} = "${THE_APPS}"

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

# Install all npm modules these apps require
do_install() {
   set -e
   pfx="${D}${libdir}/node_modules"
   install -d ${pfx}
   for app in ${THE_APPS}; do
     echo "Doing npm install in ${STAGING_DATADIR}/radiodan/packages/${app}"
     cd ${STAGING_DATADIR}/radiodan/packages/${app}
     npm install
     cp -R node_modules/* ${pfx}
   done
}

FILES_${PN} = "${libdir}/node_modules"
