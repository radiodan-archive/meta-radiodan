SUMMARY = "Magic button"

SRCREV   = "afef131d9797352192288b7891bbdf993becedc8"
SRCBRANCH = "stable"

require radiodan-app.inc

inherit radiodannode allarch

do_install_append() {
    cd ${D}/opt/radiodan/apps/magic-button/releases/0/config
    # We have to rename this file rather than make a link as node 0.10 will
    # dereference the link and then choke on the file because of the 'example'
    # extension
    mv radiodan-config.json.example radiodan-config.json
    mv physical-ui-config.json.example physical-ui-config.json
}

INITSCRIPT_PACKAGES = ""
