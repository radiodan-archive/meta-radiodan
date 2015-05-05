SUMMARY = "Radiodan applications meta package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0835ade698e0bcf8506ecda2f7b4f302"
SECTION = "radiodan"

ALLOW_EMPTY_${PN} = "1"

NODEJS_APPS = "radiodan-server radiodan-magic radiodan-buttons radiodan-example"
GO_APPS = "radiodan-debug radiodan-cease radiodan-updater"

RDEPENDS_${PN} = "${NODEJS_APPS} ${GO_APPS}"

inherit stdlicense

ALLOW_EMPTY_${PN} = "1"
