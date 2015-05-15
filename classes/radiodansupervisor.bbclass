#
# inheriting radiodansupervisor class adds support for process control with
# supervisor (http://github.com/Supervisor/supervisor)
#
# The package needs to include a supervisor.conf source file which will be
# packaged into ${PN}-supervisor package (as ${PN}.conf by default).
#
# The following two variables can be set in the recipe to further tune the
# class behaviour:
#
# SUPERVISOR_DISABLED: if set, the supervisor config file will be installed
# into the holding /etc/supervisor/available directory, but will not be
# symlinked into /etc/supervisor/conf.d
#
# SUPERVISOR_TYPED: if set, instead of installing the config file as ${PN}.conf
# it will be installed as 'radiodan-type-<name>', where name is the last part
# of PN, e.g., radiodan-magic, -> radiodan-type-magic

SRC_URI += "file://supervisor.conf"

PACKAGES += "${PN}-supervisor"

RDEPENDS_${PN}-supervisor = "${PN} supervisor"

def radiodansupervisor_name(d):
    typed = d.getVar('SUPERVISOR_TYPED', True)

    if typed:
        name = (d.getVar('PN', True) or "").split("-").pop()
        return "radiodan-type-%s" % name
    else:
        return d.getVar('PN', True)


do_install_append () {
    name="${@radiodansupervisor_name(d)}"

    install -d ${D}${sysconfdir}/supervisor/available
    install -d ${D}${sysconfdir}/supervisor/conf.d
    install -m 0644 ${WORKDIR}/supervisor.conf \
                    ${D}${sysconfdir}/supervisor/available/$name.conf

    if [ -z "${SUPERVISOR_DISABLED}" ]; then
        ln -sf ${sysconfdir}/supervisor/available/$name.conf \
               ${D}${sysconfdir}/supervisor/conf.d
    fi
}

FILES_${PN}-supervisor = "${sysconfdir}/supervisor"
