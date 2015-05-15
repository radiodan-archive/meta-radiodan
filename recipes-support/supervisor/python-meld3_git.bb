LICENSE = "Repoze"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9e7581cef5645475fcefebdc15ed7abf"

VER_MAJ = "1"
VER_MIN = "0"
VER_MIC = "2"

PV = "${VER_MAJ}.${VER_MIN}.${VER_MIC}"
TAG = "${VER_MAJ}.${VER_MIN}-branch"

SRC_URI = "git://github.com/Supervisor/meld3.git;tag=${PV}"

S = "${WORKDIR}/git"

inherit setuptools
