SUMMARY = "Radiodan image."

IMAGE_FSTYPES ?= "tar.bz2 ext4 rpi-sdimg"
IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

# The RPi boot partion name
BOOTDD_VOLUME_ID = "boot"
# The RPi rootfs type
SDIMG_ROOTFS_TYPE = "ext4"

CORE_IMAGE_EXTRA_INSTALL = "packagegroup-radiodan"

inherit core-image

do_rootfs[depends] += "radiodan-minifs:do_build"

fakeroot do_add_minifs() {
	cp ${DEPLOY_DIR_IMAGE}/radiodan-minifs-${MACHINE}.ext4 \
           ${WORKDIR}/rootfs/radiodan-minifs.ext4
}

ROOTFS_POSTPROCESS_COMMAND += "do_add_minifs; "
