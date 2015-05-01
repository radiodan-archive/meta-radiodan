SUMMARY = "Radiodan cease"

SRCREV    = "a0d3d252784058d02d2cbf14843479a1341281b0"
SRCBRANCH = "pubsub"
SRCREPO   = "cease"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

DEPENDS += "go-zmq4"

inherit radiodango
