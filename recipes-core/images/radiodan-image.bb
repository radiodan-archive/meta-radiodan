require radiodan-image-common.inc

# set root password 'radiodan'
set_root_passwd() {
   sed 's%^root:[^:]*:%root:$6$onRprIDS$oY.iiebgFdQtT1GvUF3OxQOaKElluAIGPDS/aXL02rt77bPZoDIHXz6XF8L6dfMYF7Sc.9VUoq20tgDhtfd2J0:%' \
       < ${IMAGE_ROOTFS}/etc/shadow \
       > ${IMAGE_ROOTFS}/etc/shadow.new;
   mv ${IMAGE_ROOTFS}/etc/shadow.new ${IMAGE_ROOTFS}/etc/shadow ;
}
ROOTFS_POSTPROCESS_COMMAND += "set_root_passwd;"
