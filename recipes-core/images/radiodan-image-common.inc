SUMMARY = "Radiodan image."
DESCRIPTION = "Basic radiodan image."

IMAGE_FSTYPES ?= "tar.bz2 ext4 rpi-sdimg"
IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

# The RPi boot partion name
BOOTDD_VOLUME_ID = "boot"

# The RPi rootfs type
SDIMG_ROOTFS_TYPE = "ext4"

# Generate .xz compressed images for easy upload -- only weakly set this, so
# we can override in other image recipes that require this one.
SDIMG_COMPRESSION ?= "xz"

CORE_IMAGE_EXTRA_INSTALL = "packagegroup-radiodan"

inherit core-image
