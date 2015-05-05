SUMMARY = "Physical Interface Server for radiodan"

SRCREV    = "706f01f645a1d1741d90f1469a49ace51dc53702"
SRCBRANCH = "master"
SRCREPO   = "physical-ui"

require radiodan-app.inc

# FIXME -- package needs license!!!
LIC_FILES_CHKSUM = "file://README.markdown;md5=bca49df90d35123676138e82a824e2c8"

RDEPENDS_${PN}_append_raspberrypi  = " wiringpi"
RDEPENDS_${PN}_append_raspberrypi2 = " wiringpi"

inherit radiodannode

DEPENDS += "zeromq"

INITSCRIPT_PACKAGES = ""
