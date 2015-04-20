DESCRIPTION = "Debian's start-stop-daemon utility"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://start-stop-daemon.c;beginline=1;endline=21;md5=535e211f008cd495dfcc55944be5a706"

SRC_URI = "file://start-stop-daemon.c"

S = "${WORKDIR}"

do_configure() {
	:
}

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -DVERSION="\"${PV}\"" ${WORKDIR}/start-stop-daemon.c -o start-stop-daemon
}

do_install () {
	install -d ${D}/${base_sbindir}
	install -m 0755 ${S}/start-stop-daemon ${D}/${base_sbindir}/start-stop-daemon.${PN}
}

pkg_postinst_${PN} () {
	update-alternatives --install ${base_sbindir}/start-stop-daemon start-stop-daemon start-stop-daemon.${PN} 100
}

pkg_prerm_${PN} () {
	update-alternatives --remove start-stop-daemon start-stop-daemon.${PN}
}
