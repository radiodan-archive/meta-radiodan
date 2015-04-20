require radiodan-image.bb

DESCRIPTION = "Radiodan image that includes development packages and toolchain"
IMAGE_FEATURES += "dev-pkgs"

IMAGE_INSTALL += "packagegroup-radiodan-devtools"
