SUMMARY = "Radiodan"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "${PN} ${PN}-base ${PN}-ruby ${PN}-web ${PN}-support"

RDEPENDS_${PN} = "\
    ${PN}-base \
    ${PN}-ruby \
    "

SUMMARY_${PN}-base  = "Radiodan base packages"
RDEPENDS_${PN}-base = "\
    connman	       \
    udev-extraconf     \
    "

SUMMARY_${PN}-ruby  = "Radiodan ruby-related packages"
RDEPENDS_${PN}-ruby = "\
    ruby	       \
    bundler	       \
    "

SUMMARY_${PN}-web  = "Radiodan web-related packages"
RDEPENDS_${PN}-web = "\
    nginx	      \
    monit	      \
    nodejs	      \
    "

SUMMARY_${PN}-support  = "Radiodan 'other' packages"
RDEPENDS_${PN}-support = "\
    samba	          \
    zeromq		  \
    "
