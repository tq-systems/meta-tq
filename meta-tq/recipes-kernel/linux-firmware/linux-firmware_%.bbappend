FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " file://HY103880XB_SX-USBAC_210323.bin"

do_install:append() {
  install -d ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/
  # SDIO firmware is also used for USB device, even on hw1.1
  install -m 0644 ${B}/ath10k/QCA9377/hw1.0/firmware-sdio-5.bin ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/firmware-usb-5.bin
  # Install board configuration
  install -m 0644 ${WORKDIR}/HY103880XB_SX-USBAC_210323.bin ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/board-usb.bin
  # TODO: Install calibration file
}
