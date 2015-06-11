#
# radiodanmanifest.bbclass facilitates fetching of package src revisions from
# the online manifest server (http://deploy.radiodan.net/)
#
# The manifest is fetched in a custom ConfigParset event handler, the json
# content is parset and injected into bitbake data as RADIODAN_MANIFEST. If the
# manifest cannot be fetched, but a previous version exists, the build will
# print a warning and continue with the older version; if no previous version
# exists, the build will fail.
#
# Subsequently, a recipe can retrive the SRCREV value using the rdm_srcrev()
# function, e.g.,
#
# SRCREV = "${@rdm_srcrev(d)}"
#


# wget Radiodan manifest, making a back up copy of any previous manifest
def rdm_runfetchcmd(uri, d):
    import subprocess
    success = False
    error_message = ""
    oldfile = False
    dldir = d.expand("${DL_DIR}")

    if not os.path.exists(dldir):
        bb.utils.mkdirhier(dldir)

    file = d.expand("${DL_DIR}/radiodan-manifest")

    try:
        os.rename(file, file + ".bak")
        oldfile = True
    except OSError:
        if os.path.exists(file + ".bak"):
            oldfile = True

    cmd = d.expand("/usr/bin/env wget -t 2 -T 30 -nv --passive-ftp --no-check-certificate -c -O ${DL_DIR}/radiodan-manifest '%s'" % uri)

    try:
        (output, errors) = bb.process.run(cmd, shell=True, stderr=subprocess.PIPE)
        success = True
    except bb.process.NotFoundError as e:
        error_message = "Fetch command %s" % (e.command)
    except bb.process.ExecutionError as e:
        if e.stdout:
            output = "output:\n%s\n%s" % (e.stdout, e.stderr)
        elif e.stderr:
            output = "output:\n%s" % e.stderr
        else:
            output = "no output"
        error_message = "Fetch command failed with exit code %s, %s" % (e.exitcode, output)
    except bb.process.CmdError as e:
        error_message = "Fetch command %s could not be run:\n%s" % (e.command, e.msg)

    if not success:
        if oldfile:
            bb.warn("Failed to download Radiodan manifest: %s." % error_message)
            bb.warn("Using cached version of Radiodan manifest.")
            os.rename(file + ".bak", file)
        else:
            bb.warn("Failed to download Radiodan manifest: %s." % error_message)
            bb.fatal("No cached version of Radiodan manifest.")


# Fetch and parse Radiodan manifest and inject it into bitbake data
def rdm_manifest(d):
    import json

    uri = "http://deploy.radiodan.net/"
    rdm_runfetchcmd(uri, d)

    try:
        file = open (d.expand("${DL_DIR}/radiodan-manifest"), 'r')
        manifest = json.load(file)
        d.setVar('RADIODAN_MANIFEST', manifest)
    except:
        bb.fatal ("Could not parse Radiodan manifest")

# Extract individual app data from the manifest
def rdm_appdata(d):
    branch = d.getVar('SRCBRANCH', True)
    name = d.getVar('SRCREPO', True)
    manifest = d.getVar('RADIODAN_MANIFEST', True)
    appdata = manifest['radiodan/' + name]
    return appdata[branch]

# Extract app SRCREV from the manifest
def rdm_srcrev(d):
    appdata = rdm_appdata(d)
    return appdata['commit']

python radiodanmanifest_eventhandler(){
    rdm_manifest(e.data)
}

addhandler radiodanmanifest_eventhandler
radiodanmanifest_eventhandler[eventmask] = "bb.event.ConfigParsed"

