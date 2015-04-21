FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://config.patch      \
	    file://fix-desktop.patch \
            file://init              \
	   "

inherit update-rc.d

do_install_append() {
    rm -f ${D}${sysconfdir}/xdg/autostart/pulseaudio-kde.desktop
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}-server
}

FILES_${PN} += "${datadir}/alsa"

RDEPENDS_pulseaudio-server += "                 \
    pulseaudio-module-switch-on-connect	        \
    pulseaudio-module-switch-on-port-available	\
    pulseaudio-module-cli                       \
    pulseaudio-module-cli-protocol-unix         \
    pulseaudio-module-mmkbd-evdev               \
    pulseaudio-module-volume-restore            \
    pulseaudio-module-rescue-streams"


FILES_${PN}-server += "${sysconfdir}/init.d"

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME_${PN}-server = "${PN}-server"
INITSCRIPT_PARAMS_${PN}-server = "defaults 99 01"
