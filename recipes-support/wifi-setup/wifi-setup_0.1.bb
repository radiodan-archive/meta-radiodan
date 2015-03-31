SUMMARY = "Set up wifi connection"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "network"

RDEPENDS_${PN} = "wpa-supplicant    \
                  hostapd           \
                  ${PN}-cli-web     \
                  ${PN}-conf-copier \
                  ${PN}-try-adhoc   \
                 "

SRC_URI = "file://try_adhoc_network      \
           file://wpa-try-adhoc          \
           file://wpa-cli-web            \
           file://wpa-conf-copier        \
           file://radiodan-volatile.conf \
          "

inherit update-rc.d

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wpa-cli-web ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wpa-conf-copier ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/wpa-try-adhoc ${D}${sysconfdir}/init.d

    install -d ${D}${sbindir}
    install -m 0700 ${WORKDIR}/try_adhoc_network ${D}${sbindir}/

    install -d ${D}${sysconfdir}/default/volatiles
    install -m 0644 ${WORKDIR}/radiodan-volatile.conf ${D}${sysconfdir}/default/volatiles/99_radiodan
    sed -i 's,/var/,${localstatedir}/,g' ${D}${sysconfdir}/default/volatiles/99_radiodan
}

PACKAGES =+ "${PN}-cli-web ${PN}-conf-copier ${PN}-try-adhoc"

FILES_${PN} = "${sysconfdir} ${sbindir}"
FILES_${PN}-cli-web = "${sysconfdir}/init.d/wpa-cli-web"
FILES_${PN}-conf-copier = "${sysconfdir}/init.d/wpa-conf-copier"
FILES_${PN}-try-adhoc = "${sysconfdir}/init.d/wpa-try-adhoc"

INITSCRIPT_PACKAGES = "${PN}-conf-copier ${PN}-try-adhoc"

INITSCRIPT_NAME_${PN}-conf-copier = "wpa-conf-copier"
INITSCRIPT_PARAMS_${PN}-conf-copier = "defaults 01 99"

INITSCRIPT_NAME_${PN}-try-adhoc = "wpa-try-adhoc"
INITSCRIPT_PARAMS_${PN}-try-adhoc = "defaults 98 98"
