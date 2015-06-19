LICENSE = "Supervisor"
LIC_FILES_CHKSUM = "file://LICENSES.txt;md5=4bf4981cc6d5d11ffa0b1f9f5c1c499f"

VER_MAJ = "3"
VER_MIN = "1"
VER_MIC = "3"

PV = "${VER_MAJ}.${VER_MIN}.${VER_MIC}"
BRANCH = "${VER_MAJ}.${VER_MIN}-branch"

RDEPENDS_${PN} += "python-meld3"

SRC_URI = "\
    git://github.com/Supervisor/${PN}.git;branch=${BRANCH};tag=${PV} \
    file://supervisord.conf                                                \
    file://init                                                           \
    file://radiodan-device-type                                           \
          "

S = "${WORKDIR}/git"

inherit setuptools update-rc.d

PACKAGES =+ "${PN}-init"

RDEPENDS_${PN}-init += "${PN}"

do_install_append () {
    install -d ${D}${bindir}
    install -m 0750 ${WORKDIR}/radiodan-device-type ${D}${bindir}

    install -d ${D}${sysconfdir}/supervisor/available
    install -d ${D}${sysconfdir}/supervisor/conf.d
    install -m 0644 ${WORKDIR}/supervisord.conf ${D}${sysconfdir}/

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}

INITSCRIPT_PACKAGES = "${PN}-init"
INITSCRIPT_NAME_${PN}-init = "${PN}"
INITSCRIPT_PARAMS_${PN}-init = "defaults 99 01"

FILES_${PN} += "${sysconfdir}"
FILES_${PN}-init = "${sysconfdir}/init.d"
