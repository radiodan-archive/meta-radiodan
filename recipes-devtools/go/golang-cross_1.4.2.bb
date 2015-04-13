LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=591778525c869cdde0ab5a1bf283cd81"
SRC_URI[md5sum] = "907f85c8fa765d31f7f955836fec4049"
SRC_URI[sha256sum] = "299a6fd8f8adfdce15bc06bde926e7b252ae8e24dd5b16b7d8791ed79e7b5e9b"

inherit cross

# CCACHE does not work properly with GO
CCACHE = ""

require golang-cross.inc
