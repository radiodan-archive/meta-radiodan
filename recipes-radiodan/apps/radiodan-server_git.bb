SUMMARY = "Radiodan server"

SRCREV    = "6d0946dd4d0746486bc0290e65df86ead40337dd"
SRCBRANCH = "master"
SRCREPO   = "radiodan.js"

require radiodan-app.inc

inherit radiodannode

LIC_FILES_CHKSUM = "file://COPYING;md5=5491a35938200a2677c639efe36240a0"

DEPENDS += "zeromq"

do_install_append() {
    mkdir -p ${D}${APPBASEDIR}/data/mopidy/m3u
}

FILES_${PN} += "${APPBASEDIR}/data"