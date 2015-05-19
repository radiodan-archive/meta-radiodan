SUMMARY = "Radiodan broker"

SRCREV    = "222e5d5dae11bed0a05d7c8b951957688669ea84"
SRCBRANCH = "master"
SRCREPO   = "broker"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

DEPENDS += "go-zmq4 go-logrus"

inherit radiodango radiodansupervisor

do_configure_append() {
	ln -sf ${S}/pubsub ${LOCAL_PATH}/pubsub
	ln -sf ${S}/service ${LOCAL_PATH}/service
}
