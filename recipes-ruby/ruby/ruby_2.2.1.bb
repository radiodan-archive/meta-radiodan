require ruby2.inc
PR = "${INC_PR}.0"

DEFAULT_PREFERENCE = "-1"

SRC_URI += "\
    file://0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch \
    file://ruby-1.9.3-always-use-i386.patch \
    file://ruby-1.9.3-disable-versioned-paths.patch \
    file://ruby-1.9.3-rubygems-1.8.11-uninstaller.patch \
    file://ruby-1.9.3-custom-rubygems-location.patch \
    file://rubygems-1.8.11-binary-extensions.patch \
    file://ruby-1.9.3-mkmf-verbose.patch \
    file://ruby-1.9.3-install-cross.patch \
    file://remove-the-dependency-on-dir.patch \
    file://ruby-mkmf.rb-fix-race-conditions-at-install-ext.patch \
"

SRC_URI[md5sum] = "b49fc67a834e4f77249eb73eecffb1c9"
SRC_URI[sha256sum] = "5a4de38068eca8919cb087d338c0c2e3d72c9382c804fb27ab746e6c7819ab28"

# it's unknown to configure script, but then passed to extconf.rb
# maybe it's not really needed as we're hardcoding the result with
# 0001-socket-extconf-hardcode-wide-getaddr-info-test-outco.patch
UNKNOWN_CONFIGURE_WHITELIST += "--enable-wide-getaddrinfo"

PACKAGECONFIG ??= ""
PACKAGECONFIG[valgrind] = "--with-valgrind=yes, --with-valgrind=no, valgrind"

EXTRA_OECONF = "\
    --enable-wide-getaddrinfo \
    --disable-versioned-paths \
    --disable-rpath \
    --enable-shared \
    --enable-load-relative \
"

EXTRA_OEMAKE = " \
    LIBRUBYARG='-lruby-static' \
"

do_install() {
    oe_runmake 'DESTDIR=${D}' install
}

FILES_${PN} += "${datadir}/rubygems \
                ${datadir}/ri"

FILES_${PN}-dbg += "${libdir}/ruby/*/.debug \
                    ${libdir}/ruby/*/*/.debug \
                    ${libdir}/ruby/*/*/*/.debug"

BBCLASSEXTEND = "native"
