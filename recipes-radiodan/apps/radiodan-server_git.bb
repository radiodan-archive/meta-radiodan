SUMMARY = "Radiodan server"

SRCREV    = "6435c4e1c21a06047468df3a66269cdb9de638f0"
SRCBRANCH = "stable"
SRCREPO   = "radiodan.js"

require radiodan-app.inc

inherit radiodannode allarch

LIC_FILES_CHKSUM = "file://COPYING;md5=5491a35938200a2677c639efe36240a0"

RDEPENDS_${PN}-init += "radiodan-magic"

INITSCRIPT_PACKAGES = ""
