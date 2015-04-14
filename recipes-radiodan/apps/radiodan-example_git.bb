SUMMARY = "Example webapp for radiodan"

SRCREV    = "66f014605b2129fa0130ff7af6ad33ee5e0a5ba3"
SRCBRANCH = "mediascape"
SRCREPO   = "client-web-example"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

DEPENDS += "avahi"

inherit radiodannode allarch

#do_install_append(){
#   sed -e 's/TheThingSystem/tthef/g' -i ${D}${datadir}/radiodan/packages/${PN}/package.json
#}

INITSCRIPT_PACKAGES = ""
