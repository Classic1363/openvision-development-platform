require openvision-image.bb
require ../../recipes-core/package-index/package-index.bb

BLINDSCAN_CHECK = "${@bb.utils.contains("MACHINE_FEATURES", "dreambox", "enigma2-plugin-systemplugins-dmblindscan", "enigma2-plugin-systemplugins-blindscan", d)}"

TRANSCODING_CHECK = "${@bb.utils.contains_any("MACHINE_FEATURES", "vuplus gigablue dags", "transtreamproxy", "streamproxy", d)}"

ENIGMA2_PLUGINS += "\
	enigma2-plugin-extensions-audiosync \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash sh4stb azbox rpi-vision", "", "enigma2-plugin-extensions-backupsuite", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-cacheflush", "", d)} \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash sh4stb azbox rpi-vision", "", "enigma2-plugin-extensions-openmultiboot", d)} \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-pictureplayer \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-extensions-pluginskinmover", "", d)} \
	enigma2-plugin-extensions-socketmmi \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "enigma2-plugin-skins-pli-hd", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "uianimation", "enigma2-plugin-systemplugins-animationsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "ci", "enigma2-plugin-systemplugins-commoninterfaceassignment", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-dvbs", "${BLINDSCAN_CHECK}" , "", d)} \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hdmicec \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-osdpositionsetup \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	${@bb.utils.contains("MACHINE_FEATURES", "sh4booster", "enigma2-plugin-systemplugins-sh4boostercontrol", "", d)} \
	enigma2-plugin-systemplugins-softwaremanager \
	${@bb.utils.contains("MACHINE_FEATURES", "videoenhancement", "enigma2-plugin-systemplugins-videoenhancement", "", d)} \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
	enigma2-plugin-systemplugins-xmlupdate \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-tmbd \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-epgrefresh \
	enigma2-plugin-extensions-reconstructapsc \
	enigma2-plugin-skins-octetfhd \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-terrestrialscan", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	enigma2-plugin-extensions-dreamirc \
	enigma2-plugin-extensions-filecommander \
	enigma2-plugin-extensions-historyzapselector \
	enigma2-plugin-extensions-keyadder \
	enigma2-plugin-extensions-openwebif-themes \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-systemplugins-autobouquetsmaker", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash libeplayer", "", "enigma2-plugin-systemplugins-serviceapp", d)} \
	"

DEPENDS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "blindscan-tbs", "blindscan-s2" , "", d)} \
	enigma2 \
	enigma2-c-plugins \
	enigma2-alliance-plugins \
	enigma2-locale-meta \
	enigma2-plugins \
	openvision-bootlogo \
	"

RDEPENDS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "emmc", "dosfstools mtools e2fsprogs-resize2fs partitions-by-name" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "fastboot", "dosfstools mtools android-tools" , "", d)} \
	"

# These machine feature related plugins should not be enabled for smallflash STBs as there isn't enough space for them!
MACHINE_FEATURE_RELATED_PLUGINS += "\
	${@bb.utils.contains("MACHINE_FEATURES", "bluetooth", "enigma2-plugin-extensions-btdevicesmanager kernel-module-bluetooth", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "gbipboxclient", "enigma2-plugin-extensions-gbipboxclient", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "grautec", "enigma2-plugin-extensions-grautec", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "multitranscoding", "enigma2-plugin-systemplugins-multitranscodingsetup", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "satip", "enigma2-plugin-systemplugins-satipclient" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "dvd", "cdtextinfo", "", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "streamproxy transcoding multitranscoding", "${TRANSCODING_CHECK}", "", d)} \
	"

IMAGE_INSTALL += "\
	aio-grab \
	cronie \
	dhrystone \
	enigma2 \
	enigma2-locale-meta \
	${ENIGMA2_PLUGINS} \
	${@bb.utils.contains("DEVELOPER_NAME", "persianprince", "enigma2-plugin-extensions-persianpalace", "", d)} \
	frequency-xml-list-atsc \
	frequency-xml-list-cables \
	frequency-xml-list-satellites \
	frequency-xml-list-terrestrial \
	frequency-xml-list-unicable \
	${@bb.utils.contains("MACHINE_FEATURES", "sh4stb", "kernel-module-block2mtd libcrypto", "", d)} \
	kernel-module-autofs4 \
	kernel-module-fuse \
	kernel-module-ipv6 \
	libavahi-client \
	libcrypto-compat \
	ntpdate \
	settings-autorestore \
	tuxbox-links \
	${@bb.utils.contains_any("MACHINE", "vuuno4kse vuultimo4k vuduo4k vuduo4kse", "vuplus-hdmi-in-helper", "", d)} \
	wget \
	${@bb.utils.contains("MACHINE_FEATURES", "smallflash", "", " \
	${MACHINE_FEATURE_RELATED_PLUGINS}", d)} \
	${@bb.utils.contains_any("MACHINE_FEATURES", "smallflash middleflash", "", " \
	cifs-utils \
	dvbfetool \
	nfs-utils \
	openssh-sftp-server \
	openvision-core-plugin", d)} \
	"

export IMAGE_BASENAME = "openvision-enigma2"
