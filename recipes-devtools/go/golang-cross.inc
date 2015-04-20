PROVIDES = "virtual/${TARGET_PREFIX}golang"

inherit cross

CC_FOR_TARGET = "${TARGET_SYS}-gcc ${TARGET_CC_ARCH}"
CXX_FOR_TARGET = "${TARGET_SYS}-g++ ${TARGET_CC_ARCH}"

do_install () {
	# Install the executables into build system
	mkdir -p ${D}${bindir}
	cp -a bin/go ${D}${bindir}/
	mkdir -p ${D}${libdir}/go
	cp -a pkg ${D}${libdir}/go/
	cp -a include ${D}${libdir}/go/
	cp -a api ${D}${libdir}/go/
	cp -a src ${D}${libdir}/go/
}
