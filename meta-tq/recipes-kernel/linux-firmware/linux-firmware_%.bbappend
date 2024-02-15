FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://HY103880XB_SX-USBAC_210323.bin"

do_install:append() {
  # Remove backward-compatibility link from ath10k directory to qcom/sdm845.
  # This will create a pseudo dependency of linux-firmware-ath10k to linux-firmware-qcom-sdm845-modem.
  # Do not modify WHENCE file to not affect `WHENCE_CHKSUM`
  if [ "${PV}" -ge "20231030" ]; then
    rm ${D}${nonarch_base_libdir}/firmware/ath10k/WCN3990/hw1.0/wlanmdsp.mbn
  fi

  install -d ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/
  # SDIO firmware is also used for USB device, even on hw1.1
  install -m 0644 ${B}/ath10k/QCA9377/hw1.0/firmware-sdio-5.bin ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/firmware-usb-5.bin
  # Install board configuration
  install -m 0644 ${WORKDIR}/HY103880XB_SX-USBAC_210323.bin ${D}${nonarch_base_libdir}/firmware/ath10k/QCA9377/hw1.1/board-usb.bin
  # TODO: Install calibration file
}
