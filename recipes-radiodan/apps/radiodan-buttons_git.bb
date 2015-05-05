SUMMARY = "Physical Interface Server for radiodan"

SRCREV    = "706f01f645a1d1741d90f1469a49ace51dc53702"
SRCBRANCH = "master"
SRCREPO   = "physical-ui"

require radiodan-app.inc

# FIXME -- package needs license!!!
LIC_FILES_CHKSUM = "file://README.markdown;md5=bca49df90d35123676138e82a824e2c8"

RDEPENDS_${PN}_append_raspberrypi  = " wiringpi"
RDEPENDS_${PN}_append_raspberrypi2 = " wiringpi"

inherit radiodannode

DEPENDS += "zeromq"

INITSCRIPT_PACKAGES = ""

# wiring-pi does not crosscompile, the basic problem is that it does a git
# checkout of wiringpi, which it then attempts to build, but wiringpi cannot
# be crosscompiled without patching the Makefiles because it hard codes "CC=gcc"
#
# There is no nice way to fix this, as there is no way to hook up into the
# npm install midway to patch, so what we do is:
#
#  1. run npm install normally, this will do the git checkouts, and build
#     wiringPi bits with the *host* compiler
#
#  2. Blow away the wiringpi binary products, patch the Makefiles
#
#  3. Maually re-run the compile steps for wiringpi
#
#  4. Patch the wiring-pi install.sh script to skip the checkouts and
#     cleanup, just run the gyp command
#
#  5. run npm rebuild

do_install () {
    install -d ${D}${APPDIR}
    echo -e "{\"name\":\"radiodan/${PN}\",\"ref\":\"${SRCBRANCH}\",\"commit\":\"\"}" > ${D}${APPDIR}/.deploy

    cd ${D}/opt/radiodan/apps/${PN}
    ln -sf releases/0 current

    # npm creates a cache in $HOME/.npm, so point $HOME at the WORKDIR
    export HOME="${WORKDIR}"

    cd ${S}
    echo "Running first npm install"
    npm install --arch=${@map_nodejs_arch(d.getVar('TARGET_ARCH', True), d)}


    echo "Patching wiringpi Makefiles"
    subdirs="wiringPi gpio devLib examples pins"
    cd ${S}/node_modules/wiring-pi/wiringpi

    for s in $subdirs; do
        cd $s
        sed -e "s|CC[\t ]*=[\t ]*gcc|CC = ${TARGET_SYS}-gcc ${TARGET_CC_ARCH}|g" -i Makefile
        sed -e "s|DESTDIR=/usr|DESTDIR=${D}|g" -i Makefile
        sed -e "s|PREFIX=/local|PREFIX=/usr|g" -i Makefile
        rm -f *.o *.a lib*.so*
        cd ..
    done

    cd ./wiringPi/
    make clean > ../../install.log 2>&1
    make static >> ../../install.log 2>&1
    cd ../devLib/
    make clean >> ../../install.log 2>&1
    make static >> ../../install.log 2>&1
    cd ../gpio/
    make clean >> ../../install.log 2>&1
    make >> ../../install.log 2>&1
    cd ../
    node-gyp rebuild 2>&1 | tee -a ./install.log

    cd ${S}
    cat > node_modules/wiring-pi/install.sh <<EOF
      node-gyp rebuild 2>&1 | tee -a ./install.log
EOF

    rm -rf ${WORKDIR}/.npm/wiring-pi
#    rm -rf ${S}/node_modules/wiring-pi/build

    echo "Running npm rebuild"
    npm rebuild --arch=${@map_nodejs_arch(d.getVar('TARGET_ARCH', True), d)}

    cp -R ${S}/* ${D}${APPDIR}
}

FILES_${PN}-staticdev += "${APPDIR}/node_modules/wiring-pi/wiringpi/*/*.a"
