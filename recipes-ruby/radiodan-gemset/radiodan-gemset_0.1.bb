SUMMARY = "radiodan gemset"
SECTION = "devtools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "devtools"

RDEPENDS_${PN} = "eventmachine thin"

GEMSET_BUILD_GEMS = ""
GEMSET_INSTALL_GEMS = "rack              \
                       rack-protection   \
                       sinatra tilt      \
                       wpa_cli_ruby      \
                       wpa_cli_web       \
                       daemons           \
                      "

inherit gemset stdlicense
