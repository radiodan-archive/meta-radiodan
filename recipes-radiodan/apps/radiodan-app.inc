SECTION = "radiodan"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6c4db32a2fa8717faffa1d4f10136f47"

SRCREV    ?= "${@radiodanapp_srcrev(d)}"
SRCBRANCH ?= "master"
SRCREPO   ?= "${PN}"

SRC_URI   = "git://github.com/radiodan/${SRCREPO};branch=${SRCBRANCH}"

PV = "git${SRCPV}"
S = "${WORKDIR}/git"

inherit radiodanapp
