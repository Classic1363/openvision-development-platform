DESCRIPTION = "Glamour Aura FHD skin by MCelliot_g for Open Vision"
MAINTAINER = "MCelliot_g"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-weathermsn"

SRC_URI = "git://github.com/MCelliotG/GlamourAuraFHD-skin.git;protocol=git"

FILES_${PN} = "/usr/share/enigma2/ ${libdir}/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
}

do_install() {
	install -d ${D}/usr/share
	cp -r --preserve=mode,links ${S}/usr/share/* ${D}/usr/share/
	chmod -R a+rX ${D}/usr/share/enigma2/

        install -d ${D}${libdir}
        cp -r --preserve=mode,links ${S}${libdir}/* ${D}${libdir}
        chmod -R a+rX ${D}${libdir}/enigma2/
}
