SUMMARY = "Radiodan"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "${PN} ${PN}-base ${PN}-ruby ${PN}-web ${PN}-support ${PN}-tools"

RDEPENDS_${PN} = "	\
    ${PN}-base		\
    ${PN}-ruby		\
    ${PN}-web		\
    ${PN}-support	\
    ${PN}-tools		\
    "

SUMMARY_${PN}-base  = "Radiodan base packages"
RDEPENDS_${PN}-base = "\
    wpa-supplicant     \
    udev-extraconf     \
    avahi-daemon       \
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

SUMMARY_${PN}-tools  = "Radiodan extra tools"
RDEPENDS_${PN}-tools = "\
    bootchart2	     	\
    "
