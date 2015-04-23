DESCRIPTION = "Tornado is an open source version of the scalable, non-blocking web server and tools that power FriendFeed."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://README;md5=3567b82324e778064d10bf5c381fa6cb"

PV = "2.4.1"

SRC_URI = "git://github.com/facebook/tornado.git;tag=v${PV}"

S = "${WORKDIR}/git"

inherit setuptools


