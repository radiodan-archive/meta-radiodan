FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${PV}:${THISDIR}/files:"

SRC_URI += "file://fix-driver-include-paths \
            file://hostapd.conf             \
           "

#
# HACK -- the file we need to patch is in a directory parallel with the
#         WORKDIR, so it the normal patch task cannot handle it; add a
#         simple patch method to handle this one case
#
do_pre_patch(){
  cd ${WORKDIR}/hostapd-${PV}
  patch -p1 < ${WORKDIR}/fix-driver-include-paths
}

addtask pre_patch before do_patch after do_unpack

# We do not want the service started automatically, so assign it to an
# unused run level 4 (we run in 5)
INITSCRIPT_PARAMS = "start 99 4 . stop 20 0 1 6 ."

do_install_append () {
    install -m 0600 ${WORKDIR}/hostapd.conf ${D}${sysconfdir}/
}