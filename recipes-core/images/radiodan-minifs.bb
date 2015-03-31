SUMMARY = "Radiodan mini fs image."
LICENSE = "MIT"

export IMAGE_BASENAME = "radiodan-minifs"

IMAGE_FSTYPES = "ext4 tar.bz2"
IMAGE_FEATURES = ""
IMAGE_LINGUAS = " "

PACKAGE_INSTALL = "busybox e2fsprogs"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
BAD_RECOMMENDATIONS += "busybox-syslog"
