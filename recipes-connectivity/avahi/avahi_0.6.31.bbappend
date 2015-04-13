
EXTRA_OECONF += "--enable-compat-libdns_sd"

do_install_append(){
    # Make a link to dns_sd.h so apps that it can be located by apps that
    # know nothing about avahi includes
    cd ${D}${includedir}
    ln -sf avahi-compat-libdns_sd/dns_sd.h dns_sd.h
}

PACKAGES =+ "libavahi-dnssd"

FILES_libavahi-dnssd = "${libdir}/libdns_sd.so.*"
