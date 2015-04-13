SUMMARY = "Physical Interface Server for radiodan"

SRCREV    = "26cc87638e6d731079195f81fac9008144137fa6"
SRCBRANCH = "stable"
SRCREPO   = "physical-ui"

require radiodan-app.inc

# FIXME -- package needs license!!!
LIC_FILES_CHKSUM = "file://README.markdown;md5=bca49df90d35123676138e82a824e2c8"

inherit radiodannode allarch
