
# We only want the mad plugin
DEPENDS_remove = "mpeg2dec liba52 lame"

EXTRA_OECONF_remove = "--with-plugins=a52dec,lame,id3tag,mad,mpeg2dec,mpegstream,mpegaudioparse,asfdemux,realmedia"
EXTRA_OECONF += "--with-plugins=mad"
