DESCRIPTION = "Command-line (scriptable) Music Player Daemon (mpd) Client"
HOMEPAGE = "http://www.musicpd.org/clients/mpc/"
SECTION = "multimedia"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "libmpdclient"

PV = "0.26"

SRC_URI = "git://git.musicpd.org/master/mpc.git;tag=v${PV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
