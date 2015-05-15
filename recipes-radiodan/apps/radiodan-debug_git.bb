SUMMARY = "Radiodan debug server"

SRCREV    = "2a47f043ed114bea397c0a41809522879d198f10"
SRCBRANCH = "stable"
SRCREPO   = "debug"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

inherit radiodango radiodansupervisor

do_configure_append() {
	ln -sf ${S}/builder ${LOCAL_PATH}/builder
	ln -sf ${S}/radiodan ${LOCAL_PATH}/radiodan
	ln -sf ${S}/utils ${LOCAL_PATH}/utils
}

