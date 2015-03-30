SUMMARY = "radiodan gemset"
SECTION = "devtools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "devtools"

RDEPENDS_${PN} = "eventmachine"

GEMSET_BUILD_GEMS = ""
GEMSET_INSTALL_GEMS = "rack              \
                       rack-protection   \
                       sinatra tilt      \
                       wpa_cli_ruby      \
                       wpa_cli_web       \
                      "

inherit gemset

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

