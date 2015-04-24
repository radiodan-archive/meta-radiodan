# A class for packages that have no source other than the packaging and use
# one of the common licenses that Yocto knows about -- copy the license file
# from the Yocto license directory into the ${S} instead of adding a separate
# license file to each package.

# Define this to match the license file name if you want something else than
# MIT
STDLICENSE ?= "MIT"

do_copy_std_license() {
    cp ${TOPDIR}/../meta/files/common-licenses/${STDLICENSE} ${S}/LICENSE
}

addtask copy_std_license after do_unpack before do_patch
