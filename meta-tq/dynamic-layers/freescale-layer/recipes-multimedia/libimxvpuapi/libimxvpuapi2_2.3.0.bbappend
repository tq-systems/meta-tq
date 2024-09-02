FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRCREV = "8639837a246f8d85fba8a707c130239aeabc0a19"
SRC_URI += " \
    file://0001-imx6-coda-return-IMX_VPU_API_ENC_RETURN_CODE_INVALID.patch \
    file://0002-imx8m-hantro-implement-imx_vpu_api_enc_get_skipped_f.patch \
    file://0003-imx8m-hantro-dummy_encoder-implement-imx_vpu_api_enc.patch \
"
