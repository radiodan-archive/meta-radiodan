SUMMARY = "Magic button"

SRCREV   = "afef131d9797352192288b7891bbdf993becedc8"
SRCBRANCH = "stable"

require radiodan-app.inc

do_install_append() {
    cd ${D}/opt/radiodan/apps/magic-button/releases/0/config
    ln -s radiodan-config.json.example radiodan-config.json
}