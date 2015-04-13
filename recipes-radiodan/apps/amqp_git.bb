DESCRIPTION = "AMPQ"
HOMEPAGE = "https://github.com/streadway/amqp"
SECTION = "devel/go"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=29358571796140f6172846eac9be80d2"

PR = "r0"

PKG_NAME = "github.com/streadway/${PN}"
SRC_URI = "git://${PKG_NAME}.git"

SRCREV = "7d6d1802c7710be39564a287f860360c6328f956"
PV = "0.0+git${SRCREV}"

S = "${WORKDIR}/git"

do_install() {
	install -d ${D}${prefix}/local/go/src/${PKG_NAME}
	cp -r ${S}/* ${D}${prefix}/local/go/src/${PKG_NAME}/
}

SYSROOT_PREPROCESS_FUNCS += "ampq_sysroot_preprocess"

ampq_sysroot_preprocess () {
    install -d ${SYSROOT_DESTDIR}${prefix}/local/go/src/${PKG_NAME}
    cp -r ${D}${prefix}/local/go/src/${PKG_NAME} ${SYSROOT_DESTDIR}${prefix}/local/go/src/$(dirname ${PKG_NAME})
}

FILES_${PN} += "${prefix}/local/go/src/${PKG_NAME}/*"
