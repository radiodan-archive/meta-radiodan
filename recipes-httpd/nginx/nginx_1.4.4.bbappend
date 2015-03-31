FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://radiodan404.html     \
            file://radiodan_client      \
            file://status511.html       \
            file://wpa_cli_web_redirect \
	    file://nginx.conf           \
           "

do_configure () {
	if [ "${SITEINFO_BITS}" = "64" ]; then
		PTRSIZE=8
	else
		PTRSIZE=4
	fi

	echo $CFLAGS
	echo $LDFLAGS

	./configure \
	--crossbuild=Linux:${TUNE_ARCH} \
	--with-endian=${@base_conditional('SITEINFO_ENDIANNESS', 'le', 'little', 'big', d)} \
	--with-int=4 \
	--with-long=${PTRSIZE} \
	--with-long-long=8 \
	--with-ptr-size=${PTRSIZE} \
	--with-sig-atomic-t=${PTRSIZE} \
	--with-size-t=${PTRSIZE} \
	--with-off-t=${PTRSIZE} \
	--with-time-t=${PTRSIZE} \
	--with-sys-nerr=132 \
	--conf-path=${sysconfdir}/nginx/nginx.conf \
	--http-log-path=${localstatedir}/log/nginx/access.log \
	--error-log-path=${localstatedir}/log/nginx/error.log \
	--pid-path=/run/nginx/nginx.pid \
	--prefix=${prefix} \
	--with-http_ssl_module \
	--with-http_gzip_static_module \
	--with-http_sub_module
}

do_install_append() {
    # Install site files
    install -d ${D}${sysconfdir}/${BPN}/sites-available
    install -d ${D}${sysconfdir}/${BPN}/sites-enabled
    install -m 0644 ${WORKDIR}/radiodan_client ${D}${sysconfdir}/${BPN}/sites-available
    install -m 0644 ${WORKDIR}/wpa_cli_web_redirect ${D}${sysconfdir}/${BPN}/sites-available

    # Enable sites
    # NB: these are mutually exclussive; the default site is radiodan_client
    ln -snf ${sysconfdir}/${BPN}/sites-available/radiodan_client ${D}${sysconfdir}/${BPN}/sites-enabled/radiodan_client
    # ln -snf ${sysconfdir}/${BPN}/sites-available/wpa_cli_web_redirect ${D}${sysconfdir}/${BPN}/sites-enabled/wpa_cli_web_redirect


    install -d ${D}/opt/radiodan/static/
    install -m 0644 ${WORKDIR}/status511.html ${D}/opt/radiodan/static/status511.html
    install -m 0644 ${WORKDIR}/radiodan404.html ${D}/opt/radiodan/static/radiodan404.html

    rm -f ${D}${sysconfdir}/${BPN}/*.default
}

FILES_${PN} += "/opt"
