SECTION = "radiodan"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6c4db32a2fa8717faffa1d4f10136f47"

SRCREV    ?= "${@radiodanapp_srcrev(d)}"
SRCBRANCH ?= "master"
SRCREPO   ?= "${PN}"
SRC_URI   = "git://github.com/radiodan/${SRCREPO};branch=${SRCBRANCH} \
             file://init                                              \
            "

S = "${WORKDIR}/git"

inherit allarch radiodanapp update-rc.d

PACKAGES += "${PN}-init"

RDEPENDS_${PN} += "${PN}-init"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}

FILES_${PN}-init = "${sysconfdir}"

INITSCRIPT_PACKAGES = "${PN}-init"
INITSCRIPT_NAME_${PN}-init = "${PN}"
INITSCRIPT_PARAMS_${PN}-init = "defaults 99 01"
