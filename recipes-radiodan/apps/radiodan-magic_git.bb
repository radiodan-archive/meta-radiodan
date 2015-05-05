SUMMARY = "Magic button"

SRCREV    = "ff34a89ab678459d9288074d81ea0e98a4b7ffb8"
SRCBRANCH = "master"
SRCREPO   = "magic-button"

require radiodan-app.inc

inherit radiodannode

DEPENDS += "zeromq"

do_install_append() {
    cd ${D}/opt/radiodan/apps/radiodan-magic/releases/0/config
    # We have to rename this file rather than make a link as node 0.10 will
    # dereference the link and then choke on the file because of the 'example'
    # extension
    mv radiodan-config.json.example radiodan-config.json
    mv physical-ui-config.json.example physical-ui-config.json
}

INITSCRIPT_PACKAGES = ""
