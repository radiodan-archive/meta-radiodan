SUMMARY = "Mopidy"
HOMEPAGE = "http://mopidy.com"
SECTION = "apps/multimedia"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.0.0"

SRC_URI   = "git://github.com/mopidy/${BPN};tag=v${PV} \
             file://init                               \
            "

S = "${WORKDIR}/git"

inherit setuptools update-rc.d

DEPENDS += "gst-plugins-good gst-plugins-ugly"

PACKAGES += "${PN}-init"

RDEPENDS_${PN} += "python-distribute         \
                   python-gst                \
                   python-tornado            \
                   python-pykka              \
                   python-argparse           \
                   python-netserver          \
                   python-xml                \
                   python-numbers            \
                   python-json               \
                   python-misc               \
                   start-stop-daemon         \
                   ${PN}-init                \
                   gst-plugins-base-meta     \
                   gst-plugins-good-meta     \
                   gst-plugins-ugly-mad      \
                  "

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
}

FILES_${PN}-init = "${sysconfdir}/init.d"

INITSCRIPT_PACKAGES = "${PN}-init"
INITSCRIPT_NAME_${PN}-init = "${PN}"
INITSCRIPT_PARAMS_${PN}-init = "defaults 99 01"
