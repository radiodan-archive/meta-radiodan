
require golang.inc
require golang-common.inc
require golang-${PV}.inc

RDEPENDS_${PN} += "${PN}-staticdev"

# This removes the --hash-style argument from LDFLAGS, which in turn shuts up
# the sanity check for GNU_HASH
export LDFLAGS="${BUILD_LDFLAGS}"

# Go binaries do not strip; the official line is that they should not be
# stripped.
INHIBIT_PACKAGE_STRIP = "1"

do_install () {
	# blow away any host architecture stuff
	if [ "${BUILD_ARCH}" = "x86_64" ]; then
		GOHOSTARCH="amd64"
	else
		GOHOSTARCH="${BUILD_ARCH}"
	fi

	rm -rf ${S}/src/runtime/race
	rm -rf ${S}/src/debug
	rm -rf ${S}/pkg/linux_${GOHOSTARCH}

        #find ${S}/src -name "*${GOHOSTARCH}*" -print0 | xargs -0 /bin/rm -rf

	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		GOARCH="amd64"
	else
		GOARCH="${TARGET_ARCH}"
	fi

	# Install the executables into build system
	mkdir -p ${D}${bindir}
	cp -a bin/linux_${GOARCH}/go ${D}${bindir}/
	mkdir -p ${D}${libdir}/go/pkg
	cp -a pkg/linux_${GOARCH} ${D}${libdir}/go/pkg/
	cp -a include ${D}${libdir}/go/
	cp -a api ${D}${libdir}/go/
	cp -a src ${D}${libdir}/go/
}

FILES_${PN}-staticdev += "${libdir}/go/pkg/*/*.a         \
                          ${libdir}/go/pkg/*/*/*.a       \
                          ${libdir}/go/pkg/*/*/*/*.a     \
                          ${libdir}/go/pkg/*/*/*/*/*.a   \
                          ${libdir}/go/pkg/*/*/*/*/*/*.a \
                         "
FILES_${PN} += "${libdir}/go"