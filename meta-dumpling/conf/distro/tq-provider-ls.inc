PREFERRED_PROVIDER_virtual/kernel:tqmls1012al ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls1028a ?= "linux-imx-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmls10xxa ?= "linux-ls-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmlx2160a ?= "linux-lsdk-tq"
PREFERRED_PROVIDER_virtual/kernel:tqmt10xx ?= "linux-tqmt"

IMAGE_CLASSES += "\
    image_type_bootonly \
    image_type_pblvariant \
"

BOOTONLY_IMAGE_FSTYPES = "\
    wic.bootonly \
    wic.bootonly.pblvariant \
"

EXTRA_IMAGE_FSTYPES = ""
EXTRA_IMAGE_FSTYPES:tqmls10xxa = "wic.bootonly"
EXTRA_IMAGE_FSTYPES:tqmlx2160a = "${BOOTONLY_IMAGE_FSTYPES}"

IMAGE_FSTYPES += "${EXTRA_IMAGE_FSTYPES}"
