SUMMARY = "Ralink 88x2BU v5.6.1"
HOMEPAGE = "http://www.realtek.com.tw"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=feae8f8e0b09f7b911830b066c727a35"

inherit module machine_kernel_pr

PACKAGE_ARCH = "${MACHINE_ARCH}"

BRANCH = "5.6.1_30362.20181109_COEX20180928-6a6a"

SRC_URI = " \
	git://github.com/cilynx/rtl88x2bu.git;branch=${BRANCH} \
	file://0001-gcc49.patch \
	file://0002-adjust-policy-kernelversion.patch \
	file://0003-fix-multiple-definitions.patch \
	file://rtl88x2bu-kernel-5.8.patch \
	"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR} KSRC=${STAGING_KERNEL_DIR}"

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
	install -m 0644 ${S}/88x2bu.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}

do_package_qa() {
}
