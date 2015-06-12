SUMMARY = "Radiodan cease"

SRCREV    = "6f6d23c63665bf8e516afae3c1feb22b03fae41e"
SRCBRANCH = "master"
SRCREPO   = "cease"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

DEPENDS += "go-zmq4"

inherit radiodango radiodansupervisor
