inherit radiodanapp

DEPENDS += "golang-cross"

# This removes the --hash-style argument from LDFLAGS, which in turn shuts up
# the sanity check for GNU_HASH
export LDFLAGS="${BUILD_LDFLAGS}"

# The configure step creates a local gopath directory and symlinks the top
# level git dir into there as PN, creating a directory structure that golang
# expects.
radiodango_do_configure() {
	MYPN="${BPN}"
        PNPATH=${MYPN/-/\//}
	LOCAL_PATH=".gopath/src/github.com/${PNPATH}"

	cd ${S}
	rm -rf .gopath
	mkdir -p ${LOCAL_PATH}

	ln -sf ${S}/../git ${LOCAL_PATH}/${BPN}
}

radiodango_do_compile() {
	export GOARCH="${TARGET_ARCH}"
	# supported amd64, 386, arm
	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		export GOARCH="amd64"
	fi

	MYPN="${BPN}"
        PNPATH=${MYPN/-/\//}
	LOCAL_PATH=".gopath/src/github.com/${PNPATH}"

	cd ${LOCAL_PATH}/${BPN}

	export CGO_ENABLED="1"
	export GOPATH="${S}/.gopath:${STAGING_DIR_TARGET}/${prefix}/local/go"
	export CGO_CFLAGS="${BUILD_CFLAGS}"
	export CGO_LDFLAGS="${BUILD_LDFLAGS}"

	go install
}

do_install_append() {
	MYPN="${BPN}"
        SHORT_NAME=${MYPN#radiodan-}
        PNPATH=${MYPN/-/\//}
	LOCAL_PATH=".gopath/src/github.com/${PNPATH}"

	cp ${S}/.gopath/bin/linux_*/* ${D}${DIR}

	cd releases/0
        ln -sf ${BPN} ${SHORT_NAME}
}

EXPORT_FUNCTIONS do_configure do_compile

# The elf binaries that golang generates can't be stripped
INHIBIT_PACKAGE_STRIP = "1"
