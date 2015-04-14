SUMMARY = "Radiodan updater service"

SRCREV    = "718ab549be290cb7ccd2a60e4378d0f55905b95c"
SRCBRANCH = "master"
SRCREPO   = "updater"

require radiodan-app.inc

LIC_FILES_CHKSUM = "file://LICENSE;md5=00fb0986f133b1a5ec05e533b4298a3d"

inherit radiodango

do_configure_append() {
    ln -sf ${S}/commands ${LOCAL_PATH}/commands
    ln -sf ${S}/deployed ${LOCAL_PATH}/deployed
    ln -sf ${S}/model ${LOCAL_PATH}/model
    ln -sf ${S}/updates ${LOCAL_PATH}/updates
}

do_install_append() {
    install -d ${D}${sbindir}
    ln -sf /opt/radiodan/apps/${PN}/current/radiodan-updater ${D}${sbindir}

    install -d ${D}/opt/radiodan/updates/manifests
    install -d ${D}/opt/radiodan/updates/downloads
}

FILES_${PN} += "/opt/radiodan/updates ${sbindir}"
