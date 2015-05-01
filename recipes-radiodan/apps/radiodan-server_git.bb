SUMMARY = "Radiodan server"

SRCREV    = "70f2323b34f1611179a80a581cfa22f33cbb582d"
SRCBRANCH = "master"
SRCREPO   = "radiodan.js"

require radiodan-app.inc

inherit radiodannode allarch

LIC_FILES_CHKSUM = "file://COPYING;md5=5491a35938200a2677c639efe36240a0"

RDEPENDS_${PN}-init += "radiodan-magic"

INITSCRIPT_PACKAGES = ""
