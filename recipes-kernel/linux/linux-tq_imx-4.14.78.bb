require linux-tq-common.inc

SRC_URI = "\
  ${TQ_GIT_BASEURL}/linux-tqmaxx.git;protocol=${TQ_GIT_PROTOCOL};branch=${SRCBRANCH} \
  file://defconfig \
  file://0001-ASoC-tlv320aic32x4-Kernel-OOPS-while-entering-DAPM-s.patch \
  file://0002-ASoC-tlv320aic32x4-SND_SOC_DAPM_MICBIAS-is-deprecate.patch \
  file://0003-tlv320aic32x4-delay-i2c-access-by-1-ms-after-hardwar.patch \
  file://0004-ASoC-tlv320aic32x4-Break-out-clock-setting-into-sepa.patch \
  file://0005-ASoC-tlv320aic32x4-Properly-Set-Processing-Blocks.patch \
  file://0006-ASoC-tlv320aic32x4-Model-PLL-in-CCF.patch \
  file://0007-ASoC-tlv320aic32x4-Model-CODEC_CLKIN-in-CCF.patch \
  file://0008-ASoC-tlv320aic32x4-Model-DAC-ADC-dividers-in-CCF.patch \
  file://0009-ASoC-tlv320aic32x4-Model-BDIV-divider-in-CCF.patch \
  file://0010-ASoC-tlv320aic32x4-Control-clock-gating-with-CCF.patch \
  file://0011-ASoC-tlv320aic32x4-Move-aosr-and-dosr-setting-to-sep.patch \
  file://0012-ASoC-tlv320aic32x4-Dynamically-Determine-Clocking.patch \
  file://0013-ASoC-tlv320aic32x4-Restructure-set_dai_sysclk.patch \
  file://0014-ASoC-tlv320aic32x4-Remove-mclk-references.patch \
  file://0015-ASoC-tlv320aic32x4-Allow-192000-Sample-Rate.patch \
  file://0016-ASoC-tlv320aic32x4-Add-Switch-for-Setting-Common-Mod.patch \
  file://0017-ASoC-tlv320aic32x4-Add-Playback-PowerTune-Controls.patch \
  file://0018-ASoC-tlv320aic32x4-Remove-set-but-not-used-variable-.patch \
  file://0019-asoc-imx-tlv320aic32x4-add-widgets-and-routing.patch \
"

SRCBRANCH = "TQMaxx2-v4.14-rel_imx_4.14.78_1.0.0_ga"
SRCREV = "b6a3c5f948ba7f321bc69d73f811053f18e472ca"

COMPATIBLE_MACHINE = "tqma7x"
COMPATIBLE_MACHINE_append = "|tqma6ulx"
COMPATIBLE_MACHINE_append = "|tqma6ullx"

S = "${WORKDIR}/git"
