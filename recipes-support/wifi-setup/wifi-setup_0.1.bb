SUMMARY = "Set up wifi connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "network"

RDEPENDS_${PN} = "wpa-supplicant hostapd ${PN}-cli-web ${PN}-conf-copier"

SRC_URI = "file://try_adhoc_network \
           file://rc.local          \
           file://wpa-cli-web       \
           file://wpa-conf-copier   \
          "

inherit update-rc.d

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wpa-cli-web ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wpa-conf-copier ${D}${sysconfdir}/init.d
    install -m 0700 ${WORKDIR}/rc.local ${D}${sysconfdir}/

    install -d ${D}${sbindir}
    install -m 0700 ${WORKDIR}/try_adhoc_network ${D}${sbindir}/
}


PACKAGES =+ "${PN}-cli-web ${PN}-conf-copier"

FILES_${PN} = "${sysconfdir} ${sbindir}"
FILES_${PN}-cli-web = "${sysconfdir}/init.d/wpa-cli-web"
FILES_${PN}-conf-copier = "${sysconfdir}/init.d/wpa-conf-copier"

INITSCRIPT_PACKAGES = "${PN}-conf-copier"
INITSCRIPT_NAME_${PN}-conf-copier = "wpa-conf-copier"
INITSCRIPT_PARAMS_${PN}-conf-copier = "defaults 01 99"
