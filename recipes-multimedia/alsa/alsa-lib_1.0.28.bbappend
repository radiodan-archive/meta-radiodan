FILESEXTRAPATHS_prepend_raspberrypi := "${THISDIR}/raspberrypi:"
FILESEXTRAPATHS_prepend_raspberrypi2 := "${THISDIR}/raspberrypi:"

RPI_SRC = "file://alsa-base.conf"

SRC_URI_raspberrypi += "${RPI_SRC}"
SRC_URI_raspberrypi2 += "${RPI_SRC}"

rpi_install() {
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/alsa-base.conf ${D}${sysconfdir}/modprobe.d/alsa-base.conf
}

do_install_append_raspberrypi() {
  rpi_install
}

do_install_append_raspberrypi2() {
  rpi_install
}

BASE_CONF_raspberrypi  = "${sysconfdir}/modprobe.d/*"
BASE_CONF_raspberrypi2 = "${sysconfdir}/modprobe.d/*"

FILES_alsa-conf-base += "${BASE_CONF}"