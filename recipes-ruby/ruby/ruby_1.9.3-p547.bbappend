
BASE_VER = "${@bb.data.getVar('PV',d,1).split('.')[0]}.${@bb.data.getVar('PV',d,1).split('.')[1].${@bb.data.getVar('PV',d,1).split('.')[2]}"

# This can be parsed out of the ruby version, but we would need to have a
# ruby cross for that
RUBY_ARCH_raspberrypi = "arm-linux-eabi"
RUBY_ARCH_raspberrypi2 = "arm-linux-eabi"

do_install_append_class-target() {
    cd ${D}${libdir}/ruby/${RUBY_ARCH}
    sed -e 's#--\(with-libtool-\)*sysroot=.*${MACHINE}##g' -i rbconfig.rb
}