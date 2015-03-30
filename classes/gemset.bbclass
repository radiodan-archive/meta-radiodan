def get_rubygemsversion(p):
    import re
    from os.path import isfile
    import subprocess
    found_version = "SOMETHING FAILED!"

    cmd = "%s/gem" % p

    if not isfile(cmd):
       return found_version

    version = subprocess.Popen([cmd, "env", "gemdir"], stdout=subprocess.PIPE).communicate()[0]

    r = re.compile(".*([0-9]+\.[0-9]+\.[0-9]+)$")
    m = r.match(version)
    if m:
        found_version = m.group(1)

    return found_version

RUBY_GEM_VERSION ?= "${@get_rubygemsversion("${STAGING_BINDIR_NATIVE}")}"

export GEM_HOME = "${STAGING_DIR_NATIVE}/usr/lib/ruby/gems/${RUBY_GEM_VERSION}"

gemset_do_compile() {
	for gem in ${GEMSET_BUILD_GEMS}; do
		${RUBY_COMPILE_FLAGS} gem build $gem
	done
}

gemset_do_install() {
	export CXX="${TARGET_PREFIX}g++"
	export CC="${TARGET_PREFIX}gcc"

	for gem in ${GEMSET_INSTALL_GEMS}; do
		gem install --verbose --ignore-dependencies --env-shebang --install-dir ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/ $gem
	done

	# create symlink from the gems bin directory to /usr/bin
	for i in ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/*; do
		if [ -e "$i" ]; then
			if [ ! -d ${D}/${bindir} ]; then mkdir -p ${D}/${bindir}; fi
			b=`basename $i`
			ln -sf ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/$b ${D}/${bindir}/$b
		fi
	done
}

gemset_do_() {
	export CXX="${TARGET_PREFIX}g++"
	export CC="${TARGET_PREFIX}gcc"

	for gem in ${GEMSET_INSTALL_GEMS}; do
		gem install --verbose --ignore-dependencies --env-shebang --install-dir ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/ $gem
	done

	# create symlink from the gems bin directory to /usr/bin
	for i in ${D}/${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/*; do
		if [ -e "$i" ]; then
			if [ ! -d ${D}/${bindir} ]; then mkdir -p ${D}/${bindir}; fi
			b=`basename $i`
			ln -sf ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin/$b ${D}/${bindir}/$b
		fi
	done
}

EXPORT_FUNCTIONS do_compile do_install

PACKAGES = "${PN}-dbg ${PN} ${PN}-doc ${PN}-dev"

FILES_${PN}-dbg += " \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/gems/*/*/.debug \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/gems/*/*/*/.debug \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/gems/*/*/*/*/.debug \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/gems/*/*/*/*/*/.debug \
        "

FILES_${PN} += " \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/gems \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/cache \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/bin \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/specifications \
        "

FILES_${PN}-doc += " \
        ${libdir}/ruby/gems/${RUBY_GEM_VERSION}/doc \
        "
