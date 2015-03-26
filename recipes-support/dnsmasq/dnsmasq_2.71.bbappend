FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://wifi.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/dnsmasq.d
    install -m 0600 ${WORKDIR}/wifi.conf ${D}${sysconfdir}/dnsmasq.d/
}

# We do not want the service started automatically, so assign it to an
# unused run level 4 (we run in 5)
INITSCRIPT_PARAMS = "start 99 4 . stop 20 0 1 6 ."