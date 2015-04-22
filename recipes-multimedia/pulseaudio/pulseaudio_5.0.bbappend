FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://config.patch      \
	    file://fix-desktop.patch \
            file://init              \
	   "

PACKAGES =+ "${PN}-system-server"

inherit update-rc.d

do_install_append() {
    rm -f ${D}${sysconfdir}/xdg/autostart/pulseaudio-kde.desktop
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}-server
}

RDEPENDS_pulseaudio-server += "                 \
    pulseaudio-module-switch-on-connect	        \
    pulseaudio-module-switch-on-port-available	\
    pulseaudio-module-cli                       \
    pulseaudio-module-cli-protocol-unix         \
    pulseaudio-module-mmkbd-evdev               \
    pulseaudio-module-volume-restore            \
    pulseaudio-module-rescue-streams"

RDEPENDS_${PN}-system-server = "${PN}-server"

FILES_${PN}-system-server = "${sysconfdir}/init.d"

INITSCRIPT_PACKAGES = "${PN}-system-server"
INITSCRIPT_NAME_${PN}-system-server = "${PN}-server"
INITSCRIPT_PARAMS_${PN}-system-server = "defaults 99 01"

# When running as a system server, ensure the root user is member of the pulse
# access group, as pulseaudio only grants access to the pulse user and the access
# group; yocto configures pulse to use 'audio' as the access group
USERADD_PACKAGES += "${PN}-system-server"
GROUPMEMS_PARAM_${PN}-system-server = "--group audio --add root"