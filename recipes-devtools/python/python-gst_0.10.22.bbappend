FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fix-py-lib-loc-define.patch"

RDEPENDS_${PN} += "python-pygobject"
RDEPENDS_${PN}_remove = "python-pygtk"
