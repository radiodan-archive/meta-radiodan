FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://fix-py-lib-loc-define.patch"

# The python-dev dependency is stupid, but the plugin attempts to load
# python as libpythonX.so, and the .so symlink is in the dev package
RDEPENDS_${PN} += "python-pygobject python-dev"
RDEPENDS_${PN}_remove = "python-pygtk"

INSANE_SKIP_${PN} = "dev-deps"