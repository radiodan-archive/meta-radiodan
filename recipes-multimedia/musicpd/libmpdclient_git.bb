DESCRIPTION = "Music Player Daemon (mpd) Client Library"
HOMEPAGE = "http://www.musicpd.org"
SECTION = "multimedia"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=06b9dfd2f197dc514d8ef06549684b77"

PV = "2.10"

SRC_URI = "git://git.musicpd.org/master/libmpdclient.git;tag=v${PV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

FILES_${PN}-dev += "${datadir}/vala/vapi"