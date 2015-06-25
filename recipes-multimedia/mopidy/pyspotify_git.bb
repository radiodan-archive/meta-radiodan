SUMMARY = "Mopidy"
HOMEPAGE = "http://mopidy.com"
SECTION = "apps/multimedia"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.12"

SRC_URI   = "git://github.com/mopidy/${BPN};branch=v1.x/master;tag=v${PV}"

S = "${WORKDIR}/git"

inherit setuptools

DEPENDS += "libspotify"

RDEPENDS_${PN} += "libspotify"

