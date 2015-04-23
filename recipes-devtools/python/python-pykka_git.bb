DESCRIPTION = "Pykka is a Python implementation of the actor model."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.2.0"

SRC_URI = "git://github.com/jodal/pykka.git;tag=v${PV}"

S = "${WORKDIR}/git"

inherit setuptools

RDEPENDS_${PN} += "python-threading"
