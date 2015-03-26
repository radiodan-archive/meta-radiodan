FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}-${PV}:"

SRC_URI += "file://fix-driver-include-paths"

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
