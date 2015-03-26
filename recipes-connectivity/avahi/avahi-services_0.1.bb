SUMMARY = "Avahi services"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "network"

DEPENDS = "avahi"

SRC_URI = "file://http.service \
           file://smb.service  \
           file://ssh.service  \
          "

PACKAGES += "${PN}-http ${PN}-smb ${PN}-ssh"

RDEPENDS_${PN} = "             \
                  avahi-daemon \
                  ${PN}-http   \
                  ${PN}-smb    \
                  ${PN}-ssh    \
                 "

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

do_install() {
    install -d ${D}${sysconfdir}/avahi/services
    install -m 0644 ${WORKDIR}/http.service ${D}${sysconfdir}/avahi/services/
    install -m 0644 ${WORKDIR}/smb.service ${D}${sysconfdir}/avahi/services/
    install -m 0644 ${WORKDIR}/ssh.service ${D}${sysconfdir}/avahi/services/
}

FILES_${PN} = ""
FILES_${PN}-http = "${sysconfdir}/avahi/services/http.service"
FILES_${PN}-smb  = "${sysconfdir}/avahi/services/smb.service"
FILES_${PN}-ssh  = "${sysconfdir}/avahi/services/ssh.service"
