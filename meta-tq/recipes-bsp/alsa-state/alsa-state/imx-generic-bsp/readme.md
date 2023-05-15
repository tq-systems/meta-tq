# Common ALSA mixer setting `asound.state`

This directory is used for all i.MX based boards, which do share the same
audio codec and the same wiring. Due to that the controls can share the same
default settings on all boards.

Due to historical and backward-compatibility reasons several audio card
models are configured in this directory:

| DT model name             | card id           | description                                             |
| ------------------------- | ----------------- | ------------------------------------------------------- |
| `tqm-tlv320aic32`         | `tqmtlv320aic32`  | i.MX8* based boards on NXP kernel                       |
| `imx-audio-tlv320aic32x4` | `imxaudiotlv320a` | i.MX6/7 based boards on NXP and mainline kernels        |
| `tq-tlv320aic32x`         | `tqtlv320aic32x`  | all (new) i.MX based boards on NXP and mainline kernels |

'card id' is the model name containing only alphanumeric characters, which is
used inside ALSA for referring to specific sound cards.

All three models are set in the common `asound.state`. Newer boards shall use
the last entry `tq-tlv320aic32x`, which takes the maximum length of card names into account.
Any change to a card in `asound.state` must be applied to the others as well
as they essentially configure the same hardware.
