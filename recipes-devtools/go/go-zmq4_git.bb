DESCRIPTION = "ZMQ4"
HOMEPAGE = "https://github.com/pebbe/zmq4"
SECTION = "devel/go"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=767ea24eb6c3dd674bca6b7d9c3ebd5f"

PR = "r0"

DEPENDS += "zeromq"

PKG_NAME = "github.com/pebbe/zmq4"
SRC_URI = "git://${PKG_NAME}.git"

SRCREV = "10fadfeb51bd5d0250d09a0d31f9b53ed399b6ae"
PV = "0.0+git${SRCREV}"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${prefix}/local/go/src/${PKG_NAME}
	cp -r ${S}/* ${D}${prefix}/local/go/src/${PKG_NAME}/
}

SYSROOT_PREPROCESS_FUNCS += "go_zmq4_sysroot_preprocess"

go_zmq4_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${prefix}/local/go/src/${PKG_NAME}
    cp -r ${D}${prefix}/local/go/src/${PKG_NAME} ${SYSROOT_DESTDIR}${prefix}/local/go/src/$(dirname ${PKG_NAME})
}

FILES_${PN} += "${prefix}/local/go/src/${PKG_NAME}/*"
