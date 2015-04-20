DESCRIPTION = "Go Programming Language Compiler."
HOMEPAGE = "golang.org"

DEPENDS = "libgcc"

SRC_URI = "http://golang.org/dl/go${PV}.src.tar.gz"

S="${WORKDIR}/go"

do_compile () {
	export CC="${BUILD_CC}"
	export GOROOT_FINAL="${libdir}/go"
	export GOARCH="${TARGET_ARCH}"

	# supported amd64, 386, arm
	if [ "${TARGET_ARCH}" = "x86_64" ]; then
		export GOARCH="amd64"
	fi

	if [ "${TARGET_ARCH}" = "arm" ]
	then
		if [ `echo ${TUNE_PKGARCH} | cut -c 1-7` = "cortexa" ]
		then
			echo GOARM 7
			export GOARM="7"
		fi
	fi
	export GOOS="linux"
	export GO_GCFLAGS=""
	export GO_LDFLAGS=""
	export GO_CCFLAGS=""
	export CGO_ENABLED="1"
	export CC_FOR_TARGET="${CC_FOR_TARGET}"
	export CXX_FOR_TARGET="${CXX_FOR_TARGET}"

	echo GOARCH ${GOARCH}
	echo CC_FOR_TARGET ${CC_FOR_TARGET}

	cd src
	sh -x ./make.bash
}

# CCACHE does not work properly with GO
CCACHE = ""
