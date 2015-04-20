SUMMARY = "Radiodan"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "${PN}               \
            ${PN}-base          \
            ${PN}-ruby          \
            ${PN}-web           \
            ${PN}-support       \
            ${PN}-tools         \
            ${PN}-devtools      \
            ${PN}-radiodan-apps \
           "

RDEPENDS_${PN} = "      \
    ${PN}-base	        \
    ${PN}-ruby	        \
    ${PN}-web	        \
    ${PN}-support       \
    ${PN}-tools	        \
    ${PN}-devtools      \
    ${PN}-radiodan-apps	\
    "

# sudo is needed because of the wpa-cli gem

SUMMARY_${PN}-base  = "Radiodan base packages"
RDEPENDS_${PN}-base = "\
    wpa-supplicant     \
    udev-extraconf     \
    avahi-services     \
    hostapd            \
    linux-firmware     \
    kbd-keymaps        \
    resize-root	       \
    sudo               \
    "

SUMMARY_${PN}-ruby  = "Radiodan ruby-related packages"
RDEPENDS_${PN}-ruby = "\
    ruby	       \
    bundler	       \
    radiodan-gemset    \
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
    dnsmasq		  \
    wifi-setup            \
    mpc                   \
    "

SUMMARY_${PN}-tools  = "Radiodan extra tools"
RDEPENDS_${PN}-tools = "\
    bootchart2	     	\
    util-linux-sfdisk   \
    parted              \
    "

SUMMARY_${PN}-devtools  = "Radiodan developer tools"
RDEPENDS_${PN}-devtools = "          \
    packagegroup-core-buildessential \
    coreutils                        \
    ccache                           \
    diffutils                        \
    intltool                         \
    perl-module-re                   \
    perl-module-text-wrap            \
    findutils                        \
    quilt                            \
    less                             \
    ldd                              \
    file                             \
    tcl                              \
    git                              \
    golang                           \
    "

SUMMARY_${PN}-radiodan-apps  = "Radiodan Applications"
RDEPENDS_${PN}-radiodan-apps = "\
    radiodan-apps               \
    "
