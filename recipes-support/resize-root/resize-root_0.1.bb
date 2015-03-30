SUMMARY = "Resize root partition to the available size"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SECTION = "network"

SRC_URI = "file://resize-root      \
          "

inherit update-rc.d

do_configure() {
    cp ${TOPDIR}/../meta/COPYING.MIT ${S}
}

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/resize-root ${D}${sysconfdir}/init.d/0resize-root
}

FILES_${PN} = "${sysconfdir}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME_${PN} = "0resize-root"
INITSCRIPT_PARAMS_${PN} = "start 00 2 3 4 5 ."
