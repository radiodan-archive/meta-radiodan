SUMMARY = "Eventmachine gem"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://GNU;md5=904005457611884b150f6bda894a505e"

require bingem.inc

DEPENDS += "openssl"

do_configure() {
    cd ${S}
    L=$(find usr -name GNU)
    echo "found license at $L"
    ln -sf $L GNU
}
