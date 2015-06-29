FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "c2e75105eefdefdcfc17c39f90d7b9279f77a750"

SRC_URI = "\
          git://github.com/nekuz0r/wiringpi.git;branch=incoming-v2.25 \
          file://fix-makefiles.patch \
          "

CFLAGS_prepend = "-I${S}/devLib "

do_compile() {
    oe_runmake -C devLib
    oe_runmake -C wiringPi
    oe_runmake -C gpio 'LDFLAGS=${LDFLAGS} -L${S}/wiringPi -L${S}/devLib'
}

do_install_append() {
    # Somewhere in the radiodan-buttons stack the gpio program is called with
    # a hardcoded path to /usr/local/bin, so make symlink
    install -d ${D}${prefix}/local/bin
    ln -sf /usr/bin/gpio ${D}${prefix}/local/bin/
}

FILES_${PN} += "${prefix}/local/bin"