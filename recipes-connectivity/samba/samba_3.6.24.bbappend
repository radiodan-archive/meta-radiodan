FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " \
    --no-create-home \
    --home /home/pi \
    --user-group pi"

do_install_append () {
    install -d -m 0777 ${D}/media/music
}

FILES_${PN} += "/media/music"

pkg_postinst_${PN}_append () {
    if [ "x$D" = "x" ]; then
        /usr/bin/yes pi | smbpasswd -a -s pi
    else
       exit 1
    fi
}
