SUMMARY = "Radiodan server"

SRCREV    = "8915c2a9f040f7b82a83ba181e5f60c07bcdc911"
SRCBRANCH = "stable"
SRCREPO   = "radiodan.js"

require radiodan-app.inc

inherit radiodannode allarch

LIC_FILES_CHKSUM = "file://COPYING;md5=5491a35938200a2677c639efe36240a0"

RDEPENDS_${PN}-init += "radiodan-magic"

INITSCRIPT_PACKAGES = ""
