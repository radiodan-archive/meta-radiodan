# No compressed sdk images for now
SDIMG_COMPRESSION ?= ""

require radiodan-image.bb

DESCRIPTION = "Radiodan image that includes all development packages and tools needed for standalone SDK."

IMAGE_FEATURES += "dev-pkgs tools-sdk\
	           tools-debug eclipse-debug tools-profile tools-testapps \
                   debug-tweaks ssh-server-openssh"

IMAGE_INSTALL += "kernel-dev"

