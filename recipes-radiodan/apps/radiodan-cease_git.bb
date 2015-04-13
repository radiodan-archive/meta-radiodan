SUMMARY = "Radiodan cease"

SRCREV    = "4cb9e6566ce95fef53fe9b12640bca9d1c94da1c"
SRCBRANCH = "stable"
SRCREPO   = "cease"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=d2794c0df5b907fdace235a619d80314"

DEPENDS += "golang-cross"

do_configure() {
}

do_compile() {
	export GOARCH="${TARGET_ARCH}"
	# supported amd64, 386, arm
	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		export GOARCH="amd64"
	fi

	export GOPATH="${STAGING_DIR_TARGET}/${prefix}/local/go"
	cd ${S}

	export CGO_CFLAGS="${BUILD_CFLAGS}"
	export CGO_LDFLAGS="${BUILD_LDFLAGS}"

	go install
}

RDEPENDS_${PN}-init += ""
