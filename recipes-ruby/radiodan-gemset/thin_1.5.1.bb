SUMMARY = "Thin server gem"

LICENSE = "Ruby"
LIC_FILES_CHKSUM = "file://LICENSE;md5=105fc57d3f4d3122db32912f3e6107d0"

require bingem.inc

do_configure() {
    cp ${TOPDIR}/../meta/files/common-licenses/Ruby ${S}/LICENSE
}

